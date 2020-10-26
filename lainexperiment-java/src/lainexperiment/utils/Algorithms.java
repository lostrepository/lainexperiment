/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import static org.junit.Assert.assertEquals;

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
}
