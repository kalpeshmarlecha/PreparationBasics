package com.paypal.risk.riskonboardingmsgd.services;

public class ReverseLL {

	Node head;
	
	class Node{
		int data;
		Node next;
		
		public Node(int dat){
			this.data = dat;
			this.next = null;
		}
	}
	
	public void printLL(Node n){
		while(n != null){
			System.out.print(n.data + " ");
			n = n.next;
		}
		System.out.println();
	}
	
	public Node reverseIterate(Node n){
		
		if(n == null) return null;
		
		Node prev = null, next = null, curr = n;
		
		while(curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		n = prev;
		return n;
	}
	
	public Node reverseRec(Node curr, Node prev){
		
		if(curr.next == null){
			this.head = curr;
			curr.next = prev;
			return null;
		}
		
		Node next1 = curr.next;
		curr.next = prev;
		reverseRec(next1, curr);
		
		return this.head;
	}
	
	public static void main(String[] args) {
		ReverseLL revLL = new ReverseLL();
		Node first = revLL.new Node(1);
		first.next = revLL.new Node(2);
		first.next.next = revLL.new Node(3);
		first.next.next.next = revLL.new Node(4);
		first.next.next.next.next = revLL.new Node(5);
		first.next.next.next.next.next = revLL.new Node(9);
		first.next.next.next.next.next.next = revLL.new Node(10);
		
		revLL.printLL(first);
		
		Node temp = revLL.reverseIterate(first);
		revLL.printLL(temp);
		
		Node temp1 = revLL.reverseRec(first, null);
		revLL.printLL(temp1);
		
		int number =5;
		int power = 10;
		
		System.out.println( "SOL "  + calcExponent(number, power) );
		System.out.println( "SOL "  + calcExponentRec(number, power) );
		
	}

	private static int calcExponent(int number, int power) {

		int sol = 1;
		if(number == 0) return 0;
		if(power == 0) return 1;
		
		while(power>0){
			sol = sol * number;
			power--;
		}
		
		return sol;
	}

	private static int calcExponentRec(int number, int power) {

		int sol = 1;
		if(power == 0) return 1;
		
		int ans = calcExponentRec(number, power/2);
		sol = ans*ans;
		
		if(power%2 == 0)
			return sol;
		else
			return sol*number;
		
	}
	
	
}
