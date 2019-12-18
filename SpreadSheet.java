package com.ebay.crm.kycriskrtr;

public class SpreadSheet {
	int rows;
	int cols;
	String[][] content = null;
	
	public SpreadSheet(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.content = new String[rows][cols];
		
		for (int i=0; i<this.rows; i++) 
			for (int j=0; j<this.cols; j++) 
				content[i][j] = "";
	}
	
	public static void main(String[] args) {
		System.out.println("Starting");
		SpreadSheet ss = new SpreadSheet(4,3);
		ss.updateContent(0, 0, "bob");
		ss.updateContent(0, 1, "10");
		ss.updateContent(0, 2, "foo");
		ss.updateContent(1, 0, "alice");
		ss.updateContent(1, 1, "5");
		//ss.updateContent(row, col, content);
		//ss.updateContent(row, col, content);
		//ss.updateContent(row, col, content);
		ss.printSpreadSheet();
		ss.prettyPrint();
		ss.sumFormula(0,1, 1,1);
	}

	private void sumFormula(int row1, int col1, int row2, int col2) {
		int sum = 0;
		for (int i=row1; i<row2; i++) {
			for (int j=col1; j<col2; j++) {
				sum += Integer.parseInt(content[i][j]);
			}
		}
		System.out.println("Sum: " + sum);
	}

	private void prettyPrint() {
		int[] colummnLength = new int[cols];
		for (int i=0; i<this.cols; i++) {
			int max = 0;
			for (int j=0; j<this.rows; j++) {
				colummnLength[i] = Math.max(content[j][i].length(), max);
				if (max < content[j][i].length()) {
					max = content[j][i].length();
				}
			}
		}
		
		// Pretty Print
		for (int i=0; i<this.rows; i++) {
			for (int j=0; j<this.cols; j++) {
				if (j==cols-1) {
					// Dont print separator for last row
					System.out.print(String.format("%-"+colummnLength[j]+"s", content[i][j]));
				} else {
					System.out.print(String.format("%-"+colummnLength[j]+"s", content[i][j]) + "|");
				}
			}
			System.out.println();
		}
	}

	private void printSpreadSheet() {
		for (int i=0; i<this.rows; i++) {
			for (int j=0; j<this.cols; j++) {
				if (j==cols-1) {
					// Dont print separator for last row
					System.out.print(content[i][j]);
				} else {
					System.out.print(content[i][j] + "|");
				}
			}
			System.out.println();
		}
	}

	// Index values are passed
	private void updateContent(int row, int col, String content) {
		this.content[row][col] = new String(content);
	}
	
}
