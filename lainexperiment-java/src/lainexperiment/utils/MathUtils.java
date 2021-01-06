/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * <p>Set of mahth functions.</p>
 */
public class MathUtils {

    /*
     * Multiplicative inversion
     */
    private static long mulInv(long a, long mod) {
        return BigInteger.valueOf(a)
            .modInverse(BigInteger.valueOf(mod))
            .longValue();
    }
    
    /**
     * Calculates a product of all numbers in range [s, e] % mod
     * where s and e should be greater than 0.
     * 
     * O(n)
     */
    public static long product(long s, long e, long mod) {
        if (e < s) throw new RuntimeException("Invalid range:" + s + ", " + e);
        if (s == 0 || e == 0) throw new RuntimeException("Invalid range:" + s + ", " + e);
        long l = LongStream.rangeClosed(s, e)
            .map(i -> i % mod)
            .reduce((r, i) -> (r * i) % mod)
            .getAsLong();
        return l % mod;
    }

    /**
     * Unordered combinations of m things out of n.
     */
    public static long unorderedCombinations(long n, long m, long mod) {
        if (m == 0) return 0;
        if (n == m) return 1;
        long[] f = new long[3];
        if (m < n - m) {
            f[1] = product(1, m, mod) % mod;
            f[2] = (product(m + 1, n - m, mod) * f[1]) % mod;
            f[0] = (product(n - m + 1, n, mod) * f[2]) % mod;
        } else {
            f[2] = product(1, n - m, mod) % mod;
            var s = n - m + 1;
            f[1] = (((m < s)? 1: product(s, m, mod)) * f[2]) % mod;
            f[0] = (product(m + 1, n, mod) * f[1]) % mod;
        }
        return (f[0] * mulInv(f[1], mod) % mod) * mulInv(f[2], mod) % mod;
    }
}
