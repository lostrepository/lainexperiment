/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 05/12/2016
 * 
 * Hacker rank
 * Week of code 26
 * Problem: Twins
 * Status: accepted
 * 
 * Lia is fascinated by anything she considers to be a twin. 
 * She calls a pairs of positive integers, i and j, twins if:
 * 
 * - They are both prime. A prime number is an integer greater 
 * 1 than that has no positive divisors other than 1 and itself.
 * 
 * - Their absolute difference is exactly equal to 2 
 * (i.e., |j - i|= 2).
 * 
 * Given an inclusive interval of integers from n to m, can you 
 * help Lia find the number of pairs of twins there are in the 
 * interval (i.e. n, m)? Note that pairs (i, j) and (j, i) are 
 * considered to be the same pair.
 * 
 * Input Format
 * 
 * Two space-separated integers describing the respective values of n 
 * and m.
 * 
 * Output Format
 * 
 * Print a single integer denoting the number of pairs of twins.
 * 
 * 
 * Sample Input
 * 
3 13
 *
 * Sample Output
 * 
3
 *
 */

package lainexperiment.hackerrank.weekofcode._26;

import static java.lang.Math.sqrt;
import static java.util.Arrays.fill;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Twins {

    static boolean[] PRIMES;
    
    /**
     * Sieve of Eratosthenes: Generating List of Prime Numbers
     */
    static boolean[] sieve(int n) {
        boolean[] primes = new boolean[n];
        fill(primes, true);
        int len = (int) sqrt(n);
        for (int i = 1; i <= len; i++) {
            for (int j = (i + 1) * 2; j <= n; j += (i + 1)) {
                primes[j - 1] = false;
            }
        }
        return primes;
    }
    
    static void solve(int s, int e) {
        boolean[] sieve = sieve((int) sqrt(e) + 6);
        int[] primes = range(1, sieve.length)
            .filter(i -> sieve[i])
            .map(i -> i + 1)
            .toArray();
        int[] div6 = range(s + 1, e)
            .filter(i -> (i % 6) == 0 || i == 4)
            .toArray();
        int c = 0;
        for (int i = 0; i < div6.length; i++) {
            boolean isTwins = true;
            for (int j = 0; j < primes.length; j++) {
                if (primes[j] >= div6[i]) break;
                int lo = div6[i] - 1;
                int hi = div6[i] + 1;
                if (hi > e) break;
                if (((lo % primes[j]) == 0 && lo != primes[j]) || 
                        ((hi % primes[j]) == 0) && hi != primes[j])
                {
                    isTwins = false;
                    break;
                }
            }
            if (isTwins) c++;
        }
        System.out.println(c);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Twins.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Twins.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int s = scanner.nextInt();
            int e = scanner.nextInt();
            solve(s, e);
        }
        scanner.close();
    }

}
