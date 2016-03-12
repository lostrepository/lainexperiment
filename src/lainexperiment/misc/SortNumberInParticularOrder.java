/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 01/02/2015
 * 
 * Problem
 * 
 * Given two arrays first is the numbers which needs to sort and second 
 * their order positions according to which they needs to be sorted.
 *
 * Input
 * 
 * 17, 5, 1, 9
 * 3, 2, 4, 1
 * 
 * Output
 * 
 * 9, 5, 17, 1
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class SortNumberInParticularOrder {

    static void sort(int[] a, int[] b) {
        for (int i = 0; i < a.length; ++i) {
            while (b[i] - 1 != i) {
                swap(a, i, b[i] - 1);
                swap(b, i, b[i] - 1);
            }
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] a = null;
        int[] b = null;
        int[] c = null;
        
        a = new int[]{17, 5, 1, 9};
        b = new int[]{3, 2, 4, 1};
        c = new int[]{9, 5, 17, 1};
        sort(a, b);
        assertTrue(Arrays.equals(a, c));
        
        a = new int[]{3, 5, 11, 6, 8};
        b = new int[]{5, 2, 1, 3, 4};
        c = new int[]{11, 5, 6, 8, 3};
        sort(a, b);
        assertTrue(Arrays.equals(a, c));
    }

}
