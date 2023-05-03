// -------------------------------------------------------
// Assignment 0
// Written by: Aman Nihaal Nuckchady 40249877
// COMP 249 Section 2224 PP – Winter 2023
// Question: PART II
// Date Created: 10 January 2023
// -------------------------------------------------------

import java.util.Scanner;
public class PlayLadderAndSnake {

	public static void main(String[] args) {
		Scanner keyIn = new Scanner(System.in);
		
		//Prompt user to enter number of players
		System.out.print("Enter the number of players needed to play the game with:");
		LadderAndSnake game = new LadderAndSnake(keyIn.nextInt());
		
		//initiate game
		game.play();
		
		keyIn.close(); //close Scanner object

	}

}
