package com.ebay.crm.kycriskrtr;

//Java program to print all 
//possible strings of length k 

class AllCombination {

	public static void printAllKLength(char[] set, int k) {
		int n = set.length;
		printAllKLengthRec(set, 0, "", n);
	}

	public static void printAllKLengthRec(char[] set, int k, String prefix, int n) {
		if (k == n) {
			System.out.println(prefix);
			return;
		}
		
		for (int i=n-1; i>=0; i--) {
			String newPrefix = prefix + set[i];
			printAllKLengthRec(set, k+1, newPrefix, n);
		}
	}



	// Driver Code
	public static void main(String[] args) {
		System.out.println("First Test");
		char[] set1 = { 'a', 'b' };
		int k = 3;
		printAllKLength(set1, k);

		System.out.println("\nSecond Test");
		char[] set2 = { 'a', 'b', 'c', 'd' };
		k = 1;
		printAllKLength(set2, k);
		
		System.out.println("\nSecond Test");
		char[] set3 = { 'a', 'b', 'c', 'd' };
		k = 4;
		//printAllKLength(set3, k);
	}
}
