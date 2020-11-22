/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * Date: 28/06/2015
 * 
 * Problem
 * 
 * You are given set of people and you need to find a judge - person 
 * to whom everybody trust and who trust no one.
 *
 * Input Format
 * 
 * The first line contains two space-separated positive integers, n
 * (num of people) and m. 
 * Each i line of the subsequent m lines contains the person and set
 * of whom he trust.
 * 
 * Input
 * 
5 4
1 0 2 3
2 0 1 3
3 0
4 0 3
 * 
 * Output
 * 
0
 * 
 * }</pre>
 */
public class Judge {

    static boolean M[][];
    
    static int solve() {
        int c = 0;
        for (int i = 1; i < M.length; i++) {
            if (M[i][c]) continue;
            c = i;
        }
        for (int i = 0; i < M.length; i++) {
            if (i == c) continue;
            if (M[c][i] || !M[i][c]) return -1;
        }
        return c;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Judge.class.getResourceAsStream("Judge.in"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        M = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            String[] a = scanner.nextLine().split(" ");
            int k = Integer.parseInt(a[0]);
            for (int j = 1; j < a.length; j++) {
                M[k][Integer.parseInt(a[j])] = true;
            }
        }
        System.out.println(solve());
        scanner.close();
    }

}
