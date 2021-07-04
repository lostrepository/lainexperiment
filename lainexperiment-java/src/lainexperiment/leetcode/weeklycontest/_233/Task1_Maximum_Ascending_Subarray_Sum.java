/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._233;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 20/03/2021
 * 
 * Problem: Maximum Ascending Subarray Sum
 * Status: accepted
 * 
 * Given an array of positive integers nums, return the maximum possible
 * sum of an ascending subarray in nums.
 * 
 * A subarray is defined as a contiguous sequence of numbers in an array.
 * 
 * A subarray [nums_l, nums_l+1, ..., nums_r-1, nums_r] is ascending if
 * for all i where l <= i < r, nums_i < nums_i+1. Note that a
 * subarray of size 1 is ascending.
 * 
 * Input
 * 
10,20,30,5,10,50
 * 
 * Output
 * 
65
 * 
 * }</pre>
 */
public class Task1_Maximum_Ascending_Subarray_Sum {
    
    public int maxAscendingSum(int[] a) {
        var max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            int s = a[i];
            int p = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[p] < a[j]) {
                    p = j;
                    s += a[j];
                } else {
                    break;
                }
            }
            max = Math.max(max, s);
        }
        return max;
    }

    @Test
    public void test() {
        assertEquals(100, maxAscendingSum(new int[] {100, 10}));
        assertEquals(100, maxAscendingSum(new int[] {100}));
        assertEquals(100, maxAscendingSum(new int[] {100,10,1}));
        assertEquals(33, maxAscendingSum(new int[] {12,17,15,13,10,11,12}));
        assertEquals(150, maxAscendingSum(new int[] {10,20,30,40,50}));
        assertEquals(65, maxAscendingSum(new int[] {10,20,30,5,10,50}));
    }
}
