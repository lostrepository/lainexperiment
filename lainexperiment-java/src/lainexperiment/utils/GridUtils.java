/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

/**
 * Utilities to work with 2d arrays.
 */
public class GridUtils {

    /**
     * Print 2d array to stdout
     */
    public static void print(int[][] a) {
        System.out.println(Arrays.deepToString(a).replace("],", "]\n"));
    }
    
    /**
     * @param g Grid where to run BFS
     * @param start Coordinates of starting point on the grid
     * @param visitor Node visitor. BFS expands the node only when visitor returns true
     */
    public static void bfs(int[][] g, PairInt start, Function<PairInt, Boolean> visitor) {
        int rows = g.length;
        int cols = g[0].length;
        boolean[][] visited = new boolean[rows][cols];
        var q = new LinkedList<PairInt>();
        q.add(start);
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        while (!q.isEmpty()) {
            var p = q.poll();
            if (visited[p.a][p.b]) continue;
            visited[p.a][p.b] = true;
            if (!visitor.apply(p)) continue;
            for (int k = 0; k < 4; k++) {
                var next = new PairInt(p.a + y[k], p.b + x[k]);
                if (next.a < 0 || next.a >= rows || next.b < 0 || next.b >= cols) 
                    continue;
                q.add(next);
            }
        }
    }
    
    @Test
    public void test_bfs() {
        var g = new int[][] {
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,1,1,0,1}};
        
        bfs(g, new PairInt(0, 0), n -> {
            System.out.println(n);
            return g[n.a][n.b] == 1 ;
        });
    }
}
