/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._229;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 20/02/2021
 * 
 * Problem: Merge Strings Alternately
 * Status: accepted
 * 
 * You are given two strings word1 and word2. Merge the strings by
 * adding letters in alternating order, starting with word1. If
 * a string is longer than the other, append the additional letters
 * onto the end of the merged string.
 * 
 * Return the merged string.
 *
 * Input
 * 
abc
pqr
 * 
 * Output
 * 
apbqcr
 * 
 * }</pre>
 */
public class Task1_Merge_Strings_Alternately {
    
    public String mergeAlternately(String word1, String word2) {
        char[] l = word1.toCharArray();
        char[] r = word2.toCharArray();
        return merge(l, r, 0, 0);
    }
    
    private String merge(char[] l, char[] r, int i1, int i2) {
        if (i1 == l.length) {
            return new String(r, i2, r.length - i2);
        }
        return l[i1] + merge(r, l, i2, i1 + 1);
    }

    @Test
    public void test() {
        assertEquals("apbqrs", mergeAlternately("ab", "pqrs"));
        assertEquals("apbqcd", mergeAlternately("abcd", "pq"));
        assertEquals("apbqcr", mergeAlternately("abc", "pqr"));
        assertEquals("ababa", mergeAlternately("aaa", "bb"));
    }
}
