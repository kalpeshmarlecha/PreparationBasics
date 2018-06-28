// Iterative Queue based Java program to do level order traversal
// of Binary Tree

/* importing the inbuilt java classes required for the program */
import java.util.Queue;
import java.util.LinkedList;

/* Class to represent Tree node */
class Node {
	int data;
	Node left, right;

	public Node(int item) {
		data = item;
		left = null;
		right = null;
	}
}

/* Class to print Level Order Traversal */
class LevelOrderTraversal_BinaryTree {

	Node root;

	/* Given a binary tree. Print its nodes in level order
	using array for implementing queue */
	void printLevelOrder() 
	{
		Queue<Node> queue = new LinkedList<Node>();
		Node endNode = new Node(Integer.MAX_VALUE);
		queue.add(root);
		queue.add(endNode);
		while (!(queue.size()==1 && queue.peek().data==Integer.MAX_VALUE)) 
		{
			//Node tempNode = queue.peek();
			//System.out.println("New Level " + tempNode.data + " ");
			
			while (queue.peek().data != Integer.MAX_VALUE){
			    
    			System.out.print("Printing element ->" + queue.peek().data + ", ");
    
    			if (queue.peek().left != null) {
    			    //System.out.print(" AddedLeft:"+ queue.peek().left.data);
    				queue.add(queue.peek().left);
    			}
    
    			if (queue.peek().right != null) {
    			    //System.out.print(" AddedRight:"+ queue.peek().right.data);
    				queue.add(queue.peek().right);
    			}
    			queue.remove();
			}
			
			queue.remove();
			queue.add(endNode);
			System.out.println();
			
		}
	}

	public static void main(String args[]) 
	{
		/* creating a binary tree and entering 
		the nodes */
		BinaryTree tree_level = new BinaryTree();
		tree_level.root = new Node(10);
		tree_level.root.left = new Node(11);
		tree_level.root.right = new Node(12);
		tree_level.root.left.left = new Node(13);
		tree_level.root.left.right = new Node(14);
        //tree_level.root.right.left = new Node(15);
		tree_level.root.right.right = new Node(16);

		System.out.println("Level order traversal of binary tree is - ");
		tree_level.printLevelOrder();
	}
}
