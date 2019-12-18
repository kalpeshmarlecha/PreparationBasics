package com.ebay.crm.kycriskrtr;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

public class DoorDash {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    //strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
   
    int[][] inputTimes = {{3,20}, {-2, 0}, {0,2}, {16,17}, {19,23}, {30,40}, {27, 33}};

    // sort required ? - Runtime would be Nlog(N) - N being the total interval's count
    Arrays.sort(inputTimes, new Comparator<int[]>() {
		
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0] - o2[0];
		}
	});
    
    for (int[] aSlot: inputTimes) {
    	System.out.println("[Start: " + aSlot[0] + ", End: " + aSlot[1] + "]");
    }
    
    int index = 0;
    int i=1;
    
    int currStart = inputTimes[index][0];
    int currEnd = inputTimes[index][1];

    int nextStart = inputTimes[index][0];
    int nextEnd = inputTimes[index][1];
    
    for (i=1; i<inputTimes.length; i++) {
    	nextStart = inputTimes[i][0];
    	nextEnd = inputTimes[i][1];
    	System.out.println("Current [Start: " + currStart + ", End: " + currEnd + "]");
    	System.out.println("Next [Start: " + nextStart + ", End: " + nextEnd + "]");
    	
    	if (currEnd >= nextStart) {
    		// Merge
    		inputTimes[index][0] = Math.min(currStart, nextStart);
    		inputTimes[index][1] = Math.max(currEnd, nextEnd);
    		
    		currStart = inputTimes[index][0];
    		currEnd = inputTimes[index][1];
    		System.out.println("Merged [Start: " + currStart + ", End: " + currEnd + "]");
    		
    	} else {
    		index++;
    		inputTimes[index][0] = nextStart;
    		inputTimes[index][1] = nextEnd;
    		currStart = inputTimes[index][0];
    		currEnd = inputTimes[index][1];
    		System.out.println("Added [Start: " + currStart + ", End: " + currEnd + "]");
    	}
    	System.out.println();
    }
    
    System.out.println();
    
    for (int k=0; k<=index; k++) {
    	System.out.println("[Start: " + inputTimes[k][0] + ", End: " + inputTimes[k][1] + "]");
    }
    System.out.println("SIZE: " + index);
   
  }
}

// -2 0 /  0 2 / 3 20 / ----> -5 -3 / -4 -2 /
//
/*
Description:
Google Calendar, Outlook, iCal has been banned from your company! So an intrepid engineer has decided to roll their own implementation. Unfortunately one major missing feature is the ability to find out what time slots are free for a particular individual.

Given a list of time blocks where a particular person is already booked/busy, a start and end time to search between, a minimum duration to search for, find all the blocks of time that a person is free for a potential meeting that will last the aforementioned duration.

Given: start_time, end_time, duration, meetings_list -> open_meeting_windows

Let's assume we abstract the representation of times as simple integers, so a valid time is any valid integer supported by your environment.

Here is an example input:
meetings_list: [3,20], [-2, 0], [0,2], [16,17], [19,23], [30,40], [27, 33]
start_time: -5
end_time: 27
min_duration: 2

expected answer:
free_time: [-5, -2], [23,27]

Feel free to represent the meetings however you would like, i.e. List of Lists, Lists of Objects etc.

*/