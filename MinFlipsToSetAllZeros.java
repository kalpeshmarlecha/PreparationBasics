package com.ebay.crm.kycriskrtr;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MinFlipsToSetAllZeros {
	
    public int flip2D(int[][] board){
        //Base case, no board
    	if(board.length==0||board[0].length==0) return 0;
        
        String originalBoardString=twoD2String(board);
        System.out.println("Original Board: " + originalBoardString);
        int[][] allZeros = new int[board.length][board[0].length];
        if(originalBoardString.equals(twoD2String(allZeros))) {
        	System.out.println("AllZeros, You are done here");
        	return 0;
        }
        LinkedList<int[][]> curLs = new LinkedList<>();
        curLs.add(allZeros);
        System.out.println("StartWithEmptyLL: " + twoD2String(curLs.get(0)) + ", Size: " + curLs.size());
        
        int step = 0;
        while(!curLs.isEmpty()){
            int s = curLs.size();
            System.out.println("LL SIZE: " + s);
            step++;
            while(s>0){
                int[][] curBoard = curLs.poll();
                for(int i=0;i<board.length;i++){
                    for(int j=0;j<board[0].length;j++){
                        flip(curBoard,i,j);
                        String curBoardString = twoD2String(curBoard);
                        //System.out.println("("+i+", "+j+") Flipped Board: " + twoD2String(curBoard));
                        if(curBoardString.equals(originalBoardString)) {
                        	System.out.println("Matches Original Board NOW: " + curBoardString);
                        	return step;
                        }
                        curLs.add(copy2D(curBoard));
                        flip(curBoard,i,j);
                        //System.out.println("("+i+", "+j+") Reverse Flipp: " + twoD2String(curBoard));
                    }
                }
                s--;
            }
        }
        return -1;
    }

    //Helper classes
    private String twoD2String(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int[] row:board){
            for(int i:row){
                sb.append(i);
            }
        }
        return sb.toString();
    }
    
    /**
     * Flip the i, j location on the board
     * @param board
     * @param i
     * @param j
     */
    private void flip(int[][] board, int i, int j){
        board[i][j] ^=1; 							// Element is flipped
        if(i>0) board[i-1][j]^=1; 					// Row-1 is flipped
        if(i<board.length-1) board[i+1][j]^=1; 		// Row+1 is flipped
        if(j>0) board[i][j-1]^=1; 					// Col-1 is flipped
        if(j<board[0].length-1) board[i][j+1]^=1;	// Col+1 is flipped
    }
    
    /**
     * Construct the copy of same board and return a new instance of it
     * @param board
     * @return
     */
    private int[][] copy2D(int[][] board){
        int[][] copy = new int[board.length][board[0].length];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                copy[i][j]=board[i][j];
            }
        }
        return copy;
    }

    public static void main(String[] args) {
        int[][] board1 = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] board2 = {{0,0,0},{0,0,0},{0,0,0}};
        int[][] board3 = {{0,1,0},{1,1,1},{0,1,0}};
        int[][] board4 = {{0,1,0},{1,1,0},{0,1,1}};
        MinFlipsToSetAllZeros cur = new MinFlipsToSetAllZeros();
        System.out.println("Board1: " + cur.flip2D(board1));
        System.out.println("Board2: " + cur.flip2D(board2));
        System.out.println("Board3: " + cur.flip2D(board3));
        System.out.println("Board4: " + cur.flip2D(board4));
    }
}