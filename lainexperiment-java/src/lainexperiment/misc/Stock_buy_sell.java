/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <pre>{@code
 * Date: 08/05/2016
 * 
 * Problem
 * 
 * The cost of a stock on each day is given in an array, find the max profit that you 
 * can make by buying and selling in those days. It is possible to perform only one 
 * operation per day. We can buy and sell more than once but at any time we can keep 
 * only one order.
 *
 * Input
 * 
100, 180, 260, 310, 40, 535, 695
 * 
 * Output
 * 
865
 * 
 * }</pre>
 */
public class Stock_buy_sell {

    static int maxProfit(int[] a) {
        int s = 0;
        int res = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i - 1] <= a[i]) continue;
            res += a[i - 1] - a[s];
            s = i;
        }
        res += a[a.length - 1] - a[s];
        return res;
    }
    
    public static void main(String[] args) {
        assertEquals(2, maxProfit(new int[]{1, 2, 3}));
        assertEquals(1, maxProfit(new int[]{1, 2, 1}));
        assertEquals(865, maxProfit(new int[]{100, 180, 260, 310, 40, 535, 695}));
    }
    
}
