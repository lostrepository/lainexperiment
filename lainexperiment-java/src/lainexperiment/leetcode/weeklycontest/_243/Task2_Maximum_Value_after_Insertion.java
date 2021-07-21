/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._243;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 29/05/2021
 * 
 * Problem: Maximum Value after Insertion
 * Status: accepted
 * 
 * You are given a very large integer n, represented as a string,​​​​​​ and
 * an integer digit x. The digits in n and the digit x are in the inclusive
 * range [1, 9], and n may represent a negative number.
 * 
 * You want to maximize n's numerical value by inserting x anywhere in the
 * decimal representation of n​​​​​​. You cannot insert x to the left of the
 * negative sign.
 * 
 * For example, if n = 73 and x = 6, it would be best to insert it between
 * 7 and 3, making n = 763.
 * If n = -55 and x = 2, it would be best to insert it before the first 5,
 * making n = -255.
 * 
 * Return a string representing the maximum value of n​​​​​​ after the insertion.
 * 
 * Input
 * 
n = "-13", x = 2
 * 
 * Output
 * 
-123
 * 
 * }</pre>
 */
public class Task2_Maximum_Value_after_Insertion {
    
    public String maxValue(String n, int x) {
        return insert(n, x, n.charAt(0) != '-'? Comparator.naturalOrder(): Comparator.reverseOrder());
    }

    private String insert(String n, int x, Comparator<Integer> cmp) {
        for (int i = 0; i < n.length(); i++) {
            char ch = n.charAt(i);
            if (ch == '-') continue;
            if (cmp.compare(x, Integer.valueOf("" + ch)) <= 0) continue;
            return n.substring(0, i) + x + n.substring(i);
        }
        return n + x;
    }

    @Test
    public void test() {
        assertEquals("-1323", maxValue("-132", 3));
        assertEquals("-123", maxValue("-13", 2));
        assertEquals("-255", maxValue("-55", 2));
        assertEquals("763", maxValue("73", 6));
        assertEquals("999", maxValue("99", 9));
    }
}
