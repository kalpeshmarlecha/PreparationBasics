package com.ebay.crm.kycriskrtr;

public class CountWays {

	public static void main (String[] args) {
		int input = 4;
		int output = findWays(input);
		System.out.println("Output: " + output);
	}
	
	public static int findWays(int n){
	    
	    if (n <= 0) {
	        return 0;
	    }
	    if (n==1 || n==2) {
	        return n;
	    } 
	    return findWays(n-1) + findWays(n-2);
	    
	}
	
}
