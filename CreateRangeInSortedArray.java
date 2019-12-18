package com.ebay.crm.kycriskrtr;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted list of numbers, return a list of strings that represent all of the consecutive numbers.
 * Example:
 * Input: [0, 1, 2, 5, 7, 8, 9, 9, 10, 11, 15]
 * Output: ['0->2', '5->5', '7->11', '15->15']
 * */

public class CreateRangeInSortedArray {
	public static void main(String[] args) {
		int input[] = {0, 1, 2, 5, 7, 8, 9, 9, 10, 11, 15};
		
		List<String> list = createList(input);
		for (String str : list) {
			System.out.println(str);
		}
	}

	private static List<String> createList(int[] input) {
		List<String> list = new ArrayList<String>();
		int start = input[0];
		int val = input[0];
		for (int i=0; i<input.length; ) {
			if (val + 1 == input[i] || val == input[i]) {
				val = input[i];
				i++;
			} else {
				list.add(start + "->" + val);
				start = input[i];
				val = input[i];
			}
			
			if (i == input.length) {
				list.add(start+"->"+val);
				i++;
			}
		}
		return list;
	}
}
