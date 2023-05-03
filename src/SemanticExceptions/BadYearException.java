package SemanticExceptions;
/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 */


/**
 * Exception for invalid year
 *
 */
public class BadYearException extends Exception{
	public BadYearException() {
		super("Error: invalid year");
	}
	

}
