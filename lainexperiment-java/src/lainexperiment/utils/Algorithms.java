/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
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
