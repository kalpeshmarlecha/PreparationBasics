package com.paypal.risk.riskonboardingmsgd.services;


import java.util.LinkedList;
import java.util.Queue;

/*          7
 *     4          12
 * 1      6    10     13
 *                         16
 * 1. Output: 1 4 6 7 10 12 13 16
 * 2. Output: 0: 7
 *            1: 4 12
 *            2: 1 6 10 13
 *            3: 16
 */
 
 // Looks like the call got disconnected redialed!
 public class Node {
 
     int data;
     Node left, right;
     
     public Node(int dat){
         this.data = dat;
         left = right = null;
     }
 
     void  printInOrder(Node n){
         
         if(n == null){
             return;
         }
         printInOrder(n.left);
         System.out.print(n.data + " ");
         printInOrder(n.right);
         
     }

     public void printByHeight(Node n){
    	 
    	 if(n==null) return;
    	 
    	 Queue<Node> q = new LinkedList<Node>();
    	 q.add(n);
    	 q.add(new Node(Integer.MIN_VALUE));
    	 
    	 int level = 0;

    	 while(true){
    		 int qLength = q.size();
    	 
    		 if(qLength < 2)
    			 return;
    		 
    		 System.out.print(level + ":");
    		 
	    	 while(qLength > 0){
	    		Node myNode = q.peek();
	    		 
	    		if(myNode.data == Integer.MIN_VALUE){
	    			 //System.out.println("Level: " + level);
	    			 q.remove();
	    			 level++;
	    			 q.add(new Node(Integer.MIN_VALUE));
	    			 break;
	    		}
	    		 
	    		System.out.print(" " + myNode.data + " ");
	    		q.remove();
			 
	    		if(myNode.left != null)
	    			q.add(myNode.left);
	    		if(myNode.right != null)
	    			q.add(myNode.right);
	    		qLength--;
	    	 }
	    	 System.out.println();
    	 }
     }
     
     
     
 	// Driver program to test the above functions
     public static void main(String args[])
     {
    	 Node root = new Node(7);
    	 root.left = new Node(4);
    	 root.right = new Node(12);
    	 root.left.left = new Node(1);
    	 root.left.right = new Node(6);
    	 root.right.left = new Node(10);
    	 root.right.right = new Node(13);
    	 root.right.right.right = new Node(16);
    	 
    	 //root.printInOrder(root);
    	 
    	 root.printByHeight(root);

     }
     
 }
 