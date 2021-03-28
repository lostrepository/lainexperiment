/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 23/03/2020
 * 
 * Problem: Split a String Into the Max Number of Unique Substrings
 * Status: accepted
 * 
 * Given a string s, return the maximum number of unique substrings
 * that the given string can be split into.
 * 
 * You can split string s into any list of non-empty substrings, where
 * the concatenation of the substrings forms the original string.
 * However, you must split the substrings such that all of them are
 * unique.
 * 
 * A substring is a contiguous sequence of characters within a string.
 *
 * Input
 * 
ababccc
 * 
 * Output
 * 
5
 * 
 * 
 * }</pre>
 */
public class Task2_Split_a_String_Into_the_Max_Number_of_Unique_Substrings {

    public int maxUniqueSplit(String s) {
        var a = s.toCharArray();
        return split(a, 0, new HashSet<String>());
    }
    
    private int split(char[] a, int i, HashSet<String> set) {
        if (i == a.length) return 0;
        var m = 0;
        for (int j = i + 1; j <= a.length; j++) {
            var s = new String(a, i, j - i);
            System.out.println(s);
            if (set.contains(s)) continue;
            set.add(s);
            m = Math.max(m, split(a, j, set) + 1);
            set.remove(s);
        }
        return m;
    }
    
    @Test
    public void test() {
        assertEquals(2, maxUniqueSplit("aba"));
        assertEquals(5, maxUniqueSplit("ababccc"));
        assertEquals(2, maxUniqueSplit("ab"));
        assertEquals(1, maxUniqueSplit("aa"));
    }
}
