/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * <pre>{@code
 * Set of basic algorithms
 * }</pre>
 */
public class Algorithms {

    /**
     * @param func unary function which accepts values in the
     * range [s..e) and returns following codes:
     *  
     *   positive - current number is big - go left
     *   negative or 0 - current value is small so it can be
     *   answer but we try to look for even better - got right
     *  
     *  @return biggest smallest value encountered or -1
     */
    public static long bisection(long s, long e, LongUnaryOperator func) {
        if (s > e) return -1;
        long m = (s + e) / 2;
        long r = func.applyAsLong(m);
        if (r > 0)
            return bisection(s, m - 1, func);
        long res = bisection(m + 1, e, func);
        return res == -1? m: res;
    }

    /**
     * Binary search which supports multiple elements of the same key.
     * Java standard Arrays.binarySearch unfortunately does not support that.
     * @return similar to Arrays.binarySearch except it guarantees to return
     * index of left most key if there are multiple of them
     */
    public static int binarySearch(int[] a, int s, int e, int val) {
        var p = Arrays.binarySearch(a, s, e, val);
        if (p < 0) return p;
        if (s == p) return s;
        var r = binarySearch(a, s, p, val);
        return r < 0? p: r;
    }
    
    /**
     * Merge sort implementation. It uses aux storage and merges everything
     * inplace into input list.
     */
    public static <T extends Comparable<T>> void mergeSort(ArrayList<T> a) {
        if (a.size() < 2) return;
        int m = a.size() / 2;
        ArrayList<T> l = new ArrayList<>(a.subList(0, m));
        mergeSort(l);
        ArrayList<T> r = new ArrayList<>(a.subList(m, a.size()));
        mergeSort(r);
        int li = 0, ri = 0;
        int i = 0;
        while (i < a.size()) {
            if (li == l.size() || ri == r.size())
                break;
            if (l.get(li).compareTo(r.get(ri)) < 0) {
                a.set(i, l.get(li));
                li++;
            } else {
                a.set(i, r.get(ri));
                ri++;
            }
            i++;
        }
        while (li < l.size()) {
            a.set(i++, l.get(li++));
        }
        while (ri < r.size()) {
            a.set(i++, r.get(ri++));
        }
    }
    

    /**
     * Finds local minimum in the range [s, e) based on comparison
     * defined in cmp function.
     * It works with ranges which have only one local minimum. If
     * range has multiple local minimums the result is undefined.
     */
    public static long localMin(long s, long e, Comparator<Long> cmp) {
        //System.out.format("%d-%d\n", s, e);
        if (s == e) return s;
        if (e - s == 1) return s;
        if (e - s == 2) return cmp.compare(s, e - 1) <= 0? s: e - 1;
        if (cmp.compare(s, s + 1) < 0) return s;
        if (cmp.compare(e - 1, e - 2) < 0) return e - 1;
        var m = (s + e) / 2;
        var d = cmp.compare(m, m + 1);
        if (d == 0) {
            var l = localMin(s, m + 1, cmp);
            var r = localMin(m, e, cmp);
            return cmp.compare(l, r) <= 0? l: r;
        }
        if (d < 0) {
            return localMin(s, m + 1, cmp);
        }
        return localMin(m, e, cmp);
    }
    
    
    /*
     * 
     * 
     * TESTS
     * 
     * 
     */

    static Stream<int[]> testDataBisection() {
        return Stream.of(
            new int[]{1,2,3,4,5,6},
            new int[]{1,2,3,4,5,6,7},
            new int[]{1,2,3,4,5,6,7,8},
            new int[]{1},
            new int[]{1,2});
    }

    @ParameterizedTest
    @MethodSource("testDataBisection")
    void testBisection(int[] a) {
        for (int i = 0; i < a.length; i++) {
            final int v = a[i];
            assertEquals(i, bisection(0, a.length - 1, k -> a[(int) k] - v));
        }
    }

    @Test
    void testBisection2() {
        int[] a = new int[]{1,1,3,4,5,10};
        int[] v = new int[1];
        v[0] = 3;
        assertEquals(2, bisection(0, a.length, k -> a[(int) k] - v[0]));
        v[0] = 7;
        assertEquals(4, bisection(4, a.length, k -> a[(int) k] - v[0]));
        v[0] = 0;
        assertEquals(-1, bisection(4, a.length, k -> a[(int) k] - v[0]));
    }

