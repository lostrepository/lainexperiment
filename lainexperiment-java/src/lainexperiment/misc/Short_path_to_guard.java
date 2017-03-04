/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
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
 */

package lainexperiment.misc;

import static java.lang.Math.min;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortPathToGuard {

    static int[][] A;
    static int B = -2, G = -1, X = Integer.MAX_VALUE;
    
    public static void bfs(int[][] grid, Point s)
    {
        int[][] M = new int[grid.length][];
        for (int i = 0; i < M.length; i++) {
            M[i] = grid[i].clone();
        }
        Queue<Point> q = new LinkedList<>();
        q.add(s);
        M[s.y][s.x] = B;
        int c = 0;
        while (!q.isEmpty()) {
            Queue<Point> nq = new LinkedList<>();
            while (!q.isEmpty()) {
                Point p = q.poll();
                grid[p.y][p.x] = min(grid[p.y][p.x], c);
                if (p.x > 0 && M[p.y][p.x - 1] > 0) {
                    M[p.y][p.x - 1] = B;
                    nq.add(new Point(p.x - 1, p.y));
                }
                if (p.y > 0 && M[p.y - 1][p.x] > 0) {
                    nq.add(new Point(p.x, p.y - 1));
                    M[p.y - 1][p.x] = B;
                }
                if (p.x < grid[0].length - 1 && M[p.y][p.x + 1] > 0) {
                    nq.add(new Point(p.x + 1, p.y));
                    M[p.y][p.x + 1] = B;
                }
                if (p.y < grid.length - 1 && M[p.y + 1][p.x] > 0) {
                    nq.add(new Point(p.x, p.y + 1));
                    M[p.y + 1][p.x] = B;
                }
            }
            q = nq;
            c++;
        }
    }
    
    static void solve() {
        for (int y = 0; y < A.length; y++)
        {
            for (int x = 0; x < A[0].length; x++)
            {
                if (A[y][x] == G)
                    bfs(A, new Point(x, y));
            }
        }
        Arrays.stream(A).map(Arrays::toString)
            .map(s -> s.replace("" + B, "B").replace("" + G, "G").replace("" + X, "X"))
            .forEach(System.out::println);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        A = new int[][]{
            {X, X, X, X, X, X},
            {B, G, G, X, X, X},
            {B, X, G, X, X, X},
        };
        solve();
        
        A = new int[][]{
            {X, X, X, X, X},
            {X, B, B, B, X},
            {X, B, G, B, X},
            {X, B, B, B, X},
            {X, X, X, X, X},
        };
        solve();
        
        A = new int[][]{
            {X, X, X, X, X},
            {X, B, B, B, X},
            {X, B, G, B, X},
            {X, B, X, B, X},
            {X, X, X, X, X},
        };
        solve();
    }

}
