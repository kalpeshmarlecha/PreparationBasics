package com.ebay.crm.kycriskrtr;

import java.util.*; 

class SmallestNumber { 
  
    // main function 
    public static void main(String[] args) 
    { 
        int arr[] = { 0, 10, 2, -10, -20 }; 
        int arr_size = arr.length; 
        int missing = findMissing(arr, arr_size); 
        System.out.println("The smallest positive missing number is " + missing); 
    }

	private static int findMissing(int[] arr, int arr_size) {
		int j=0;
		for (int i=0; i<arr_size; i++) {
			if (arr[i] <= 0) {
				swap(arr, i, j);
				j++;
			}
		}
		System.out.println("J is: " + j);
		
		int arr2[] = new int[arr_size-j];
		for (int i=0; i<arr_size-j; i++) {
			arr2[i] = arr[j+i];
		}
		
		for (int k: arr2) {
			System.out.print(k + ", ");
		}
		System.out.println();
		
		int sol = findMissingPositive(arr2, j);
		
		return sol;
	}

	private static int findMissingPositive(int[] arr2, int size) {
		int i=0;
		for (i=0; i<size; i++) {
			int x = Math.abs(arr2[i]);
			if (x-1 < size && arr2[x-1] > 0) {
				arr2[x-1] = -arr2[x-1];
			}
		}
		for (int j=0; j<size; j++) {
			if (arr2[j] > 0) {
				return j+1;
			}
		}
		
		return size+1;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	} 
}
