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
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * <p>Set of math functions.</p>
 */
public class MathUtils {

    public static final long MOD = 1_000_000_007L;
    
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
     * @return Arithmetic sequence sum from 1 to n modulo mod
     */
    public static long arithmeticSum(long n, long mod) {
        long res = n * (n + 1) / 2 % mod;
        if (res < 0) throw new RuntimeException(String.format("Negative sum %d", res));
        return res;
    }
    
    /**
     * @return Arithmetic sequence sum from a to b modulo mod
     */
    public static long arithmeticSum(long a, long b, long mod) {
        if (a > b) throw new RuntimeException(String.format("%d > %d", a, b));
        long v = (b - a + 1) * (a + b);
        // in case overflow
        if (v < 0) throw new RuntimeException(String.format("Negative sum %d", v));
        return v / 2 % mod;
    }

    /**
     * Adds two numbers mod {@link MOD}
     */
    public static long sumMod(long a, long b) {
        return (a + b) % MOD;
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
    public static long ceilDiv(long l, long b) {
        return (l + b - 1) / b;
    }

    /**
     * Return number closest to v or v itself if it is present.
     * If there are two closest numbers return one which is smaller.
     * For example v = 2, a = [3,2,1] closest number is 1
     */
    public static int closest(int v, int...a) {
        var r = a[0];
        for (int i = 0; i < a.length; i++) {
            int d1 = Math.abs(a[i] - v);
            int d2 = Math.abs(r - v);
            if (d1  == d2) {
                r = Math.min(r, a[i]);
            } else if (d1 < d2) {
                r = a[i];
            }
        }
        return r;
    }

    /**
     * Return number closest to v or v itself if it is present.
     * If there are two closest numbers return one which is smaller.
     */
    public static double closest(double v, double...a) {
        var r = a[0];
        for (int i = 0; i < a.length; i++) {
            var d1 = Math.abs(a[i] - v);
            var d2 = Math.abs(r - v);
            if (d1  == d2) {
                r = Math.min(r, a[i]);
            } else if (d1 < d2) {
                r = a[i];
            }
        }
        return r;
    }

    /**
     * @return minimum number
     */
    public static int min(int... a) {
        return Arrays.stream(a).min().getAsInt();
    }

    /*
     * 
     * 
     * TESTS
     * 
     * 
     */

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
    public void test_arithmeticSum() {
        assertEquals(19, arithmeticSum(9, 10, MOD));
        assertEquals(30, arithmeticSum(9, 11, MOD));
        assertEquals(18, arithmeticSum(18, 18, MOD));
        
        assertEquals(36, arithmeticSum(8, MOD));
        assertEquals(6, arithmeticSum(3, MOD));
        assertEquals(3, arithmeticSum(2, MOD));
        assertEquals(0, arithmeticSum(8, 3));
        assertEquals(1, arithmeticSum(8, 5));
        assertEquals(4, arithmeticSum(8, 8));
    }
    
    @Test
    public void test_closest() {
        Assertions.assertEquals(1, closest(2, 3, 1));
        Assertions.assertEquals(4, closest(4, 3, 1, 6, 4, 8));
    }
    
    @Test
    public void test_min() {
        Assertions.assertEquals(-11, min(-11, 2, 3, 1));
    }
}
