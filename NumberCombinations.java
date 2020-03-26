package com.ebay.kyc.remedy.invoker.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberCombinations {

	private static Map<Integer, String> map = new  HashMap();
	
	static {
		int i=1;
		char charLooper = 'A';
		for (charLooper = 'A'; charLooper<='Z'; charLooper++) {
			map.put(i, String.valueOf(charLooper));
			i++;
		}
		System.out.println(map);
	}
	
	public static void main(String[] args) {
		System.out.println("Output: " + getAllCombinations("126"));
		System.out.println("Output: " + getAllCombinations("12321"));
		System.out.println("Output: " + getAllCombinations("12"));
		System.out.println("Output: " + getAllCombinations("25123"));
	}

	private static List<String> getAllCombinations(String input) {
		List<String> result = new ArrayList<String>();
		getValidCombi(result, input, "");
		return result;
	}

	private static void getValidCombi(List<String> result, String remaining, String fixed) {
		if (remaining.length() == 0) {
			result.add(fixed);
			return;
		} else if (remaining.length() == 1) {
			String probableNum = getNum(remaining);
			if (probableNum != null) {
				result.add(fixed + probableNum);
				return;
			}
		}
		String singleDigit = getNum(remaining.substring(0, 1));
		String doubleDigit = getNum(remaining.substring(0, 2));

		if (singleDigit != null) {
			System.out.println("Single: " + remaining.substring(0, 1));
			System.out.println("Remaining: " + remaining.substring(1) + ", SingleFixed: " + fixed + singleDigit);
			getValidCombi(result, remaining.substring(1), fixed+singleDigit);
		}
		if (doubleDigit != null) {
			System.out.println("Double: " + remaining.substring(0, 2));
			System.out.println("Remaining: " + remaining.substring(2) + ", DoubleFixed: " + fixed + doubleDigit);
			getValidCombi(result, remaining.substring(2), fixed+doubleDigit);
		}
	}

	private static String getNum(String input) {
		int i = Integer.parseInt(input);
		if (i>=1 && i<=26) {
			return map.get(i);
		}
		return null;
	}

}
