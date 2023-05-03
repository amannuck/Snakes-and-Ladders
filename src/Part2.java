import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import SemanticExceptions.BadIsbn10Exception;
import SemanticExceptions.BadIsbn13Exception;
import SemanticExceptions.BadPriceException;
import SemanticExceptions.BadYearException;

/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 * Part II
 */

public class Part2 {

	/**
	 * Static method which appends each semantic error passed to semantic_error_file.txt
	 * 
	 * @param pw
	 * @param record
	 * @param msg
	 * @param filename
	 */
	public static void appendSemanticError(PrintWriter pw, String record, String msg, String filename) {
		pw.println("semantic error in file: " + filename);
		pw.println("====================");
		pw.println(msg);
		pw.println("Record: " + record);
		pw.println();

	}

	/**
	 * Static method which validates the ISBN-10 of a book record. 
	 * If invalid, it throws a BadIsbn10Exception caught by the method calling it.
	 * @param isbn
	 * @throws BadIsbn10Exception
	 */
	public static void checkISBN10(String isbn) throws BadIsbn10Exception {
		final int length = 10; //length should always be 10
		int sum = 0;

		if (isbn.charAt(length - 1) == 'X') {
			//verifying if the last character of the isbn is an 'X' for it is valid
			sum += 10;
		} else if (Character.isDigit(isbn.charAt(length - 1))) {
			//if it is not an 'X'
			sum += (Integer.parseInt("" + isbn.charAt(length - 1)));
		} else
			throw new BadIsbn10Exception(); //if it is an invalid character

		for (int i = 0; i < length - 1; i++) {
			if (Character.isDigit(isbn.charAt(i))) {
				//if character at i is a digit
				sum = sum + (Integer.parseInt("" + isbn.charAt(i)) * (length - i));
			} else
				//if it is an invalid character
				throw new BadIsbn10Exception();
		}

		if (sum % 11 != 0)
			//if sum is not a multiple of 11
			throw new BadIsbn10Exception();
	}

	/**
	 * Static method which validates the ISBN-13 of a book record. 
	 * If invalid, it throws a BadIsbn13Exception caught by the method calling it.
	 * @param isbn
	 * @throws BadIsbn13Exception
	 */
	public static void checkISBN13(String isbn) throws BadIsbn13Exception {
		final int length = 13;//length should be 13
		int sum = 0;

		if (isbn.charAt(length - 1) == 'X') {
			sum += 10;
		} else if (Character.isDigit(isbn.charAt(length - 1))) {
			sum = sum + (Integer.parseInt("" + isbn.charAt(length - 1)));
		} else
			throw new BadIsbn13Exception();

		for (int i = 0; i < length - 1; i++) {
			if (Character.isDigit(isbn.charAt(i))) {
				if (i % 2 == 0) {
					sum = sum + (Integer.parseInt("" + isbn.charAt(i)));
				} else {
					sum = sum + (Integer.parseInt("" + isbn.charAt(i)) * 3);
				}
			} else
				throw new BadIsbn13Exception();
		}

		if (sum % 10 != 0)
			//if sum is not a multiple of 10
			throw new BadIsbn13Exception();

	}

	
	/**
	 * Static method to check the book record of a CSV-formatted text file semantically
	 * It returns a Book object(valid)
	 * @param semantic
	 * @param content
	 * @param filename
	 * @return Book
	 */
	public static Book checkSemantics(PrintWriter semantic, String content, String filename) {
		String[] fields = null;

		// Splitting book records into data fields
		try {
			if (content.charAt(0) == '"') {
				String[] temp = content.split("\"");

				fields = temp[2].split(",");

				fields[0] = temp[1];

			} else {

				fields = content.split(",");
			}

			double price = Double.parseDouble(fields[2]);
			int year = Integer.parseInt(fields[5]);
			String isbn = fields[3];

			if (price < 0)
				throw new BadPriceException();

			if (year < 1995 || year > 2010)
				throw new BadYearException();

			if (isbn.length() == 10)
				checkISBN10(isbn);

			if (isbn.length() == 13)
				checkISBN13(isbn);

			Book newbook = new Book(fields[0], fields[1], price, isbn, fields[4], year); //creating new Book object if valid
			return newbook;
		} catch (BadPriceException e) {
			appendSemanticError(semantic, content, e.getMessage(), filename);
			return null;
		} catch (BadYearException e) {
			appendSemanticError(semantic, content, e.getMessage(), filename);
			return null;
		} catch (BadIsbn10Exception e) {
			appendSemanticError(semantic, content, e.getMessage(), filename);
			return null;
		} catch (BadIsbn13Exception e) {
			appendSemanticError(semantic, content, e.getMessage(), filename);
			return null;
		}
	}

	/**
	 * Method to generate the file size of a CSV-text formatted file
	 * 
	 * @param filename
	 * @return count
	 */
	public static int generateFileSize(String filename) {
		Scanner scan = null;
		try {
			scan = new Scanner(new FileInputStream(filename));

			int count = 0;
			while (scan.hasNextLine()) {
				scan.nextLine();
				count++;
			}

			return count;

		} catch (FileNotFoundException e) {
			System.out.println("File not Found in Part 2");
			return 0;
		}
	}

	// MAIN
	/**
	 * Static method to open each CSV-formatted text files and verifying its semantics
	 * It creates an array of Books (Serializable) and creates a binary file which contains the 
	 * valid book record of the genre-based CSV formatted file
	 * 
	 * @param filename
	 * @param semantic
	 */
	public static void checkGenreFiles(String filename, PrintWriter semantic) {
		String line = "";
		int count = 0;
		int count1 = 0;

		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename + ".ser"));
			Scanner scan = new Scanner(new FileInputStream(filename));
			Book[] books = new Book[generateFileSize(filename)];
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				Book newbook = checkSemantics(semantic, line, filename);
				if (newbook != null) {
					books[count] = newbook;
					count++;
				}

			}

			Book[] books1 = new Book[count];
			for (int i = 0; i < count; i++) {
				books1[i] = books[i];
			}

			outputStream.writeObject(books1);
			outputStream.close();
			scan.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found in Part 2");
			System.out.println("Ending Program.");
			System.exit(0);

		} catch (IOException e) {
			System.out.println("File Could Not be Accessed in Part 2");
			System.out.println("Ending Program.");
			System.exit(0);

		}

	}

	/**
	 * Main static method which opens the semantic_error_file.txt and verify each CSV-formatted text files
	 */
	public static void do_part2() {

		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt"));
			checkGenreFiles("Cartoons_Comics.csv", pw);
			checkGenreFiles("Hobbies_Collectibles.csv", pw);
			checkGenreFiles("Movies_TV_Books.csv", pw);
			checkGenreFiles("Music_Radio_Books.csv", pw);
			checkGenreFiles("Nostalgia_Eclectic_Books.csv", pw);
			checkGenreFiles("Old_Time_Radio_Books.csv", pw);
			checkGenreFiles("Sports_Sports_Memorabilia.csv", pw);
			checkGenreFiles("Trains_Planes_Automobiles.csv", pw);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found in Part 2");
			System.out.println("Ending Program.");
			System.exit(0);

		}
		
		

	}

}
