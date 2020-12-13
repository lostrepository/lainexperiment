/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._218;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 05/12/2020
 * 
 * Problem: Max Number of KSum Pairs
 * Status: accepted
 * 
 * You are given an integer array nums and an integer k.
 * 
 * In one operation, you can pick two numbers from the
 * array whose sum equals k and remove them from the array.
 * 
 * Return the maximum number of operations you can perform
 * on the array.
 *
 * Example 1:
 * 
nums = [1,2,3,4], k = 5
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task2_Max_Number_of_KSum_Pairs {

    public int maxOperations(int[] a, int k) {
        Arrays.sort(a);
        int lo = 0, hi = a.length - 1;
        int c = 0;
        while (lo < hi) {
            int sum = a[lo] + a[hi];
            if (sum == k) {
                lo++;
                hi--;
                c++;
                continue;
            }
            if (sum < k) {
                lo++;
            } else {
                hi--;
            }
        }
        return c;
    }
    
    @Test
    public void test() {
        assertEquals(2, maxOperations(new int[] {1,2,3,4}, 5));
        assertEquals(1, maxOperations(new int[] {3,1,3,4,3}, 6));
        assertEquals(1, maxOperations(new int[] {3,1,3,4,3}, 6));
        assertEquals(1, maxOperations(new int[] {2, 0, 6, 3, 9, 9, 1, 9, 2, 1}, 5));
        assertEquals(1, maxOperations(new int[] {2, 0, 6, 3, 9, 9, 1, 9, 2, 1}, 6));
        assertEquals(2, maxOperations(new int[] {3, 3, 3, 3, 3}, 6));
    }

}
