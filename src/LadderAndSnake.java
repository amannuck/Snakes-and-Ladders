// -------------------------------------------------------
// Assignment 0
// Written by: Aman Nihaal Nuckchady 40249877
// COMP 249 Section 2224 PP – Winter 2023
// Question: PART I
// Date Created: 10 January 2023
// -------------------------------------------------------

/**
 * The LadderAndSnake class A LadderAndSnake object has a board of 
 * 10x10 and a number of players attached to it, which is initialized 
 * at the creation time of the object. For the scope of this assignment,
 * the number of players will be limited to exactly 2.
 * 
 * @author Aman Nihaal Nuckchady
 *
 */

import java.util.Scanner;

public class LadderAndSnake {

	// 4 ATTRIBUTES
	private boolean[][] board = new boolean[10][10];
	private int player_num;

	// 1-D array to store every ladder's position with its corresponding destination
	private int[] ladders = new int[100];
	// 1-D array to store every snake's position with its corresponding destination
	private int[] snakes = new int[100];

	// CONSTRUCTOR
	public LadderAndSnake() {
		player_num = 2;

	}

	// PARAMETERISED CONTRUCTOR
	public LadderAndSnake(int player_num) {
		if (player_num > 2) {
			System.out.print("Initialization was attempted for " + player_num
					+ " member of players; however, this is only expected for an extended version the game. Value will be set to 2 \n");
			this.player_num = 2;
		} else if (player_num < 2) {
			System.out.print("Error: Cannot execute the game with less than 2 players! Will exit.");
			System.exit(0);
		} else {
			this.player_num = 2;
		}
	}

	
	
	
	
	
	
	// METHOD TO FLIP DICE
	
	/**
	* Method to create a random integer between 1 and 6 (inclusive) and return that integer
	* @return an integer value
	*/
	public int flipDice() {
		return (int) (1 + Math.random() * 6);
	}

	
	
	// ACCESSORS FOR SNAKES AND LADDERS ATTRIBUTES
	public void setSnakes() {
		snakes[16] = 6;
		snakes[48] = 30;
		snakes[64] = 60;
		snakes[79] = 19;
		snakes[93] = 68;
		snakes[95] = 24;
		snakes[97] = 76;
		snakes[98] = 78;
	}

	public void setLadders() {
		ladders[1] = 38;
		ladders[4] = 14;
		ladders[9] = 31;
		ladders[28] = 84;
		ladders[21] = 42;
		ladders[36] = 44;
		ladders[51] = 67;
		ladders[80] = 100;
		ladders[71] = 91;
	}

//METHOD TO CHECK WHETHER PLAYER MET A LADDER
	/**
	*Takes an array of type Player and an integer num to verify if players encounter a ladder or not
	*
	* @param player array of type Player
	* @param num of type integer
	* @return a String which will result in a final statement being printed
	*/

	public String checkLadders(Player[] player, int num) {
		int oldposition, row, col;
		for (int count = 0; count <= 80; count++) {
			// GOES THROUGH EVERY ELEMENT OF ARRAY LADDERS
			/*
			 * COMPARES LADDER'S POSITION WITH PLAYER'S POSITION AND CHECKS ALSO IF ARRAY
			 * ELEMENT IS NOT EMPTY
			 */

			if (count == player[num].getPosition() && ladders[count] != 0) {
				// IF PLAYER MET WITH A LADDER

				oldposition = player[num].getPosition();
				// STORES PLAYER'S OLD POSITION

				row = (int) (ladders[count]) / 10;
				col = ((ladders[count] - 1) % 10);
				// CONVERTS POSITION(1D FORMAT) TO ROWS AND COLUMNS(2D FORMAT)

				board[player[num].getRow()][player[num].getCol()] = false; // SETS PLAYER'S ORIGINAL POSITION TO FALSE
																			// SINCE PLAYER ABOUT TO MOVE TO ANOTHER
																			// SQUARE

				player[num].setRow(row); // SETS CORRESPONDING ROW VARIABLE TO PLAYER'S ROW/HORIZONTAL POSITION ON BOARD
				player[num].setCol(col);// SETS CORRESPONDING COLUMN VARIABLE TO PLAYER'S COLUMN/VERTICAL POSITION ON
										// BOARD

				player[num].setPosition(ladders[count]);// REPLACES ORIGINAL POSITION OF PLAYER WITH FINAL POSITION

				System.out.println("************ " + player[num].getName() + " encountered a ladder! ************");
				return " ,gone to square " + oldposition + ", up to square " + player[num].getPosition();

			}
		}

		return player[num].currentPosition(player[num].getPosition()); // ELSE IF PLAYER DOES NOT MEET ANY LADDERS,
																		// RETURNS PLAYER'S CURRENT POSITION

	}

