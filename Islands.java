package com.ebay.crm.kycriskrtr;

public class Islands {

	public static void main(String[] args) {
		Islands islands = new Islands();
		//int[][] input = {{1,1,0,0,0}, {0,1,0,0,1}, {1,0,1,1,1}, {0,0,0,0,0}};
		int[][] input = { { 1, 1, 0, 0, 0 }, 
                { 0, 1, 0, 0, 1 }, 
                { 1, 0, 0, 1, 1 }, 
                { 0, 0, 0, 0, 0 }, 
                { 1, 0, 1, 0, 1 } };
		int sol = islands.islandCount(input);
		System.out.println("Solution: " + sol);
	}
	
	private int islandCount(int[][] input) {
		int rows = input.length;
		int cols = input[0].length;
		int visited[][] = new int[rows][cols];
		int count = 0;
		
		for (int i=0; i<rows; i++) {
			for (int j=0; j<rows; j++) {
				if (visited[i][j] == 0 && input[i][j] == 1) {
					count++;
					System.out.println("Not yet visited and one");
					DFS(input, visited, i, j);
				}
			}
		}
		return count;
	}

	private void DFS(int[][] input, int[][] visited, int i, int j) {
		visited[i][j] = 1;
		
		// adjacent
		// top
		if (isValid(input, visited, i-1, j)) {
			DFS(input, visited, i-1, j);
		}
		// bottom 
		if (isValid(input, visited, i, j+1)) {
			DFS(input, visited, i, j+1);
		}
		// left
		if (isValid(input, visited, i, j-1)) {
			DFS(input, visited, i, j-1);
		}
		// right
		if (isValid(input, visited, i+1, j)) {
			DFS(input, visited, i+1, j);
		}
		
		
		//diagonal
		if (isValid(input, visited, i-1, j-1)) {
			DFS(input, visited, i-1, j-1);
		}
		if (isValid(input, visited, i+1, j+1)) {
			DFS(input, visited, i+1, j+1);
		}
		if (isValid(input, visited, i-1, j+1)) {
			DFS(input, visited, i-1, j+1);
		}
		if (isValid(input, visited, i+1, j-1)) {
			DFS(input, visited, i+1, j-1);
		}
		
	}

	private boolean isValid(int[][] input, int[][] visited, int i, int j) {
		int rows = input.length; 
		int cols = input[0].length;
		if (i<0 || i>=rows || j<0 || j>=cols) {
			System.out.println("Out of range [" + i + ", "+ j+"]");
			return false;
		}
		
		if (visited[i][j] == 1 || input[i][j] == 0) {
			System.out.println("Visited already / Input is 0");
			return false;
		}
		
		
		return true;
	}
	
	
}
