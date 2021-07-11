/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;

import lainexperiment.utils.tuples.TupleInt;

/**
 * <pre>{@code
 * Date: 03/04/2021
 * 
 * Problem: Minimum Absolute Sum Difference
 * Status: accepted
 * 
 * You are given two positive integer arrays nums1 and nums2,
 * both of length n.
 * 
 * The absolute sum difference of arrays nums1 and nums2 is
 * defined as the sum of |nums1[i] - nums2[i]| for each
 * 0 <= i < n (0-indexed).
 * 
 * You can replace at most one element of nums1 with any other
 * element in nums1 to minimize the absolute sum difference.
 * 
 * Return the minimum absolute sum difference after replacing
 * at most one element in the array nums1. Since the answer may
 * be large, return it modulo 10^9 + 7.
 * 
 * Input
 * 
nums1 = [1,7,5], nums2 = [2,3,5]
 * 
 * Output
 * 
3
 * 
 * There are multiple ways to get 3. One of them is to replace:
 * 1, 7, 5 => 1, 1, 5
 * 
 * }</pre>
 */
public class Task3_Minimum_Absolute_Sum_Difference {


    public int minAbsoluteSumDiff(int[] n1, int[] n2) {
        var mod = 1_000_000_007;
        var l = IntStream.range(0, n1.length)
                .mapToObj(i -> new TupleInt(n1[i], n2[i]))
                .sorted(TupleInt.compareByAB())
                .collect(Collectors.toList());
        var total = l.stream().mapToLong(p -> abs(p.a() - p.b())).sum();
        var min = Long.MAX_VALUE;
        for (var p: l) {
            int i = Collections.binarySearch(l, new TupleInt(p.b(), 0), TupleInt.compareByA());
            if (i < 0) {
                i = -i - 2;
                var t = Integer.MAX_VALUE;
                if (i >= 0) {
                    t = Math.abs(l.get(i).a() - p.b());
                }
                if (i + 1 < l.size() && t > abs(l.get(i + 1).a() - p.b())) {
                    i++;
                }
            }
            var pj = l.get(i);
            var t = total - abs(p.a() - p.b()) + abs(pj.a() - p.b());
            if (t < min) {
                min = t;
            }
        }
        return (int) (min % mod);
    }

    @Test
    public void test() {
        assertEquals(3, minAbsoluteSumDiff(new int[] {1,7,5}, new int[] {2,3,5}));
        assertEquals(20, minAbsoluteSumDiff(new int[] {1,10,4,4,2,7}, new int[] {9,3,5,1,7,4}));
        assertEquals(0, minAbsoluteSumDiff(new int[] {2,4,6,8,10}, new int[] {2,4,6,8,10}));

    }
}
