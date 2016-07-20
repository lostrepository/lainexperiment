/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 29/06/2016
 * 
 * Cimpress Codility
 * Problem: Task 3
 * Status: passed
 * 
 * Problem
 * 
 * Given integer array and number K. Print how many pairs in
 * the array sums to K (are K-complimentary)
 * 
 * Sample Input
 * 
1, 8, -3, 0, 1, 3, -2, 4, 5
K = 6
 * 
 * Sample Output
 * 
7
 * 
 */

package lainexperiment.codility.cimpress;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class Task3 {
   
    public int solution(int K, int[] A) {
        Arrays.sort(A);
        int c = 0;
        for (int i = 0; i < A.length; i++) {
            int b = K - A[i];
            int[] r = find(A, b, 0, A.length - 1);
            if (r == null) continue;
//            System.out.println(A[i] + " " + b);
            c += (r[1] - r[0]) + 1;
        }
        return c;
    }

    private int[] find(int[] a, int v, int s, int e)
    {
        if (e < s)
            return null;
        if (e == s) {
            if (a[e] == v)
                return new int[]{s, s};
            else
                return null;
        }
        int m = (e - s) / 2 + s;
        if (a[m] < v)
            return find(a, v, m + 1, e);
        if (a[m] > v)
            return find(a, v, s, m - 1);
        if (a[m] != v) 
            return null;
        int[] l = null;
        if (m != 0)
            l = find(a, v, s, m - 1);
        if (l == null)
            l = new int[]{m, m};
        int[] r = null;
        if (m != a.length - 1)
            r = find(a, v, m + 1, e);
        if (r == null)
            r = new int[]{m, m};
        return new int[]{l[0], r[1]};
    }

    public static int solve(int K, int[] A) {
        return new Task3().solution(K, A);
    }
    
    public static void main(String[] args) {
        assertEquals(7, solve(6, new int[]{1, 8, -3, 0, 1, 3, -2, 4, 5}));
        assertEquals(4, solve(6, new int[]{6, 0, 0}));
        assertEquals(0, solve(6, new int[]{4, 0, 0}));
        assertEquals(5, solve(6, new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(4, solve(8, new int[]{4, 4}));
        assertEquals(2, solve(-1, new int[]{-4, -1, -2, 3, 2}));
    }
    
}