	// METHOD TO CHECK WHETHER PLAYER MET A SNAKE
	/**
	*Takes an array of type Player and an integer num to verify if players encounter a snake or not
	*
	* @param player array of type Player
	* @param num of type integer
	* @return a String which will result in a final statement being printed
	*/
	public String checkSnakes(Player[] player, int num) {
		int oldposition, row, col;
		for (int count = 0; count <= 98; count++) {
			// GOES THROUGH EVERY ELEMENT OF ARRAY SNAKES
			/*
			 * COMPARES SNAKE POSITION WITH PLAYER'S POSITION AND CHECKS ALSO IF ARRAY
			 * ELEMENT IS NOT EMPTY
			 */
			if (count == player[num].getPosition() && snakes[count] != 0) {
				// IF PLAYER MET WITH A SNAKE

				oldposition = player[num].getPosition();

				row = (int) (snakes[count]) / 10;
				col = ((snakes[count] - 1) % 10);

				board[player[num].getRow()][player[num].getCol()] = false;

				player[num].setRow(row); // SETS CORRESPONDING ROW VARIABLE TO PLAYER'S ROW/HORIZONTAL POSITION ON BOARD
				player[num].setCol(col);// SETS CORRESPONDING COLUMN VARIABLE TO PLAYER'S COLUMN/VERTICAL POSITION ON
										// BOARD

				player[num].setPosition(snakes[count]);

				System.out.println("********** " + player[num].getName() + " encountered a snake! ************");
				return " ,gone to square " + oldposition + ", down to square " + player[num].getPosition();

			}
		}

		return player[num].currentPosition(player[num].getPosition());

	}

//METHOD CHECKING PLAYERS POSITION AND WHETHER A PLAYER MEETS WITH THE OTHER
	/**
	*Takes an array of type Player,an integer num1 and an integer num2 to verify
	*if players encounters ladders or snakes and whether they encounter each other or not
	*
	* @param player array of type Player
	* @param num1 of type integer
	* @param num2 of type integer
	*/
	public void checkPlayer(Player[] player, int num1, int num2) {

		String statement = "";

		statement = checkLadders(player, num1);
		statement = checkSnakes(player, num1);

		// verifying if position is vacant
		if (!board[player[num1].getRow()][player[num1].getCol()]) {

			board[player[num1].getRow()][player[num1].getCol()] = true;

			System.out.println(player[num1] + statement);

		} else {
			// if not vacant, kick player
			player[num2].resetRowCol();
			System.out.println(player[num1] + statement);
			System.out.println("!!!!!!!! " + player[num1].getName() + " kicked out " + player[num2].getName()
					+ " back to square 0. !!!!!!!!");
		}

	}

	// MAIN METHOD TO INITIATE CORE ENGINE SNAKES AND LADDERS GAME
	public void play() {

		Scanner keyIn = new Scanner(System.in);
		int i = 0, j = 1;
		int attempts = 0;
		boolean winner = false;

		// Prompt user to enter names of players
		System.out.print("Enter Player1 and Player2's name respectively: ");
		Player[] player = new Player[player_num]; // array object of Players
		player[i] = new Player();
		player[j] = new Player();
		player[i].setName(keyIn.next());
		player[j].setName(keyIn.next());
		System.out.println();

		// initialise the locations of the snakes and ladders on the board
		setSnakes();
		setLadders();

		System.out.println("Now deciding which player will start playing:");

		// DECISION OF ORDER OF PLAYERS
		do {
			player[i].setDiceNum(flipDice());
			System.out.println(player[i]);
			player[j].setDiceNum(flipDice());
			System.out.println(player[j]);

			++attempts;
			// player 1 starts first

			if (player[i].getDiceNum() < player[j].getDiceNum()) {
				// player 2 starts first
				int temp = i;
				i = j;
				j = temp;
			}
			System.out.println();

			System.out.println("Reached final decision on order of playing: " + player[i] + " then " + player[j]
					+ ". It took " + attempts + " attempts before a\ndecision could be made.");
			System.out.println();

			if (player[i].getDiceNum() == player[j].getDiceNum()) {
				System.out.println("A tie was acheived between " + player[i] + " and " + player[j]
						+ ". Attempting to break the tie.");
			}
		} while (player[i].getDiceNum() == player[j].getDiceNum());

		// GAME STARTED
		do {

			/*
			 * set last position of players to zero (since they are about to move and leave
			 * their initial position)
			 */

			board[player[i].getRow()][player[i].getCol()] = false;
			board[player[j].getRow()][player[j].getCol()] = false;

			// flip dice and assign each dice values to each player
			player[i].setDiceNum(flipDice());
			player[j].setDiceNum(flipDice());

			// assign initial row and columns values for players following dice number
			player[i].setRowCol();
			player[j].setRowCol();

			// CHECK STATUS OF EACH PLAYER(SNAKE OR LADDER ENCOUNTER & PLAYER ENCOUNTER)
			checkPlayer(player, i, j);
			checkPlayer(player, j, i);

			// checking for potential winner
			if (player[i].getPosition() == 100) {
				System.out.println("Congratulations: Winner is " + player[i].getName());
				winner = true;
				break;
			} else if (player[j].getPosition() == 100) {
				System.out.println("Congratulations: Winner is " + player[j].getName());
				winner = true;
				break;
			} else {
				System.out.println("Game not over; flipping again \n");
			}

		} while (winner == false); // loops until winner is declared

		// ending message
		System.out.println("END OF GAME: Thank you for playing!");

		keyIn.close();

	}

}
