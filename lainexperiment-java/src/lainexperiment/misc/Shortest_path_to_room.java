/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.pairs.PairInt;

/**
 * <pre>{@code
 * Date: 18/12/2016
 * 
 * Problem
 * 
 * Matrix contains values 0 - room, 1 - obstacle. Print 
 * shortest path from room with coords 0, 0 to the given 
 * room.
 *
 * Input
 * 
0, 0, 0, 0
1, 0, 1, 0
1, 0, 0, 0
room: 1, 3
 * 
 * Output
 * 
4
 * 
 * }</pre>
 */
public class Shortest_path_to_room {

    public static int bfsShortestPath(int[][] g, PairInt start, PairInt end) {
        if (g[0][0] == 1)
            return -1;
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
                if (p.equals(end))
                    return s;
                if (visited[p.a][p.b]) continue;
                visited[p.a][p.b] = true;
                for (int k = 0; k < 4; k++) {
                    var next = new PairInt(p.a + y[k], p.b + x[k]);
                    if (next.a < 0 || next.a >= rows || next.b < 0 || next.b >= cols) 
                        continue;
                    if (g[next.a][next.b] != 0) continue;
                    nq.add(next);
                }
            }
            q = nq;
            s++;
        }
        return -1;
    }
    
    @Test
    public void test() {
        int[][] maze = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {1, 0, 0, 0},
        };
        assertEquals(4, bfsShortestPath(maze, new PairInt(), new PairInt(1, 3)));
        assertEquals(2, bfsShortestPath(maze, new PairInt(), new PairInt(1, 1)));
        assertEquals(4, bfsShortestPath(maze, new PairInt(), new PairInt(2, 2)));
        assertEquals(5, bfsShortestPath(maze, new PairInt(), new PairInt(2, 3)));
        
        maze = new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 1, 0},
        };
        assertEquals(-1, bfsShortestPath(maze, new PairInt(), new PairInt(2, 3)));
        assertEquals(-1, bfsShortestPath(maze, new PairInt(), new PairInt(2, 2)));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 0, 1, 1},
        };
        assertEquals(-1, bfsShortestPath(maze, new PairInt(), new PairInt(2, 3)));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(7, bfsShortestPath(maze, new PairInt(), new PairInt(2, 3)));
     
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(9, bfsShortestPath(maze, new PairInt(), new PairInt(2, 1)));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(3, bfsShortestPath(maze, new PairInt(), new PairInt(2, 1)));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(9, bfsShortestPath(maze, new PairInt(), new PairInt(2, 1)));
        
        maze = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(-1, bfsShortestPath(maze, new PairInt(), new PairInt(2, 1)));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
        };
        assertEquals(13, bfsShortestPath(maze, new PairInt(), new PairInt(2, 1)));
    }
}
