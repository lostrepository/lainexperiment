/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._218;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 05/12/2020
 * 
 * Problem: Concatenation of Consecutive Binary Numbers
 * Status: accepted
 * 
 * Given an integer n, return the decimal value of the binary
 * string formed by concatenating the binary representations
 * of 1 to n in order, modulo 10^9 + 7.
 *
 * Example 1:
 * 
n = 3
 * 
 * Output
 * 
27
 * 
 * In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
 * After concatenating them, we have "11011", which corresponds
 * to the decimal value 27.
 * 
 * }</pre>
 */
public class Task3_Concatenation_of_Consecutive_Binary_Numbers {

    int M = 1_000_000_007;
    
    public int concatenatedBinary(int n) {
        long k = 0;
        for (int i = 1; i <= n; i++) {
            k <<= Integer.toBinaryString(i).length();
            k |= i;
            k %= M;
        }
        return (int) k;
    }
    
    @Test
    public void test() {
        System.out.println(Integer.parseInt("11100101110111", 2) % 4);
        System.out.println(Integer.parseInt("011100101110111", 2) % 4);
        System.out.println(Integer.parseInt("1011100101110111", 2) % 4);
        System.out.println(Integer.parseInt("11011100101110111", 2));
        assertEquals(1, concatenatedBinary(1));
        assertEquals(27, concatenatedBinary(3));
        assertEquals(505379714, concatenatedBinary(12));
        M = 4;
        assertEquals(3, concatenatedBinary(7));
        M = 3;
        assertEquals(2, concatenatedBinary(7));
    }

}
