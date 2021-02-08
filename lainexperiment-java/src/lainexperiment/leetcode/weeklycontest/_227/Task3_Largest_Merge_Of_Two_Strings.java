/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._227;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 08/02/2021
 * 
 * Problem: Largest Merge Of Two Strings
 * Status: accepted
 * 
 * You are given two strings word1 and word2. You want to construct
 * a string merge in the following way: while either word1 or word2
 * are non-empty, choose one of the following options:
 * 
 * - If word1 is non-empty, append the first character in word1 to
 * merge and delete it from word1. For example, if word1 = "abc" and
 * merge = "dv", then after choosing this operation, word1 = "bc"
 * and merge = "dva".
 * 
 * - If word2 is non-empty, append the first character in word2 to
 * merge and delete it from word2. For example, if word2 = "abc" and
 * merge = "", then after choosing this operation, word2 = "bc" and
 * merge = "a".
 * 
 * Return the lexicographically largest merge you can construct.
 *
 * Input
 * 
word1 = "cabaa", word2 = "bcaaa"
 * 
 * Output
 * 
cbcabaaaaa
 * 
 * }</pre>
 */
public class Task3_Largest_Merge_Of_Two_Strings {
    
    public String largestMerge(String word1, String word2) {
        char[] a1 = word1.toCharArray();
        char[] a2 = word2.toCharArray();
        char[] r = new char[a1.length + a2.length];
        int i1 = 0;
        int i2 = 0;
        int c = 0;
        while (i1 < a1.length && i2 < a2.length) {
            if (a1[i1] == a2[i2]) {
                int p = Arrays.mismatch(a1, i1, a1.length, a2, i2, a2.length);
                if (p == -1) {
                    r[c++] = a1[i1++];
                } else if (i1 + p >= a1.length) {
                    r[c++] = a2[i2++];
                } else if (i2 + p >= a2.length) {
                    r[c++] = a1[i1++];
                } else {
                    if (a1[i1 + p] > a2[i2 + p]) {
                        r[c++] = a1[i1++];
                    } else {
                        r[c++] = a2[i2++];
                    }
                }
                continue;
            }
            if (a1[i1] > a2[i2]) {
                r[c++] = a1[i1++];
            } else {
                r[c++] = a2[i2++];
            }
        }
        while (i1 < a1.length) {
            r[c++] = a1[i1++]; 
        }
        while (i2 < a2.length) {
            r[c++] = a2[i2++]; 
        }
        return new String(r);
    }
    
    @Test
    public void test() {
        assertEquals("cbcabaaaaa", largestMerge("cabaa", "bcaaa"));
        assertEquals("abdcabcabcaba", largestMerge("abcabc", "abdcaba"));
    }
}
