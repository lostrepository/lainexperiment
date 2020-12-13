/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._217;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/28/2020
 * 
 * Problem: Richest Customer Wealth
 * Status: accepted
 * 
 * You are given an m x n integer grid accounts where accounts[i][j]
 * is the amount of money the i​​​​​​​​​​​th​​​​ customer has in the j​​​​​​​​​​​th​​​​ bank. Return
 * the wealth that the richest customer has.
 * 
 * A customer's wealth is the amount of money they have in all their
 * bank accounts. The richest customer is the customer that has the
 * maximum wealth.
 *
 * Example 1:
 * 
accounts = [[1,2,3],[3,2,1]]
 * 
 * Output
 * 
6
 * 
 * }</pre>
 */
public class Task1_Richest_Customer_Wealth {

    public int maximumWealth(int[][] accounts) {
        int m = Integer.MIN_VALUE;
        for (int i = 0; i < accounts.length; i++) {
            int s = Arrays.stream(accounts[i])
                .sum();
            m = Math.max(m, s);
        }
        return m;
    }

    @Test
    public void test() {
        assertEquals(6, maximumWealth(new int[][] {
            {1, 2, 3},
            {0, 5, 0},
        }));
        assertEquals(9, maximumWealth(new int[][] {
            {1, 2, 3},
            {0, 5, 0},
            {1, 4, 4}
        }));
    }
}
