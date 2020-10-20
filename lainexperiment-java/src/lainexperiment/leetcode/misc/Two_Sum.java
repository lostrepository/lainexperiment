/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 18/10/2020
 * 
 * Problem: Two Sum
 * Status: accepted
 * 
 * Given an array of integers nums and an integer target, return indices of
 * the two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you
 * may not use the same element twice.
 * 
 * You can return the answer in any order.
 *
 * Example 1:
 * 
a = [2,7,11,15], s = 9
 * 
 * Output
 * 
[0,1]
 * 
 * Because a[0] + a[1] == 9, we return [0, 1].
 * 
 * Example 2:
 * 
nums = [3,2,4], target = 6
 * 
 * Output
 * 
[1,2]
 * 
 */

package lainexperiment.leetcode.misc;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class Two_Sum {

    private int[] find2sum(int[] a, int s) {
        var m = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < a.length; i++) {
            m.compute(a[i], (k, v) -> v == null? new ArrayList<>(): v).add(i); 
        }
        Arrays.sort(a);
        int lo = 0;
        int hi = a.length - 1;
        while (a[lo] + a[hi] != s) {
            if (a[lo] + a[hi] > s)
                hi--;
            else
                lo++;
            if (lo == hi) return null;
        }
        return new int[] {m.get(a[lo]).remove(0), m.get(a[hi]).remove(0)};
    }

    @Test
    public void test() {
        assertArrayEquals(new int[] {1, 2}, find2sum(new int[] {1, 2, 3}, 5));
        assertArrayEquals(new int[] {1, 2}, find2sum(new int[] {1, 2, 3, 5, 6}, 5));
        assertNull(find2sum(new int[] {1, 2, 3, 5, 6}, 15));
        assertArrayEquals(new int[] {0, 1}, find2sum(new int[] {2, 7, 11, 15}, 9));
        assertArrayEquals(new int[] {1, 2}, find2sum(new int[] {3, 2, 4}, 6));
        assertArrayEquals(new int[] {0, 1}, find2sum(new int[] {2, 7, 11, 15}, 9));
        assertArrayEquals(new int[] {0, 1}, find2sum(new int[] {3, 3}, 6));
    }
}
