/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.lang.Math.min;

import lainexperiment.utils.PairInt;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 28/05/2016
 * 
 * Problem
 * 
 * Matrix contains values 0 - room, B - obstacle and G - guard.
 * Need to calculate the steps from a room to nearest guard.
 *
 * Input
 * 
0 0 0
B G G
B 0 0
 * 
 * Output
 * 
2 1 1
B G G
B 1 1
 * 
 * }</pre>
 */
public class Short_path_to_guard {

    static int[][] A;
    static int B = -2, G = -1, X = Integer.MAX_VALUE;
    
    public static int bfsShortestPath(int[][] g, PairInt start) {
        int rows = g.length;
        int cols = g[0].length;
        boolean[][] visited = new boolean[rows][cols];
        var q = new LinkedList<PairInt>();
        q.add(start);
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        int s = 0;
        while (!q.isEmpty()) {
            var nq = new LinkedList<PairInt>();
            while (!q.isEmpty()) {
                var p = q.poll();
                g[p.a][p.b] = min(g[p.a][p.b], s);
                if (visited[p.a][p.b]) continue;
                visited[p.a][p.b] = true;
                for (int k = 0; k < 4; k++) {
                    var next = new PairInt(p.a + y[k], p.b + x[k]);
                    if (next.a < 0 || next.a >= rows || next.b < 0 || next.b >= cols) 
                        continue;
                    if (g[next.a][next.b] < 0) continue;
                    nq.add(next);
                }
            }
            q = nq;
            s++;
        }
        return -1;
    }
    
    static void solve() {
        for (int r = 0; r < A.length; r++)
        {
            for (int c = 0; c < A[0].length; c++)
            {
                if (A[r][c] == G)
                    bfsShortestPath(A, new PairInt(r, c));
            }
        }
        Arrays.stream(A).map(Arrays::toString)
            .map(s -> s.replace("" + B, "B").replace("" + G, "G").replace("" + X, "X"))
            .forEach(System.out::println);
    }
    
    @Test
    public void test() {
        A = new int[][]{
            {X, X, X, X, X, X},
            {B, G, G, X, X, X},
            {B, X, G, X, X, X},
        };
        var R = new int[][] {
            {2, 1, 1, 2, 3, 4},
            {B, G, G, 1, 2, 3},
            {B, 1, G, 1, 2, 3}};
        solve();
        assertArrayEquals(R, A);
        
        A = new int[][]{
            {X, X, X, X, X},
            {X, B, B, B, X},
            {X, B, G, B, X},
            {X, B, B, B, X},
            {X, X, X, X, X},
        };
        R = new int[][] {
            {X, X, X, X, X},
            {X, B, B, B, X},
            {X, B, G, B, X},
            {X, B, B, B, X},
            {X, X, X, X, X}};
        solve();
        assertArrayEquals(R, A);
        
        A = new int[][]{
            {X, X, X, X, X},
            {X, B, B, B, X},
            {X, B, G, B, X},
            {X, B, X, B, X},
            {X, X, X, X, X},
        };
        R = new int[][] {
            {8, 9, 10, 9, 8},
            {7, B, B, B, 7},
            {6, B, G, B, 6},
            {5, B, 1, B, 5},
            {4, 3, 2, 3, 4}};
        solve();
        assertArrayEquals(R, A);
    }

}
