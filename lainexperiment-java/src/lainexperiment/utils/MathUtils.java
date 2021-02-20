/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>Set of math functions.</p>
 */
public class MathUtils {

    public final long MOD = 1_000_000_007L;
    
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
     * @return Arithmetic sum from 1 to n modulo mod
     */
    public static long sum(int n, long mod) {
        return sum(1, n, mod);
    }
    
    /**
     * @return Arithmetic sum from a to b modulo mod
     */
    public static long sum(int a, int b, long mod) {
        return (long) (((b - a + 1) / 2.) * ((a + b) % mod) % mod);
    }

    /**
     * Unordered combinations of m things out of n.
     */
    public static long unorderedCombinations(int n, int m) {
        return factorial(n) / (factorial(n - m) * factorial(m));
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

    /**
     * Return list of factors of n.
     * This list is sorted and may contain duplicate values.
     */
    public static List<Integer> factors(long n) {
        int sqrt = (int) Math.sqrt(n);
        var factors = new ArrayList<Integer>();
        for (int i = 2; i < sqrt; i++) {
            if (n < i) break;
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 1) factors.add((int) n);
        return factors;
    }

    public static long factorial(int n) {
        if (n > 20) throw new RuntimeException("Limit exceeded");
        if (n <= 1) return 1;
        return LongStream.range(1, n + 1)
                .reduce((r, i) -> r * i)
                .getAsLong();
    }

    /**
     * Returns largest integer value after dividing a to b
     */
    public static int ceilDiv(int a, int b) {
        return (a + b - 1) / b;
    }

    @Test
    public void test_factors() {
        Assertions.assertEquals("[3, 3, 5, 7]", factors(315).toString());
        Assertions.assertEquals("[2, 2, 5]", factors(20).toString());
        Assertions.assertEquals("[11]", factors(11).toString());
    }
    
    @Test
    public void test_factorial() {
        Assertions.assertEquals(2432902008176640000L, factorial(20));
        Assertions.assertThrows(RuntimeException.class, () -> factorial(30));
    }
    
    @Test
    public void test_ceilDiv() {
        Assertions.assertEquals(1, ceilDiv(1, 1));
        Assertions.assertEquals(2, ceilDiv(2, 1));
        Assertions.assertEquals(2, ceilDiv(6, 3));
        Assertions.assertEquals(3, ceilDiv(7, 3));
        Assertions.assertEquals(3, ceilDiv(8, 3));
        Assertions.assertEquals(3, ceilDiv(9, 3));
    }
    
    @Test
    public void test_sum() {
        assertEquals(6, sum(3, MOD));
        assertEquals(3, sum(2, MOD));
    }
}
