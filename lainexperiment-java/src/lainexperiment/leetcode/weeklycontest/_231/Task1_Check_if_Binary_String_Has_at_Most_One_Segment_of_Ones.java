/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._231;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 06/03/2021
 * 
 * Problem: Check if Binary String Has at Most One Segment of Ones
 * Status: accepted
 * 
 * Given a binary string s ​​​​​without leading zeros, return true​​​ if s
 * contains at most one contiguous segment of ones. Otherwise, return false.
 * 
 * Input 1
 * 
1001
 * 
 * Output
 * 
false
 * 
 * Input 2
 * 
110
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Check_if_Binary_String_Has_at_Most_One_Segment_of_Ones {
    
    public boolean checkOnesSegment(String s) {
        char[] a = s.toCharArray();
        boolean start = false;
        boolean found = false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == '1') {
                if (found) return false;
                start = true;
                continue;
            }
            if (start) {
                found = true;
                start = false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        assertEquals(true, checkOnesSegment("1"));
        assertEquals(false, checkOnesSegment("10101"));
        assertEquals(false, checkOnesSegment("1001"));
        assertEquals(true, checkOnesSegment("110"));
        assertEquals(false, checkOnesSegment("1010101011"));
        assertEquals(true, checkOnesSegment("111"));
    }
}
