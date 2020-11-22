/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <pre>{@code
 * Date: 01/02/2015
 * 
 * Problem
 * 
 * Determine minimum sequence of adjacent values in the input parameter array that
 * is greater than input parameter sum.
 *
 * Input
 * 
 * 2,1,1,4,3,6
 * 8
 * 
 * Output
 * 
 * 2
 * 
 * }</pre>
 */
public class Shortest_adj_subset_greater_than_K {

    static int length(int[] a, int k) {
        int sum = 0;
        int s = 0, e = 0;
        int m = Integer.MAX_VALUE;
        while (e <= a.length) {
            if (sum > k) {
                m = Math.min(m, e - s);
                sum -= a[s];
                s++;
            } else if (e == a.length)
                return m;
            else
                sum += a[e++];
        }
        return m;
    }

    public static void main(String[] args) {
        assertEquals(2, length(new int[]{2, 1, 1, 3, 6, 4, 3, 1}, 8));
        assertEquals(2, length(new int[]{2, 1, 1, 4, 3, 6}, 8));
    }

}
