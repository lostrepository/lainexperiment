/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._225;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 23/03/2021
 * 
 * Problem: Find Kth Largest XOR Coordinate Value
 * Status: accepted
 * 
 * You are given a 2D matrix of size m x n, consisting of
 * non-negative integers. You are also given an integer k.
 * 
 * The value of coordinate (a, b) of the matrix is the XOR
 * of all matrix[i][j] where 0 <= i <= a < m and
 * 0 <= j <= b < n (0-indexed).
 * 
 * Find the kth largest value (1-indexed) of all the
 * coordinates of matrix.
 * 
 * Example 1:
 * 
k = 1

5 2
1 6
 * 
 * Output
 * 
7
 * 
 * }</pre>
 */
public class Task3_Find_Kth_Largest_XOR_Coordinate_Value {

    public int kthLargestValue(int[][] a, int k) {
        var rows = a.length;
        var cols = a[0].length;
        var L = new int[rows][cols];
        L[0][0] = a[0][0];
        var acc = new ArrayList<Integer>();
        acc.add(L[0][0]);
        for (int i = 1; i < cols; i++) {
            L[0][i] = L[0][i - 1] ^ a[0][i];
            acc.add(L[0][i]);
        }
        for (int i = 1; i < rows; i++) {
            L[i][0] = L[i - 1][0] ^ a[i][0];
            acc.add(L[i][0]);
        }
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                var up = 0;
                for (int i = 0; i < r; i++) {
                    up ^= a[i][c];
                }
                L[r][c] = L[r][c - 1] ^ up ^  a[r][c];
                acc.add(L[r][c]);
            }
        }
        acc.sort(Collections.reverseOrder());
        return acc.get(k - 1);
    }

    @Test
    public void test() {
        assertEquals(0, kthLargestValue(new int[][] {
            {5,2},{1,6}
        }, 4));
        assertEquals(4, kthLargestValue(new int[][] {
            {5,2},{1,6}
        }, 3));
        assertEquals(5, kthLargestValue(new int[][] {
            {5,2},{1,6}
        }, 2));
        assertEquals(7, kthLargestValue(new int[][] {
            {5,2},{1,6}
        }, 1));
    }
}
