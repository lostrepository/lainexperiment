/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._233;

import static lainexperiment.utils.MathUtils.sumMod;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.MathUtils;

/**
 * <pre>{@code
 * Date: 22/03/2021
 * 
 * Problem: Number of Orders in the Backlog
 * Status: accepted
 * 
 * You are given a 2D integer array orders, where each
 * orders[i] = [price_i, amount_i, orderType_i] denotes that amount_i
 * orders have been placed of type orderType_i at the price price_i.
 * The orderType_i is:
 * 
 * - 0 if it is a batch of buy orders, or
 * - 1 if it is a batch of sell orders.
 * 
 * Note that orders[i] represents a batch of amount_i independent orders
 * with the same price and order type. All orders represented by orders[i]
 * will be placed before all orders represented by orders[i+1] for all
 * valid i.
 * 
 * There is a backlog that consists of orders that have not been executed.
 * The backlog is initially empty. When an order is placed, the following
 * happens:
 * 
 * - If the order is a buy order, you look at the sell order with the
 * smallest price in the backlog. If that sell order's price is smaller
 * than or equal to the current buy order's price, they will match and be
 * executed, and that sell order will be removed from the backlog. Else,
 * the buy order is added to the backlog.
 * 
 * - Vice versa, if the order is a sell order, you look at the buy order
 * with the largest price in the backlog. If that buy order's price is
 * larger than or equal to the current sell order's price, they will match
 * and be executed, and that buy order will be removed from the backlog.
 * Else, the sell order is added to the backlog.
 * 
 * Return the total amount of orders in the backlog after placing all the
 * orders from the input. Since this number can be large, return it
 * modulo 10^9 + 7.
 * 
 * Input 1
 * 
orders = [[10,5,0],[15,2,1],[25,1,1],[30,4,0]]
 * 
 * Output
 * 
6
 * 
 * }</pre>
 */
public class Task2_Number_of_Orders_in_the_Backlog {
    public final long MOD = 1_000_000_007L;
    
    public int getNumberOfBacklogOrders(int[][] orders) {
        var sell = new TreeMap<Long, Long>();
        var buy = new TreeMap<Long, Long>(Collections.reverseOrder());
        for (int i = 0; i < orders.length; i++) {
            var o = orders[i];
            if (o[2] == 0) { //buy
                place(sell.headMap((long)o[0], true), buy, false, o[0], o[1]);
            }
            if (o[2] == 1) {
                place(buy.headMap((long)o[0], true), sell, true, o[0], o[1]);
            }
        }
        long r = 0;
        for (var v: sell.values()) r = sumMod(r, v);
        for (var v: buy.values()) r = sumMod(r, v); 
        return (int) r;
    }
    
    private void place(Map<Long, Long> orders, Map<Long, Long> log, boolean sell, long price, long amount) {
        var iter = orders.entrySet().iterator();
        while (iter.hasNext()) {
            var e = iter.next();
            var take = Math.min(e.getValue(), amount);
            e.setValue(e.getValue() - take);
            if (e.getValue() == 0) iter.remove();
            amount -= take;
            if (amount == 0) return;
        }
        log.merge(price, amount, MathUtils::sumMod);
    }

    @Test
    public void test() {
        assertEquals(107, getNumberOfBacklogOrders(new int[][] {
            {10,5,0},{15,2,1},{25,100,1}}));
        assertEquals(40, getNumberOfBacklogOrders(new int[][] {
            {27,30,0},{28,30,1}, {27,10,1}, {27,10,1}, {27,10,1}, {27,10,1}}));
        assertEquals(60, getNumberOfBacklogOrders(new int[][] {
            {27,30,0},{28,30,1}}));
        assertEquals(82, getNumberOfBacklogOrders(new int[][] {
            {27,30,0},{10,10,1},{28,17,1},{19,28,0},{16,8,1},{14,22,0},{12,18,1},{3,15,0},{25,6,1}}));
        assertEquals(1000000000, getNumberOfBacklogOrders(new int[][] {
            {7,1000000000,1}}));
        assertEquals(999999984, getNumberOfBacklogOrders(new int[][] {
            {7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}}));
        assertEquals(6, getNumberOfBacklogOrders(new int[][] {
            {10,5,0},{15,2,1},{25,1,1},{30,4,0}}));
    }
}
