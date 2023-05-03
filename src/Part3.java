import java.io.ObjectInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 * Part III
 */

public class Part3 {
	
/**
 * Static method for the submenu of the program displaying all the binary files
 * @param filename
 * @param books
 */
	public static void submenu(String[] filename, Book[][] books) {
		System.out.println("----------------------------");
		System.out.println("        File Sub-Menu       ");
		System.out.println("----------------------------");
		for (int i = 0; i < filename.length; i++) {
			System.out.printf("%d  %-35s (%-3d records)%n", i + 1, filename[i], books[i].length);
		}
		System.out.println("9  Exit");
		System.out.println("----------------------------");
		System.out.print("Enter Your Choice: ");

	}

	/**
	 * Static method for displaying the menu of the program
	 * @param filename
	 */
	public static void menu(String filename) {
		System.out.println("----------------------------");
		System.out.println("        Main Menu         ");
		System.out.println("----------------------------");
		System.out.println(" v  View the selected file: " + filename);
		System.out.println(" s  Select a file to view");
		System.out.println(" x  Exit");
		System.out.println("----------------------------");
		System.out.println();
		System.out.print("Enter Your Choice: ");
	}

	
	/**
	 * 
	 * Static method to convert the binary files into a 2d-array 
	 * containing each arrays of Book Objects respective to all the .csv.ser files
	 * and returns the array
	 * 
	 * @param filename
	 * @return Book[][]
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Book[][] generateBook(String[] filename)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		Book[][] newbooks = new Book[8][];
		for (int i = 0; i < 8; i++) {
			FileInputStream f = new FileInputStream(filename[i]);
			ObjectInputStream outstream = new ObjectInputStream(f);
			newbooks[i] = (Book[]) outstream.readObject();
			outstream.close();
			f.close();
		}
		return newbooks;

	}

	/**
	 * The main static method of part 3 whereby the binary files are converted into a 2d-array
	 * It allows for user interaction and access to each files and book records
	 */
	public static void do_part3() {
		String choice = "";
		//array storing all .csv.ser file names
		String[] filename = { "Cartoons_Comics.csv.ser", "Hobbies_Collectibles.csv.ser", "Movies_TV_Books.csv.ser",
				"Music_Radio_Books.csv.ser", "Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio_Books.csv.ser",
				"Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser" };

		Book[][] allBooks = null;
		try {
			allBooks = generateBook(filename);

		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			System.out.println("Ending Program.");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("File could not be opened");
			System.out.println("Ending Program.");
			System.exit(0);

		} catch (ClassNotFoundException e) {
			System.out.println("File not Found");
			System.out.println("Ending Program.");
			System.exit(0);
		}

		int accessed = 0;
		Scanner keyb = new Scanner(System.in);

		while (!choice.equals("x") && !choice.equals("X") && accessed != 9) {
			menu(filename[accessed]);
			choice = keyb.next();
			if (choice.equals("s")) {
				//Selecting a file
				submenu(filename, allBooks);
				accessed = keyb.nextInt();
				System.out.println();
				if (accessed != 9)
					accessed--;
			}
			if (choice.equals("v")) {
				//Viewing a file
				int n = 1;
				int index = 0;
				while (n != 0) {
					//if n == 0 then stop the viewing session
					System.out.println();
					System.out.printf("viewing: %s  (%,d records)%n", filename[accessed], allBooks[accessed].length);
					System.out.println();
					System.out.print("Enter your choice to view records: ");
					n = keyb.nextInt();
					System.out.println();

					if (n == 1 || n == -1) {
						//if n == 1, then display the current record on the pointer
						System.out.println("viewing records: ");
						System.out.println();
						System.out.println("-------- Book No. " + index + " ----------");
						System.out.println();
						System.out.println(allBooks[accessed][index]);
					} else if (n > 0) {
						int count = 0;
						System.out.println("viewing records: ");
						while (index < n) {
							if (index < allBooks[accessed].length) {
								System.out.println("-------- Book No. " + index + " ----------");
								System.out.println();
								System.out.println(allBooks[accessed][index]);
								System.out.println();

							} else {
								//if count exceeds the length of the allBooks array then break the while loop
								System.out.println("EOF has been reached");
								break;
							}
							index++;
						}
					if (index >= allBooks[accessed].length) index--;

					} else if (n < 0) {
						int count = 0;
						System.out.println("viewing records: ");
						while (count < (-1 * n)) {
							if (index >= 0) {
								System.out.println("-------- Book No. " + index + " ----------");
								System.out.println();
								System.out.println(allBooks[accessed][index]);
								System.out.println();
								index--;

							} else {
								//if count < 0, break the while loop
								index =0;
								System.out.println("EOF has been reached");
								break;
							}
							count++;

						}
					}

				}

			}
		}
		keyb.close();
		
		//Ending message
		System.out.println("Thank you for using the program.");
		System.exit(0);
	}

}
