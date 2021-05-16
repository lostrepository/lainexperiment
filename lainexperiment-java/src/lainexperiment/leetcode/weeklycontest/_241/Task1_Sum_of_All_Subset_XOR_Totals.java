/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._241;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 15/05/2021
 * 
 * Problem: Sum of All Subset XOR Totals
 * Status: accepted
 * 
 * The XOR total of an array is defined as the bitwise XOR of all its elements,
 * or 0 if the array is empty.
 * 
 * For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
 * 
 * Given an array nums, return the sum of all XOR totals for every subset of nums.
 * 
 * Note: Subsets with the same elements should be counted multiple times.
 * 
 * An array a is a subset of an array b if a can be obtained from b by deleting
 * some (possibly zero) elements of b.
 * 
 * Input
 * 
5,1,6
 * 
 * Output
 * 
28
 * 
 * }</pre>
 */
public class Task1_Sum_of_All_Subset_XOR_Totals {
    
    int SUM;

    public void subsetXORSum(int[] nums, int i, LinkedList<Integer> acc) {
        if (i == nums.length) {
            if (!acc.isEmpty()) {
                SUM += acc.stream()
                        .reduce((a, b) -> a ^ b)
                        .get();
            }
            return;
        }
        acc.add(nums[i]);
        subsetXORSum(nums, i + 1, acc);
        acc.removeLast();
        subsetXORSum(nums, i + 1, acc);
    }
    
    public int subsetXORSum(int[] nums) {
        SUM = 0;
        subsetXORSum(nums, 0, new LinkedList<>());
        return SUM;
    }

    @Test
    public void test() {
        assertEquals(480, subsetXORSum(new int[] {3,4,5,6,7,8}));
        assertEquals(28, subsetXORSum(new int[] {5,1,6}));
        assertEquals(6, subsetXORSum(new int[] {1,3}));
    }
}
