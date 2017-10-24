package com.paypal.risk.riskonboardingmsgd.services;

public class QuickSort {

	int arr[] = { 5, 3, 2, 4, 1, 6, 8 };

	public void printArr() {
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	public void sort(int arr[], int low, int high) {

		if (high > low) {
			int partition = partition(arr, 0, high);
			sort(arr, low, partition - 1);
			sort(arr, partition + 1, high);
		}
	}

	private int partition(int[] arr, int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);

		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, (i + 1), high);
		return (i + 1);
	}

	private void swap(int[] arr2, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private int quickSelect(int[] arr, int k) {
		int low = 0;
		int high = arr.length - 1;

		if (k < 0 || k > arr.length)
			return -1;

		while (high > low) {
			int partition = partition(arr, low, high);

			if (partition > k) {
				high = partition-1;
			} else if (partition < k) {
				low = partition+1;
			}else{
				return k;
			}

		}
		return -1;
	}

	public static void main(String[] args) {
		QuickSort qs = new QuickSort();
		qs.printArr();

		//qs.sort(qs.arr, 0, qs.arr.length - 1);
		//qs.printArr();

		int k = 3;
		int sol = qs.quickSelect(qs.arr, k);
		System.out.println("SOL " + sol);
	}

}
