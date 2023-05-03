package SemanticExceptions;
/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 */


/**
 * BadIsbn10Exception class for invalid isbn-10 fields
 */
public class BadIsbn10Exception extends Exception {
	public BadIsbn10Exception() {
		super("Error: invalid ISBN-10");
	}
	

}
