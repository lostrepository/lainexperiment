/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._232;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 13/03/2021
 * 
 * Problem: Check if One String Swap Can Make Strings Equal
 * Status: accepted
 * 
 * You are given two strings s1 and s2 of equal length.
 * A string swap is an operation where you choose two
 * indices in a string (not necessarily different) and
 * swap the characters at these indices.
 * 
 * Return true if it is possible to make both strings equal
 * by performing at most one string swap on exactly one of
 * the strings. Otherwise, return false.
 * 
 * Input 1
 * 
1001
 * 
 * Output
 * 
false
 * 
 * Input 2
 * 
bank
kanb
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Check_if_One_String_Swap_Can_Make_Strings_Equal {
    
    public boolean areAlmostEqual(String s1, String s2) {
        var diff = new ArrayList<Integer>();
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) continue;
            diff.add(i);
        }
        if (diff.isEmpty()) return true;
        if (diff.size() != 2) return false;
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1));
    }

    @Test
    public void test() {
        assertEquals(false, areAlmostEqual("abcd", "dcba"));
        assertEquals(true, areAlmostEqual("kelb", "kelb"));
        assertEquals(false, areAlmostEqual("attack", "deffend"));
        assertEquals(true, areAlmostEqual("bank", "kanb"));
        assertEquals(true, areAlmostEqual("112", "121"));
        assertEquals(true, areAlmostEqual("12", "21"));
        assertEquals(true, areAlmostEqual("12", "12"));
        assertEquals(false, areAlmostEqual("1", "2"));
    }
}
