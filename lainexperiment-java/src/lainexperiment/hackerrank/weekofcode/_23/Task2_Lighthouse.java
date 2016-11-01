/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/09/2016
 * 
 * Hacker rank
 * Week of code 23
 * Problem: Lighthouse
 * Status: accepted
 * 
 * CubeCraft is a game consisting entirely of cubes (or 
 * voxels) having a side length equal to 1, and each voxel's 
 * vertices have integer coordinates. After playing this 
 * game for a while, Jessie is eager to create something 
 * impressive so she decides to build a lighthouse.
 * First, Jessie decides she wants the lighthouse to have 
 * a round base; because there is no such thing as a perfect 
 * circle in a cubic world, she defines a circle with integer 
 * radius r to be the set of all cubes having centers with a 
 * Euclidean distance to the center of the circle (which 
 * coincides with the center of some cube) <= r. Because r is 
 * the Euclidean distance between the center points of two cubes, 
 * the value of r for a single cube is 0.
 * Next, she chooses an n x n grid for the base of the lighthouse. 
 * This presents some difficulty for Jessie because there are 
 * landscape features (e.g., rocks, trees, etc.) in the grid. 
 * She doesn't want to change the landscape and cannot build 
 * over it, so she must find the maximum radius of any circle 
 * she can place inside the grid's free space (i.e., where no 
 * landscape obstructions are contained within the confines of 
 * the circle). Note that circle have to be placed completely 
 * inside the grid, i.e. there should be no points with euclidean 
 * distance to the center <= r outside the grid. 
 * Given n and the landscape features of Jessie's grid, find and 
 * print the maximum possible value of r.
 * 
 * Input Format
 * 
 * The first line contains an integer, n, denoting the side 
 * dimensions of the grid where Jessie wants to build the 
 * lighthouse.
 * Each line i of the n subsequent lines contains a string of n
 * characters describing the landscape features of row i in the grid. 
 * A . indicates that (i, j, 1) is empty, and a * indicates that 
 * it's obstructed. 
 * 
 * Output Format
 * 
 * Print the value of r denoting the maximum integer radius of the 
 * circle Jessie can place inside the grid's free space (recall that 
 * r may be 0).
 * 
 * 
 * Sample Input
 * 
9
*********
****.****
**.....**
**.....**
*.......*
**.....**
**.....**
****.****
*********
 *
 * Sample Output
 * 
3
 *
 */

package lainexperiment.hackerrank.weekofcode._23;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2_Lighthouse {

    static char[][] A;
    static char E = '.';

    static int dist(Point p, int x, int y) {
        if (p.x == x) return abs(p.y - y);
        if (p.y == y) return abs(p.x - x);
        return (int)ceil(p.distance(x, y));
    }

    static int bfs(Point p, int radius) {
        int d = Integer.MAX_VALUE;
        for (int x = p.x - radius; x <= p.x + radius; x++) {
            if (A[p.y - radius][x] != E)
                d = min(d, dist(p, x, p.y - radius));
            if (A[p.y + radius][x] != E)
                d = min(d, dist(p, x, p.y + radius));
        }
        for (int y = p.y - radius; y <= p.y + radius; y++) {
            if (A[y][p.x - radius] != E)
                d = min(d, dist(p, p.x - radius, y));
            if (A[y][p.x + radius] != E)
                d = min(d, dist(p, p.x + radius, y));
        }
        return d;
    }

    static int maxRadius(Point p) {
        if (A[p.y][p.x] != E)
            return 0;
        int m = min(A.length, A[0].length);
        int rad = Integer.MAX_VALUE;
        int r = 1;
        for (; r < m && r != rad; ++r) {
            if (p.x - r < 0 || p.x + r >= A[0].length)
                break;
            if (p.y - r < 0 || p.y + r >= A.length)
                break;
            rad = min(rad, bfs(p, r));
        }
        return min(r, rad) - 1;
    }

    static void solve() {
        int m = 0;
        for (int y = 0; y < A.length; y++) {
            for (int x = 0; x < A[0].length; x++) {
                m = max(m, maxRadius(new Point(x, y)));
            }
        }
        System.out.println(m);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task2_Lighthouse.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task2_Lighthouse.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            A = new char[n][n];
            scanner.nextLine();
            setAll(A, i -> A[i] = 
                   scanner.nextLine().toCharArray());
            solve();
        }
        scanner.close();
    }

}
