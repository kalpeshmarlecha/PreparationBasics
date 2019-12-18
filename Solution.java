package com.ebay.crm.kycriskrtr;

class NodeTree {
	int val;
	NodeTree left;
	NodeTree right;
	
	public NodeTree(int value){
		this.val = value;
		left = null;
		right = null;
	}
}

public class Solution {

	public void stretch(NodeTree root, int target) {
		stretchUtil(root, 2, true);
	}
	
	public void stretchUtil(NodeTree root, int target, boolean isLeft) {
		if (root == null) {
			return;
		}
		
		int createNodeWithValue = root.val / target;
		root.val = createNodeWithValue;
		for (int i=0; i<target-1; i++) {
			System.out.println("Loop Number " + i+1);
			if (isLeft) {
				NodeTree newNode = new NodeTree(createNodeWithValue);
				newNode.left = root;
			} else {
				NodeTree newNode = new NodeTree(createNodeWithValue);
				newNode.right = root;
			}
			
		}
		stretchUtil(root.left, target, true);
		stretchUtil(root.right, target, false);
		
	}
	
	public void inOrder(NodeTree root) {
		if (root == null) {return;}
		inOrder(root.left);
		System.out.println( root.val );
		inOrder(root.right);
	}
	
	public static void main(String[] args) {
		NodeTree root = new NodeTree(12);
		root.left = new NodeTree(81);
		root.right = new NodeTree(34);
		root.left.right = new NodeTree(56);
		
		root.right = new NodeTree(34);
		root.right.left = new NodeTree(19);
		root.right.right = new NodeTree(6);
		
		Solution s = new Solution();
		s.stretch(root, 2);
		
		s.inOrder(root);
	}
	
}
