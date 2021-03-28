/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._212;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.pairs.Pair;
import lainexperiment.utils.pairs.PairInt;

/**
 * <pre>{@code
 * Date: 11/02/2020
 * 
 * Problem: Path With Minimum Effort
 * Status: accepted
 * 
 * You are a hiker preparing for an upcoming hike. You are given
 * heights, a 2D array of size rows x columns, where heights[row][col]
 * represents the height of cell (row, col). You are situated in the
 * top-left cell, (0, 0), and you hope to travel to the bottom-right
 * cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up,
 * down, left, or right, and you wish to find a route that requires
 * the minimum effort.
 * 
 * A route's effort is the maximum absolute difference in heights
 * between two consecutive cells of the route.
 * 
 * Return the minimum effort required to travel from the top-left
 * cell to the bottom-right cell.
 *
 * Input
 * 
heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 
 * Output
 * 
2
 * 
 * The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * 
 * }</pre>
 */
public class Task3_Path_With_Minimum_Effort {

    public int minimumEffortPath(int[][] h) {
        return dijkstra(h);
    }
    
    int dijkstra(int[][] h) {
        var q = new PriorityQueue<Pair<Integer, PairInt>>(Pair::compareByA);
        q.add(new Pair<>(0, new PairInt()));
        int rows = h.length;
        int cols = h[0].length;
        boolean[][] visited = new boolean[rows][cols];
        while (!q.isEmpty()) {
            var p = q.poll();
            var c = p.b;
            if (c.a == rows - 1 && c.b == cols -1) return p.a;
            if (visited[c.a][c.b]) continue;
            visited[c.a][c.b] = true;
            int[] x = {0, 0, 1, -1};
            int[] y = {1, -1, 0, 0};
            for (int k = 0; k < 4; k++) {
                var next = new PairInt(c.a + y[k], c.b + x[k]);
                if (next.a >= 0 && next.a < rows && next.b >= 0 && next.b < cols) {
                    int m = Math.abs(h[c.a][c.b] - h[next.a][next.b]);
                    q.add(new Pair<>(Math.max(p.a, m), next));
                }
            }
        }
        throw new RuntimeException();
    }

    @Test
    public void test() {
        assertEquals(9, minimumEffortPath(new int[][]{
            {1,10,6,7,9,10,4,9}}));
        assertEquals(1, minimumEffortPath(new int[][]{
            {0, 1, 1},
            {1, 2, 3}}));
        assertEquals(0, minimumEffortPath(new int[][]{
            {1,2,1,1,1},
            {1,2,1,2,1},
            {1,2,1,2,1},
            {1,2,1,2,1},
            {1,1,1,2,1}}));
        assertEquals(0, minimumEffortPath(new int[][]{
            {0, 1, 1},
            {0, 0, 0}}));
        assertEquals(1, minimumEffortPath(new int[][]{
            {0, 1, 1},
            {4, 2, 3}}));
        assertEquals(2, minimumEffortPath(new int[][]{
            {1,2,2},
            {3,8,2},
            {5,3,5}}));
        assertEquals(1, minimumEffortPath(new int[][]{
            {1,2,3},
            {3,8,4},
            {5,3,5}}));
    }
}
