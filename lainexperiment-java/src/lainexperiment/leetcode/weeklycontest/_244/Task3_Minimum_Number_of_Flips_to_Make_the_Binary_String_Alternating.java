/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._244;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 01/08/2021
 * 
 * Problem: Minimum Number of Flips to Make the Binary String Alternating
 * Status: wrong answer
 * 
 * You are given a binary string s. You are allowed to perform two types of
 * operations on the string in any sequence:
 * 
 * - Type-1: Remove the character at the start of the string s and append
 *   it to the end of the string.
 *   
 * - Type-2: Pick any character in s and flip its value, i.e., if its
 *   value is '0' it becomes '1' and vice-versa.
 *
 * Return the minimum number of type-2 operations you need to perform
 * such that s becomes alternating.
 * 
 * The string is called alternating if no two adjacent characters are equal.
 * 
 * For example, the strings "010" and "1010" are alternating, while the
 * string "0100" is not.
 * 
 * Input
 * 
111000
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task3_Minimum_Number_of_Flips_to_Make_the_Binary_String_Alternating {
    
    public int minFlips(String s) {
        var a = s.toCharArray();
        var c = cost(a, '1');
        c = Math.min(c, cost(a, '0'));
        a = roll(a);
        c = Math.min(c, cost(a, '1'));
        c = Math.min(c, cost(a, '0'));
        return c;
        
    }

    private char[] roll(char[] a) {
        var b = new char[a.length];
        for (int i = 0; i < b.length - 1; i++) {
            b[i] = a[i + 1];
        }
        b[b.length - 1] = a[0];
        return b;
    }

    private int cost(char[] a, char ch) {
        var c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != ch) c++;
            ch = ch == '0'? '1': '0';
        }
        return c;
    }

    @Test
    public void test() {
        assertEquals(0, minFlips("11010"));
        assertEquals(1, minFlips("1110"));
        assertEquals(0, minFlips("010"));
        assertEquals(2, minFlips("111000"));
    }
}
