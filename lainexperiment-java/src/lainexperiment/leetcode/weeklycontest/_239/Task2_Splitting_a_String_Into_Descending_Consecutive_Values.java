/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._239;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 01/05/2021
 * 
 * Problem: Splitting a String Into Descending Consecutive Values
 * Status: accepted
 * 
 * You are given a string s that consists of only digits.
 * 
 * Check if we can split s into two or more non-empty substrings such
 * that the numerical values of the substrings are in descending order
 * and the difference between numerical values of every two adjacent
 * substrings is equal to 1.
 * 
 * For example, the string s = "0090089" can be split into
 * ["0090", "089"] with numerical values [90,89]. The values are in
 * descending order and adjacent values differ by 1, so this way is
 * valid.
 * 
 * Another example, the string s = "001" can be split into ["0", "01"],
 * ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid
 * because they have numerical values [0,1], [0,1], and [0,0,1]
 * respectively, all of which are not in descending order.
 * 
 * Return true if it is possible to split s​​​​​​ as described above, or
 * false otherwise.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * Input
 * 
050043
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task2_Splitting_a_String_Into_Descending_Consecutive_Values {
    
    public boolean splitString(char[] a, LinkedList<BigInteger> buf, int i) {
        if (i == a.length) {
            if (buf.size() == 1) return false;
            System.out.println(buf);
            return buf.get(0).subtract(buf.getLast())
                    .compareTo(BigInteger.valueOf(buf.size() - 1)) == 0;
        }
        for (int j = i + 1; j <= a.length; j++) {
            var n = new BigInteger(new String(a, i, j - i));
            if (!buf.isEmpty() && n.compareTo(buf.getLast()) > 0) continue;
            buf.add(n);
            if (splitString(a, buf, j)) return true;
            buf.removeLast();
        }
        return false;
    }
    
    public boolean splitString(String s) {
        var a = s.toCharArray();
        var buf = new LinkedList<BigInteger>();
        return splitString(a, buf, 0);
    }

    @Test
    public void test() {
        assertEquals(false, splitString("3202872336"));
        assertEquals(true, splitString("0090089"));
        assertEquals(true, splitString("10009998"));
        assertEquals(false, splitString("9080701"));
        assertEquals(true, splitString("050043"));
        assertEquals(false, splitString("01234"));
    }
}
