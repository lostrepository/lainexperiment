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

import lainexperiment.utils.GridUtils;

/**
 * <pre>{@code
 * Date: 05/06/2021
 * 
 * Problem: Determine Whether Matrix Can Be Obtained By Rotation
 * Status: accepted
 * 
 * Given two n x n binary matrices mat and target, return true if
 * it is possible to make mat equal to target by rotating mat in
 * 90-degree increments, or false otherwise.
 * 
 * Input 1
 * 
source =
0 1
1 0

target =
1 0
0 1
 * 
 * Output
 * 
true
 * 
 * Input 2
 * 
source =
0 0 0
0 1 0
1 1 1

target =
1 1 1
0 1 0
0 0 0
 * 
 * Output
 * 
true
 *
 * }</pre>
 */
public class Task1_Determine_Whether_Matrix_Can_Be_Obtained_By_Rotation {
    
    public boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            mat = rotate(mat);
            if (Arrays.deepEquals(mat, target)) return true;
        }
        return false;
    }

    private int[][] rotate(int[][] mat) {
        int len = mat.length;
        var a = new int[mat.length][mat[0].length];
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                a[c][len - r - 1] = mat[r][c];
            }
        }
        return a;
    }

    @Test
    public void test() {
        assertEquals(true, findRotation(new int[][] {
            {0,1},
            {1,0},
        }, new int[][] {
            {1,0},
            {0,1},
        }));
        
        assertEquals(false, findRotation(new int[][] {
            {0,1},
            {1,1},
        }, new int[][] {
            {1,0},
            {0,1},
        }));
        
        assertEquals(true, findRotation(new int[][] {
            {0,0,0},
            {0,1,0},
            {1,1,1}
        }, new int[][] {
            {1,1,1},
            {0,1,0},
            {0,0,0}
        }));
        var m = rotate(new int[][] {
            {0,1,2},
            {3,4,5},
            {6,7,8}
        });
        GridUtils.print(m);
    }
}
