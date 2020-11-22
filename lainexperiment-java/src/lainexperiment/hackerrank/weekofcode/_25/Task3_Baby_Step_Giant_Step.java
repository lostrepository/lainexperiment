/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._25;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 01/11/2016
 * 
 * Hacker rank
 * Week of code 25
 * Problem: Baby Step, Giant Step
 * Status: accepted
 * 
 * You are standing at point (0, 0) on an infinite plane.
 * In one step, you can move from some point (xf, yf) to 
 * any point (xt, yt) as long as the Euclidean distance, 
 * sqrt((xf - xt)^2 + (yf - yt)^2), between the two points 
 * is either a or b. In other words, each step you take must 
 * be exactly a or b in length.
 * 
 * You are given q queries in the form of a, b, and d. For 
 * each query, print the minimum number of steps it takes to 
 * get from point (0, 0) to point (d, 0) on a new line.
 * 
 * Input Format
 * 
 * The first line contains an integer, q, denoting the number 
 * of queries you must process.
 * Each of the q subsequent lines contains three space-separated 
 * integers describing the respective values of a, b, and d for 
 * a query. 
 * 
 * Output Format
 * 
 * For each query, print the minimum number of steps necessary 
 * to get to point (d, 0) on a new line.
 * 
 * 
 * Sample Input
 * 
3
2 3 1
1 2 0
3 4 11
 *
 * Sample Output
 * 
2
0
3
 *
 * }</pre>
 */
public class Task3_Baby_Step_Giant_Step {

    static long A, B, D;
    
    static long solve() {
        if (D == 0) return 0;
        if (D < min(A, B))
            return 2;
        if (A > D)
            return B == D? 1: 2;
        if (B > D)
            return A == D? 1: 2;
        return (long) min(ceil(D / (double)A), ceil(D / (double)B));
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task3_Baby_Step_Giant_Step.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task3_Baby_Step_Giant_Step.class.getResourceAsStream(inputFile));
        int q = scanner.nextInt();
        for (int i = 0; i < q; i++) {
            A = scanner.nextLong();
            B = scanner.nextLong();
            D = scanner.nextLong();
            System.out.println(solve());
        }
        scanner.close();
    }

}
