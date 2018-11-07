package com.ebay.kyc.admintool.controller;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LevelOrder {
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	static void printLevelOrderSpiral(Node root) {
	    Stack<Node> stack1 = new Stack<Node>();
	    Stack<Node> stack2 = new Stack<Node>();
	    stack1.push(root); 
	    boolean isItFirstStack = true;
	                                                            
	    while(!stack1.isEmpty() || !stack2.isEmpty()) {
	         if(isItFirstStack) {
	             Node currentNode = stack1.pop();
	             System.out.print(currentNode.data + " ");
	             if(currentNode.left != null) {
	                 stack2.push(currentNode.left);
	             }
	             
	             if(currentNode.right != null) {
	                 stack2.push(currentNode.right);
	             }
	             
	             if(stack1.isEmpty()) {
	            	 isItFirstStack = false;
	             	System.out.println();
	             }             
	            
	         } else { 
	            
	             Node currentNode = stack2.pop();
	             System.out.print(currentNode.data + " ");
	             if(currentNode.right != null) {
	                 stack1.add(currentNode.right);
	             }
	             
	             if(currentNode.left != null) {
	                 stack1.add(currentNode.left);
	             }
	             
	             if(stack2.isEmpty()) {
	                isItFirstStack = true;
	                System.out.println();
	             }           
	               
	         }
	    }
		
	}
	
	// Iterative method to do level order traversal line by line
	static void printLevelOrder(Node root) {
		if (root == null) {
			return;

		}

		Queue<Node> queue1 = new LinkedList<>();
		Queue<Node> queue2 = new LinkedList<>();
		queue1.add(root);
		boolean isItFirstQueue = true;

		while (!queue1.isEmpty() || !queue2.isEmpty()) {
			if (isItFirstQueue) {
				Node currentNode = queue1.poll();
				System.out.print(currentNode.data + " ");
				if (currentNode.left != null) {
					queue2.add(currentNode.left);
				}

				if (currentNode.right != null) {
					queue2.add(currentNode.right);
				}

				if (queue1.isEmpty()) {
					isItFirstQueue = false;
					System.out.println();
				}

			} else {

				Node currentNode = queue2.poll();
				System.out.print(currentNode.data + " ");
				if (currentNode.left != null) {
					queue1.add(currentNode.left);
				}

				if (currentNode.right != null) {
					queue1.add(currentNode.right);
				}

				if (queue2.isEmpty()) {
					isItFirstQueue = true;
					System.out.println();
				}

			}

		}

	}

	// Driver program to test above functions
	public static void main(String[] args) {

        // Let us create binary tree shown in above diagram 
       /*               1 
                   /     \ 
                  2       3 
                /   \       \ 
               4     5       6 
              / \   / \     / \
             7   8 9   10  11  12
       */
        
        // Output:
        // 1
        // 2 3
        // 4 5 6
        // 7 8 9 10 11 12
		
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.right = new Node(6);

		root.left.left.left = new Node(7);
		root.left.left.right = new Node(8);
		root.left.right.left = new Node(9);
		root.left.right.right = new Node(10);
		root.right.right.left = new Node(11);
		root.right.right.right = new Node(12);

		//printLevelOrder(root);
		printLevelOrderSpiral(root);

	}

}