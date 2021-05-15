/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.hackerrank.nvidia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 19/04/2021
 *
 * Problem: Minimum Value to Get Positive Step by Step Sum
 * Status: partially accepted (1 test case timed out)
 * 
 * Given an array of integers nums, you start with an initial
 * positive value x.
 * 
 * In each iteration, you calculate the step by step sum of x
 * plus elements in nums (from left to right).
 * 
 * Return the minimum positive value of x such that the step by
 * step sum is never less than 1.
 * 
 * Input
 * 
-3,2,-3,4,2
 * 
 * Output
 * 
5
 * 
 * i sum x
 * 0 -3  4
 * 1 -1  4
 * 2 -4  5
 * 3  0  5
 * 4  2  5
 * 
 * }</pre>
 */
public class Minimum_Value_to_Get_Positive_Step_by_Step_Sum {

    public int minStartValue(int[] nums) {
        int x = 1;
        while (true) {
            int sum = 0;
            boolean found = true;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (x + sum >= 1) continue;
                found = false;
                break;
            }
            if (found) return x;
            x++;
        }
    }

    @Test
    public void test() {
        assertEquals(5, minStartValue(new int[] {-3,2,-3,4,2}));
    }
}
