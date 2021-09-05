/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._256;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 28/08/2021
 * 
 * Problem: Find the Kth Largest Integer in the Array
 * Status: accepted
 * 
 * You are given an array of strings nums and an integer k.
 * Each string in nums represents an integer without leading zeros.
 * 
 * Return the string that represents the kth largest integer
 * in nums.
 * 
 * Note: Duplicate numbers should be counted distinctly. For
 * example, if nums is ["1","2","2"], "2" is the first largest
 * integer, "2" is the second-largest integer, and "1" is the third
 * largest integer.
 * 
 * Input
 * 
nums = ["2","21","12","1"], k = 3
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task2_Find_the_Kth_Largest_Integer_in_the_Array {
    
    public String kthLargestNumber(String[] a, int k) {
        return Arrays.stream(a)
                .map(BigInteger::new)
                .sorted(Comparator.reverseOrder())
                .skip(k - 1)
                .findFirst()
                .map(BigInteger::toString)
                .get();
    }
    
    @Test
    public void test() {
        assertEquals("0", kthLargestNumber(new String[] {"0","0"}, 2));
        assertEquals("2", kthLargestNumber(new String[] {"2","21","12","1"}, 3));
        assertEquals("3", kthLargestNumber(new String[] {"3","6","7","10"}, 4));

    }
}
