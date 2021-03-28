/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._226;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.pairs.Pair;
import lainexperiment.utils.pairs.PairInt;

/**
 * <pre>{@code
 * Date: 30/01/2021
 * 
 * Problem: Restore the Array From Adjacent Pairs
 * Status: accepted
 * 
 * There is an integer array nums that consists of n unique
 * elements, but you have forgotten it. However, you do remember
 * every pair of adjacent elements in nums.
 * 
 * You are given a 2D integer array adjacentPairs of size n - 1
 * where each adjacentPairs[i] = [ui, vi] indicates that the
 * elements ui and vi are adjacent in nums.
 * 
 * It is guaranteed that every adjacent pair of elements nums[i]
 * and nums[i+1] will exist in adjacentPairs, either as
 * [nums[i], nums[i+1]] or [nums[i+1], nums[i]]. The pairs can
 * appear in any order.
 * 
 * Return the original array nums. If there are multiple solutions,
 * return any of them.
 *
 * Example 1:
 * 
2,1
3,4
3,2
 * 
 * Output
 * 
1,2,3,4
 * 
 * }</pre>
 */
public class Task2_Restore_the_Array_From_Adjacent_Pairs {
    
    public int[] restoreArray(int[][] adjacentPairs) {
        var ends = Arrays.stream(adjacentPairs)
            .flatMap(a -> Stream.of(a[0], a[1]))
            .collect(groupingBy(i -> i, counting())).entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Entry::getKey)
            .collect(toList());
        var pairs = new ArrayList<PairInt>();
        for (var a: adjacentPairs) {
            pairs.add(new PairInt(a[0], a[1]));
            pairs.add(new PairInt(a[1], a[0]));
        }
        pairs.sort(Pair::compareByAB);
        var out = new ArrayList<Integer>();
        int cur = ends.get(0);
        var visited = new HashSet<Integer>();
        while (!pairs.isEmpty()) {
            int p = Collections.binarySearch(pairs, new PairInt(cur, 0), Pair::compareByA);
            if (p < 0) {
                break;
            }
            int next = pairs.remove(p).b;
            if (visited.contains(next)) continue;
            out.add(cur);
            visited.add(cur);
            cur = next;
        }
        out.add(cur);
        return out.stream().mapToInt(i -> i).toArray();
    }
    
    @Test
    public void test() {
        assertEquals("[1, 2, 3, 4]", Arrays.toString(restoreArray(new int[][] {
            {2,1},{3,4},{3,2}
        })));
        assertEquals("[-2, 4, 1, -3]", Arrays.toString(restoreArray(new int[][] {
            {4,-2},{1,4},{-3,1}
        })));
        assertEquals("[100000, -100000]", Arrays.toString(restoreArray(new int[][] {
            {100000,-100000}
        })));
    }
}
