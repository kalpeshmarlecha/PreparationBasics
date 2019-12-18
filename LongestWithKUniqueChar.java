package com.ebay.crm.kycriskrtr;

import java.util.HashMap;

public class LongestWithKUniqueChar {

	public static void main(String[] args) {
		String input = "aabcdefff";
		System.out.println("Input: " + input);
		int k = 3;
		String sol = longestSubstringWithKUnique(input, k);
		System.out.println("Solution: " + sol);
	}

	private static String longestSubstringWithKUnique(String input, int k) {
		if (input == null) return null;
		char[] arr = input.toCharArray();
		int n = input.length();
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();  
		
		int index = 0;
		int len = 0;
		int max = 0;
		int startIndex = 0;
		for (int i=0; i<n;) {
			if (map.size() <= k) {
				if (map.containsKey(arr[i])) {
					map.put(arr[i], map.get(arr[i]) + 1);
				} else {
					map.put(arr[i], 1);
				}
				len++;
				i++;
			} else {
				int count = map.get(arr[index]) == null ? 0: map.get(arr[index]);
				if (count <= 1) {
					map.remove(arr[index]);
				} else {
					map.put(arr[index], count - 1);
				}
				len--;
				index++;
			}
			
			if (len >= max) {
				max = len;
				startIndex = index;
			}
		}
		return input.substring(startIndex, startIndex+max);
	}

}
