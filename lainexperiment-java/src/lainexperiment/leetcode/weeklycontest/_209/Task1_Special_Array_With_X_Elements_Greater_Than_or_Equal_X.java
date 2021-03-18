/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.leetcode.weeklycontest._209;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 13/03/2020
 * 
 * Problem: Special Array With X Elements Greater Than or Equal X
 * Status: accepted
 * 
 * You are given an array nums of non-negative integers. nums is
 * considered special if there exists a number x such that there
 * are exactly x numbers in nums that are greater than or equal
 * to x.
 * 
 * Notice that x does not have to be an element in nums.
 * 
 * Return x if the array is special, otherwise, return -1. It can
 * be proven that if nums is special, the value for x is unique.
 *
 * Input
 * 
0,4,3,0,4
 * 
 * Output
 * 
3
 * 
 * }</pre>
 */
public class Task1_Special_Array_With_X_Elements_Greater_Than_or_Equal_X {

    public int specialArray(int[] a) {
        if (a.length == 0) return 0;
        Arrays.sort(a);
        for (int i = 1; i <= a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j] < i) continue;
                if (i == a.length - j) return i;
                break;
            }
        }
        return -1;
    }
    
    @Test
    public void test() {
        assertEquals(6, specialArray(new int[] {3,9,7,8,3,8,6,6}));
        assertEquals(-1, specialArray(new int[] {0,0}));
        assertEquals(3, specialArray(new int[] {0,4,3,0,4}));
        assertEquals(-1, specialArray(new int[] {3,6,7,7,0}));
        assertEquals(2, specialArray(new int[] {3,4}));
        assertEquals(5, specialArray(new int[] {6,7,8,9,10}));
    }
}
