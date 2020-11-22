/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._34;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * <pre>{@code
 * 
 * Date: 30/07/2017
 * 
 * Hacker rank
 * Week of code 34
 * Problem: Maximum Gcd and Sum
 * Status: timeout
 * 
 * You are given two arrays A and B containing n elements each. 
 * Choose a pair of elements x, y such that:
 * 
 * - x belongs to array A.
 * - y belongs to array B.
 * - gcd(x, y) is the maximum of all pairs x, y.
 * 
 * If there is more than one such pair x, y having maximum 
 * gcd, then choose the one with maximum sum. Print the sum 
 * of elements of this maximum-sum pair.
 * 
 * Input
 * 
 * The first line of the input contains n denoting the total 
 * number of elements of arrays A and B. Next line contains 
 * n space separated positive integers denoting the elements 
 * of array A. Next line contains n space separated positive 
 * integers denoting the elements of array B.
 * 
 * Output
 * 
 * From all the pairs having maximum gcd, print the sum of 
 * one that has the maximum sum. 
 * 
 * Sample Input
 * 
5
3 1 4 2 8
5 2 12 8 3
 *
 * Sample Output
 * 
16
 *
 * }</pre>
 */
public class Task2_Maximum_Gcd_and_Sum {
    
    static int[] A, B;
    static int[] p = new int[2];
    
    static boolean isDev(int[] a, int s, int d, int i) {
        boolean isDev = range(s, a.length)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .filter(j -> a[j] % d == 0)
            .peek(j -> p[i] = a[j])
            .findFirst()
            .isPresent();
        return isDev;
    }
    
    static boolean isGcd(int gcd) {
        int sa = binarySearch(A, gcd);
        sa = sa < 0? abs(sa) - 1: sa;
        int sb = binarySearch(B, gcd);
        sb = sb < 0? abs(sb) - 1: sb;
        return isDev(A, sa, gcd, 0) && isDev(B, sb, gcd, 1);
    }
    
    static void solve() {
        sort(A);
        sort(B);
        int m = min(A[A.length - 1], B[B.length - 1]);
        int sum = rangeClosed(2, m)
            .boxed()
            .sorted(Comparator.reverseOrder())
            //.peek(out::println)
            .filter(Task2_Maximum_Gcd_and_Sum::isGcd)
            .map(g -> p[0] + p[1])
            .findFirst().orElse(A[A.length - 1] + B[B.length - 1]);
        //out.format("%d %d\n", p[0], p[1]);
        out.println(sum);
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = Task2_Maximum_Gcd_and_Sum.class;
        String inputFile = clazz.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(in): 
            new Scanner(Task2_Maximum_Gcd_and_Sum.class.getResourceAsStream(inputFile));
        scanner.nextLine();
        A = Pattern.compile(" ").splitAsStream(scanner.nextLine())
                .mapToInt(Integer::parseInt)
                .toArray();
        B = Pattern.compile(" ").splitAsStream(scanner.nextLine())
                .mapToInt(Integer::parseInt)
                .toArray();
        solve();
        scanner.close();
    }
    
}
