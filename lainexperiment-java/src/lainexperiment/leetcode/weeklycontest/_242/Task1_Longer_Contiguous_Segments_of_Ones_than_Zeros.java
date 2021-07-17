/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._242;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 22/05/2021
 * 
 * Problem: Longer Contiguous Segments of Ones than Zeros
 * Status: accepted
 * 
 * Given a binary string s, return true if the longest contiguous
 * segment of 1s is strictly longer than the longest contiguous
 * segment of 0s in s. Return false otherwise.
 * 
 * For example, in s = "110100010" the longest contiguous segment
 * of 1s has length 2, and the longest contiguous segment of 0s has
 * length 3.
 * 
 * Note that if there are no 0s, then the longest contiguous segment
 * of 0s is considered to have length 0. The same applies if there
 * are no 1s.
 * 
 * Input
 * 
1101
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Longer_Contiguous_Segments_of_Ones_than_Zeros {
    
    public boolean checkZeroOnes(String s) {
        var ones = Arrays.stream(s.split("0"))
                .peek(System.out::println)
            .mapToInt(String::length)
            .max()
            .orElse(0);
        var zeros = Arrays.stream(s.split("1"))
                .mapToInt(String::length)
                .max()
                .orElse(0);
        return ones > zeros;
    }

    @Test
    public void test() {
        assertEquals(false, checkZeroOnes("110100010"));
        assertEquals(false, checkZeroOnes("111000"));
        assertEquals(true, checkZeroOnes("1101"));
        assertEquals(true, checkZeroOnes("01100111"));
        assertEquals(false, checkZeroOnes("01100"));
    }
}
