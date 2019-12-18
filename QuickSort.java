package com.ebay.crm.kycriskrtr;

public class QuickSort {

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		int[] arr = {3, 5, 1, 6, 98, 4, 8, 9, 2};
		qs.sort(arr, 0, arr.length-1);
		
		for (int i: arr) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		
		int arrNew[] = {3, 5, 1, 6, 98, 4, 8, 9, 2};
		int sol = qs.quickSelect(arrNew, 0, arr.length-1, 0);
		System.out.println("Solution: " + sol);
		
	}

	private int quickSelect(int[] arrNew, int i, int j, int k) {
		int pivot = find(arrNew, i, j);
		if (k>=0) {
			if (k == pivot) {
				return arrNew[pivot];
			} else if (k >= pivot) {
				return quickSelect(arrNew, pivot+1, j, k-pivot+1);
			} else {
				return quickSelect(arrNew, i, pivot-1, k);
			}
		}
		return -1;
	}

	public void sort(int[] arr, int low, int high) {
		if (high > low) {
			int pivot = find(arr, low, high);
			sort(arr, low, pivot-1);
			sort(arr, pivot+1, high);
		}
	}
	
	public int find(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low-1);
		for (int j= low; j<high; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i+1, high);
		return (i+1);
	}
	
	public void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
