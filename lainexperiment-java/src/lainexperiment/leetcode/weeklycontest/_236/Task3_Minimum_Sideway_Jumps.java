/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._236;

import static lainexperiment.utils.MathUtils.min;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 10/04/2021
 * 
 * Problem: Minimum Sideway Jumps
 * Status: accepted
 * 
 * There is a 3 lane road of length n that consists of n + 1 points
 * labeled from 0 to n. A frog starts at point 0 in the second lane
 * and wants to jump to point n. However, there could be obstacles
 * along the way.
 * 
 * You are given an array obstacles of length n + 1 where each
 * obstacles[i] (ranging from 0 to 3) describes an obstacle on the
 * lane obstacles[i] at point i. If obstacles[i] == 0, there are no
 * obstacles at point i. There will be at most one obstacle in the
 * 3 lanes at each point.
 * 
 * For example, if obstacles[2] == 1, then there is an obstacle on
 * lane 1 at point 2.
 * 
 * The frog can only travel from point i to point i + 1 on the same
 * lane if there is not an obstacle on the lane at point i + 1.
 * To avoid obstacles, the frog can also perform a side jump to jump
 * to another lane (even if they are not adjacent) at the same point
 * if there is no obstacle on the new lane.
 * 
 * For example, the frog can jump from lane 3 at point 3 to lane 1
 * at point 3.
 * 
 * Return the minimum number of side jumps the frog needs to reach
 * any lane at point n starting from lane 2 at point 0.
 * 
 * Note: There will be no obstacles on points 0 and n.
 * 
 * Input
 * 
0,1,2,3,0
 * 
 * Output
 * 
2
 * 
 * 
 * 0 1 2 3 4
 * =========
 *   #
 * F   #
 *       #
 * =========
 * 
 * The optimal solution is to:
 * 
 * - jump from line 2 to 3 at point 1
 * - jump from line 3 to 1 at point 2
 * 
 * Note that the frog can jump over obstacles only when making side jumps
 * (as shown at point 2).
 * 
 * }</pre>
 */
public class Task3_Minimum_Sideway_Jumps {
    
    public int minSideJumps(int[] a) {
        var L = new int[a.length][3];
        L[0][0] = 1;
        L[0][1] = 0;
        L[0][2] = 1;
        var X = Integer.MAX_VALUE - 2;
        for (int i = 1; i < a.length; i++) {
            var jumps = new ArrayList<Integer>();
            for (int j = 0; j < 3; j++) {
                if (a[i] == j + 1) {
                    L[i][j] = X;
                    continue;
                }
                jumps.add(j);
            }
            for (var j: jumps) {
                if (L[i - 1][j] == X)
                    L[i][j] = X - 1;
                else
                    L[i][j] = L[i - 1][j]; 
            }
            if (L[i][0] != X) {
                L[i][0] = min(L[i][0], L[i][1] + 1, L[i][2] + 1);
            }
            if (L[i][1] != X) {
                L[i][1] = min(L[i][0] + 1, L[i][1], L[i][2] + 1);
            }
            if (L[i][2] != X) {
                L[i][2] = min(L[i][0] + 1, L[i][1] + 1, L[i][2]);
            }
        }
        var l = L[L.length - 1];
        return min(l);
    }

    @Test
    public void test() {
        assertEquals(2, minSideJumps(new int[] {0,2,1,0,3,0}));
        assertEquals(0, minSideJumps(new int[] {0,1,1,3,3,0}));
        assertEquals(2, minSideJumps(new int[] {0,1,2,3,0}));
    }
}
