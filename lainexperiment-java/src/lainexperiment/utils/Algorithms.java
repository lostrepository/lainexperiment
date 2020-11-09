/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Set of basic algorithms
 */
public class Algorithms {

    /**
     * @param func unary function which accepts values in the
     * range s..e and returns following codes:
     *  
     *   negative - current value is small
     *   0 or positive - current value is big
     *  
     *  @return latest big value encountered.
     */
    static long bisection(long s, long e, LongUnaryOperator oper) {
        if (e - s < 0) return -1;
        long m = (s + e) / 2;
        long r = oper.applyAsLong(m);
        if (r < 0)
            return bisection(m + 1, e, oper);
        long res = bisection(s, m - 1, oper);
        return res == -1? m: res;
    }
    
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
            assertEquals(i, bisection(0, a.length, k -> a[(int) k] - v));
        }
    }

    /**
     * Merge sort implementation. It uses aux storage and merges everything
     * inplace into input list.
     */
    static <T extends Comparable<T>> void mergeSort(ArrayList<T> a) {
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
}
