/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._26;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 04/12/2016
 * 
 * Hacker rank
 * Week of code 26
 * Problem: Satisfactory Pairs
 * Status: timeout
 * 
 * Given a positive integer, n, find and print the number of pairs 
 * of positive integers (a, b) where a < b, that exist such that 
 * the equation x * a + y * b = n (where x and y are positive 
 * integers) has at least one solution.
 * 
 * Input Format
 * 
 * A single positive integer denoting n.
 * 
 * Output Format
 * 
 * Print a single integer denoting the number of such pairs.
 * 
 * 
 * Sample Input
 * 
4
 *
 * Sample Output
 * 
2
 *
 * }</pre>
 */
public class Satisfactory_Pairs {

    static int N;
    
    static void solve() {
        int C = 0;
        for (int b = 1; b < N; b++) {
            l1: for (int a = 1; a < b; a++) {
                for (int x = 1; x <= N / a; x++) {
                    int k = N - a * x;
                    if ((k % b) == 0 && k / b > 0) {
//                        System.out.format("%d*%d + %d*%d\n", a, x, b, k/b);
                        C++;
                        continue l1;
                    }
                }
            }
        }
        System.out.println(C);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Satisfactory_Pairs.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Satisfactory_Pairs.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            solve();
        }
        scanner.close();
    }

}

