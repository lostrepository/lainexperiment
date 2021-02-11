/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._220;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 19/12/2020
 * 
 * Problem: Maximum Erasure Value
 * Status: accepted
 * 
 * You are given an array of positive integers nums and want
 * to erase a subarray containing unique elements. The score
 * you get by erasing the subarray is equal to the sum of
 * its elements.
 * 
 * Return the maximum score you can get by erasing exactly
 * one subarray.
 * 
 * An array b is called to be a subarray of a if it forms a
 * contiguous subsequence of a, that is, if it is equal to
 * a[l],a[l+1],...,a[r] for some (l,r).
 *
 * Example 1:
 * 
4,2,4,5,6
 * 
 * Output
 * 
17
 *
 * Example 2:
 * 
5,2,1,2,5,2,1,2,5
 * 
 * Output
 * 
8
 * }</pre>
 */
public class Task2_Maximum_Erasure_Value {

    public int maximumUniqueSubarray(int[] a) {
        var set = new HashSet<Integer>();
        var list = new LinkedList<Integer>();
        int m = 0;
        int s = 0;
        for (int i = 0; i < a.length; i++) {
            while (set.contains(a[i])) {
                var n = list.pollFirst();
                s -= n;
                set.remove(n);
            }
            s += a[i];
            set.add(a[i]);
            list.add(a[i]);
            m = Math.max(m, s);
        }
        return m;
    }

    @Test
    public void test() {
        assertEquals(17, maximumUniqueSubarray(new int[] {4,2,4,5,6}));
        assertEquals(8, maximumUniqueSubarray(new int[] {5,2,1,2,5,2,1,2,5}));

    }
}
