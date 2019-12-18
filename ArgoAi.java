package com.ebay.crm.kycriskrtr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;



/**
 * 
 * 21
 * 
 * 
 * 
 * [1, 2, 3, 4, 5, 6    6, 7, 8]
 * 21
 * 
 * [1, 2, 3, 4, 5, 6, 7]
 * [1,3,6,10,15,21, 28]
 * 
 * 
 * 
 * 18
 * [3, 4, 5, 6]
 * [5, 6, 7]
 * 
 * [3, 7, 12, 18]
 * 
 * 1, 2, 3, 4, 5, 6, 7
 * 
 * [1,2,3,4,5,6]
 * [1,3,6,10,15,21]
 * 
 * 
 * [6, 7, 8]
 * [10, 11]
 * 
 * INPUT: 21
 * OUTPUT: 3
 * 
 * 
 * 11
 * 
 * [5,6]
 */



public class ArgoAi {
    public static void main(String args[] ) throws Exception {
        int input = 21;
        int output = findConsecutive(input);
        System.out.println("Output: " + output);
        
        int outputFaster = findConsecutiveFaster(input);
        System.out.println("OutputFaster: " + outputFaster);
    }
    
    public static int findConsecutive(int input){
        int solution = 0;
        int start = 1;
        
        int interimSum = 0;
        for (int i=start; i<input; i++) {
            interimSum = interimSum + i;
            if (interimSum == input) {
                solution++;
            } else if (interimSum > input) {
                start++;
                i=start;
                interimSum = 0;
            }
            
        }
        
        return solution;
    }
    
    public static int findConsecutiveFaster(int input){
        int solution = 0;
        int start = 1;
        int arr[] = new int[input+1];
        for (int j=1; j<=input; j++) {
            arr[j] = j;
        }
        int sum = arr[start];
        
        for (int i=start+1; i<input; ) {
            if (sum == input) {
            	sum = sum + arr[i];
                solution++;
                i++;
            } else if (sum > input) {
                sum = sum - start;
                start++;
            } else {
            	sum = sum + arr[i];
            	i++;
            }
        }
        
        return solution;
    }
}
