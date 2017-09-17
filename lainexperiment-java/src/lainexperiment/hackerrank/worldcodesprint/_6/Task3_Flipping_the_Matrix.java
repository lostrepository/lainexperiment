/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 27/08/2016
 * 
 * Hacker rank
 * World CodeSprint #6
 * Problem: Flipping the Matrix
 * Status: accepted
 * 
 * Sean invented a game involving a 2n x 2n matrix where each cell of 
 * the matrix contains an integer. He can reverse any of its rows or 
 * columns any number of times, and the goal of the game is to maximize 
 * the sum of the elements in the n x n submatrix located in the upper-left 
 * corner of the 2n x 2n matrix (i.e., its upper-left quadrant).
 * Given the initial configurations for q matrices, help Sean reverse the 
 * rows and columns of each matrix in the best possible way so that the 
 * sum of the elements in the matrix's upper-left quadrant is maximal. 
 * For each matrix, print the maximized sum on a new line.
 * 
 * Input Format
 * 
 * The first line contains an integer, q, denoting the number of queries. 
 * The subsequent lines describe each of the q queries in the following 
 * format:
 * - The first line of each query contains an integer, n.
 * - Each line i the 2n subsequent lines contains 2n space-separated integers 
 * describing the respective values of row i in the matrix.
 * 
 * Output Format
 * 
 * You must print q lines of output. For each query (i.e., matrix), print the 
 * maximum possible sum of the elements in the matrix's upper-left quadrant.
 * 
 * 
 * Sample Input
 * 
1
2
112 42 83 119
56 125 56 49
15 78 101 43
62 98 114 108
 *
 * Sample Output
 * 
414
 *
 *
 */

package lainexperiment.hackerrank.worldcodesprint._6;

import static java.lang.Math.max;
import static java.util.Arrays.setAll;
import static java.util.stream.IntStream.generate;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task3_Flipping_the_Matrix {

    static int[][] M;
    
    static void solve() {
        int ss = M.length / 2;
        int s = M.length - 1;
        int sum = 0;
        for (int row = 0; row < ss; row++) {
            for (int col = 0; col < ss; col++) {
                int m = M[row][col];
                m = max(m, M[s - row][col]);
                m = max(m, M[s - row][s - col]);
                m = max(m, M[row][s - col]);
                sum += m;
            }
        }
        System.out.println(sum);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task3_Flipping_the_Matrix.class;
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(clazz.getResourceAsStream(clazz.getSimpleName() + ".in"));
        while (scanner.hasNext()) {
            int q = scanner.nextInt();
            for (int i = 0; i < q; i++) {
                int n = scanner.nextInt() * 2;
                M = new int[n][n];
                setAll(M, j -> M[j] = 
                        generate(scanner::nextInt).limit(n).toArray());
                solve();
            }
        }
        scanner.close();
    }

}
