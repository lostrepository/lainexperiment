/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._228;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.MathUtils;

/**
 * <pre>{@code
 * Date: 13/02/2021
 * 
 * Problem: Count Number of Homogenous Substrings
 * Status: accepted
 * 
 * Given a string s, return the number of homogenous substrings of
 * s. Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * A string is homogenous if all the characters of the string are
 * the same.
 * 
 * A substring is a contiguous sequence of characters within a string.
 *
 * Input
 * 
abbcccaa
 * 
 * Output
 * 
13
 * 
 * }</pre>
 */
public class Task2_Count_Number_of_Homogenous_Substrings {
    
    final long MOD = 1_000_000_007L;
    
    public int countHomogenous(String s) {
        int l = 0;
        char delim = '#';
        s = s + delim;
        char p = delim;
        long c = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == p) {
                l++;
                continue;
            }
            c = (c + MathUtils.sequenceSum(l, MOD)) % MOD;
            p = ch;
            l = 1;
        }
        return (int) c;
    }
    
    @Test
    public void test() {
        assertEquals(13, countHomogenous("abbcccaa"));
        assertEquals(2, countHomogenous("xy"));
        assertEquals(15, countHomogenous("zzzzz"));
        assertEquals(15, countHomogenous("zzzzz"));
    }
}
