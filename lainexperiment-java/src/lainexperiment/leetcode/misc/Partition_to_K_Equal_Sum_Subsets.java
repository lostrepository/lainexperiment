/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.misc;

import static lainexperiment.utils.BitUtils.isSet;
import static lainexperiment.utils.BitUtils.set;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 01/05/2021
 * 
 * Problem: Partition to K Equal Sum Subsets
 * Status: accepted
 * 
 * Given an integer array nums and an integer k, return true if it
 * is possible to divide this array into k non-empty subsets whose
 * sums are all equal.
 *
 * Example 1:
 * 
nums = [4,3,2,3,5,2,1], k = 4
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Partition_to_K_Equal_Sum_Subsets {

    /**
     * @param used bitmask of used items
     * @param total sum of items left
     * @param M memoization
     * @param nums
     * @param target sum per subset
     */
    boolean solve(int used, int total, Boolean[] M, int[] nums, int target) {
        if (M[used] != null) return M[used];
        M[used] = false;
        int max = (total - 1) % target + 1;
        for (int i = 0; i < nums.length; i++) {
            if (!isSet(used, i) && nums[i] <= max) {
                if (solve(set(used, i), total - nums[i], M, nums, target)) {
                    M[used] = true;
                    break;
                }
            }
        }
        return M[used];
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;
        int len = 1 << nums.length;
        Boolean[] M = new Boolean[len];
        M[len - 1] = true;
        var t = sum / k;
        if (t == 0) return true;
        return solve(0, sum, M, nums, t);
    }

    @Test
    public void test() {
        assertEquals(true, canPartitionKSubsets(new int[] {4,3,2,3,5,2,1}, 4));
        assertEquals(false, canPartitionKSubsets(new int[] {1,2,3,4}, 3));
    }
}
