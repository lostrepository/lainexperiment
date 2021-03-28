/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._234;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 27/03/2021
 * 
 * Problem: Minimum Number of Operations to Reinitialize a Permutation
 * Status: accepted
 * 
 * You are given an even integer n​​​​​​. You initially have a permutation
 * perm of size n​​ where perm[i] == i​ (0-indexed)​​​​.
 * 
 * In one operation, you will create a new array arr, and for each i:
 * 
 * - If i % 2 == 0, then arr[i] = perm[i / 2].
 * - If i % 2 == 1, then arr[i] = perm[n / 2 + (i - 1) / 2].
 * 
 * You will then assign arr​​​​ to perm.
 * 
 * Return the minimum non-zero number of operations you need to perform
 * on perm to return the permutation to its initial value.
 * 
 * Input
 * 
4
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task2_Minimum_Number_of_Operations_to_Reinitialize_a_Permutation {
    
    public int reinitializePermutation(int n) {
        var a = IntStream.range(0, n).toArray();
        int c = 0;
        while (true) {
            var aa = new int[a.length];
            boolean same = true;
            for (int i = 0; i < a.length; i++) {
                if (i % 2 == 0) aa[i] = a[i / 2];
                if (i % 2 == 1) aa[i] = a[n / 2 + (i - 1) / 2];
                if (aa[i] != i) same = false;
            }
            a = aa;
            c++;
            if (same) return c;
        }
    }

    @Test
    public void test() {
        assertEquals(36, reinitializePermutation(1000));
        assertEquals(4, reinitializePermutation(6));
        assertEquals(2, reinitializePermutation(4));
        assertEquals(1, reinitializePermutation(2));
    }
}
