/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._210;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.StringUtils;

/**
 * <pre>{@code
 * Date: 14/03/2020
 * 
 * Problem: Split Two Strings to Make Palindrome
 * Status: accepted
 * 
 * You are given two strings a and b of the same length. Choose
 * an index and split both strings at the same index, splitting
 * a into two strings: aprefix and asuffix where a = aprefix + asuffix,
 * and splitting b into two strings: bprefix and bsuffix where
 * b = bprefix + bsuffix. Check if aprefix + bsuffix or
 * bprefix + asuffix forms a palindrome.
 * 
 * When you split a string s into sprefix and ssuffix, either ssuffix
 * or sprefix is allowed to be empty. For example, if s = "abc",
 * then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 * 
 * Return true if it is possible to form a palindrome string, otherwise
 * return false.
 *
 * Input
 * 
a = "ulacfd", b = "jizalu"
 * 
 * Output
 * 
true
 * 
 * Split them at index 3 to get ulaalu
 * 
 * }</pre>
 */
public class Task3_Split_Two_Strings_to_Make_Palindrome {

    public boolean checkPalindromeFormation(String a, String b) {
        var a1 = a.toCharArray();
        var a2 = b.toCharArray();
        if (StringUtils.isPalindrome(a1)) return true;
        if (StringUtils.isPalindrome(a2)) return true;
        return isPalindrome(a1, a2) || isPalindrome(a2, a1);
    }
    
    private boolean isPalindrome(char[] a1, char[] a2) {
        var lo = 0;
        var hi = a2.length - 1;
        while (hi - lo >= 1) {
            if (a1[lo] != a2[hi]) break;
            lo++;
            hi--;
        }
        if (hi == lo) {
            return lo != 0;
        }
        if (hi - lo == 1) {
            return a1[lo] == a2[hi];
        }
        return StringUtils.isPalindrome(a1, lo, hi + 1) || StringUtils.isPalindrome(a2, lo, hi + 1);
    }

    @Test
    public void test() {
        assertEquals(true, checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp"));
        assertEquals(false, checkPalindromeFormation("abda", "acmc"));
        assertEquals(false, checkPalindromeFormation("xa", "yb"));
        assertEquals(true, checkPalindromeFormation("ulacfd", "jizalu"));
        assertEquals(false, checkPalindromeFormation("xbdef", "xecab"));
        assertEquals(true, checkPalindromeFormation("abdef", "fecab"));
        assertEquals(true, checkPalindromeFormation("xx", "yz"));
        assertEquals(true, checkPalindromeFormation("xxx", "yyy"));
        assertEquals(true, checkPalindromeFormation("xx", "yy"));
        assertEquals(true, checkPalindromeFormation("x", "y"));
    }
}
