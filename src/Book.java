import java.io.Serializable;

/**
 * @see Aman Nihaal Nuckchady 40249877   COMP249 PP 2224   Assignment3  27th March 2023
 *
 */

/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 */

//* Book class that implements Serializable and
//* has six instance variables: title, authors, price, isbn, genre, and year, of which price
//* is a double, year is an int, the remaining four instance variables are all of type String;
//* initialize the instance variable in a Book constructor taking six parameters corresponding to
//* the six instance variables.


public class Book implements Serializable {
	private String title;
	private String author;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	
	
/**
 * Parameterised constructor for Book class
 * 
 * @param title
 * @param author
 * @param price
 * @param isbn
 * @param genre
 * @param year
 */
	public Book(String title, String author, double price, String isbn, String genre, int year) {
		this.title = title;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.genre = genre;
		this.year = year;
	}
/**
 * 
 * @param String title
 */
	public void setTitle(String title) {
		this.title = title;
	}
/**
 * 
 * @param Stringauthor
 */
	public void setAuthor(String author) {
		this.author = author;
	}
/**
 * 
 * @param double price
 */
	public void setPrice(double price) {
		this.price = price;
	}
/**
 * 
 * @param String isbn
 */
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
/**
 * 
 * @param String genre
 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
/**
 * 
 * @param int year
 */
	public void setYear(int year) {
		this.year = year;
	}
/**
 * 
 * @return String title
 */
	public String getTitle() {
		return this.title;
	}
/**
 * 
 * @return String author
 */
	public String getAuthor() {
		return this.author;
	}
/**
 * 
 * @return double price
 */
	public double getPrice() {
		return this.price;
	}

/**
 * 
 * @return String isbn
 */
	public String getISBN() {
		return this.isbn;
	}

	/**
	 * 
	 * @return String genre
	 */
	public String getGenre() {
		return this.genre;
	}

	/**
	 * 
	 * @return int year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * toString() method to print Book properties
	 * 
	 */
	public String toString() {
		return "This Book has title : " + title + "\nAuthor is : " + author + "\nPrice of : $" + price + "\nISBN is: "
				+ isbn + "\nPublished year is: " + year;
	}

	/**
	 * equals method to compare two Book objects
	 * 
	 * @param book1
	 * @return boolean
	 */
	public boolean equals(Book book1) {
		return (title.equals(book1.getTitle()) && author.equals(book1.getAuthor()) && this.price == book1.getPrice()
				&& this.isbn.equals(book1.getISBN()) && this.genre.equals(book1.getISBN())
				&& this.year == book1.getYear());
	}

}
