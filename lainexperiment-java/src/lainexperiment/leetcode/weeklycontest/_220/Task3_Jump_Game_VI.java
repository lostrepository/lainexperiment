/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._220;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/12/2020
 * 
 * Problem: Jump Game VI
 * Status: accepted
 * 
 * You are given a 0-indexed integer array nums and an integer k.
 * 
 * You are initially standing at index 0. In one move, you can
 * jump at most k steps forward without going outside the
 * boundaries of the array. That is, you can jump from index i
 * to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * 
 * You want to reach the last index of the array (index n - 1).
 * Your score is the sum of all nums[j] for each index j you
 * visited in the array.
 * 
 * Return the maximum score you can get.
 *
 * Example 1:
 * 
nums = [1,-1,-2,4,-7,3], k = 2
 * 
 * Output
 * 
7
 * 
 * Example 2:
 * 
nums = [10,-5,-2,4,0,3], k = 3
 * 
 * Output
 * 
17
 * }</pre>
 */
public class Task3_Jump_Game_VI {

    Map<Integer, Integer> M = new HashMap<>();
    
    private int maxResult_(int[] nums, int i, int k) {
        if (i == nums.length - 1) return nums[i];
        System.out.println(nums[i]);
        //if (M.containsKey(i)) return M.get(i);
        int m = nums[i] + maxResult_(nums, i + 1, k);
        for (int j = 2; j <= k; j++) {
            if (i + j == nums.length) break;
            m = Math.max(m, nums[i] + maxResult_(nums, i + j, k));
        }
        M.put(i, m);
        return m;
    }
    
    public int maxResult2(int[] nums, int k) {
        M.clear();
        return maxResult_(nums, 0, k);
    }

    public int maxResult(int[] nums, int k) {
        class T {
            int max;
            boolean removed;
            T(int max) { this.max = max; }
            @Override
            public String toString() {
                return max + " " + removed;
            }
        };
        Queue<T> prio = new PriorityQueue<>(Comparator.<T>comparingInt(t -> t.max).reversed());
        T[] M = new T[nums.length];
        M[0] = new T(nums[0]);
        prio.add(M[0]);
        for (int i = 1; i < nums.length; i++) {
            T m = prio.peek();
            while (m.removed) {
                prio.poll();
                m = prio.peek();
            }
            M[i] = new T(m.max + nums[i]);
            prio.add(M[i]);
            if (i - k < 0) continue;
            M[i - k].removed = true;
        }
        return M[nums.length - 1].max;
    }
    
    @Test
    public void test() {
        assertEquals(7, maxResult(new int[] {1,-1,-2,4,-7,3}, 2));
        assertEquals(17, maxResult(new int[] {10,-5,-2,4,0,3}, 3));
        assertEquals(0, maxResult(new int[] {1,-5,-20,4,-1,3,-6,-3}, 2));
    }
}
