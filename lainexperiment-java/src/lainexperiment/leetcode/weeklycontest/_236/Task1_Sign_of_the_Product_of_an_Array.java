/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._236;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 10/04/2021
 * 
 * Problem: Sign of the Product of an Array
 * Status: accepted
 * 
 * There is a function signFunc(x) that returns:
 * 
 * - 1 if x is positive.
 * - -1 if x is negative.
 * - 0 if x is equal to 0.
 * 
 * You are given an integer array nums. Let product be the
 * product of all values in the array nums.
 * 
 * Return signFunc(product).
 * 
 * Input
 * 
-1,-2,-3,-4,3,2,1
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class Task1_Sign_of_the_Product_of_an_Array {
    
    public int arraySign(int[] nums) {
        var sign = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) return 0;
            if (nums[i] < 0) sign *= -1;
        }
        return sign;
    }

    @Test
    public void test() {
        assertEquals(-1, arraySign(new int[] {-1,1,-1,1,-1}));
        assertEquals(0, arraySign(new int[] {1,5,0,2,-3}));
        assertEquals(1, arraySign(new int[] {-1,-2,-3,-4,3,2,1}));
    }
}
