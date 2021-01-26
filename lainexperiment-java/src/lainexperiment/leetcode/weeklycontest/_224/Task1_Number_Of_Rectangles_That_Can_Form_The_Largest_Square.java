/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._224;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 16/01/2021
 * 
 * Problem: Number Of Rectangles That Can Form The Largest Square
 * Status: accepted
 * 
 * You are given an array rectangles where rectangles[i] = [li, wi]
 * represents the ith rectangle of length li and width wi.
 * 
 * You can cut the ith rectangle to form a square with a side length
 * of k if both k <= li and k <= wi. For example, if you have a
 * rectangle [4,6], you can cut it to get a square with a side length
 * of at most 4.
 * 
 * Let maxLen be the side length of the largest square you can obtain
 * from any of the given rectangles.
 * 
 * Return the number of rectangles that can make a square with a side
 * length of maxLen.
 *
 * Example 1:
 * 
rectangles = [[5,8],[3,9],[5,12],[16,5]]
 * 
 * Output
 * 
3
 * 
 * }</pre>
 */
public class Task1_Number_Of_Rectangles_That_Can_Form_The_Largest_Square {

    public int countGoodRectangles(int[][] r) {
        int maxLen = 0;
        for (int i = 0; i < r.length; i++) {
            var l = Math.min(r[i][0], r[i][1]);
            maxLen = Math.max(maxLen, l);
        }
        int c = 0;
        for (int i = 0; i < r.length; i++) {
            if (maxLen <= Math.min(r[i][0], r[i][1])) c++;
        }
        return c;
    }

    @Test
    public void test() {
        assertEquals(3, countGoodRectangles(new int[][] {
            {5,8},
            {3,9},
            {5,12},
            {16,5}
        }));
        assertEquals(3, countGoodRectangles(new int[][] {
            {2,3},
            {3,7},
            {4,3},
            {3,7}
        }));
    }
}
