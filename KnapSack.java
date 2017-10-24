package com.paypal.risk.riskonboardingmsgd.services;

public class KnapSack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int val[] = {60,100,120};
		int wt[] =  {10,20, 30};
		int n= val.length;
		int W=50;
		
		System.out.println("SOL " + knapSack(W, wt, val, n));
		
	}

	private static int knapSack(int W, int[] wt, int[] val, int n) {
		if(W==0 || n==0) return 0;
		
		if(wt[n-1] > W)
			return knapSack(W, wt, val, n-1);
		else
			return Math.max(
					knapSack(W, wt, val, n-1), 
					val[n-1] + knapSack(W-wt[n-1], wt, val, n-1) 
					);
	}

}
