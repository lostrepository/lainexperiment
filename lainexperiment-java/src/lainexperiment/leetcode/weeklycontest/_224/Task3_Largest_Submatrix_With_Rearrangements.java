/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._224;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 25/01/2021
 * 
 * Problem: Largest Submatrix With Rearrangements
 * Status: accepted
 * 
 * You are given a binary matrix matrix of size m x n, and you are
 * allowed to rearrange the columns of the matrix in any order.
 * 
 * Return the area of the largest submatrix within matrix where every
 * element of the submatrix is 1 after reordering the columns optimally.
 *
 * Example 1:
 * 
[0,0,1]
[1,1,1]
[1,0,1]]
 * 
 * Output
 * 
4
 * 
 * }</pre>
 */
public class Task3_Largest_Submatrix_With_Rearrangements {

    public int largestSubmatrix(int[][] m) {
        var tested = new HashSet<Integer>();
        int max = 0;
        for (int i = 0; i < m.length; i++) {
            var a = scanRow(m, i, tested);
            if (a.isEmpty()) continue;
            max = Math.max(max, calcArea(m, i, a));
        }
        return max;
    }
    
    int calcArea(int[][] m, int r, Set<Integer> set) {
        int maxSoFar = set.size();
        for (int i = r + 1; i < m.length; i++) {
            var iter = set.iterator();
            while (iter.hasNext()) {
                int c = iter.next();
                if (m[i][c] != 1) {
                    iter.remove();
                }
            }
            maxSoFar = Math.max(maxSoFar, (i - r + 1) * set.size());
        }
        return maxSoFar;
    }

    Set<Integer> scanRow(int[][] m, int r, HashSet<Integer> tested) {
        var res = new HashSet<Integer>();
        for (int c = 0; c < m[r].length; c++) {
            if (m[r][c] == 1) {
                res.add(c);
            } else {
                tested.remove(c);
            }
        }
        if (tested.containsAll(res)) return Set.of();
        tested.addAll(res);
        return res;
    }
    
    @Test
    public void test() {
        assertEquals(4, largestSubmatrix(new int[][] {
            {0,0,1},
            {1,1,1},
            {1,0,1}}));
        assertEquals(2, largestSubmatrix(new int[][] {
            {1,1,0},
            {1,0,1}}));
        assertEquals(0, largestSubmatrix(new int[][] {
            {0,0},
            {0,0}}));
    }
}
