/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._256;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.Utils;

/**
 * <pre>{@code
 * Date: 28/08/2021
 * 
 * Problem: Minimum Difference Between Highest and Lowest of K Scores
 * Status: accepted
 * 
 * You are given a 0-indexed integer array nums, where nums[i] represents
 * the score of the ith student. You are also given an integer k.
 * 
 * Pick the scores of any k students from the array so that the difference
 * between the highest and the lowest of the k scores is minimized.
 * 
 * Return the minimum possible difference.
 * 
 * Input
 * 
nums = [9,4,1,7], k = 2
 * 
 * Output
 * 
2
 * 
 * Constraints:
 * 1 <= k <= nums.length <= 1000
 * 0 <= nums[i] <= 10^5
 * 
 * }</pre>
 */
public class Task1_Minimum_Difference_Between_Highest_and_Lowest_of_K_Scores {
    
    public int minimumDifference(int[] a, int k) {
        Arrays.sort(a);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= a.length - k; i++) {
            min = Math.min(min, Math.abs(a[i] - a[i + k - 1]));
        }
        return min;
    }
    
    @Test
    public void test() {
        assertEquals(2, minimumDifference(Utils.asIntArray("[9,4,1,7]"), 2));
        assertEquals(0, minimumDifference(Utils.asIntArray("90"), 1));
    }
}
