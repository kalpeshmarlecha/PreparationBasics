package com.ebay.crm.kycriskrtr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
# parent_child_pairs = [
       1 is a friend of 3,
       2 is a friend of 3,
       3 is a friend of 6, 
       5 is a friend of 6,
       5 is a friend of 7,
       4 is a friend of 5,
       4 is a friend of 8,
       8 is a friend of 10
       
#     (1, 3), (2, 3), (3, 6), (5, 6),
#     (5, 7), (4, 5), (4, 8), (8, 10)
# ]
*/


// 1 -> 3
// 2 -> 3
// 3 -> 6, 5-> 6


public class FB {
    public static void main(String args[] ) throws Exception {
        int[][] inputArr = {{1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {8,10}};
        FB sol = new FB();
        Map<Integer, List<Integer>> map = null; //sol.getFriends(inputArr);
       
        // Common Friends
        int n1 = 7;
        int n2 = 10;
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> set1 = sol.getMutualFriends(n1, map, set);
        set = new HashSet<Integer>();
        Set<Integer> set2 = sol.getMutualFriends(n2, map, set);
        System.out.print("CommonFriends of {" + n1 + ", " + n2 + "} is ");
        for (int element1 : set1) {
            if (set2.contains(element1)) {
                System.out.print(element1 + ", ");
            }
        }
        System.out.println();
        // Maximum Depth
       
        int element = 6;
        int solutionMax = sol.getAncestorsAtMaxDepth(element, map);
        System.out.println("Maximum Depth Parent of element " + element + " is " + solutionMax);
        System.out.println("Common " + sol.getMutualFriends(1, 5));
    }
    /*
      getAncestors(int n, Map<Int, List<Int>> map, Set<Integer> set, int depth) {
          List newParents = map.get(n);
          set.addList(newParent);
          for(Int parent: newParents) {
              getAncestors(parent, map, set, depth+1);
          }
          return set;
      }
     
    */
   
    public int getAncestorsAtMaxDepth(int n, Map<Integer, List<Integer>> map) {
        int answer = -1;
        List<Integer> listFriends = map.get(n);
        if (listFriends.size() == 0) {
            return n;
        }
        for (int element: listFriends) {
            answer = getAncestorsAtMaxDepth(element, map);
        }
        return answer;
    }
   
    public Set<Integer> getMutualFriends(int n, Map<Integer, List<Integer>> map, Set<Integer> set) {
        List<Integer> listParents = map.get(n);
        set.addAll(listParents);
        for (int element: listParents) {
            set.add(element);
            getMutualFriends(element, map, set);
        }
        return set;
    }
   
    /*public Map<Integer, List<Integer>> getFriends(int[][] input){
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int[] pair:input) {
            int parent = pair[0];
            int child = pair[1];
           
            if (map.containsKey(child)) {
                List<Integer> list = map.get(child);
                list.add(parent);
                map.put(child, list);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(parent);
                map.put(child, list);
            }
           
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Integer>());
            }
           
        }
       
        return map;
    }*/
    
    
    public List<Integer> getMutualFriends(int n1, int n2) {
    	List<Integer> friendListOfN1 = Arrays.asList(2, 3);
    	List<Integer> friendListOfN2 = Arrays.asList(2, 3, 4);
    	
    	// Get common elements of N1 and N2
    	//friendListOfN1.retainAll(friendListOfN2);
    	List<Integer> solution = new ArrayList<Integer>();
    	for (int anElement : friendListOfN1) {
    		if (friendListOfN2.contains(anElement)) {
    			solution.add(anElement);
    		}
    	}
    	return solution;
    }
    
 // I felt like i was trying to solve for Get Friends, so i was building a map.
    public List<Integer> getFriends(int id){
    	// These are the connections that are given as an input
    	int[][] input = {{1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {8,10}};
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int[] pair:input) {
            int friend1 = pair[0];
            int friend2 = pair[1];
           
            if (map.containsKey(friend2)) {
                List<Integer> list = map.get(friend2);
                list.add(friend1);
                map.put(friend2, list);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(friend1);
                map.put(friend2, list);
            }
           
            if (!map.containsKey(friend1)) {
                map.put(friend1, new ArrayList<Integer>());
            }
           
        }
       
        return map.get(id);
    }
}