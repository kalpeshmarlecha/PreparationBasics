package com.ebay.crm.kycriskrtr;

import java.util.HashMap;
import java.util.Map;

class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
	 }


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveConsecutive {
	public ListNode removeZeroSumSublists(ListNode head) {
        int sum = 0;
        ListNode ptr = head;
        ListNode prev = head;
        ListNode start = head;
        ListNode finalHead = head;
        
        while (ptr != null) {
            sum = 0;
            start = ptr;
            System.out.println("\tCurrent: " + start.val);
            while (start != null) {
            	System.out.println("Sum: " + (sum + start.val));
                if (sum + start.val != 0) {
                    sum = sum + start.val;
                } else {
                    // Change pointers
                	sum = 0;
                	if (finalHead == ptr) {
                		finalHead = start.next;
                		prev.next = start.next;
                	} else {
                		prev.next = start.next;
                	}
                }
                start = start.next;
            }
            
            prev = ptr;
            ptr = ptr.next;
        }
        return finalHead;
    }
	
	public ListNode removeZeroSumSublistsCopied(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        for(ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            map.put(sum, node);
        }
        sum = 0;
        for(ListNode node = dummy; node != null; node = node.next) {
            sum += node.val;
            node.next = map.get(sum).next;
        }
        return dummy.next;
    }
    
	public void printLL(ListNode head ){
    	while (head != null) {
    		System.out.print(head.val + "->");
    		head = head.next;
    	}
    	System.out.println();
    }
    
    public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(-3);
		ListNode node5 = new ListNode(4);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		RemoveConsecutive r = new RemoveConsecutive();
		r.printLL(node1);
		ListNode outputNode = r.removeZeroSumSublistsCopied(node1);
		r.printLL(outputNode);
	}
}