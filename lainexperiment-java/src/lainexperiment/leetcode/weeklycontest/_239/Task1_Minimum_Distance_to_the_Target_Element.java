/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._239;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 01/05/2021
 * 
 * Problem: Minimum Distance to the Target Element
 * Status: accepted
 * 
 * Given an integer array nums (0-indexed) and two integers target
 * and start, find an index i such that nums[i] == target and
 * abs(i - start) is minimized. Note that abs(x) is the absolute
 * value of x.
 * 
 * Return abs(i - start).
 * 
 * It is guaranteed that target exists in nums.
 * 
 * Input
 * 
nums = [1,2,3,4,5], target = 5, start = 3
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task1_Minimum_Distance_to_the_Target_Element {
    
    public int getMinDistance(int[] a, int target, int start) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                min = Math.min(min, Math.abs(i - start));
            }
        }
        return min;
    }

    @Test
    public void test() {
        assertEquals(0, getMinDistance(new int[] {1,1,1,1,1,1,1,1,1,1}, 1, 0));
        assertEquals(0, getMinDistance(new int[] {1}, 1, 0));
        assertEquals(1, getMinDistance(new int[] {1,2,3,4,5}, 5, 3));
    }
}
