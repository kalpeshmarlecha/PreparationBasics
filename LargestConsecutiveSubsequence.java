package com.paypal.risk.test.romd.userdatachange;

import java.util.Arrays;

public class LargestConsecutiveSubsequence {

	public static int largestConsecutiveSubsequence(int input[]){
		//Sort the array
		Arrays.sort(input);
		
		int startIndex=0, endIndex=1;
		int maxSoFar=0;
		
		for(int i=0; i<input.length && endIndex < input.length; i++){
			
			if(input[endIndex]-input[startIndex] == (endIndex-startIndex)){
				endIndex++;
				if(maxSoFar < (endIndex-startIndex)){
					maxSoFar = endIndex-startIndex;
				}
				
			}else{
				endIndex++;
				startIndex++;
			}
		}
		
		return maxSoFar;
	}

	
	public static void main(String[] args) {

		int input[] = {1,5,6,7,2,3,4,8, 10,11};
		System.out.println("SET : " + largestConsecutiveSubsequence(input));
		
		int input2[] = {1,15, 16,17, 5, 6, 11, 18};
		System.out.println("SET : " + largestConsecutiveSubsequence(input2));
	}

}
