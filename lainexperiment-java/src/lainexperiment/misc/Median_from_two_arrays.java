/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 10/01/2015
 *
 * Problem
 * 
 * Given two sorted arrays a, b need to find k-th smallest element in it.
 * 
 * Input
 * 
 * k = 5
 * a = 1, 32, 36, 77
 * b = 21, 65, 76, 87
 * 
 * Output
 * 
 * 65
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.function.IntFunction;

public class Median_from_two_arrays {
    
    static int median(int[] a1, int[] a2, int s, int e, int k)
    {
        IntFunction<Integer> index = (len) -> k >= len? len - 1: k - 1;
        if (s > e)
            return Integer.MAX_VALUE;
        if (e <= 0)
            return a2[index.apply(a2.length)];
        int m = (s + e) / 2;
        if (m > k - 1)
            return Math.min(a1[k - 1], median(a1, a2, s, m - 1, k));
        if (k - m - 2 < 0)
            return Math.min(a1[k - 1], median(a1, a2, s, m - 1, k));
        if (k - m - 2 >= a2.length)
            return median(a1, a2, m + 1, e, k);
        int c1 = a1[m];
        int c2 = a2[k - m - 2];
        int r = 0;
        if (c1 > c2) {
            r = median(a1, a2, s, m - 1, k);
        } else {
            r = median(a1, a2, m + 1, e, k);
        }
        return Math.min(Math.max(c1, c2), r);
    }
    
    public static void main(String[] args) {
        int[] a1 = null;
        int[] a2 = null;
        
        a1 = new int[]{1, 2, 3, 4};
        a2 = new int[]{5, 6, 7, 10};
        assertEquals(3, median(a1, a2, 0, a1.length - 1, 3));
        
        a1 = new int[]{1, 3, 5, 7};
        a2 = new int[]{2, 4, 6, 8};
        assertEquals(3, median(a1, a2, 0, a1.length - 1, 3));
        
        a1 = new int[]{1, 3, 5, 7};
        a2 = new int[]{2, 4, 6, 8};
        assertEquals(4, median(a1, a2, 0, a1.length - 1, 4));

        a1 = new int[]{1, 3, 5, 7};
        a2 = new int[]{2, 4, 6, 8};
        assertEquals(5, median(a1, a2, 0, a1.length - 1, 5));

        a1 = new int[]{1, 3, 5, 7};
        a2 = new int[]{2, 4, 6, 8};
        assertEquals(6, median(a1, a2, 0, a1.length - 1, 6));

        a1 = new int[]{1, 3, 5, 7};
        a2 = new int[]{2, 4, 6, 8};
        assertEquals(1, median(a1, a2, 0, a1.length - 1, 1));
        
        a1 = new int[]{1, 3, 5, 7};
        a2 = new int[]{2, 4, 6, 8};
        assertEquals(8, median(a1, a2, 0, a1.length - 1, 8));
        
        a1 = new int[]{1, 32, 36, 77};
        a2 = new int[]{21, 65, 76, 87};
        assertEquals(65, median(a1, a2, 0, a1.length - 1, 5));
        
    }
    
}
