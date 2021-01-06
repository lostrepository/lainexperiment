/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._222;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static lainexperiment.utils.MathUtils.unorderedCombinations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.BitUtils;

/**
 * <pre>{@code
 * Date: 01/02/2021
 * 
 * Problem: Count Good Meals
 * Status: accepted
 * 
 * A good meal is a meal that contains exactly two different
 * food items with a sum of deliciousness equal to a power
 * of two.
 * 
 * You can pick any two different foods to make a good meal.
 * 
 * Given an array of integers deliciousness where
 * deliciousness[i] is the deliciousness of the i​​​​​​th​​​​​​​​ item of
 * food, return the number of different good meals you can
 * make from this list modulo 109 + 7.
 * 
 * Note that items with different indices are considered
 * different even if they have the same deliciousness value.
 *
 * Example 1:
 * 
deliciousness = [1,3,5,7,9]
 * 
 * Output
 * 
4
 * 
 * }</pre>
 */
public class Task2_Count_Good_Meals {

    static final int MOD = 1_000_000_007;

    public int countPairs(int[] a) {
        var m = Arrays.stream(a)
            .sorted()
            .boxed()
            .collect(groupingBy(Function.identity(), LinkedHashMap::new, counting()));
        var amounts = m.keySet().stream()
                .mapToInt(Integer::intValue)
                .toArray();
        var counts = m.values().stream()
                .mapToInt(Long::intValue)
                .toArray();
        int[] n = new int[amounts.length + 1];
        for (int i = 0; i < amounts.length; i++) {
            int j = i + 1;
            n[j] = n[j - 1];
            for (int p = (int) Math.pow(2, BitUtils.length(Math.max(0, amounts[i] - 1))); p <= amounts[i] * 2; p *= 2) {
                var d = p - amounts[i];
                if (d > amounts[i]) continue;
                int lo = Arrays.binarySearch(amounts, d);
                if (lo < 0) continue;
                int c = counts[lo];
                if (lo == i) {
                    if (c == 1) continue;
                    n[j] += unorderedCombinations(c, 2, MOD);
                } else
                    n[j] += c * counts[i];
                n[j] %= MOD;
            }
            
        }
        return n[n.length - 1];
    }

    @Test
    public void test() {
        System.out.println(unorderedCombinations(33, 2, MOD));
        assertEquals(1, countPairs(new int[] {0,2}));
        assertEquals(3, countPairs(new int[] {64,64,64}));
        assertEquals(3, countPairs(new int[] {0,0,0,2}));
        assertEquals(7, countPairs(new int[] {0,0,0,2,2}));
        assertEquals(4, countPairs(new int[] {1,3,5,7,9}));
        assertEquals(15, countPairs(new int[] {1,1,1,3,3,3,7}));
        assertEquals(12, countPairs(new int[] {149,107,1,63,0,1,6867,1325,5611,2581,39,89,46,18,12,20,22,234}));
    }
}
