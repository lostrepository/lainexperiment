/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.lang.Math.max;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 10/04/2017
 * 
 * Problem
 * 
 * The longest palindromic subsequence (LPS) problem is 
 * the problem of finding the longest subsequence of a 
 * string (a subsequence is obtained by deleting some of 
 * the characters from a string without reordering the 
 * remaining characters) which is also a palindrome.
 * 
 * Input
 * 
banana
 * 
 * Output
 * 
5
 * 
 * }</pre>
 */
public class Longest_palindromic_subsequence {

    static int maxPalin(char[] A) {
        int[][] L = new int[A.length][A.length];
        for (int r = 0; r < A.length; r++) {
            for (int l = r; l >= 0; l--) {
                // base case 1: even palindrom
                if (l == r) {
                    L[l][r] = 1;
                    continue;
                }
                // base case 2: odd palindrom
                if (l + 1 == r && A[l] == A[r]) {
                    L[l][r] = 2;
                    continue;
                }
                // recurrence
                if (A[l] == A[r])
                    L[l][r] = 2 + L[l + 1][r - 1];
                else
                    L[l][r] = max(L[l + 1][r], L[l][r - 1]);
            }
        }
        return L[0][A.length - 1];
    }

    @Test
    public void test() {
        assertEquals(7, maxPalin("a3b4dagb3a".toCharArray()));
        assertEquals(5, maxPalin("banana".toCharArray()));
        assertEquals(7, maxPalin("bananab".toCharArray()));
        assertEquals(9, maxPalin("bbbbbbbbb".toCharArray()));
    }
}
