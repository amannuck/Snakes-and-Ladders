package SyntaxExceptions;
/*
 * Name: Aman Nihaal Nuckchady
 * ID: 40249877
 * Section: COMP 249 PP 2224
 * Assignment: 3
 * Date due: 27 March 2023
 */

/**
 * 
 * Exception class for too few fields
 *
 */
public class TooFewFieldsException extends Exception {

	public TooFewFieldsException() {
		super("Error: Too few fields");
	}
}
