package com.ebay.crm.kycriskrtr;

public class DeadOrAliveCell {
	static int rows = 0;
	static int cols = 0;
	public static void main(String[] args) {
		int[][] input = {{0,1,0}, {0,0,1}, {1,1,1}, {0,0,0}};
		rows = input.length; 
		cols = input[0].length; 
		System.out.println("InputBoard");
		printBoard(input);

		System.out.println();
		System.out.println("OutputBoard");
		int[][] output = getNextBoardState(input);
		printBoard(output);
	}

	private static void printBoard(int[][] output) {
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				System.out.print(output[i][j]+", ");
			}
			System.out.println();
		}
	}

	/**
	 * I don't remember the exact rules, so i tried to document it here
	 * Live Cell fewer than 2 cells dies (nextState: 9)
	 * Live Cell with 2 or 3 neighbors lives, so values doesn't change (nextState: 8)
	 * Live Cell with more than 3 neighbors dies (nextState: 9)
	 * 
	 * Dead Cell with exactly 3 neighbors live (nextState: 5) 
	 * 
	 * */
	
	private static int[][] getNextBoardState(int[][] input) {
		// Convert to 4,5,8,9 board
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				int nextState = getNextState(input, i, j);
				input[i][j] = nextState;
			}
		}
		
		// convert back from 4,5,8,9 to 0 and 1
		for (int i=0; i<rows; i++) {
			for (int j=0; j<cols; j++) {
				input[i][j] = getFinalState(input[i][j]);
			}
		}
		return input;
	}

	private static int getNextState(int[][] input, int i, int j) {
		int countAlive = getOriginal(input, i-1, j-1) + getOriginal(input, i-1, j) + getOriginal(input, i-1, j+1) + 
				getOriginal(input, i, j-1) + getOriginal(input, i, j+1) + getOriginal(input, i+1, j-1) + 
				getOriginal(input, i+1, j) + getOriginal(input, i+1, j+1);
		int nextState = getOriginal(input, i, j);
		if (getOriginal(input, i, j) == 1) { // Original Live Cell
			if (countAlive < 2) {
				nextState = 9; // Going from Live to Dead
			} else if (countAlive == 2 || countAlive == 3){
				nextState = 8; // Staying the same
			} else if (countAlive > 3) {
				nextState = 9; // Going from Live to Dead
			}
		} else { // Original Dead Cell
			if (countAlive == 3) {
				nextState = 5; // Going from Dead to Live
			}
		}
		
		return nextState;
	}

	private static int getOriginal(int[][] input, int i, int j) {
		if (i<0 || i>rows-1 || j<0 || j>cols-1) {
			// If you are going out of bounds, consider rest of the cells are DEAD
			return 0;
		}
		if ( input[i][j]==0 || input[i][j]==4 || input[i][j]==5) { //Previous State was 0
			return 0;
		}
		return 1;
	}
	
	private static int getFinalState(int value) {
		if (value == 4 || value == 9) {
			return 0;
		} 
		if (value == 5 || value == 8) {
			return 1;
		}
		return value;
	}
}
