/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._241;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 15/05/2021
 * 
 * Problem: Minimum Number of Swaps to Make the Binary String Alternating
 * Status: accepted
 * 
 * Given a binary string s, return the minimum number of character swaps
 * to make it alternating, or -1 if it is impossible.
 * 
 * The string is called alternating if no two adjacent characters are
 * equal. For example, the strings "010" and "1010" are alternating, while
 * the string "0100" is not.
 * 
 * Any two characters may be swapped, even if they are not adjacent.
 * 
 * Input
 * 
111000
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task2_Minimum_Number_of_Swaps_to_Make_the_Binary_String_Alternating {
    
    public int minSwaps(String s) {
        var c0 = s.chars()
                .filter(i -> i == '0')
                .count();
        var c1 = s.chars()
                .filter(i -> i == '1')
                .count();
        var r = Math.min(count('0', s, c0, c1), count('1', s, c0, c1));
        return r == Integer.MAX_VALUE? -1: r / 2;
    }

    private int count(char val, String s, long c0, long c1) {
        var a = s.toCharArray();
        var r = 0;
        for (int i = 0; i < a.length; i++, val = val == '1'? '0': '1') {
            if (a[i] != val) r++;
            if (a[i] == '0') {
                if (a[i] != val) c1--;
                else c0--;
            } else {
                if (a[i] != val) c0--;
                else c1--;
            }
            if (c0 < 0) return Integer.MAX_VALUE;
            if (c1 < 0) return Integer.MAX_VALUE;
        }
        return r;
    }

    @Test
    public void test() {
        assertEquals(2, minSwaps("11011000"));
        assertEquals(-1, minSwaps("1110"));
        assertEquals(1, minSwaps("111000"));
        assertEquals(0, minSwaps("01"));
    }
}
