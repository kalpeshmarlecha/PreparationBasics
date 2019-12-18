package com.ebay.crm.kycriskrtr;

public class MoveZeros {

	public void moveZeroes(int[] nums) {
        int n = nums.length;
        int firstNonZeroIndex = -1;
        int firstZeroIndex = -1;
        int totalSwap = 0;
        // Build
        int i=0;  
        while (i<n) {
            if (nums[i] != 0 && firstNonZeroIndex == -1) {
                firstNonZeroIndex = i;
            }
            if (nums[i] == 0 && firstZeroIndex == -1) {
                firstZeroIndex = i;
            }
            i++;
            if (firstNonZeroIndex != -1 && firstZeroIndex != -1) {
                // SWAP
                if (firstZeroIndex < firstNonZeroIndex) {
                	totalSwap++;
                    int temp = nums[firstNonZeroIndex];
                    nums[firstNonZeroIndex] = nums[firstZeroIndex];
                    nums[firstZeroIndex] = temp;
                }
                i = Math.min(firstNonZeroIndex, firstZeroIndex) + 1;
                
                // RESET
                firstNonZeroIndex = -1;
                firstZeroIndex = -1;
            }
        }
        System.out.println("Total Swaps: " + totalSwap);
    }
    
    public static void main(String[] args) {
    	int[] input = {0,1,0,3,12};
    	MoveZeros mz = new MoveZeros();
    	mz.moveZeroes(input);
    	
    	int input1[] = {0,0,0,0,0,1};
    	mz.moveZeroes(input1);
    	
	}
	
}
