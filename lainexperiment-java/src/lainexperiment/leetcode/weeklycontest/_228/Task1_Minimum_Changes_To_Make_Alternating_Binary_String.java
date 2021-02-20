/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._228;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 13/02/2021
 * 
 * Problem: Minimum Changes To Make Alternating Binary String
 * Status: accepted
 * 
 * You are given a string s consisting only of the characters '0'
 * and '1'. In one operation, you can change any '0' to '1' or
 * vice versa.
 * 
 * The string is called alternating if no two adjacent characters
 * are equal. For example, the string "010" is alternating, while
 * the string "0100" is not.
 * 
 * Return the minimum number of operations needed to make s alternating.
 *
 * Input
 * 
0100
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task1_Minimum_Changes_To_Make_Alternating_Binary_String {
    
    public int minOperations(String s) {
        int c = 0;
        char[] a = s.toCharArray();
        char cur = '1';
        for (int i = 0; i < a.length; i++) {
            if (a[i] != cur) c++;
            cur = cur == '1'? '0': '1';
        }
        int m = c;
        c = 0;
        cur = '0';
        for (int i = 0; i < a.length; i++) {
            if (a[i] != cur) c++;
            cur = cur == '1'? '0': '1';
        }
        return Math.min(m, c);
    }
    
    @Test
    public void test() {
        assertEquals(2, minOperations("10010"));
        assertEquals(0, minOperations("10"));
        assertEquals(2, minOperations("1001"));
        assertEquals(0, minOperations("101010"));
        assertEquals(2, minOperations("1111"));
    }
}
