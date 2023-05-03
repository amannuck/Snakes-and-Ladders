import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import SyntaxExceptions.MissingFieldException;
import SyntaxExceptions.TooFewFieldsException;
import SyntaxExceptions.TooManyFieldsException;
import SyntaxExceptions.UnknownGenreException;

/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 * Part I
 */

public class PartI {

	/**
	 * Static method to append all syntax errors to respective error file
	 * 
	 * @param pw
	 * @param record
	 * @param msg
	 * @param filename
	 */
	public static void appendSyntaxError(PrintWriter pw, String record, String msg, String filename) {
		pw.println("syntax error in file: " + filename);
		pw.println("====================");
		pw.println(msg);
		pw.println("Record: " + record);
		pw.println();
	}

	
	/**
	 * Static method to add syntactically valid record of regular text file
	 * 
	 * @param content
	 * @param pw
	 * @param filename
	 */
	public static void appendGenreFile(String content, PrintWriter pw, String filename) {
			pw.println(content);
	}

	/**
	 * Static method to verify the syntax of each record of regular text file
	 * 
	 * @param content
	 * @param filename
	 * @param pw1
	 * @param pw2
	 * @param pw3
	 * @param pw4
	 * @param pw5
	 * @param pw6
	 * @param pw7
	 * @param pw8
	 * @param pw_error
	 */
	public static void checkSyntax(String content, String filename, PrintWriter pw1, PrintWriter pw2, PrintWriter pw3,
			PrintWriter pw4, PrintWriter pw5, PrintWriter pw6, PrintWriter pw7, PrintWriter pw8, PrintWriter pw_error) {
		String[] fields = null;
		int commas = 0; //number of commas

		// Splitting book records into data fields
		if (content.charAt(0) == '"') {
			String[] temp = content.split("\"");
			for (int i = 0; i < temp[2].length(); i++) {
				//counting number of commas
				if (temp[2].charAt(i) == ',') {
					commas++;
				}
			}
			fields = temp[2].split(",");

			fields[0] = temp[1];

		} else {
			for (int i = 0; i < content.length(); i++) {
				if (content.charAt(i) == ',')
					commas++;
			}
			fields = content.split(",");
		}

		// Syntax Error Verification
		try {
			// verifying for number of fields by counting number of commas(=5)
			if (commas > 5) {
				throw new TooManyFieldsException();
			}
			if (commas < 5) {
				throw new TooFewFieldsException();
			}

			//verifying for missing fields from each record
			for (int i = 0; i < 5; i++) {
				if (fields[i].equals("")) {
					switch (i) {
					case 0:
						throw new MissingFieldException("title");
					case 1:
						throw new MissingFieldException("author");
					case 2:
						throw new MissingFieldException("price");
					case 3:
						throw new MissingFieldException("isbn");
					case 4:
						throw new MissingFieldException("genre");
					}

				}
			}
			if (fields.length == 5) {
				throw new MissingFieldException("year");
			}

			// if genre is valid then append to respective genre-based file
			// if genre is invalid, then append to syntax error file
			switch (fields[4]) {

			case "CCB":
				appendGenreFile(content, pw1, "Cartoons_Comics");
				break;
			case "HCB":
				appendGenreFile(content, pw2, "Hobbies_Collectibles");
				break;
			case "MTV":
				appendGenreFile(content, pw3, "Movies_TV_Books");
				break;
			case "MRB":
				appendGenreFile(content, pw4, "Music_Radio_Books");
				break;
			case "NEB":
				appendGenreFile(content, pw5, "Nostalgia_Eclectic_Books");
				break;
			case "OTR":
				appendGenreFile(content, pw6, "Old_Time_Radio_Books");
				break;
			case "SSM":
				appendGenreFile(content, pw7, "Sports_Sports_Memorabilia");
				break;
			case "TPA":
				appendGenreFile(content, pw8, "Trains_Planes_Automobiles");
				break;
			default:
				throw new UnknownGenreException();
			}

			//Exceptions caught, append error with appropriate error message to syntax_error_file.txt
		} catch (TooManyFieldsException e) {
			appendSyntaxError(pw_error, content, e.getMessage(), filename);
		} catch (TooFewFieldsException e) {
			appendSyntaxError(pw_error, content, e.getMessage(), filename);

		} catch (MissingFieldException e) {
			appendSyntaxError(pw_error, content, e.getMessage(), filename);
		} catch (UnknownGenreException e) {
			appendSyntaxError(pw_error, content, e.getMessage(), filename);
		}

	}

	/**
	 * Main static method which validates each regular text file names
	 * from part1_input_file_names.txt, open each valid regular text files and
	 * creates and open each CSV-formatted file. The method reads each record 
	 * from each regular text files and validates the record syntactically before 
	 * appending the valid records to appropriate genre file and invalid ones to error file
	 */
	public static void do_part1() {

		String line = "";
		Scanner scan = null;
		Scanner file_exist = null;
		String file_name;
		PrintWriter pw1 = null;
		PrintWriter pw2 = null;
		PrintWriter pw3 = null;
		PrintWriter pw4 = null;
		PrintWriter pw5 = null;
		PrintWriter pw6 = null;
		PrintWriter pw7 = null;
		PrintWriter pw8 = null;
		PrintWriter pw_error; //PrintWriter object for error file
		
		try {

			scan = new Scanner(new FileInputStream(
					"C:\\Users\\amann\\eclipse-workspace\\Assignment_3\\part1_input_file_names.txt"));

			pw1 = new PrintWriter(new FileOutputStream("Cartoons_Comics.csv"));
			pw2 = new PrintWriter(new FileOutputStream("Hobbies_Collectibles.csv"));
			pw3 = new PrintWriter(new FileOutputStream("Movies_TV_Books.csv"));
			pw4 = new PrintWriter(new FileOutputStream("Music_Radio_Books.csv"));
			pw5 = new PrintWriter(new FileOutputStream("Nostalgia_Eclectic_Books.csv"));
			pw6 = new PrintWriter(new FileOutputStream("Old_Time_Radio_Books.csv"));
			pw7 = new PrintWriter(new FileOutputStream("Sports_Sports_Memorabilia.csv"));
			pw8 = new PrintWriter(new FileOutputStream("Trains_Planes_Automobiles.csv"));
			pw_error = new PrintWriter(new FileOutputStream("syntax_error_file.txt"));

			scan.nextLine();
			while (scan.hasNextLine()) {
				file_name = scan.nextLine();
				try {
					file_exist = new Scanner(
							new FileInputStream("C:\\Users\\amann\\eclipse-workspace\\Assignment_3\\" + file_name));
					while (file_exist.hasNextLine()) {
						line = file_exist.nextLine();
						checkSyntax(line, file_name, pw1, pw2, pw3, pw4, pw5, pw6, pw7, pw8, pw_error);
					}
					file_exist.close();
				} catch (FileNotFoundException e) {
					//If a file does not exist, you will simply display an error
      				//message and move on to the next input file, if any.
					System.out.println("Error: file does not exist, moving on...");
				}
			}
			pw1.close();
			pw2.close();
			pw3.close();
			pw4.close();
			pw5.close();
			pw6.close();
			pw7.close();
			pw8.close();
			pw_error.close();
			scan.close();
			
		
		} catch (FileNotFoundException e) {
			System.out.println("File not found in part 1");
			System.out.println("Ending Program.");
			System.exit(0);
		}
		

	}

}
