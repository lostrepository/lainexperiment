/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._237;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 17/04/2021
 * 
 * Problem: Maximum Ice Cream Bars
 * Status: accepted
 * 
 * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
 * 
 * At the store, there are n ice cream bars. You are given an array costs of
 * length n, where costs[i] is the price of the ith ice cream bar in coins.
 * The boy initially has c coins to spend, and he wants to buy as many ice
 * cream bars as possible.
 * 
 *  Return the maximum number of ice cream bars the boy can buy with c coins.
 *  
 *  Note: The boy can buy the ice cream bars in any order.
 * 
 * Input
 * 
costs = [1,3,2,4,1], coins = 7
 * 
 * Output
 * 
4
 * 
 * }</pre>
 */
public class Task2_Maximum_Ice_Cream_Bars {
    
    public int maxIceCream(int[] a, int coins) {
        Arrays.sort(a);
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            if (coins < a[i]) break;
            coins -= a[i];
            r++;
        }
        return r;
    }

    @Test
    public void test() {
        assertEquals(6, maxIceCream(new int[] {1,6,3,1,2,5}, 20));
        assertEquals(0, maxIceCream(new int[] {10,6,8,7,7,8}, 5));
        assertEquals(4, maxIceCream(new int[] {1,3,2,4,1}, 7));
    }
}
