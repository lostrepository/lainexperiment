/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._221;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 19/12/2020
 * 
 * Problem: Determine if String Halves Are Alike
 * Status: accepted
 * 
 * You are given a string s of even length. Split this
 * string into two halves of equal lengths, and let a
 * be the first half and b be the second half.
 * 
 * Two strings are alike if they have the same number of
 * vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
 * Notice that s contains uppercase and lowercase letters.
 * 
 * Return true if a and b are alike. Otherwise, return false.
 *
 * Example 1:
 * 
book
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Determine_if_String_Halves_Are_Alike {

    public boolean halvesAreAlike(String s) {
        s = s.toLowerCase();
        var mid = s.length() / 2;
        var s1 = s.substring(0, mid);
        var s2 = s.substring(mid);
        var vow = Set.of('a', 'e', 'i', 'o', 'u')
                .stream()
                .map(ch -> Integer.valueOf(ch))
                .collect(toSet());
        var c1 = s1.chars()
                //.distinct()
                .filter(i -> vow.contains(i))
            .count();
        var c2 = s2.chars()
                //.distinct()
                .filter(i -> vow.contains(i))
                .count();
        return c1 == c2;
    }

    @Test
    public void test() {
        assertTrue(halvesAreAlike("book"));
        assertFalse(halvesAreAlike("textbook"));
        assertFalse(halvesAreAlike("MerryChristmas"));
        assertTrue(halvesAreAlike("AbCdEfGh"));
    }
}
