/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * <pre>{@code
 * Date: 25/10/2020
 * 
 * Problem
 * 
 * Implement binary search for sorted array A of unique numbers.
 * 
 * Input Format
 * 
 * Array A and value v to search
 * 
 * Input Format
 * 
 * Index where value v is located or -1 if it does not exist.
 * 
 * Sample Input
 * 
A = [1, 2, 3, 4, 5]
v = 3
 *
 * Sample Output
 * 
2
 * 
 * }</pre>
 */
public class Binary_search {

    int bsearch(int[] a, int v) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (v < a[mid])
                hi = mid;
            else if (v > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }
    
    static Stream<int[]> testDataProvider() {
        return Stream.of(
            new int[]{1,2,3,4,5,6},
            new int[]{1,2,3,4,5,6,7},
            new int[]{1,2,3,4,5,6,7,8},
            new int[]{1},
            new int[]{1,2});
    }

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void test(int[] a) {
        for (int i = 0; i < a.length; i++) {
            assertEquals(i, bsearch(a, a[i]));
        }
    }

}
