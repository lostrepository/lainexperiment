/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._223;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.PairInt;
import lainexperiment.utils.QuickFindSet;

/**
 * <pre>{@code
 * Date: 09/01/2021
 * 
 * Problem: Minimize Hamming Distance After Swap Operations
 * Status: accepted
 * 
 * You are given two integer arrays, source and target,
 * both of length n. You are also given an array allowedSwaps
 * where each allowedSwaps[i] = [ai, bi] indicates that you
 * are allowed to swap the elements at index ai and index
 * bi (0-indexed) of array source. Note that you can swap
 * elements at a specific pair of indices multiple times
 * and in any order.
 * 
 * The Hamming distance of two arrays of the same length,
 * source and target, is the number of positions where
 * the elements are different. Formally, it is the number
 * of indices i for 0 <= i <= n-1 where
 * source[i] != target[i] (0-indexed).
 * 
 * Return the minimum Hamming distance of source and target
 * after performing any amount of swap operations on array
 * source.
 *
 * Example 1:
 * 
source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task3_Minimize_Hamming_Distance_After_Swap_Operations {

    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        QuickFindSet qf = new QuickFindSet(100_000);
        for (int i = 0; i < allowedSwaps.length; i++) {
            qf.join(allowedSwaps[i][0], allowedSwaps[i][1]);
        }
        int d = 0;
        List<PairInt> s = new ArrayList<>();
        List<PairInt> t = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            var l = qf.getId(i);
            if (l == -1) {
                if (source[i] != target[i]) d++;
                continue;
            }
            s.add(new PairInt(l, source[i]));
            t.add(new PairInt(l, target[i]));
        }
        var sm = s.stream()
            .collect(groupingBy(i -> i, counting()));
        var tm = t.stream()
            .collect(groupingBy(i -> i, counting()));
        for (var e: sm.entrySet()) {
            var n = tm.get(e.getKey());
            if (n == null) {
                d += e.getValue();
                continue;
            }
            if (e.getValue() > n)
                d += e.getValue() - n;
        }
        return d;
    }

    @Test
    public void test() {
        assertEquals(1, minimumHammingDistance(new int[] {1,2,3,4}, new int[] {2,1,4,5}, new int[][] {
            {0,1},
            {2,3}
        }));
        
        assertEquals(2, minimumHammingDistance(new int[] {1,2,3,4}, new int[] {1,3,2,4}, new int[][] {
            {0,1},
            {2,3}
        }));
        
        assertEquals(0, minimumHammingDistance(new int[] {5,1,2,4,3}, new int[] {1,5,4,2,3}, new int[][] {
            {0,4},
            {4,2},
            {1,3},
            {1,4}
        }));
        
        assertEquals(2, minimumHammingDistance(new int[] {1,2,3,4}, new int[] {1,3,2,4}, new int[][] {
        }));
        
        assertEquals(0, minimumHammingDistance(new int[] {1,2,3,4}, new int[] {1,2,3,4}, new int[][] {
        }));
        
        assertEquals(3, minimumHammingDistance(new int[] {1,1,1,1}, new int[] {1,2,3,4}, new int[][] {
        }));
        
        assertEquals(3, minimumHammingDistance(new int[] {1,1,1,1}, new int[] {1,2,3,4}, new int[][] {
            {0, 1}
        }));
    }
}
