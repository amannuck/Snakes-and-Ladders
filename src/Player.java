// -------------------------------------------------------
// Assignment 0
// Written by: Aman Nihaal Nuckchady 40249877
// COMP 249 Section 2224 PP – Winter 2023
// Question: ADDITIONAL CLASS (PLAYER)
// Date Created: 10 January 2023
// -------------------------------------------------------

//CLASS PLAYER
/**
* The Player class stores player's attributes such as:
* name, dice number obtained, row and column position on board
* and square position.
*
*
* @author Aman Nihaal Nuckchady
*/

public class Player {
	private String name;
	private int dice_num;
	private int row;
	private int col;
	private int position;

	/*
	 * Constructor with no parameters
	 */
	public Player() {
		name = "";
		dice_num = 0;
		row = 0;
		col = 0;
		position = 0;
	}

	// CONSTRUCTOR WITH ONE PARAMETER
	/*
	 * Constructor with one parameter
	 * 
	 * @param name of player
	 */
	public Player(String name) {
		this.name = name;
	}

	
	
	// ACCESSORS AND MUTATORS
	public void setName(String name) {
		this.name = name;
	}

	public void setDiceNum(int dice_num) {
		this.dice_num = dice_num;
	}

	public String getName() {
		return this.name;
	}

	public int getDiceNum() {
		return this.dice_num;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getRow() {
		if (row == 10) {
			// if ever row becomes 10 which exceeds index of array, decrease it to 9
			return this.row - 1;
		}
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPosition() {
		return this.position;
	}

	
	
	
	
	
	
	// METHOD TO INCREMENT POSITION AND ROW & COL, PROVIDED THEY DON'T EXCEED THE
	// ARRAY LENGTH
	public void setRowCol() {
		position += dice_num;
		if (row < 9) {
			col += dice_num - 1;
			if (col > 9) {
				col = col - 10;
				row++;
			}
		} else if (row == 9) {
			/*
			 * If a player is close to 100 (on the 9th row of the board), and the dice value
			 * exceeds the maximum possible moves, the player moves backward with the
			 * excessive amount.
			 */
			col += dice_num - 1;
			if (col > 9) {
				col = 9 - (col - 9);
				position = 100 - (position - 100); // DECREASE POSITION ALONG WITH DECREASING COLUMN VALUE
			}
		}
	}

	public void resetRowCol() {
		// RESETS ALL ROWS AND COLUMNS AFTER ONE PLAYER KICKED OTHER PLAYER OUT
		this.row = 0;
		this.col = 0;
		position = 0;
	}

	public String toString() {
		return name + " got a dice value of " + dice_num;
	}

	public String currentPosition(int position) {
		// GIVES CURRENT POSITION/ SQUARE OF THE PLAYER ON THE BOARD
		return "; now in square " + position;
	}

}
