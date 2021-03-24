/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._208;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 15/03/2020
 * 
 * Problem: Maximum Profit of Operating a Centennial Wheel
 * Status: accepted
 * 
 * You are the operator of a Centennial Wheel that has four gondolas,
 * and each gondola has room for up to four people. You have the
 * ability to rotate the gondolas counterclockwise, which costs you
 * runningCost dollars.
 * 
 * You are given an array customers of length n where customers[i]
 * is the number of new customers arriving just before the ith
 * rotation (0-indexed). This means you must rotate the wheel i times
 * before the customers[i] customers arrive. You cannot make customers
 * wait if there is room in the gondola. Each customer pays boardingCost
 * dollars when they board on the gondola closest to the ground and
 * will exit once that gondola reaches the ground again.
 * 
 * You can stop the wheel at any time, including before serving all
 * customers. If you decide to stop serving customers, all subsequent
 * rotations are free in order to get all the customers down safely.
 * Note that if there are currently more than four customers waiting
 * at the wheel, only four will board the gondola, and the rest will
 * wait for the next rotation.
 * 
 * Return the minimum number of rotations you need to perform to
 * maximize your profit. If there is no scenario where the profit
 * is positive, return -1.
 *
 * Input
 * 
customers = [10,9,6], boardingCost = 6, runningCost = 4
 * 
 * Output
 * 
7
 * 
 * 1. 10 customers arrive, 4 board and 6 wait for the next gondola, the wheel rotates.
 *    Current profit is 4 * $6 - 1 * $4 = $20.
 * 2. 9 customers arrive, 4 board and 11 wait (2 originally waiting, 9
 *    newly waiting), the wheel rotates. Current profit is 8 * $6 - 2 * $4 = $40.
 * 3. The final 6 customers arrive, 4 board and 13 wait, the wheel rotates.
 *    Current profit is 12 * $6 - 3 * $4 = $60.
 * 4. 4 board and 9 wait, the wheel rotates. Current profit is 16 * $6 - 4 * $4 = $80.
 * 5. 4 board and 5 wait, the wheel rotates. Current profit is 20 * $6 - 5 * $4 = $100.
 * 6. 4 board and 1 waits, the wheel rotates. Current profit is 24 * $6 - 6 * $4 = $120.
 * 7. 1 boards, the wheel rotates. Current profit is 25 * $6 - 7 * $4 = $122.
 * 
 * }</pre>
 */
public class Task2_Maximum_Profit_of_Operating_a_Centennial_Wheel {

    public int minOperationsMaxProfit(int[] a, int boardingCost, int runningCost) {
        int i = 0;
        int max = 0;
        int b = 0;
        int t = 1;
        int res = -1;
        while (i < a.length) {
            int c = 0;
            while (i <= t - 1) {
                var take = Math.min(a[i], 4 - c);
                a[i] -= take;
                c += take;
                if (a[i] == 0) i++;
                if (i == a.length) break;
                if (c == 4) break;
            }
            b += c;
            var r = b * boardingCost - t * runningCost;
            //System.out.println(r);
            if (max < r) {
                res = t;
                max = r;
            }
            t++;
        }
        return res;
    }

    @Test
    public void test() {
        assertEquals(9, minOperationsMaxProfit(new int[] {10,10,6,4,7}, 3, 8));
        assertEquals(-1, minOperationsMaxProfit(new int[] {3,4,0,5,1}, 1, 92));
        assertEquals(7, minOperationsMaxProfit(new int[] {10,9,6}, 6, 4));
        assertEquals(3, minOperationsMaxProfit(new int[] {8,3}, 5, 6));
    }
}
