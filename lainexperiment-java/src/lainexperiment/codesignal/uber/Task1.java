/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.codesignal.uber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 09/06/2020
 *
 * Problem: Digits Manipulations
 * Status: accepted
 * 
 * Problem
 * 
 * Given number N print difference between product of all
 * its digits and their sum.
 * 
 * Input Format
 * 
 * Number N
 * 
 * Output Format
 * 
 * Differences between product and sum
 * 
 * Input
 * 
123456
 * 
 * Output
 * 
699
 * 
 * }</pre>
 */
public class Task1 {

    long digitsManipulations(long n) {
        String s = Long.toString(n);
        long prod = 1, sum = 0;
        for (char ch: s.toCharArray()) {
            int v = ch - '0';
            prod *= v;
            sum += v;
        }
        return prod - sum;
    }
    
    @Test
    public void test() {
        assertEquals(699, digitsManipulations(123456));
        assertEquals(-2, digitsManipulations(1010));
        assertEquals(0, digitsManipulations(0));
        assertEquals(-1, digitsManipulations(10));
    }
    
}
