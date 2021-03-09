/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._230;

import static lainexperiment.utils.MathUtils.closest;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 27/02/2021
 * 
 * Problem: Closest Dessert Cost
 * Status: accepted
 * 
 * You would like to make dessert and are preparing to buy the ingredients.
 * You have n ice cream base flavors and m types of toppings to choose from.
 * You must follow these rules when making your dessert:
 * 
 * - There must be exactly one ice cream base.
 * - You can add one or more types of topping or have no toppings at all.
 * - There are at most two of each type of topping.
 * 
 * You are given three inputs:
 * 
 * - baseCosts, an integer array of length n, where each baseCosts[i]
 *   represents the price of the ith ice cream base flavor.
 * - toppingCosts, an integer array of length m, where each toppingCosts[i]
 *   is the price of one of the ith topping.
 * - target, an integer representing your target price for dessert.
 * 
 * You want to make a dessert with a total cost as close to target as possible.
 * 
 * Return the closest possible cost of the dessert to target. If there are
 * multiple, return the lower one.
 *
 * Input
 * 
baseCosts = [1,7], toppingCosts = [3,4], target = 10
 * 
 * Output
 * 
10
 * 
 * }</pre>
 */
public class Task2_Closest_Dessert_Cost {
    
    int COST;
    
    public int closestCost(int[] b, int[] t, int target) {
        int c = b[0];
        for (int i = 0; i < b.length; i++) {
            if (b[i] == target) return b[i];
            if (target < b[i]) {
                COST = 0;
            } else {
                COST = t[0];
                cost(t, 0, target - b[i], 0);
            }
            c = closest(target, c, b[i] + COST);
        }
        return c;
    }

    private void cost(int[] t, int i, int target, int acc) {
        if (i == t.length) {
            COST = closest(target, acc, COST);
            return;
        }
        cost(t, i + 1, target, acc + t[i]);
        cost(t, i + 1, target, acc + t[i] * 2);
        cost(t, i + 1, target, acc);
    }

    @Test
    public void test() {
        assertEquals(6, closestCost(new int[] {8,6}, new int[] {7}, 6));
        assertEquals(10, closestCost(new int[] {10}, new int[] {1}, 1));
        assertEquals(8, closestCost(new int[] {3,10}, new int[] {2,5}, 9));
        assertEquals(17, closestCost(new int[] {2,3}, new int[] {4,5,100}, 18));
        assertEquals(10, closestCost(new int[] {1,7}, new int[] {3,4}, 10));
    }
}
