/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._241;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 15/05/2021
 * 
 * Problem: Finding Pairs With a Certain Sum
 * Status: accepted
 * 
 * You are given two integer arrays nums1 and nums2. You are
 * tasked to implement a data structure that supports queries of
 * two types:
 * 
 * - Add a positive integer to an element of a given index in
 * the array nums2.
 * 
 * - Count the number of pairs (i, j) such that nums1[i] + nums2[j]
 * equals a given value (0 <= i < nums1.length and 0 <= j < nums2.length).
 * 
 * Implement the FindSumPairs class:
 * 
 * - FindSumPairs(int[] nums1, int[] nums2)
 *   Initializes the FindSumPairs object with two integer arrays
 *   nums1 and nums2.
 *   
 * - void add(int index, int val)
 *   Adds val to nums2[index], i.e., apply nums2[index] += val.
 *   
 * - int count(int tot)
 *   Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 * 
 * Input
 * 
-1,-2,-3,-4,3,2,1
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task3_Finding_Pairs_With_a_Certain_Sum {
    
    class FindSumPairs {
        int[] a1, a2;
        Map<Integer, Long> M;
        public FindSumPairs(int[] nums1, int[] nums2) {
            a1 = nums1;
            a2 = nums2;
            M = Arrays.stream(a2)
                .boxed()
                .collect(groupingBy(i -> i, counting()));
        }
        
        public void add(int i, int val) {
            var k = a2[i];
            M.merge(k, -1L, Long::sum);
            k += val;
            a2[i] = k;
            M.merge(k, 1L, Long::sum);
        }
        
        public int count(int tot) {
            int r = 0;
            for (var n: a1) {
                r += M.getOrDefault(tot - n, 0L);
            }
            return r;
        }
    }

//    List<Integer> solve(List<String> cmds, List<List> args) {
//        var out = new ArrayList<Integer>();
//        FindSumPairs fsp = null;
//        for (int i = 0; i < array.length; i++) {
//            switch (cmds.get(i)) {
//            case "": 
//                List<int[]> l = args.get(i);
//                fsp = new FindSumPairs(l.get(0), l.get(1));
//                break;
//            case "count":
//                out.add(fsp.count((int) args.get(i).get(0)));
//                break;
//            case "add":
//                
//            }
//        }
//        return out;
//    }

    @Test
    public void test() {
        FindSumPairs findSumPairs = new FindSumPairs(new int[] {1, 1, 2, 2, 2, 3}, new int[] {1, 4, 5, 2, 5, 4});
        assertEquals(8, findSumPairs.count(7));  // return 8; pairs (2,2), (3,2), (4,2), (2,4), (3,4), (4,4) make 2 + 5 and pairs (5,1), (5,5) make 3 + 4
        findSumPairs.add(3, 2); // now nums2 = [1,4,5,4,5,4]
        assertEquals(2, findSumPairs.count(8));  // return 2; pairs (5,2), (5,4) make 3 + 5
        assertEquals(1, findSumPairs.count(4));  // return 1; pair (5,0) makes 3 + 1
        findSumPairs.add(0, 1); // now nums2 = [2,4,5,4,5,4]
        findSumPairs.add(1, 1); // now nums2 = [2,5,5,4,5,4]
        assertEquals(11, findSumPairs.count(7));  // return 11; pairs (2,1), (2,2), (2,4), (3,1), (3,2), (3,4), (4,1), (4,2), (4,4) make 2 + 5 and pairs (5,3), (5,5) make 3 + 4
    }
}
