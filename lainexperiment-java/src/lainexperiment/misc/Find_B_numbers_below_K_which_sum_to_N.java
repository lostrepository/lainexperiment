/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * <pre>{@code
 * Date: 10/09/2017
 * 
 * Problem
 * 
 * Find B distinct numbers below K with their sum equal N.
 *
 * Input
 * 
B = 3
K = 6
N = 6
 * 
 * Output
 * 
1, 2, 3
 * 
 * }</pre>
 */
public class Find_B_numbers_below_K_which_sum_to_N {

    static int[] solve(int b, int k, int n) {
        int[] res = new int[0];
        int lo = rangeClosed(1, b)
            .sum();
        if (lo > n)
            return res;
        int hi = rangeClosed(k - b, k)
            .sum();
        if (hi < n)
            return res;
        int d = n - lo;
        int prev = n + 1;
        res = rangeClosed(1, b)
            .toArray();
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] + d < prev) {
                res[i] += d;
                break;
            }
            d = d - (prev - res[i]);
            res[i] = prev - 1;
            prev = res[i];
        }
        return res;
    }

    public static void main(String[] args) {
        assertEquals("[1, 2, 3]", Arrays.toString(solve(3, 6, 6)));
        assertEquals("[1, 8]", Arrays.toString(solve(2, 6, 9)));
        assertEquals("[]", Arrays.toString(solve(4, 6, 9)));
        assertEquals("[1, 2, 3, 12]", Arrays.toString(solve(4, 6, 18)));
        assertEquals("[1, 2, 3, 11]", Arrays.toString(solve(4, 6, 17)));
        assertEquals("[1, 2, 3, 10]", Arrays.toString(solve(4, 6, 16)));
    }

}
