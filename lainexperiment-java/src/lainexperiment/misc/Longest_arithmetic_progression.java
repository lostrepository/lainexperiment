/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 02/01/2015
 * 
 * Problem
 * 
 * Given an array of integers A, give an algorithm to find the longest 
 * Arithmetic progression in it, i.e find a sequence i1 < i2 < … < ik, such that 
 * A[i1], A[i2], ..., A[ik] forms an arithmetic progression, and k is the
 * largest possible index.
 * The sequence S1, S2, ..., Sk is called an arithmetic progression if S<j + 1> – Sj 
 * is a constant. 
 *
 * Input
 * 
 * -5, -3, 0, 1, 2, 3, 4, 6, 9, 12, 14, 15
 * 
 * Output
 * 
 * 7 (-3, 0, 3, 6, 9, 12, 15)
 * 
 */

package lainexperiment.misc;

public class Longest_arithmetic_progression {
    
    static int count(int[] a, int d, int i) {
        int j = i;
        while (j >= 0) {
            if (a[i] - a[j] == d)
                return 1 + count(a, d, j);
            j--;
        }
        return 0;
    }

    static int max(int[] a, int n) {
        int M = 0;
        for (int i = 0; i < n; i++) {
            int d = Math.max(a[n], a[i]) - Math.min(a[n], a[i]); 
            int m = count(a, d, n);
            M = Math.max(M, m);
        }
        return M;
    }
    
    static int max(int[] a) {
        int M = 0;
        for (int i = 1; i < a.length; i++) {
            int m = max(a, i);
            M = Math.max(M, m);
        }
        return M + 1;
    }

    public static void main(String[] args) {
        System.out.println(max(new int[]{-5, -3, 0, 1, 2, 3, 4, 6, 9, 12, 14, 15}));
        System.out.println(max(new int[]{0, 100, 200, 300}));
    }
}
