/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 06/12/2016
 * 
 * Hacker rank
 * Week of code 26
 * Problem: Best Divisor
 * Status: accepted
 * 
 * Kristen loves playing with and comparing numbers. She 
 * thinks that if she takes two different positive numbers, 
 * the one whose digits sum to a larger number is better 
 * than the other. If the sum of digits is equal for both 
 * numbers, then she thinks the smaller number is better. 
 * For example, Kristen thinks that 13 is better than 31
 * and that 12 is better than 11.
 * Given an integer, n, can you find the divisor of n that 
 * Kristin will consider to be the best?
 * 
 * Input Format
 * 
 * A single integer denoting n.
 * 
 * Output Format
 * 
 * Print an integer denoting the best divisor of n. 
 * 
 * 
 * Sample Input
 * 
12
 *
 * Sample Output
 * 
6
 *
 */

package lainexperiment.hackerrank.weekofcode._26;

import static java.lang.Math.min;
import static java.util.stream.IntStream.rangeClosed;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Best_Divisor {

    static int N;
    
    static class Pair implements Comparable<Pair> {
        int s;
        int n;
        Pair(int n) {
            this.n = n;
            s = String.valueOf(n)
                .chars()
                .map(ch -> ch - '0')
                .sum();
        }
        @Override
        public int compareTo(Pair o) {
            return s != o.s? s - o.s: n - o.n;
        }
        @Override
        public String toString() {
            return String.format("%s %d", s, n);
        }
    }
    
    static int solve() {
        Pair[] l = rangeClosed(1, N)
            .filter(i -> (N % i) == 0)
            .mapToObj(i -> new Pair(i))
            .sorted()
            .toArray(s -> new Pair[s]);
        int cand = l[l.length - 1].n;
        for (int i = l.length - 1; i >= 1; i--) {
            if (l[i - 1].s != l[i].s)
                return cand;
            cand = min(l[i - 1].n, cand);
        }
        return l[0].n;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Best_Divisor.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Best_Divisor.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            System.out.println(solve());
        }
        scanner.close();
    }

}
