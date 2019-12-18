package com.ebay.crm.kycriskrtr;

class Count {
	int count = 0;
}

class Node1 {
	int data; 
    Node1 left, right; 
   
    public Node1(int item)  
    { 
        data = item; 
        left = right = null; 
    }
}

public class CountUnival  
{ 
    Node1 root;   
    Count ct = new Count(); 

   
    // Driver program to test above functions 
    public static void main(String args[])  
    { 
           /* Let us construct the below tree 
                5 
              /   \ 
            4      5 
          /  \      \ 
         4    4      5 */
    	CountUnival tree = new CountUnival(); 
        tree.root = new Node1(5); 
        tree.root.left = new Node1(4); 
        tree.root.right = new Node1(5); 
        tree.root.left.left = new Node1(4); 
        tree.root.left.right = new Node1(4); 
        tree.root.right.right = new Node1(5); 
   
        System.out.println("The count of single valued sub trees is : "
                                            + tree.countUnival(tree.root)); 
    }

	private int countUnival(Node1 root) {
		if (root == null) return 0; 
		
		int left = countUnival(root.left);
		int right = countUnival(root.right);
		
		if (isUnival(root, root.data)) {
			return 1 + left + right;
		} else {
			return left + right;
		}
	}


	private boolean isUnival(Node1 root, int data) {
		if (root == null) return true;
		
		if (root.data == data) {
			return isUnival(root.left, data) && isUnival(root.right, data);
		}
		return false;
	} 
} 