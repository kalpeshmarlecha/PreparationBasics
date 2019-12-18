package com.ebay.crm.kycriskrtr;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SmartNews {

	public static void main(String[] args) {
		Map<Character, Integer> map1 = new HashMap<Character, Integer>();
		map1.put('a', 4);
		map1.put('b', 4);
		// Solution = 1
		Map<Character, Integer> map2 = new HashMap<Character, Integer>();
		map2.put('e', 2);
		map2.put('x', 1);
		map2.put('a', 1);
		map2.put('m', 1);
		map2.put('p', 1);
		map2.put('l', 1);
		// Solution = 4
		Map<Character, Integer> map3 = new HashMap<Character, Integer>();
		map3.put('c', 3);
		map3.put('a', 2);
		map3.put('f', 2);
		map3.put('d', 2);
		map3.put('e', 3);
		// Solution = 6
		SmartNews smartNews = new SmartNews();
		int sol = 0;
		int max = 3;
		System.out.println("MAX: " + max);
		int sum = smartNews.countAllValue(map3);
		System.out.println(", Sum: " + sum);
		int sumOfAll = 0;
		for (int i=smartNews.countDistinctValues(map3); i>0; i--) {
			sumOfAll = sumOfAll + (i);
		}
		sol = sum - sumOfAll;
		System.out.println();
		System.out.println("Sol: " + sol);
	}
	
	public int countAllValue(Map<Character, Integer> map) {
		int sol = 0;
		
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			sol = sol + entry.getValue();
		}
		
		return sol;
	}
	
	public int countDistinctValues(Map<Character, Integer> map) {
		Set<Integer> set = new HashSet<Integer>();
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			set.add(entry.getValue());
		}
		
		System.out.println("Distinct Values : " + set.size());
		return set.size();
	}
	
}
