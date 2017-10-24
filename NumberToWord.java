package com.paypal.risk.test.romd.userdatachange;

public class NumberToWord {

	public static String numberToWord(int num){
		//Range check from 0 to 999
		if(num<0 || num>999){
			return "Input:OutOfRange";
		}
		
		StringBuffer sol = new StringBuffer();
		int remainder = num;
		
		String[] TENS = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};
		String[] BASE_TENS = {"TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN"};
		String[] BASE_TENS_TY = {"", "", "TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINTY"};
		
		//Hundred
		if(num/100 > 0){
			sol.append(TENS[num/100] + " hundred ");
			remainder = remainder%100;
		}
		
		//TENS
		if(remainder>9 && remainder<20 ){
			sol.append(BASE_TENS[remainder%10]);
			return sol.toString();
		}else if(remainder>9){
			sol.append(BASE_TENS_TY[remainder/10] + " ");
			remainder = remainder%10;
		}
		
		//BASE
		if(remainder < 10 && remainder > 0){
			sol.append(TENS[remainder]);
		}
		
		return sol.toString();
	}

	
	public static void main(String[] args) {
		System.out.println("7489794: " + numberToWord(7489794));
		System.out.println("1: " + numberToWord(1));
		System.out.println("4: " + numberToWord(4));
		System.out.println("9: " + numberToWord(9));
		System.out.println("10: " + numberToWord(10));
		System.out.println("11: " + numberToWord(11));
		System.out.println("12: " + numberToWord(12));
		System.out.println("13: " + numberToWord(13));
		System.out.println("19: " + numberToWord(19));
		System.out.println("20: " + numberToWord(20));
		System.out.println("23: " + numberToWord(23));
		System.out.println("39: " + numberToWord(39));
		System.out.println("99: " + numberToWord(99));
		System.out.println("100: " + numberToWord(100));
		System.out.println("102: " + numberToWord(102));
		System.out.println("210: " + numberToWord(210));
		System.out.println("513: " + numberToWord(513));
		System.out.println("323: " + numberToWord(323));
		System.out.println("723: " + numberToWord(709));
		System.out.println("1000: " + numberToWord(1000));
	}

}
