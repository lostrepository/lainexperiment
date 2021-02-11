/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._213;

import static lainexperiment.utils.Algorithms.bisection;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.LongUnaryOperator;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/02/2020
 * 
 * Problem: Furthest Building You Can Reach
 * Status: accepted
 * 
 * You are given an integer array heights representing the heights
 * of buildings, some bricks, and some ladders.
 * 
 * You start your journey from building 0 and move to the next
 * building by possibly using bricks or ladders.
 * 
 * While moving from building i to building i+1 (0-indexed),
 * 
 * - If the current building's height is greater than or equal
 * to the next building's height, you do not need a ladder or
 * bricks.
 * - If the current building's height is less than the next
 * building's height, you can either use one ladder or
 * (h[i+1] - h[i]) bricks.
 * 
 * Return the furthest building index (0-indexed) you can reach if
 * you use the given ladders and bricks optimally.
 *
 * Input
 * 
heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
 * 
 * Output
 * 
4
 * 
 * }</pre>
 */
public class Task3_Furthest_Building_You_Can_Reach {

    public int furthestBuilding(int[] h, int bricks, int ladders) {
        var d = new ArrayList<Integer>();
        int p = h[0];
        d.add(0);
        for (int i = 1; i < h.length; i++) {
            var t = h[i] - p;
            if (t <= 0) t = 0; 
            d.add(t);
            p = h[i];
        }
        LongUnaryOperator func = i -> {
            return canReachEnd(d.subList(0, (int) i), bricks, ladders)? -1: 1;
        };
        return (int) bisection(0, h.length, func) - 1;
    }
    
    boolean canReachEnd(List<Integer> d, int b, int l) {
        d = new ArrayList<>(d);
        d.sort(Comparator.reverseOrder());
        for (int i = l; i < d.size(); i++) {
            int t = d.get(i);
            if (t == 0) return true;
            b -= t;
            if (b < 0) return false;
        }
        return true;
    }

    @Test
    public void test() {
        //assertEquals(3, furthestBuilding(new int[] {17,16,5,10,10,14,7}, 74, 6));
        assertEquals(false, canReachEnd(List.of(0, 0, 1, 5, 6), 2, 1));
        assertEquals(true, canReachEnd(List.of(0, 0, 5, 1, 1), 2, 1));
        assertEquals(1, furthestBuilding(new int[] {0, 4}, 4, 0));
        assertEquals(1, furthestBuilding(new int[] {4, 8}, 4, 0));
        assertEquals(2, furthestBuilding(new int[] {0, 4, 4}, 4, 1));
        assertEquals(4, furthestBuilding(new int[] {4,2,7,6,9,14,12}, 5, 1));
        assertEquals(7, furthestBuilding(new int[] {4,12,2,7,3,18,20,3,19}, 10, 2));
        assertEquals(3, furthestBuilding(new int[] {14,3,19,3}, 17, 0));
    }

}
