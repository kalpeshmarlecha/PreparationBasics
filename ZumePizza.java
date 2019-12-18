package com.ebay.crm.kycriskrtr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
# parent_child_pairs = [
       1 is a friend of 3,
       2 is a friend of 3
       
#     (1, 3), (2, 3), (3, 6), (5, 6),
#     (5, 7), (4, 5), (4, 8), (8, 10)
# ]
*/


// 1 -> 3
// 2 -> 3
// 3 -> 6, 5-> 6

/*
# Write a function that takes this data as input and returns two collections: one containing all individuals with zero known parents, and one containing all individuals with exactly one known parent.


# Write a method that, for two given individuals in our dataset, returns true if and only if they share at least one common ancestor.


# Write a method that, for a given individual in our dataset, returns their earliest known ancestor. If there is more than one ancestor tied for “earliest”, return any one of them.

*/


public class ZumePizza {
    public static void main(String args[] ) throws Exception {
        int[][] inputArr = {{1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {8,10}};
        ZumePizza sol = new ZumePizza();
        Map<Integer, List<Integer>> map = sol.findParents(inputArr);
       
        // Common Ancestor
        int n1 = 7;
        int n2 = 10;
        Set<Integer> set = new HashSet<Integer>();
        Set<Integer> set1 = sol.getAncestors(n1, map, set);
        set = new HashSet<Integer>();
        Set<Integer> set2 = sol.getAncestors(n2, map, set);
        System.out.print("CommonAncestor of {" + n1 + ", " + n2 + "} is ");
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
        List<Integer> listParents = map.get(n);
        if (listParents.size() == 0) {
            return n;
        }
        for (int element: listParents) {
            answer = getAncestorsAtMaxDepth(element, map);
        }
        return answer;
    }
   
    public Set<Integer> getAncestors(int n, Map<Integer, List<Integer>> map, Set<Integer> set) {
        List<Integer> listParents = map.get(n);
        set.addAll(listParents);
        for (int element: listParents) {
            set.add(element);
            getAncestors(element, map, set);
        }
        return set;
    }
   
    public Map<Integer, List<Integer>> findParents(int[][] input){
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
    }
}