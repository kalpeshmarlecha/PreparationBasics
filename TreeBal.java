package com.paypal.risk.riskonboardingmsgd.services;

import scala.collection.generic.BitOperations.Int;

public class TreeBal {

	class Node {
		int data;
		Node left, right;

		public Node(int dat) {
			this.data = dat;
			left = right = null;
		}
	}
	
	int height(Node root){
		if(root == null)
			return 0;
		return 1 + Math.max( height(root.left), height(root.right) );
	}
	
	int heightLin(Node root){

		if(root == null) return 0;
		
		int lHeight = heightLin(root.left) +1;
		int rHeight = heightLin(root.right) +1;
		
		int heightDiff = lHeight - rHeight;
		
		if(lHeight == Integer.MIN_VALUE || rHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		
		if(Math.abs(heightDiff) > 1)
			return Integer.MIN_VALUE;
	
		else 
			return Math.max(lHeight, rHeight);
	}

	public static void main(String[] args) {
		TreeBal tb = new TreeBal();
		Node root = tb.new Node(5);
		root.left = tb.new Node(3);
		root.right = tb.new Node(6);
		root.left.left = tb.new Node(2);
		root.left.right = tb.new Node(4);
		root.right.right = tb.new Node(8);
		//root.left.left.left = tb.new Node(1);
		
		System.out.println("SOL " + tb.heightLin(root) );
	}

}
