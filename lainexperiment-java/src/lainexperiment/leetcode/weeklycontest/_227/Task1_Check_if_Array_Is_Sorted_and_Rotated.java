/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._227;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 08/02/2021
 * 
 * Problem: Check if Array Is Sorted and Rotated
 * Status: accepted
 * 
 * Given an array nums, return true if the array was originally
 * sorted in non-decreasing order, then rotated some number of
 * positions (including zero). Otherwise, return false.
 * 
 * There may be duplicates in the original array.
 * 
 * Note: An array A rotated by x positions results in an array B
 * of the same length such that A[i] == B[(i+x) % A.length],
 * where % is the modulo operation.
 *
 * Input
 * 
3,4,5,1,2
2,1,3,4
 * 
 * Output
 * 
true
false
 * 
 * }</pre>
 */
public class Task1_Check_if_Array_Is_Sorted_and_Rotated {
    
    public boolean check(int[] a) {
        int drops = 0;
        int hi = Integer.MAX_VALUE;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] <= a[i] && a[i] <= hi) continue;
            drops++;
            if (drops == 2) return false;
            hi = a[i - 1];
        }
        if (drops == 0) return true;
        return a[0] >= a[a.length - 1];
    }
    
    @Test
    public void test() {
        assertTrue(check(new int[] {1,2,3}));
        assertTrue(check(new int[] {2,3,1}));
        assertFalse(check(new int[] {2,3, 1, 0}));
        assertTrue(check(new int[] {3,4,5,1,2}));
        assertFalse(check(new int[] {2,1,3,4}));
        assertTrue(check(new int[] {1,2,3}));
        assertTrue(check(new int[] {1,1,1}));
        assertTrue(check(new int[] {2,1}));
        assertFalse(check(new int[] {3,7,10,6,7,7}));
        assertTrue(check(new int[] {10,1,4,5,10}));
    }
}
