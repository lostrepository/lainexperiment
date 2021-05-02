/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;

/**
 * <p>Set of functions to work with bits.</p>
 * 
 * <p>Indexing starts with 0 and goes from right to left.</p>
 */
public class BitUtils {

    public static boolean isSet(long bitset, int i) {
        return (bitset & (1 << i)) > 0;
    }

    public static long set(long bitset, int i) {
        return bitset | (1 << i);
    }

    public static int set(int bitset, int i) {
        return (int) set((long)bitset, i);
    }

    public static long unset(long bitset, int i) {
        return bitset & ~(1 << i);
    }

    public static int getBit(long bitset, int i) {
        return isSet(bitset, i)? 1: 0;
    }
    
    public static long setBit(long bitset, int i, int val) {
        if (val == 1)
            return set(bitset, i);
        else
            return unset(bitset, i);
    }

    public static int setBit(int bitset, int i, int val) {
        return (int)setBit((long)bitset, i, val);
    }

    public static int length(long bitset) {
        if (bitset < 2) return (int) bitset;
        return (int) (Math.log(bitset) / Math.log(2)) + 1;
    }

    /**
     * <p>Finds range of numbers in sorted array which first len leading bits
     * match first len leading bits from n.</p>
     * <p>For example for array [8, 12, 13, 28] or binary:</p> 
     * 
     * <pre>00000000000000000000000000001000
     * 00000000000000000000000000001100
     * 00000000000000000000000000001101
     * 00000000000000000000000000011100</pre>
     * 
     * <p>range [0, 3) have numbers which prefix match n = 14 (binary 00000000000000000000000000001110)
     * and len = 29</p>
     * 
     * @param r range where to search [s, e)
     * @return range [s, e)
     */
    public static Optional<Ival> prefixSearch(int[] a, Ival r, int n, int len) {
        len = 32 - len;
        int p = (-1 << len);
        int v = n & p;
        int lo = Arrays.binarySearch(a, (int)r.s, (int)r.e, v);
        if (lo < 0)
            lo = 0 - lo - 1;
        if (lo >= r.e) return Optional.empty();
        if ((a[lo] >> len) != (n >> len))
            return Optional.empty();
        v = v | ~p;
        int hi = Arrays.binarySearch(a, (int)r.s, (int)r.e, v);
        if (hi < 0)
            hi = 0 - hi - 1;
        else
            hi++;
        if (hi == lo) hi++;
        return Optional.of(new Ival(lo, hi));
    }

    @Test
    public void test_set() {
        assertEquals(0xff, set(0xfe, 0));
        assertEquals(7, set(3, 2));
    }

    @Test
    public void test_unset() {
        assertEquals(0xfe, unset(0xff, 0));
        assertEquals(3, unset(7, 2));
    }

    @Test
    public void test_length() {
        assertEquals(0, length(0));
        assertEquals(1, length(1));
        assertEquals(2, length(2));
        assertEquals(2, length(3));
        assertEquals(3, length(4));
        assertEquals(4, length(8));
        assertEquals(4, length(15));
    }

    @Test
    public void test_bit() {
        assertEquals(1, getBit(3, 0));
        assertEquals(1, getBit(3, 1));
        assertEquals(0, getBit(3, 2));

        assertEquals(7, setBit(3, 2, 1));
    }

    @Test
    public void test_prefixSearch() {
        int[] a = null;
        a = new int[]{3, 4, 5, 6, 7, 8, 9};
        assertEquals("1-5", prefixSearch(a, new Ival(0, a.length), 4, 30).get().toString());
        
        a = new int[]{4, 5, 6, 7, 8, 9};
        assertEquals("0-4", prefixSearch(a, new Ival(0, a.length), 4, 30).get().toString());

        a = new int[]{4, 5, 6, 7, 8};
        assertEquals("0-4", prefixSearch(a, new Ival(0, a.length), 4, 30).get().toString());

        a = new int[]{4, 5, 6, 7};
        assertEquals("0-4", prefixSearch(a, new Ival(0, a.length), 4, 30).get().toString());

        assertTrue(prefixSearch(a, new Ival(0, a.length), 0, 32).isEmpty());

        a = new int[]{0, 4, 5, 6, 7};
        assertEquals("0-1", prefixSearch(a, new Ival(0, a.length), 0, 32).get().toString());

        a = new int[]{4};
        assertEquals("0-1", prefixSearch(a, new Ival(0, a.length), 4, 32).get().toString());

        a = new int[]{5};
        assertEquals("0-1", prefixSearch(a, new Ival(0, a.length), 4, 31).get().toString());

        a = new int[]{5, 8};
        assertEquals("0-1", prefixSearch(a, new Ival(0, a.length), 6, 30).get().toString());

        a = new int[]{7, 8, 9, 15};
        assertEquals("1-3", prefixSearch(a, new Ival(0, a.length), 8, 30).get().toString());

        a = new int[]{7, 8, 9, 11, 15};
        assertEquals("1-4", prefixSearch(a, new Ival(0, a.length), 8, 30).get().toString());

        a = new int[]{7, 8, 9, 11, 15};
        assertEquals("1-4", prefixSearch(a, new Ival(0, 4), 8, 30).get().toString());

        a = new int[]{7, 8, 9, 11, 15};
        assertEquals("1-3", prefixSearch(a, new Ival(0, 3), 8, 30).get().toString());

        a = new int[]{7, 8, 9, 11, 15};
        assertEquals("2-3", prefixSearch(a, new Ival(2, 3), 8, 30).get().toString());

        a = new int[]{7, 8, 9, 11, 15};
        assertTrue(prefixSearch(a, new Ival(0, a.length), 16, 28).isEmpty());
        
        a = new int[]{7, 8, 9, 11, 15};
        assertTrue(prefixSearch(a, new Ival(2, a.length), 16, 28).isEmpty());

        a = new int[]{11, 15, 16, 32, 33, 45};
        assertTrue(prefixSearch(a, new Ival(3, a.length), 16, 28).isEmpty());

        a = new int[]{11, 15, 16, 32, 33, 45};
        assertEquals("2-3", prefixSearch(a, new Ival(2, a.length), 16, 28).get().toString());
        
        a = new int[]{8, 12, 13, 28};
        assertEquals("0-3", prefixSearch(a, new Ival(0, a.length), 14, 29).get().toString());
    }
}
