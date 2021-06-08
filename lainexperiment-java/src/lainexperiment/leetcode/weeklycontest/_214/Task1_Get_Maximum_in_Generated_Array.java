/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._214;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 02/20/2021
 * 
 * Problem: Get Maximum in Generated Array
 * Status: accepted
 * 
 * You are given an integer n. An array nums of length n + 1 is generated
 * in the following way:
 * 
 * nums[0] = 0
 * nums[1] = 1
 * nums[2 * i] = nums[i] when 2 <= 2 * i <= n
 * nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
 * 
 * Return the maximum integer in the array nums​​​.
 *
 * Example 1:
 * 
7
 * 
 * Output
 * 
3
 * 
 * nums[0] = 0
 * nums[1] = 1
 * nums[(1 * 2) = 2] = nums[1] = 1
 * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 * nums[(2 * 2) = 4] = nums[2] = 1
 * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 * nums[(3 * 2) = 6] = nums[3] = 2
 * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * 
 * }</pre>
 */
public class Task1_Get_Maximum_in_Generated_Array {

    public int getMaximumGenerated(int n) {
        int[] a = new int[n + 2];
        a[0] = 0;
        a[1] = 1;
        int m = 0;
        for (int i = 1; i < a.length / 2; i++) {
            a[2 * i] = a[i];
            a[2 * i + 1] = a[i] + a[i + 1];
        }
        for (int i = 1; i < a.length - 1; i++) {
            m = Math.max(a[i], m);
        }
        System.out.println(Arrays.toString(a));
        return m;
    }

    @Test
    public void test() {
        assertEquals(3, getMaximumGenerated(7));
        assertEquals(1, getMaximumGenerated(2));
        assertEquals(2, getMaximumGenerated(3));
    }
}
