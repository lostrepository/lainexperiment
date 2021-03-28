/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._234;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 27/03/2021
 * 
 * Problem: Number of Different Integers in a String
 * Status: accepted
 * 
 * You are given a string word that consists of digits and
 * lowercase English letters.
 * 
 * You will replace every non-digit character with a space.
 * For example, "a123bc34d8ef34" will become " 123  34 8  34".
 * Notice that you are left with some integers that are
 * separated by at least one space: "123", "34", "8", and "34".
 * 
 * Return the number of different integers after performing the
 * replacement operations on word.
 * 
 * Two integers are considered different if their decimal
 * representations without any leading zeros are different.
 * 
 * Input
 * 
a123bc34d8ef34
 * 
 * Output
 * 
3
 * 
 * }</pre>
 */
public class Task1_Number_of_Different_Integers_in_a_String {
    
    public int numDifferentIntegers(String word) {
        word += 'a';
        var a = word.toCharArray();
        var s = new HashSet<String>();
        var b = "";
        for (int i = 0; i < a.length; i++) {
            if (a[i] < '0' || a[i] > '9') {
                if (!b.isEmpty())
                    s.add(b.replaceFirst("0*", ""));
                b = "";
                continue;
            };
            b += a[i];
        }
        return s.size();
    }

    @Test
    public void test() {
        assertEquals(1, numDifferentIntegers("ac000b00"));
        assertEquals(1, numDifferentIntegers("ac0"));
        assertEquals(1, numDifferentIntegers("a1b01c001"));
        assertEquals(2, numDifferentIntegers("leet1234code234"));
        assertEquals(1, numDifferentIntegers("a0001b1"));
        assertEquals(3, numDifferentIntegers("a123bc34d8ef34"));
    }
}
