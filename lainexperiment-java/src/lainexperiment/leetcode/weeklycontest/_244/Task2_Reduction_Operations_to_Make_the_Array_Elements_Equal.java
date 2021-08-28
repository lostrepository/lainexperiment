/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._244;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 05/06/2021
 * 
 * Problem: Reduction Operations to Make the Array Elements Equal
 * Status: accepted
 * 
 * Given an integer array nums, your goal is to make all elements in
 * nums equal. To complete one operation, follow these steps:
 * 
 * - Find the largest value in nums. Let its index be i (0-indexed)
 *   and its value be largest. If there are multiple elements with the
 *   largest value, pick the smallest i.
 * - Find the next largest value in nums strictly smaller than largest.
 *   Let its value be nextLargest.
 * - Reduce nums[i] to nextLargest.
 * 
 * Return the number of operations to make all elements in nums equal.
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
public class Task2_Reduction_Operations_to_Make_the_Array_Elements_Equal {
    
    public int reductionOperations(int[] a) {
        Arrays.sort(a);
        int l = 1;
        int s = 0;
        for (int i = a.length - 2; i >= 0 ; i--) {
            if (a[i] == a[i + 1]) {
                l++;
                continue;
            }
            s += l;
            l++;
        }
        return s;
    }

    @Test
    public void test() {
        assertEquals(0, reductionOperations(new int[] {1000,1000,1000}));
        assertEquals(4, reductionOperations(new int[] {1,1,2,2,3}));
        assertEquals(0, reductionOperations(new int[] {1,1,1}));
        assertEquals(3, reductionOperations(new int[] {5,1,3}));
    }
}
