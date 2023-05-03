package SemanticExceptions;
/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 */

/**
 * Exception class for invalid isbn 13
 *
 */
public class BadIsbn13Exception extends Exception{
	public BadIsbn13Exception() {
		super("Error: invalid ISBN-13");
	}
	

}
