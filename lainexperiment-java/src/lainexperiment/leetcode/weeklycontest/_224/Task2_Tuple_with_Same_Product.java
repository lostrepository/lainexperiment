/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._224;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.MathUtils;

/**
 * <pre>{@code
 * Date: 16/01/2021
 * 
 * Problem: Tuple with Same Product
 * Status: accepted
 * 
 * Given an array nums of distinct positive integers, return
 * the number of tuples (a, b, c, d) such that a * b = c * d
 * where a, b, c, and d are elements of nums, and
 * a != b != c != d.
 *
 * Example 1:
 * 
nums = [2,3,4,6]
 * 
 * Output
 * 
8
 * 
 * There are 8 valid tuples:
 * (2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
 * (3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 * }</pre>
 */
public class Task2_Tuple_with_Same_Product {

    public int tupleSameProduct(int[] a) {
        Arrays.sort(a);
        var m = new HashMap<Integer, Integer>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                m.merge(a[i] * a[j], 1, Integer::sum);
            }
        }
        var c = 0;
        for (var e: m.entrySet()) {
            if (e.getValue() == 1) continue;
            c += MathUtils.unorderedCombinations(e.getValue(), 2) * 8;
        }
        return c;
    }
    
    @Test
    public void test() {
        assertEquals(8, tupleSameProduct(new int[] {2,3,4,6}));
        assertEquals(16, tupleSameProduct(new int[] {1,2,4,5,10}));
        assertEquals(40, tupleSameProduct(new int[] {2,3,4,6,8,12}));
        assertEquals(0, tupleSameProduct(new int[] {2,3,5,7}));
    }
}
