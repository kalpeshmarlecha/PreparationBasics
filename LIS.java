package com.paypal.risk.riskonboardingmsgd.services;

public class LIS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int arr[] = {10, 20, 9, 33, 21, 50, 41, 60};
		int n = arr.length;
		int max = 0;
		int lis[] = {1,1,1,1,1,1,1,1};
		
		for(int i=1; i<n; i++){
			for(int j=0; j<n; j++){
				if(arr[i] > arr[j] && lis[i] < lis[j]+1)
					lis[i] = lis[j] + 1;
			}
		}
		
		for(int i=0; i<n; i++)
			if(max < lis[i])
				max = lis[i];
	
		System.out.println("SOL " + lis[n-1]);
		System.out.println("SOL " + max);
	}

}
