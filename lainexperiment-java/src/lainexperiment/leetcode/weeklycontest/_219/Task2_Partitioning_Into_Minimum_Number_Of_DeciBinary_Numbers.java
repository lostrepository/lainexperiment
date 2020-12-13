/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._219;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/12/2020
 * 
 * Problem: Partitioning Into Minimum Number Of Deci-Binary Numbers
 * Status: accepted
 * 
 * A decimal number is called deci-binary if each of its digits
 * is either 0 or 1 without any leading zeros. For example, 101
 * and 1100 are deci-binary, while 112 and 3001 are not.
 * 
 * Given a string n that represents a positive decimal integer,
 * return the minimum number of positive deci-binary numbers
 * needed so that they sum up to n.
 *
 * Example 1:
 * 
n = 32
 * 
 * Output
 * 
3
 * 
 * Because 10 + 11 + 11 = 32
 * 
 * }</pre>
 */
public class Task2_Partitioning_Into_Minimum_Number_Of_DeciBinary_Numbers {

    public int minPartitions2(String s) {
        int n = Integer.valueOf(s);
        var l = generate(s.length());
        Collections.sort(l);
        int c = 0;
        while (n > 0) {
            int p = Collections.binarySearch(l, n);
            if (p < 0) {
                p *= -1;
                p--;
                if (p == l.size()) p--;
            }
            c++;
            n -= l.get(p);
        }
        //if (n < 0) throw new RuntimeException();
        return c;
    }
    
    private List<Integer> generate(int len) {
        var out = new ArrayList<Integer>();
        if (len == 1) {
            out.add(1);
            return out;
        }
        var l = generate(len - 1);
        for (var i: l) {
            out.add(i * 10 + 0);
            out.add(i * 10 + 1);
        }
        out.addAll(l);
        return out;
    }

    public int minPartitions(String s) {
        return minPartitions(s.toCharArray());
    }

    public int minPartitions(char[] a) {
        int p = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '0') continue;
            if (a[i] == '1') continue;
            if (p == -1) p = i;
            a[i]--;
        }
        if (p == -1) return 1;
        var b = Arrays.copyOfRange(a, p, a.length);
        return 1 + minPartitions(b);
    }

    @Test
    public void test() {
        assertEquals("[100, 101, 110, 111, 10, 11, 10, 11, 1]", generate(3).toString());
        assertEquals(9, minPartitions("9"));
        assertEquals(1, minPartitions("10"));
        assertEquals(1, minPartitions("11"));
        assertEquals(3, minPartitions("32"));
        assertEquals(8, minPartitions("82734"));
        assertEquals(9, minPartitions("27346209830709182346"));
    }

}
