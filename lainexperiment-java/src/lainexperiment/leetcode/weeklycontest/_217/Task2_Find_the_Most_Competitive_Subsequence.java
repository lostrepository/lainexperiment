/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._217;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/28/2020
 * 
 * Problem: Find the Most Competitive Subsequence
 * Status: accepted
 * 
 * Given an integer array nums and a positive integer k, return
 * the most competitive subsequence of nums of size k.
 * 
 * An array's subsequence is a resulting sequence obtained by
 * erasing some (possibly zero) elements from the array.
 * 
 * We define that a subsequence a is more competitive than a
 * subsequence b (of the same length) if in the first position
 * where a and b differ, subsequence a has a number less than the
 * corresponding number in b. For example, [1,3,4] is more competitive
 * than [1,3,5] because the first position they differ is at the final
 * number, and 4 is less than 5.
 *
 * Example 1:
 * 
nums = [3,5,2,6], k = 2
 * 
 * Output
 * 
2, 6
 * 
 * Among the set of every possible subsequence: {[3,5], [3,2], [3,6],
 * [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 * 
 * }</pre>
 */
public class Task2_Find_the_Most_Competitive_Subsequence {

    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!s.isEmpty() && s.peek() > nums[i]) {
                if (k - s.size() == nums.length - i) {
                    break;
                }
                s.pop();
                if (s.isEmpty()) {
                    break;
                }
            }
            if (s.size() < k)
                s.push(nums[i]);
        }
        return s.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
    
    @Test
    public void test() {
        assertArrayEquals(new int[] {2, 5} , mostCompetitive(new int[] {2, 5, 6, 7}, 2));
        assertArrayEquals(new int[] {2, 6} , mostCompetitive(new int[] {5, 2, 7, 6}, 2));
        assertArrayEquals(new int[] {2, 6} , mostCompetitive(new int[] {3, 5, 2, 6}, 2));
        assertArrayEquals(new int[] {2,3,3,4} , mostCompetitive(new int[] {2,4,3,3,5,4,9,6}, 4));
        assertArrayEquals(new int[] {2,3,3} , mostCompetitive(new int[] {2,4,3,3,5,4,9,6}, 3));
        assertArrayEquals(new int[] {1, 2} , mostCompetitive(new int[] {2,1,3,3,5,4,2,6}, 2));
        assertArrayEquals(new int[] {2} , mostCompetitive(new int[] {5, 4, 4, 2}, 1));
        assertArrayEquals(new int[] {4, 2} , mostCompetitive(new int[] {5, 4, 4, 2}, 2));
        assertArrayEquals(new int[] {8, 80, 2} , mostCompetitive(new int[] {18,24, 42, 8,80,2}, 3));
        assertArrayEquals(new int[]{3,4}, mostCompetitive(new int[]{5, 8, 3, 4}, 2));
        assertArrayEquals(new int[]{3,4}, mostCompetitive(new int[]{5, 8, 3, 4, 9}, 2));
        assertArrayEquals(new int[]{1,2}, mostCompetitive(new int[]{5, 1, 8, 3, 4, 2}, 2));
        assertArrayEquals(new int[]{1,0}, mostCompetitive(new int[]{5, 1, 8, 3, 4, 2, 0}, 2));
        assertArrayEquals(new int[]{0}, mostCompetitive(new int[]{5, 1, 8, 3, 4, 2, 0}, 1));
        assertArrayEquals(new int[]{1,2,0}, mostCompetitive(new int[]{5, 1, 8, 3, 4, 2, 0}, 3));
    }

}
