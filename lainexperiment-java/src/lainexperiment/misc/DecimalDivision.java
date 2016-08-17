/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 11/01/2015
 * 
 * Problem
 * 
 * Write a function which, given two integers (a numerator and a denominator), prints 
 * the decimal representation of the rational number "numerator/denominator".
 * Since all rational numbers end with a repeating section, print the repeating section
 * of digits inside parentheses.
 * 
 * Input
 *
 * 1 , 3
 * 2 , 4
 * 22, 7
 * 
 * Output
 * 
 * 0.(3)
 * 0.(5)
 * 3.(142857)
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

public class DecimalDivision {

    private static String decimalDiv(int n, int d, Set<Integer> s, boolean isDec) {
        if (n == 0)
            return "";
        String pre = "";
        if (n < d) {
            n *= 10;
            if (!isDec) {
                isDec = !isDec;
                pre = ".";
            }
        }
        int r = n / d;
        boolean stop = s.contains(n);
        s.add(n);
        if (stop) {
            s.remove(n);
            return ")";
        }
        String res = r + decimalDiv(n - d * r, d, s, isDec);
        if (!s.contains(n))
            pre += "(";
        return pre + res;
    }
    
    private static String decimalDiv(int n, int d) {
        return decimalDiv(n, d, new HashSet<Integer>(), false);
    }
    
    public static void main(String[] args) {
        assertEquals(".(3)", decimalDiv(1, 3));
        assertEquals("5.5", decimalDiv(22, 4));
        assertEquals("3.(142857)", decimalDiv(22, 7));
        assertEquals(".2(3)", decimalDiv(7, 30));
        assertEquals("1.(00081103)", decimalDiv(1234, 1233 ));
    }

}
