package com.paypal.risk.riskonboardingmsgd.services;

public class Anagram {

	private static Anagram instance;
	
	public synchronized static Anagram getInstance(){
		if(instance == null)
			return new Anagram();
		else 
			return instance;
	}
	
	public static void main(String[] args) {

		String str = "kalKAL";
		char[] cArray = str.toCharArray();
		
		for(char c: cArray){
			System.out.println("CHAR " + c);
			int val = getCharNumber(c);
			System.out.println("NUM " + val);
		}
		
		
	}

	private static int getCharNumber(char c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		System.out.println("a:" + a + " z:" +z + " VAL:" +val);
		
		return val-a;
	}

}
