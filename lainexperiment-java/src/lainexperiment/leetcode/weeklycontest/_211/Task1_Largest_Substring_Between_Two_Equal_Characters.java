/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._211;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 10/03/2020
 * 
 * Problem: Largest Substring Between Two Equal Characters
 * Status: accepted
 * 
 * Given a string s, return the length of the longest substring
 * between two equal characters, excluding the two characters.
 * If there is no such substring return -1.
 * 
 * A substring is a contiguous sequence of characters within
 * a string.
 *
 * Input
 * 
abca
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task1_Largest_Substring_Between_Two_Equal_Characters {

    public int maxLengthBetweenEqualCharacters(String s) {
        var a = s.toCharArray();
        var m = -1;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (a[i] == a[j]) {
                    m = Math.max(m, j - i - 1);
                }
            }
        }
        return m;
    }
    
    @Test
    public void test() {
        assertEquals(4, maxLengthBetweenEqualCharacters("cabbac"));
        assertEquals(-1, maxLengthBetweenEqualCharacters("cbzxy"));
        assertEquals(2, maxLengthBetweenEqualCharacters("abca"));
        assertEquals(0, maxLengthBetweenEqualCharacters("aa"));
        assertEquals(2, maxLengthBetweenEqualCharacters("abba"));
        assertEquals(2, maxLengthBetweenEqualCharacters("aaaa"));
    }
}
