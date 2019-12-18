package com.ebay.crm.kycriskrtr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cluedo {
	HashMap<String,Boolean> map0;
	HashMap<String,Boolean> map1;
	HashMap<String,Boolean> map2;
	ArrayList<HashMap<String, Boolean>> listOfMap;
	
	public Cluedo() {
		map0 = new HashMap<String, Boolean>(); 
		map1 = new HashMap<String, Boolean>(); 
		map2 = new HashMap<String, Boolean>(); 
		listOfMap = new ArrayList<HashMap<String,Boolean>>();
		buildDefaultConfig();
	}

	public static void main(String[] args) {
		String[] input = {"S1", "W2", "R3"};
		String[] solution = {"S1", "W2", "R5"};
		
		Cluedo cluedo = new Cluedo();
		System.out.println("Start the game with input [" + input[0] + ", " + input[1] + ", " + input[2] + "]");
		cluedo.play(input, solution);
	}

	private void play(String[] input, String[] solution) {
		boolean isGameCompleted = false;
		while (!isGameCompleted) {
			int mismatchedInputIndex = isSolution(input, solution);
			if (mismatchedInputIndex == 3) {
				System.out.println("Solution Found");
				isGameCompleted = true;
				break;
			} else {
				String mismatchElement = input[mismatchedInputIndex];
				System.out.println("Incorrect Clue: " + mismatchElement + ", is at index: " + mismatchedInputIndex);
				Map<String, Boolean> mismatchedElementMap = getAppropriateMap(mismatchElement);
				// Not to look for anymore, since its an incorrect element
				mismatchedElementMap.put(mismatchElement, true); 
				String nextElement = getNextElementFromMap(mismatchedElementMap);
				System.out.println("Next Clue To Pick Up : " + nextElement);
				input[mismatchedInputIndex] = nextElement;
			}
		}
	}
	
	private String getNextElementFromMap(Map<String, Boolean> appropriateMap) {
		for (Map.Entry<String, Boolean> entry : appropriateMap.entrySet()) {
			if (entry.getValue() == null) {
				return entry.getKey();
			}
		}
		return null;
	}

	private Map<String, Boolean> getAppropriateMap(String mismatch) {
		for (Map<String, Boolean> eachMap: listOfMap) {
			if (eachMap.containsKey(mismatch)) {
				return eachMap;
			}
		}
		return null;
	}
	
	/**
	 * This will return the index of mismatched location
	 * @param input
	 * @param solution
	 * @return
	 */
	private int isSolution(String[] input, String[] solution) {
		for (int i=0; i<input.length; i++) {
			if (input[i] != solution[i]) {
				return i;
			}
		}
		return 3; // 3 denotes the solution is reached
	}
	

	
	private void buildDefaultConfig() {
		String[] subjects = {"S1", "S2", "S3", "S4", "S5"};
		String[] weapons = {"W1", "W2", "W3", "W4", "W5"};
		String[] rooms = {"R1", "R2", "R3", "R4", "R5"};
		
		for (String eachSub : subjects) {
			map0.put(eachSub, null); // Initially everything is unknown
		}
		for (String eachWeapon : weapons) {
			map1.put(eachWeapon, null); // Initially everything is unknown
		}
		for (String eachRoom : rooms) {
			map2.put(eachRoom, null); // Initially everything is unknown
		}
		listOfMap.add(map0);
		listOfMap.add(map1);
		listOfMap.add(map2);
	}
	
}
