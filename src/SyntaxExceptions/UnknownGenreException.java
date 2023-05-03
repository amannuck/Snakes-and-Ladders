package SyntaxExceptions;

public class UnknownGenreException extends Exception{
	/*
	 * Name: Aman Nihaal Nuckchady
	 * ID: 40249877
	 * Section: COMP 249 PP 2224
	 * Assignment: 3
	 * Date due: 27 March 2023
	 */
	
	
	/**
	 * Exception class for invalid genre
	 */
	public UnknownGenreException() {
		super("Error: invalid genre");
	}
}
