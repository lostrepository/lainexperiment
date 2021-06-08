/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._214;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.MathUtils;

/**
 * <pre>{@code
 * Date: 07/06/2021
 * 
 * Problem: Sell Diminishing-Valued Colored Balls
 * Status: accepted
 * 
 * You have an inventory of different colored balls, and there is a
 * customer that wants orders balls of any color.
 * 
 * The customer weirdly values the colored balls. Each colored ball's
 * value is the number of balls of that color you currently have in
 * your inventory. For example, if you own 6 yellow balls, the customer
 * would pay 6 for the first yellow ball. After the transaction, there
 * are only 5 yellow balls left, so the next yellow ball is then valued
 * at 5 (i.e., the value of the balls decreases as you sell more to the
 * customer).
 * 
 * You are given an integer array, inventory, where inventory[i] represents
 * the number of balls of the ith color that you initially own. You are
 * also given an integer orders, which represents the total number of
 * balls that the customer wants. You can sell the balls in any order.
 * 
 * Return the maximum total value that you can attain after selling orders
 * colored balls. As the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * 
inventory = [2,5], orders = 4
 * 
 * Output
 * 
14
 * 
 * }</pre>
 */
public class Task3_Sell_Diminishing_Valued_Colored_Balls {

    private static final long MOD = 1_000_000_007L;

    public int maxProfit(int[] inventory, int orders) {
        var l = Arrays.stream(inventory)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(toList());
        int i = 1;
        int c = 1;
        long r = 0;
        long p = l.get(0);
        l.add(0);
        while (i < l.size() && orders > 0) {
            long n = l.get(i);
            if (p == n) {
                c++;
                i++;
                continue;
            }
            // how many orders we can make
            long t = Math.min((p - n) * c, orders);
            long n1 = t / c;
            long n2 = t % c;
            long a = p - n1 + 1;
            if (a <= p)
                r += MathUtils.arithmeticSum(a, p, MOD) * c % MOD;
            r += n2 * (p - n1) % MOD;
            orders -= t;
            r %= MOD;
            p = n;
            c++;
            i++;
        }
        return (int) r;
    }

    @Test
    public void test() {
        assertEquals(373219333, maxProfit(new int[] {497978859,167261111,483575207,591815159}, 836556809));
        assertEquals(70267492, maxProfit(new int[] {773160767}, 252264991));
        assertEquals(14, maxProfit(new int[] {2, 5}, 4));
        assertEquals(19, maxProfit(new int[] {3, 5}, 6));
        assertEquals(110, maxProfit(new int[] {2,8,4,10,6}, 20));
        assertEquals(21, maxProfit(new int[] {1000000000}, 1000000000));
    }
}
