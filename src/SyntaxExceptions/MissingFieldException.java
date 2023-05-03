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
 * Exception class for missing fields
 *
 */
public class MissingFieldException extends Exception{

	public MissingFieldException() {
		super("Error: missing field");
	}
	
	/**
	 * Parameterised constructor for MissingFieldException 
	 * @param msg
	 */
	public MissingFieldException(String msg) {
		super("Error: missing " + msg);
	}
}
