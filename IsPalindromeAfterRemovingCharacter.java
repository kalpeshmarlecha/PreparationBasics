package com.ebay.crm.kycriskrtr;

public class IsPalindromeAfterRemovingCharacter {

	public static void main(String[] args) {
		IsPalindromeAfterRemovingCharacter is = new IsPalindromeAfterRemovingCharacter();
		String input = "abcgdegcba";
		int canBe = is.isPalindromeAfterRemovingCharacter(input);
		System.out.println("Solution : " + canBe);
	}

	private int isPalindromeAfterRemovingCharacter(String input) {
		int start = 0, end = input.length()-1;
		
		while (start < end) {
			if (input.charAt(start) == input.charAt(end)) {
				start++;
				end--;
			} else {
				System.out.println("Mismatched character start: " + start + " end: " + end);
				if (isPalindrome(input, start+1, end)) {
					return start;
				}
				if (isPalindrome(input, start, end-1)) {
					return end;
				}
				return -1; // Not possible
			}
		}
		
		return -2; // Already a palindrome
	}

	private boolean isPalindrome(String input, int start, int end) {
		while (start < end) {
			if (input.charAt(start) == input.charAt(end)) {
				start++;
				end--;
			} else {
				return false;
			}
		}
		return true;
	}
	
}
