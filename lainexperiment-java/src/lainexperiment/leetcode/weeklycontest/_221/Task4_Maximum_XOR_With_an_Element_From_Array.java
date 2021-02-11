/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._221;

import static lainexperiment.utils.BitUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.Ival;

/**
 * <pre>{@code
 * Date: 26/12/2020
 * 
 * Problem: Maximum XOR With an Element From Array
 * Status: accepted
 * 
 * You are given an array nums consisting of non-negative
 * integers. You are also given a queries array, where
 * queries[i] = [xi, mi].
 * 
 * The answer to the ith query is the maximum bitwise XOR
 * value of xi and any element of nums that does not exceed
 * mi. In other words, the answer is max(nums[j] XOR xi) for
 * all j such that nums[j] <= mi. If all elements in nums are
 * larger than mi, then the answer is -1.
 * 
 * Return an integer array answer where
 * answer.length == queries.length and answer[i] is the answer
 * to the ith query.
 *
 * Example 1:
 * 
nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
 * 
 * Output
 * 
[3,3,7]
 * 
 * }</pre>
 */
public class Task4_Maximum_XOR_With_an_Element_From_Array {

    public int[] maximizeXor(int[] nums, int[][] queries) {
        nums = IntStream.of(nums)
            .sorted()
            .distinct()
            .toArray();
        var res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            var m = queries[i][1];
            var hi = Arrays.binarySearch(nums, 0, nums.length, m);
            if (hi < 0)
                hi = 0 - hi - 1;
            else
                hi++;
            res[i] = find(nums, new Ival(0, hi), queries[i][0], m);
        }
        return res;        
    }

    int find(int[] a, Ival range, int x, int m) {
        int max = 0;
        for (int i = length(m) - 1; i >= 0; i--) {
            if (isSet(m, i) != isSet(x, i)) {
                int nmax = (int) setBit(max, i, getBit(m, i));
                var r = prefixSearch(a, range, nmax, 32 - i).orElse(null);
                if (r != null) {
                    range = r;
                    if (r.e - r.s == 1) {
                        break;
                    }
                    max = nmax;
                    continue;
                }
            }
            int nmax = (int) setBit(max, i, getBit(m, i) == 1? 0: 1);
            var r = prefixSearch(a, range, nmax, 32 - i).orElse(null);
            if (r == null) {
                max = (int) setBit(max, i, isSet(m, i)? 1: 0);
                continue;
            }
            max = nmax;
            range = r;
        }
        if (range.e - range.s == 1) {
            return a[(int) range.s] ^ x;
        }
        return -1;
    }

    @Test
    public void test() {

        assertEquals("[3, 3, 7]", Arrays.toString(maximizeXor(new int[] {0,1,2,3,4}, new int[][] {
            {3,1},
            {1,3},
            {5,6}})));

        assertEquals("[15, -1, 5]", Arrays.toString(maximizeXor(new int[] {5,2,4,6,6,3}, new int[][] {
            {12,4},
            {8,1},
            {6,3}})));

        assertEquals("[1050219420, 844498962, 540190212, 539622516, 330170208]",
            Arrays.toString(maximizeXor(new int[] {536870912,0,534710168,330218644,142254206}, new int[][] {
                {558240772,1000000000},
                {307628050,1000000000},
                {3319300,1000000000},
                {2751604,683297522},
                {214004,404207941}})));
        
        assertEquals("[941207914, 1000166606, 974898268, 410099592, 981857259]",
            Arrays.toString(maximizeXor(new int[] {5190415,382222351,307,941343836,140523813}, new int[][] {
                {161078,1000000000},
                {59150482,1000000000},
                {33554432,961546806},
                {247062407,592122938},
                {743289828,1000000000}})));
        
        assertEquals("[1004194304]",
            Arrays.toString(maximizeXor(new int[] {0,3090096,4194304,845573555,0,778971744,267447,0,4194304,4194304,446819176,45001354,4194304,3777171,873518539,0,1165390,4194304,3372544,0}, new int[][] {
                {1000000000,1000000000}})));
    }
}
