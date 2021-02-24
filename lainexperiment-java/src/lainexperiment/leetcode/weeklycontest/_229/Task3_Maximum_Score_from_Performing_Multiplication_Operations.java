/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._229;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 23/02/2021
 * 
 * Problem: Maximum Score from Performing Multiplication Operations
 * Status: accepted
 * 
 * You are given two integer arrays nums and multipliers of size n
 * and m respectively, where n >= m. The arrays are 1-indexed.
 * 
 * You begin with a score of 0. You want to perform exactly m
 * operations. On the ith operation (1-indexed), you will:
 * 
 * - Choose one integer x from either the start or the end of the
 * array nums.
 * - Add multipliers[i] * x to your score.
 * - Remove x from the array nums.
 * 
 * Return the maximum score after performing m operations.
 *
 * Input
 * 
nums = [1,2,3], multipliers = [3,2,1]
 * 
 * Output
 * 
14
 * 
 * }</pre>
 */
public class Task3_Maximum_Score_from_Performing_Multiplication_Operations {
    
    private int[][] M;
    
    public int maximumScore(int[] n, int[] m) {
        M = new int[m.length][m.length];
        for (int i = 0; i < M.length; i++)
            Arrays.fill(M[i], Integer.MIN_VALUE); 
        return maximumScore(n, m, 0, n.length - 1, 0);
    }
    
    int maximumScore(int[] n, int[] m, int lo, int hi, int mi) {
        if (mi == m.length) return 0;
        if (lo == hi) return n[lo] * m[mi];
        if (M[lo][n.length - hi - 1] != Integer.MIN_VALUE)
            return M[lo][n.length - hi - 1];
        int l = n[lo] * m[mi] + maximumScore(n, m, lo + 1, hi, mi + 1);
        int r = n[hi] * m[mi] + maximumScore(n, m, lo, hi - 1, mi + 1);
        var res = Math.max(l, r);
        M[lo][n.length - hi - 1] = res;
        return res;
    }

    @Test
    public void test() {
        assertEquals(102, maximumScore(new int[] {-5,-3,-3,-2,7,1}, new int[] {-10,-5,3,4,6}));
        assertEquals(14, maximumScore(new int[] {1,2,3}, new int[] {3,2,1}));
        assertEquals(8, maximumScore(new int[] {1,2}, new int[] {2, 3, 4}));
        assertEquals(10, maximumScore(new int[] {1,2, 2}, new int[] {2, 3}));
        assertEquals(8, maximumScore(new int[] {1,2, 1}, new int[] {2, 3}));
        assertEquals(8, maximumScore(new int[] {1,2}, new int[] {2, 3, 4}));
        assertEquals(8, maximumScore(new int[] {1,2}, new int[] {2, 3}));
        assertEquals(4, maximumScore(new int[] {1,2}, new int[] {2}));
        assertEquals(1, maximumScore(new int[] {1}, new int[] {1}));
    }
}
