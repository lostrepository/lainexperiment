/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 19/09/2016
 * 
 * Hacker rank
 * Week of code 23
 * Problem: Unexpected Problem
 * Status: accepted
 * 
 * Marc is young lover of strings who wants your help with the 
 * following problem. Given a string, s, of lowercase English 
 * alphabetic letters and an integer, m, calculate the number 
 * of string t's such that:
 * 
 * - Each t consists of lowercase English alphabetic letters
 * - 1 <= length(t) <= m
 * - s * t = t * s, meaning their concatenation is commutative
 * 
 * Print the number of string t's modulo 10^9 + 7.
 * 
 * Input Format
 * 
 * The first line contains a string denoting s.
 * The second line contains an integer denoting m.
 * 
 * Output Format
 * 
 * Print the number of string t's satisfying the conditions 
 * above, modulo 10^9 + 7.
 * 
 * 
 * Sample Input
 * 
abc
6
 *
 * Sample Output
 * 
2
 *
 */

package lainexperiment.hackerrank.weekofcode._23;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task4_Unexpected_Problem {

    static String S;
    static long M, MOD = 1_000_000_007;

    static int iskstring(char[] a) {
        int l = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] == a[l])
                l++;
            else
                l = 0;
        }
        if (l == 0)
            return 0;
        if (l == a.length - 1)
            return 0;
        if (l > a.length - l)
            l = a.length - l;
        if (a.length % l > 0)
            return 0;
        for (int i = 0, k = 0; i < a.length; i++) {
            if (a[i] != a[k])
                return 0;
            k++;
            if (k == l)
                k = 0;
        }
        return l;
    }

    static void solve() {
        int uniq = S.chars()
            .boxed()
            .collect(Collectors
                     .groupingBy(Integer::intValue))
            .size();
        long r = 0;
        if (uniq == 1) {
            r = M % MOD;
        } else {
            int k = iskstring(S.toCharArray());
            r = M / (k == 0? S.length(): k) % MOD;
        }
        System.out.println(r);
    }


    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task4_Unexpected_Problem.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task4_Unexpected_Problem.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            S = scanner.next();
            M = scanner.nextInt();
            solve();
        }
        scanner.close();
    }

}
