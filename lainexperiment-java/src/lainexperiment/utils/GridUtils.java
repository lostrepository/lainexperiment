/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.pairs.Pair;
import lainexperiment.utils.pairs.PairInt;

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
     * Print 2d array to stdout
     */
    public static void print(char[][] a) {
        System.out.println(Arrays.deepToString(a).replace("],", "]\n"));
    }
    
    /**
     * @param g Grid where to run BFS
     * @param start Coordinates of starting point on the grid
     * @param end Coordinates of finish point on the grid
     */
    public static void bfs(int[][] g, PairInt start, PairInt end) {
        int rows = g.length;
        int cols = g[0].length;
        boolean[][] visited = new boolean[rows][cols];
        var q = new LinkedList<PairInt>();
        q.add(start);
        while (!q.isEmpty()) {
            var p = q.poll();
            if (visited[p.a][p.b]) continue;
            System.out.println(p);
            visited[p.a][p.b] = true;
            if (p.equals(end)) return;
            q.addAll(findJumps(p, rows, cols));
        }
    }
    
    /**
     * This version of BFS maintains depth of the nodes as it discovers them
     * allowing to find shortest path.
     * 
     * @param g Grid where to run BFS
     * @param start Coordinates of starting point on the grid
     * @param end Coordinates of finish point on the grid
     * @return shortest path between start and end
     */
    public static int bfsShortestPath(int[][] g, PairInt start, PairInt end) {
        int rows = g.length;
        int cols = g[0].length;
        boolean[][] visited = new boolean[rows][cols];
        var q = new LinkedList<PairInt>();
        q.add(start);
        int s = 0;
        while (!q.isEmpty()) {
            var nq = new LinkedList<PairInt>();
            while (!q.isEmpty()) {
                var p = q.poll();
                if (p.equals(end))
                    return s;
                if (visited[p.a][p.b]) continue;
                visited[p.a][p.b] = true;
                nq.addAll(findJumps(p, rows, cols));
            }
            q = nq;
            s++;
        }
        return -1;
    }
    
    /**
     * 
     * @param g Grid where each g[i][j] contains a distance to it from each adjacent cell
     * @param start
     * @param end
     * @return shortest path from start to end
     */
    public static int dijkstra(int[][] g, PairInt start, PairInt end) {
        var q = new PriorityQueue<Pair<Integer, PairInt>>(Pair::compareByA);
        q.add(new Pair<>(0, new PairInt()));
        int rows = g.length;
        int cols = g[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[][] distance = new int[rows][cols];
        for (int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        while (!q.isEmpty()) {
            var p = q.poll();
            var c = p.b;
            if (c.equals(end)) return distance[c.a][c.b];
            if (visited[c.a][c.b]) continue;
            visited[c.a][c.b] = true;
            int[] x = {0, 0, 1, -1};
            int[] y = {1, -1, 0, 0};
            for (int k = 0; k < 4; k++) {
                var next = new PairInt(c.a + y[k], c.b + x[k]);
                // relax
                if (next.a >= 0 && next.a < rows && next.b >= 0 && next.b < cols) {
                    int d = p.a + g[next.a][next.b];
                    if (distance[next.a][next.b] <= d) continue;
                    distance[next.a][next.b] = d;
                    q.add(new Pair<>(d, next));
                }
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Generate all possible jumps to left, right, up and down from current
     * position (r, c) to all adjacent positions with length 1. Where rows/cols
     * define size of the grid.
     */
    public static List<PairInt> findJumps(PairInt loc, int rows, int cols) {
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        var res = new ArrayList<PairInt>();
        for (int k = 0; k < x.length; k++) {
            var next = new PairInt(loc.a + y[k], loc.b + x[k]);
            if (next.a < 0 || next.a >= rows || next.b < 0 || next.b >= cols) 
                continue;
            res.add(next);
        }
        return res;
    }
    
    /**
     * Generate all possible jumps to left, right, up and down from current
     * position (r, c) to all other positions with length len. Where rows/cols
     * define size of the grid.
     * It also generates jump to current position.
     */
    public static List<PairInt> findJumps(int r, int c, int rows, int cols, int len) {
        var res = new ArrayList<PairInt>();
        res.add(new PairInt(r, c));
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        for (int k = 0; k < 4; k++) {
            for (int i = 1; i <= len; i++) {
                var next = new PairInt(r + i * y[k], c + i * x[k]);
                if (next.a < 0 || next.a >= rows || next.b < 0 || next.b >= cols) 
                    break;
                res.add(next);
            }
        }
        return res;
    }
    
    @Test
    public void test_bfs() {
        var g = new int[][] {
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,0,0,0,1},
            {1,1,1,0,1}};
        
        bfs(g, new PairInt(0, 0), new PairInt(2, 1));
        
        assertEquals(3, bfsShortestPath(g, new PairInt(0, 0), new PairInt(2, 1)));
    }
    
    @Test
    public void test_dijkstra() {
        var g = new int[][] {
            {1,2,2,0,1},
            {1,5,5,1,5},
            {1,5,5,2,5},
            {1,5,5,1,5},
            {1,1,1,0,1}};
        
        assertEquals(7, dijkstra(g, new PairInt(0, 0), new PairInt(2, 1)));
        assertEquals(7, dijkstra(g, new PairInt(0, 0), new PairInt(4, 4)));
        assertEquals(5, dijkstra(g, new PairInt(0, 0), new PairInt(0, 4)));
    }
    
    @Test
    public void test_findJumps() {
        assertEquals("[(1, 2), (2, 2), (3, 2), (0, 2), (1, 3), (1, 1), (1, 0)]",
            findJumps(1, 2, 4, 4, 2).toString());
    }
}
