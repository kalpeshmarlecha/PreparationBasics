package com.ebay.crm.kycriskrtr;

/**
 * You are given an array of integers. 
 * Return an array of the same size where the element at each index is the product of all the elements in the original array except for the element at that index.
 * For example, an input of [1, 2, 3, 4, 5] should return [120, 60, 40, 30, 24].
 * You cannot use division in this problem.
 * */

public class ProductArr {
	public static void main(String[] args) {
		int input[] = {1, 2, 3, 4, 5};
		
		int sol[] = getProductArr(input);
		
		for (int ele : sol) {
			System.out.print(ele + ", ");
		}
		System.out.println();
	}

	private static int[] getProductArr(int[] input) {
		int productArr[] = new int[input.length];
		productArr[0] = 1;
		int product = 1;
		for (int i=1; i<input.length; i++) {
			productArr[i] = productArr[i-1] * input[i-1];
			product = input[i] * product; 
		}
		System.out.println("Product: " + product);
		
		for (int ele : productArr) {
			System.out.print(ele + ", ");
		}
		System.out.println();
		
		product = 1;
		for (int i=input.length-1; i>=0; i--) {
			productArr[i] = productArr[i] * product;
			product = product * input[i];
		}
		
		return productArr;
	}
}