    static Stream<List<List>> testDataMergeSort() {
        return Stream.of(
            List.of(List.of(5, 2, 3, 4, 1), List.of(1, 2, 3, 4, 5)),
            List.of(List.of(3, 2, 1), List.of(1, 2, 3)),
            List.of(List.of(5, 4, 6, 7), List.of(4, 5, 6, 7)),
            List.of(List.of(2, 1, 4, 3), List.of(1, 2, 3, 4)),
            List.of(List.of(2, 1, 3, 1, 2), List.of(1, 1, 2, 2, 3)),
            List.of(List.of(8, 1, 2, 3, 4), List.of(1, 2, 3, 4, 8)),
            List.of(List.of(1, 3, 5, 7, 2, 4, 6), List.of(1, 2, 3, 4, 5, 6, 7)));
    }

    @ParameterizedTest
    @MethodSource("testDataMergeSort")
    void testMergeSort(List<List> a) {
        for (int i = 0; i < a.size(); i++) {
            ArrayList<Integer> l = new ArrayList<Integer>(a.get(0));
            mergeSort(l);
            assertEquals(a.get(1), l);
        }
    }
    
    private Comparator<Long> cmp(int[] a) {
        return (n1, n2) ->
            a[n1.intValue()] - a[n2.intValue()];
    }
    
    @Test
    public void test_localMin() {
        int[] a = new int[] {1,1,1,1,1,1,1,1,1};
        for (int i = 6; i < a.length; i++) {
            a[i] = 0;
            assertEquals(i, localMin(0, a.length, cmp(a)));
            a[i] = 1;
        }
        assertEquals(1, localMin(0, 11, cmp(new int[] {3, 2, 2, 2, 2, 2, 2, 3,3,3,3})));
        assertEquals(2, localMin(0, 3, cmp(new int[] {4, 1, 0})));
        assertEquals(1, localMin(0, 3, cmp(new int[] {4, -1, 0})));
        assertEquals(0, localMin(0, 5, cmp(new int[] {-2, -1, 0, 1, 1})));
        assertEquals(1, localMin(0, 5, cmp(new int[] {4, -1, 0, 1, 1})));
        assertEquals(2, localMin(0, 5, cmp(new int[] {4, 3, 0, 1, 1})));
        assertEquals(3, localMin(0, 5, cmp(new int[] {4, 3, 3, 0, 1})));
        assertEquals(4, localMin(0, 5, cmp(new int[] {4, 3, 3, 3, 1})));
        assertEquals(2, localMin(0, 5, cmp(new int[] {4, 3, 2, 3, 4})));
        assertEquals(0, localMin(0, 5, cmp(new int[] {0, 1, 2, 3, 4})));
        
        assertEquals(0, localMin(0, 3, cmp(new int[] {4, 1, 0}).reversed()));
        assertEquals(1, localMin(0, 3, cmp(new int[] {-1, 4, 0}).reversed()));
        assertEquals(2, localMin(0, 5, cmp(new int[] {-2, -1, 4, 1, 1}).reversed()));
        assertEquals(1, localMin(0, 5, cmp(new int[] {-1, 4, 1, 0, -1}).reversed()));
        assertEquals(2, localMin(0, 5, cmp(new int[] {3, 3, 4, 1, 1}).reversed()));
        assertEquals(3, localMin(0, 5, cmp(new int[] {2, 3, 3, 4, 1}).reversed()));
        assertEquals(0, localMin(0, 5, cmp(new int[] {5, 3, 3, 3, 1}).reversed()));
        assertEquals(3, localMin(0, 5, cmp(new int[] {0, 1, 2, 3, 3}).reversed()));
    }
    
    @Test
    public void test_binarySearch() {
        var a = new int[] {1,1,1,1,3,3,3,5,5,7,7,7,7,9,12,12,12,12};
        var expected = new int[] {0,0,0,0,4,4,4,7,7,9,9,9,9,13,14,14,14,14};
        for (int i = 0; i < a.length; i++) {
            assertEquals(expected[i], binarySearch(a, 0, a.length, a[i]));
        }
        assertEquals(-1, binarySearch(a, 0, a.length, 0));
        assertEquals(-5, binarySearch(a, 0, a.length, 2));
        assertEquals(-8, binarySearch(a, 0, a.length, 4));
        assertEquals(-14, binarySearch(a, 0, a.length, 8));
        assertEquals(-19, binarySearch(a, 0, a.length, 15));
    }
}
