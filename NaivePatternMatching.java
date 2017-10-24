package com.paypal.risk.riskonboardingmsgd.services;

public class NaivePatternMatching {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String text = "ABAAAAB";
		String pattern = "AB";
		
		int matchCount = 0;
		
		for(int i=0; i<text.length();i++){
			for(int j=0; j<pattern.length(); j++){
				
				if(text.charAt(i) == pattern.charAt(j)){
					matchCount++;
					System.out.println("Matched "+ text.charAt(i) );
					System.out.println(" " + matchCount);
					i++; 
				}
				
				if(matchCount == pattern.length()){
					System.out.println("MATCH ");
					return;
				}
			
			}
			matchCount = 0;
		}
		
		System.out.println("NO MATCH ");
	}

}
