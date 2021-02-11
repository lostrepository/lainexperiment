/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._213;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/02/2020
 * 
 * Problem: Count Sorted Vowel Strings
 * Status: accepted
 * 
 * Given an integer n, return the number of strings of length
 * n that consist only of vowels (a, e, i, o, u) and are
 * lexicographically sorted.
 * 
 * A string s is lexicographically sorted if for all valid i,
 * s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 * Input
 * 
2
 * 
 * Output
 * 
15
 * 
 * }</pre>
 */
public class Task2_Count_Sorted_Vowel_Strings {

    public int countVowelStrings(int n) {
        return countVowelStrings(n, 0, 0);
    }
    
    int countVowelStrings(int n, int i, int k) {
        if (i == n) return 1;
        int acc = 0;
        for (int j = k; j < 5; j++) {
            acc += countVowelStrings(n, i + 1, j);
        }
        return acc;
    }

    @Test
    public void test() {
        assertEquals(5, countVowelStrings(1));
        assertEquals(15, countVowelStrings(2));
        assertEquals(66045, countVowelStrings(33));
        assertEquals(66045, countVowelStrings(50));
    }

}
