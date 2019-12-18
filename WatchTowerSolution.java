package com.ebay.crm.kycriskrtr;

class Node {
	int val;
	Node left;
	Node right;
	
	public Node(int value){
		this.val = value;
		left = null;
		right = null;
	}
}

public class WatchTowerSolution {

	public Node stretch(Node root, int target) {
		return stretchUtil(root, target, true, null);
	}
	
	public Node stretchUtil(Node root, int target, boolean isLeft, Node firstNode) {
		if (root == null) {
			return root;
		}

		int computedValue = root.val / target;
		root.val = computedValue;
		Node newNode = null;
		Node next = null;
		
		for (int i=0; i<target; i++) {
			newNode = new Node(computedValue);
			if (isLeft) {
				newNode.left = root;
				if (firstNode == null) {
					firstNode = newNode;
				} else {
					next = (target == 3) ? newNode: root; 
					firstNode.left = next;
				}
			} else {
				newNode.right = root;
				if (firstNode == null) {
					firstNode = newNode;
				} else {
					next = (target == 3) ? newNode: root; 
					firstNode.right = next;
				}
			}
		}
		
		root.left = stretchUtil(root.left, target, true, null);
		root.right = stretchUtil(root.right, target, false, null);
		return firstNode;
	}
	
	public void inOrder(Node root) {
		if (root == null) {return;}
		inOrder(root.left);
		System.out.print(root.val + " ");
		inOrder(root.right);
	}

	public void preOrder(Node root) {
		if (root == null) {return;}
		System.out.print(root.val + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	public static void main(String[] args) {
		WatchTowerSolution s = new WatchTowerSolution();
		Node root = new Node(12);
		root.left = new Node(81);
		root.right = new Node(34);
		root.left.right = new Node(56);
		System.out.print("Original In Order Traversal --> ");
		s.inOrder(root);
		System.out.println();
		
		root.right = new Node(34);
		root.right.left = new Node(19);
		root.right.right = new Node(6);
		
		Node returned = s.stretch(root, 3);
		
		System.out.println("Output");
		System.out.print("In Order Traversal --> ");
		s.inOrder(returned);
		System.out.println();
		System.out.print("Pre Order Traversal -->");
		s.preOrder(returned);
	}
	
}
