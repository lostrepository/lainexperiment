/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringUtils {

    public static boolean isPalindrome(char[] a) {
        return isPalindrome(a, 0, a.length);
    }
    
    /**
     * Checks if substring of a[s,e) is palindrome or not
     */
    public static boolean isPalindrome(char[] a, int s, int e) {
        var lo = s;
        var hi = e - 1;
        while (hi - lo >= 1) {
            if (a[lo] != a[hi]) return false;
            lo++;
            hi--;
        }
        return true;
    }
    
    @Test
    public void test() {
        assertEquals(false, isPalindrome("xa".toCharArray()));
        assertEquals(true, isPalindrome("zabbza".toCharArray(), 2, 4));
        assertEquals(false, isPalindrome("zabbza".toCharArray()));
        assertEquals(true, isPalindrome("abbba".toCharArray()));
        assertEquals(true, isPalindrome("abba".toCharArray()));
    }
}
