/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._212;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/02/2020
 * 
 * Problem: Arithmetic Subarrays
 * Status: accepted
 * 
 * A sequence of numbers is called arithmetic if it consists
 * of at least two elements, and the difference between every
 * two consecutive elements is the same. More formally, a
 * sequence s is arithmetic if and only if
 * s[i+1] - s[i] == s[1] - s[0] for all valid i.
 * 
 * For example, these are arithmetic sequences:
 * 
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 
 * The following sequence is not arithmetic:
 * 
 * 1, 1, 2, 5, 7
 * 
 * You are given an array of n integers, nums, and two arrays
 * of m integers each, l and r, representing the m range queries,
 * where the ith query is the range [l[i], r[i]]. All the arrays
 * are 0-indexed.
 * 
 * Return a list of boolean elements answer, where answer[i] is
 * true if the subarray nums[l[i]], nums[l[i]+1], ... , nums[r[i]]
 * can be rearranged to form an arithmetic sequence, and false
 * otherwise.
 *
 * Input
 * 
nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
 * 
 * Output
 * 
true,false,true
 * 
 * }</pre>
 */
public class Task2_Arithmetic_Subarrays {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        var out = new ArrayList<Boolean>();
        for (int i = 0; i < r.length; i++) {
            out.add(isArithmetic(nums, l[i], r[i] + 1));
        }
        return out;
    }
    
    private boolean isArithmetic(int[] a, int s, int e) {
        a = Arrays.copyOfRange(a, s, e);
        Arrays.sort(a);
        int d = a[1] - a[0];
        for (int i = 2; i < a.length; i++) {
            if (a[i] - a[i-1] != d) return false;
        }
        return true;
    }

    @Test
    public void test() {
        assertEquals("[true, true, true, false]", checkArithmeticSubarrays(
            new int[] {3, -1, -5, -9, -8},
            new int[] {0,0,2, 0},
            new int[] {2,3,3,4}).toString());
        assertEquals("[true, false, true]", checkArithmeticSubarrays(
            new int[] {4,6,5,9,3,7},
            new int[] {0,0,2},
            new int[] {2,3,5}).toString());
        assertEquals("[false, true, false, false, true, true]", checkArithmeticSubarrays(
            new int[] {-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10},
            new int[] {0,1,6,4,8,7},
            new int[] {4,4,9,7,9,10}).toString());
    }
}
