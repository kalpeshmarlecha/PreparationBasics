/*1-> A

2-> B

…

26-> Z


input: 126

output: [AZ, LF, ABF]


// Work Culture


1-26 = AZ

12-6 = LF

1-2-6 = ABF


1/2/6


1-126

A, B, C ..Z, AA - AZ



2345678 - 2 / 345678 - 23 / 45678


// Sliding Window

// Recursive - Intuition*/



public class Solution {
	Map<Integer, String> map = new HashMap();
	
	public Solution() {
		initializeMap();
	}

	public void initializeMap() {
		// Fill it with initial values
	}

	public List<String> computeCombination(String input) {
		List<String> result = new ArrayList<String>();
		int start = 0;
		int end = 0;
		int length = input.length(); // 126 - length 3
	
		StringBuffer sb = new StringBuffer();
		StringBuffer tempResults = new StringBuffer();
	
		while (end < length && start < end) {
			String prefix = input.substring(start, end)
			//sb.append(prefix);
			String num = getValueFromSubstring(prefix);
			String postfix = input.substring(end);
			if (num != null) {
				tempResults.append(num);
				recUtil(tempResults, prefix, postfix);
			} else {
				start++;
			}
			end++;
		}
		return result;
	}


	public String getValueForSubstring(String input) {
		int num = Integer.parseInt(input);
		if (num >= 1 && num <= 26) {
			return map.get(num);
		}
		return null;
	}


	public void recUtil(String tempResults, String prefix, String postfix) {
		if (getValueForSubstring(prefix+postfix.substring(1)) != null) {
			return;
		}
		if (postfix.length() == 1) {
			//tempResults.append(postfix);
			results.add(tempResults+getValueForSubstring(postfix));
		}

		if (getValueForSubstring(prefix) != null) {
			tempResults.append(prefix);
			recUtil(tempResults, prefix+postfix.substring())
		}
	}
}


