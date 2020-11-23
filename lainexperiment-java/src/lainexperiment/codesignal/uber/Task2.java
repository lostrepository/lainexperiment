/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.codesignal.uber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 09/06/2020
 *
 * Problem: Divisor substrings
 * Status: accepted
 * 
 * Problem
 * 
 * Given number N and K calculate how many DISTINCT numbers
 * from N which are substrings of digits in N with length K
 * which are factors of N.
 * 
 * Input Format
 * 
 * Number N, K
 * 
 * Output Format
 * 
 * Number of N factors
 * 
 * Input
 * 
N = 120
K = 2
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task2 {

    int divisorSubstrings(long n, int k) {
        if (k > n) return 0;
        String s = Long.toString(n);
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (i + k > s.length()) break;
            String substr = s.substring(i, i + k);
            set.add(Long.parseLong(substr));
        }
        int c = 0;
        set.remove(Long.valueOf(0));
//        System.out.println(set);
        for (long v: set) {
            c += n % v == 0? 1: 0;
        }
        return c;
    }
    
    @Test
    public void test() {
        assertEquals(2, divisorSubstrings(120, 2));
        assertEquals(0, divisorSubstrings(120, 122));
        assertEquals(2, divisorSubstrings(120, 1));
        assertEquals(1, divisorSubstrings(555, 1));
        assertEquals(0, divisorSubstrings(5341, 2));
    }
    
}
