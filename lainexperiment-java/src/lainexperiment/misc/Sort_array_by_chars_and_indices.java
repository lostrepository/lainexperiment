/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 26/07/2016
 * 
 * Problem
 * 
 * Given array of chars. Need to build array of indices for it such
 * that indices should go according alphabetical order of their
 * chars. If there are two indices with same char the index with 
 * smaller value should go first.
 *
 * Input
 * 
abacba
 * 
 * Output
 * 
025143
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Sort_array_by_chars_and_indices {

    interface SwapFunction {
        void apply(int a, int b);
    }
    
    static int partition(char[] a, int lo, int hi, SwapFunction swap) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true)
        {
            while (a[++i] < v) 
                if (i == hi) break;
            while (v < a[--j])
                if (j == lo) break;
            if (i >= j) break;
            swap.apply(i, j);
        }
        swap.apply(lo, j);
        return j;
    }

    static void qsort(char[] a, int left, int right, SwapFunction swap) {
        if (right <= left) {
            return;
        }
        int newPivot = partition(a, left, right, swap);
        qsort(a, left, newPivot - 1, swap);
        qsort(a, newPivot + 1, right, swap);
    }
    
    static int[] sort(char[] a) {
        int[] idx = new int[a.length];
        Arrays.setAll(idx, IntUnaryOperator.identity());
        qsort(a, 0, a.length - 1, (i1, i2) -> {
            int t = a[i1];
            a[i1] = a[i2];
            a[i2] = (char) t;
            t = idx[i1];
            idx[i1] = idx[i2];
            idx[i2] = t;
        });
        int s = 0;
        char ch = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] == ch) continue;
            Arrays.sort(idx, s, i);
            s = i;
            ch = a[i];
        }
        Arrays.sort(idx, s, idx.length);
        return idx;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        assertEquals("[0, 2, 5, 1, 4, 3]", Arrays.toString(sort("abacba".toCharArray())));
    }
    
}
