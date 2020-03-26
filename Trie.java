package com.ebay.kyc.remedy.invoker.resolvers;

import java.util.ArrayList;
import java.util.List;

class TrieRoot {
	Trie root;
}

public class Trie {
	char value;
	Trie[] children;
	boolean isEndOfWord = false;
	
	public Trie(char val) {
		this.value = val;
		this.children = new Trie[26];
	}
	
	public static void insert(TrieRoot trieRoot, String aWord) {
		Trie root = trieRoot.root;
		if (root == null) {
			root = new Trie('1');
			System.out.println("Initializing");
			for (int i = 0; i < 26; i++) { 
                root.children[i] = null; 
        	}
			trieRoot.root = root;
		}
		
		for (int i=0; i<aWord.length(); i++) {
			char currentChar = aWord.charAt(i);
			if (root.children[currentChar - 'a'] == null) { 
				// System.out.println("CurrentChar: "  +currentChar + ", child: " + root.children[currentChar - 'a']);
				root.children[currentChar - 'a'] = new Trie(currentChar);
			}
			root = root.children[currentChar - 'a'];
		}
		root.isEndOfWord = true;
	}
	

	private static List<String> search(Trie root, String string, List<String> sol) {
		if (root == null) {
			return null;
		}
		
		String subText = "";
		for (int i=0; i<string.length(); i++) {
			// System.out.println("Started Search: " + root.value);
			char currentChar = string.charAt(i);
			if (root.children[currentChar-'a'] == null) {
				dfs(root, sol, subText+"");
				return sol;
			} else {
				root = root.children[currentChar - 'a'];
				// System.out.println("Moved Ahead Search: " + root.value);
			}
			subText += currentChar;
		}
		if (root != null) {
			dfs(root, sol, string);
		}
		return sol;
	}
	
	public static void dfs(Trie root, List<String> sol, String sub) {
		if (root == null) return;
		
		if (root.isEndOfWord == true && !sub.equals("")) {
			sol.add(sub);
		}
		
		for (Trie aNode : root.children) {
			if (aNode != null) {
				dfs(aNode, sol, sub+aNode.value);
			}
		}
	}
	
	public static void display(Trie root, String valueToDisplay) {
		if (root == null) {
			return;
		}
		// System.out.println("Display Called " + root.value);
		if (root.isEndOfWord == true) {
			System.out.println(valueToDisplay);
		}
		for (Trie aNode : root.children) {
			if (aNode != null) {
				display(aNode, valueToDisplay + aNode.value);
			}
		}
	}
	
	public static void main(String[] args) {
		TrieRoot trieRoot = new TrieRoot();
		
		System.out.println();
		insert(trieRoot, "do");
		System.out.println();
		display(trieRoot.root, "");

		System.out.println();
		insert(trieRoot, "dog");
		display(trieRoot.root, "");
		
		System.out.println();
		insert(trieRoot, "doger");
		display(trieRoot.root, "");
		
		System.out.println();
		insert(trieRoot, "doa");
		display(trieRoot.root, "");

		System.out.println();
		insert(trieRoot, "doable");
		display(trieRoot.root, "");
		
		ArrayList<String> sol = new ArrayList<String>();
		String searchWord = "do";
		search(trieRoot.root, searchWord, sol);
		System.out.println("Solution : " + sol);
		
		sol = new ArrayList<String>();
		searchWord = "doa";
		search(trieRoot.root, searchWord, sol);
		System.out.println("Solution : " + sol);
		
		
		sol = new ArrayList<String>();
		searchWord = "dogger";
		search(trieRoot.root, searchWord , sol);
		System.out.println("Solution : " + sol);
		
	}


}
