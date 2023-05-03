package SemanticExceptions;
/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 */


/**
 * 
 * Exception for invalid price
 *
 */
public class BadPriceException extends Exception {
	public BadPriceException() {
		super("Error: invalid price");
	}
}
