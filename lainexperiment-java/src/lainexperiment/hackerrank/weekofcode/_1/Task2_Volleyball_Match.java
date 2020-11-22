/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._1;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.in;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.LongStream;

/**
 * <pre>{@code
 * 
 * Date: 17/09/2017
 * 
 * Hacker rank
 * Week of code 1
 * Problem: Volleyball Match
 * Status: correct
 * 
 * Tatyana is a big sports fan and she likes volleyball a lot! 
 * She writes down the final scores of the game after it has 
 * ended in her notebook.
 * 
 * If you are not familiar with the rules of volleyball, here's 
 * a brief:
 * 
 * - 2 teams play in total
 * - During the course of the game, each team gets points, and 
 * thus increases its score by 1.
 * - The initial score is 0 for both teams.
 * 
 * The game ends when:
 * 
 * - One of the teams gets 25 points and another team has < 24 
 * points (strictly less than 24).
 * - If the score ties at 24:24, the teams continue to play until 
 * the absolute difference between the scores is 2.
 * 
 * Given the final score of a game in the format A:B i.e., the 
 * first team has scored A points and the second has scored B 
 * points, can you print the number of different sequences of 
 * getting points by teams that leads to this final score?
 * 
 * Input Format
 * 
 * The first line contains A and the second line contains B.
 * 
 * Output Format
 * 
 * Output the number of different sequences of getting points by 
 * the teams that leads to the final score A : B. Final means that 
 * the game should be over after this score is reached. If the 
 * number is larger than 10^9 + 7, output number modulo 10^9 + 7. 
 * Print 0 if no such volleyball game ends with the given score. 
 * 
 * Sample Input
 * 
3
25
 *
 * Sample Output
 * 
2925
 *
 * }</pre>
 */
public class Task2_Volleyball_Match {
    
    static final int MOD = 1_000_000_007;
    
    /*
     * Multiplicative inversion
     */
    static long mulInv(long a) {
        return BigInteger.valueOf(a)
            .modInverse(BigInteger.valueOf(MOD))
            .longValue();
    }
    
    /*
     * Range 0..10^6.
     * 
     * O(n)
     */
    static long factorial(long s, long e, long acc) {
        if (e < s) return acc;
        if (e <= 1) return 1;
        s = s == 0? 1: s;
        acc = acc == 0? 1: acc;
        long l = LongStream.rangeClosed(s, e)
            .map(i -> i % MOD)
            .reduce((r, i) -> (r * i) % MOD)
            .getAsLong();
        return (l * acc) % MOD;
    }
    
    /* 
     * O(log N)
     */
    static long pow(long n, long p) {
        if (p == 0)
            return 1;
        if (p == 1)
            return n;
        long res;
        if ((p & 1) == 1)
            res = n * pow(n, p - 1) % MOD;
        else
            res = pow(n * n % MOD, p / 2);
        return res == 0? 1: res;
    }
    
    /*
     * Unordered combinations of m things out of n.
     * Range 0..10^6
     */
    static long unorderedCombinations(long n, long m) {
        long[] f = new long[3];
        if (m < n - m) {
            f[1] = factorial(0, m, 0);
            f[2] = factorial(m + 1, n - m, f[1]);
            f[0] = factorial(n - m + 1, n, f[2]);
        } else {
            f[2] = factorial(0, n - m, 0);
            f[1] = factorial(n - m + 1, m, f[2]);
            f[0] = factorial(m + 1, n, f[1]);
        }
        return (f[0] * mulInv(f[1]) % MOD) * mulInv(f[2]) % MOD;
    }
    
    static long solve(long a, long b) {
        long mx = max(a, b);
        long mn = min(a, b);
        if (mx < 25)
            return 0;
        if (mn >= 24 && mx - mn != 2)
            return 0;
        if (mx == 25)
            return unorderedCombinations(mx + mn - 1, mn);
        long c = unorderedCombinations(24 * 2, 24);
        return (c * pow(2, mn - 24)) % MOD;
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = Task2_Volleyball_Match.class;
        String inputFile = clazz.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(in): 
            new Scanner(Task2_Volleyball_Match.class.getResourceAsStream(inputFile));
        while (scanner.hasNextInt())
            System.out.println(solve(scanner.nextLong(), scanner.nextLong()));
        scanner.close();
    }
    
}
