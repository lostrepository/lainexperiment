/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.PairLong;

/**
 * <pre>{@code
 * Date: 25/03/2020
 * 
 * Problem: Maximum Non Negative Product in a Matrix
 * Status: accepted
 * 
 * You are given a rows x cols matrix grid. Initially,
 * you are located at the top-left corner (0, 0), and
 * in each step, you can only move right or down in the
 * matrix.
 * 
 * Among all possible paths starting from the top-left
 * corner (0, 0) and ending in the bottom-right corner
 * (rows - 1, cols - 1), find the path with the maximum
 * non-negative product. The product of a path is the
 * product of all integers in the grid cells visited
 * along the path.
 * 
 * Return the maximum non-negative product modulo 10^9 + 7.
 * If the maximum product is negative return -1.
 * 
 * Notice that the modulo is performed after getting
 * the maximum product.
 *
 * Input
 * 
1, -2, 1
1, -2, 1
3, -4, 1
 * 
 * Output
 * 
8
 * 
 * 
 * }</pre>
 */
public class Task3_Maximum_Non_Negative_Product_in_a_Matrix {

    final long MOD = 1_000_000_007L;
   
    public int maxProductPath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        var M = new PairLong[rows][cols];
        M[0][0] = new PairLong(grid[0][0], grid[0][0]);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (r == 0 && c == 0) continue;
                var val = grid[r][c];
                var l = new ArrayList<Long>();
                if (r != 0) {
                    l.addAll(List.of(M[r - 1][c].a * val, M[r - 1][c].b * val));
                }
                if (c != 0) {
                    l.addAll(List.of(M[r][c - 1].a * val, M[r][c - 1].b * val));
                }
                M[r][c] = new PairLong(Collections.min(l), Collections.max(l));
            }
        }
        int max = (int) (M[rows - 1][cols - 1].b % MOD);
        return max < 0? -1: max;
    }
    
    @Test
    public void test() {
        assertEquals(768, maxProductPath(new int[][] {
            {-1,-4,2},
            {4,3,-1},
            {2,-4,4},
            {1,-1,-4}
        }));
        assertEquals(24, maxProductPath(new int[][] {
            {2, 3},
            {1,4}
        }));
        assertEquals(2, maxProductPath(new int[][] {
            {1, 4,4,0},
            {-2, 0,0,1},
            {1,-1,1,1}
        }));
        assertEquals(0, maxProductPath(new int[][] {
            {1, 3},
            {0,-4}
        }));
        assertEquals(8, maxProductPath(new int[][] {
            {1,-2,1},
            {1,-2,1},
            {3,-4,1}
        }));
        assertEquals(-1, maxProductPath(new int[][] {
            {-1,-2,-3},
            {-2,-3,-3},
            {-3,-3,-2}
        }));
        
    }
}
