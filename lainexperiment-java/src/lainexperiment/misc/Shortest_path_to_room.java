/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
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
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class Shortest_path_to_room {

    public static int bfs(int[][] maze, int rows, int columns, int exitRow, int exitCol)
    {
        if (maze[0][0] == 1)
            return -1;
        int[][] M = new int[rows][];
        for (int i = 0; i < M.length; i++) {
            M[i] = maze[i].clone();
        }
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        M[0][0] = 1;
        int s = 0;
        while (!q.isEmpty()) {
            Queue<Point> nq = new LinkedList<>();
            while (!q.isEmpty()) {
                Point p = q.poll();
                if (p.y == exitRow && p.x == exitCol)
                    return s;
                if (p.x > 0 && M[p.y][p.x - 1] == 0) {
                    M[p.y][p.x - 1] = 1;
                    nq.add(new Point(p.x - 1, p.y));
                }
                if (p.y > 0 && M[p.y - 1][p.x] == 0) {
                    nq.add(new Point(p.x, p.y - 1));
                    M[p.y - 1][p.x] = 1;
                }
                if (p.x < columns - 1 && M[p.y][p.x + 1] == 0) {
                    nq.add(new Point(p.x + 1, p.y));
                    M[p.y][p.x + 1] = 1;
                }
                if (p.y < rows - 1 && M[p.y + 1][p.x] == 0) {
                    nq.add(new Point(p.x, p.y + 1));
                    M[p.y + 1][p.x] = 1;
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
        assertEquals(4, bfs(maze, maze.length, maze[0].length, 1, 3));
        assertEquals(2, bfs(maze, maze.length, maze[0].length, 1, 1));
        assertEquals(4, bfs(maze, maze.length, maze[0].length, 2, 2));
        assertEquals(5, bfs(maze, maze.length, maze[0].length, 2, 3));
        
        maze = new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 1, 0},
        };
        assertEquals(-1, bfs(maze, maze.length, maze[0].length, 2, 3));
        assertEquals(-1, bfs(maze, maze.length, maze[0].length, 2, 2));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 0, 1, 1},
        };
        assertEquals(-1, bfs(maze, maze.length, maze[0].length, 2, 3));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 0, 1, 1},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(7, bfs(maze, maze.length, maze[0].length, 2, 3));
     
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(9, bfs(maze, maze.length, maze[0].length, 2, 1));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(3, bfs(maze, maze.length, maze[0].length, 2, 1));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(9, bfs(maze, maze.length, maze[0].length, 2, 1));
        
        maze = new int[][]{
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 0, 0, 0},
        };
        assertEquals(-1, bfs(maze, maze.length, maze[0].length, 2, 1));
        
        maze = new int[][]{
            {0, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
        };
        assertEquals(13, bfs(maze, maze.length, maze[0].length, 2, 1));
    }
}
