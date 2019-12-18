package com.ebay.crm.kycriskrtr;

//Iterative Queue based Java program to do level order traversal 
//of Binary Tree 

/* importing the inbuilt java classes required for the program */
import java.util.Queue; 
import java.util.LinkedList; 

/* Class to represent Tree node */
class Node123 { 
	int data; 
	Node123 left, right, next; 

	public Node123(int item) { 
		data = item; 
		left = null; 
		right = null; 
		next = null;
	} 
} 

/* Class to print Level Order Traversal */
public class LevelOrder { 

	Node123 root; 

	/* Given a binary tree. Print its nodes in level order 
	using array for implementing queue */
	void printLevelOrder() 
	{ 
		Queue<Node123> queue = new LinkedList<Node123>(); 
		queue.add(root); 
		while (!queue.isEmpty()) 
		{ 
			int size = queue.size();
			/* poll() removes the present head. 
			For more information on poll() visit 
			http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
			while (size > 0) {
				Node123 tempNode = queue.poll(); 
				System.out.print(tempNode.data + " "); 
	
				/*Enqueue left child */
				if (tempNode.left != null) { 
					queue.add(tempNode.left); 
				} 
	
				/*Enqueue right child */
				if (tempNode.right != null) { 
					queue.add(tempNode.right); 
				} 
				size--;
			}
			System.out.println();
		} 
	} 
	
	public Node123 connect(Node123 root) {
        if (root == null) return null; 
        Queue<Node123> q = new LinkedList<Node123>();
        q.add(root);
        root.next = null;
        
        LinkedList<Node123> levelList = new LinkedList<Node123>();
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
            	Node123 node = q.poll();
                if (node.left != null) {
                    q.add(node.left);
                    levelList.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                    levelList.add(node.right);
                }
                node.next = levelList.pollFirst();
                if (size == 1) {
                	node.next = null;
                }
                size--;
            }
        }
        return root;
    }

	public static void main(String args[]) 
	{ 
		/* creating a binary tree and entering 
		the nodes */
		LevelOrder tree_level = new LevelOrder(); 
		tree_level.root = new Node123(1); 
		tree_level.root.left = new Node123(2); 
		tree_level.root.right = new Node123(3); 
		tree_level.root.left.left = new Node123(4); 
		tree_level.root.left.right = new Node123(5); 
		tree_level.root.right.left = new Node123(6); 
		tree_level.root.right.right = new Node123(7); 

		System.out.println("Level order traversal of binary tree is - "); 
		// tree_level.printLevelOrder(); 
		Node123 newRoot = tree_level.connect(tree_level.root); 
		tree_level.root = newRoot;
		tree_level.printLevelOrder(); 
	} 
} 
