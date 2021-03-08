/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._231;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.MathUtils;

/**
 * <pre>{@code
 * Date: 06/03/2021
 * 
 * Problem: Minimum Elements to Add to Form a Given Sum
 * Status: accepted
 * 
 * You are given an integer array nums and two integers limit and
 * goal. The array nums has an interesting property that
 * abs(nums[i]) <= limit.
 * 
 * Return the minimum number of elements you need to add to make
 * the sum of the array equal to goal. The array must maintain
 * its property that abs(nums[i]) <= limit.
 * 
 * Note that abs(x) equals x if x >= 0, and -x otherwise.
 *
 * Input
 * 
nums = [1,-1,1], limit = 3, goal = -4
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task2_Minimum_Elements_to_Add_to_Form_a_Given_Sum {
    
    public int minElements(int[] a, int limit, int goal) {
        long sum = IntStream.of(a).asLongStream().sum();
        if (sum == goal) return 0;
        return (int) MathUtils.ceilDiv(Math.abs(goal - sum), limit);
    }
    
    @Test
    public void test() {
        assertEquals(2, minElements(new int[] {1,-1,1}, 3, -4));
        assertEquals(1, minElements(new int[] {1,-10,9,1}, 100, 0));
    }
}
