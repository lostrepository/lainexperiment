/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._242;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.Algorithms;
import lainexperiment.utils.MathUtils;
import lainexperiment.utils.Utils;

/**
 * <pre>{@code
 * Date: 22/05/2021
 * 
 * Problem: Minimum Speed to Arrive on Time
 * Status: accepted
 * 
 * You are given a floating-point number hour, representing the amount
 * of time you have to reach the office. To commute to the office, you
 * must take n trains in sequential order. You are also given an integer
 * array dist of length n, where dist[i] describes the distance (in
 * kilometers) of the ith train ride.
 * 
 * Each train can only depart at an integer hour, so you may need to wait
 * in between each train ride.
 * 
 * For example, if the 1st train ride takes 1.5 hours, you must wait for
 * an additional 0.5 hours before you can depart on the 2nd train ride at
 * the 2 hour mark.
 * 
 * Return the minimum positive integer speed (in kilometers per hour) that
 * all the trains must travel at for you to reach the office on time, or
 * -1 if it is impossible to be on time.
 * 
 * Tests are generated such that the answer will not exceed 107 and hour
 * will have at most two digits after the decimal point.
 * 
 * Input
 * 
dist = [1,3,2], hour = 2.7
 * 
 * Output
 * 
3
 * 
 * With speed 3:
 * 
 * - The first train ride takes 1/3 = 0.33333 hours.
 * - The second train ride takes 3/3 = 1 hour.
 * - The third train takes 2/3 = 0.66667 hours.
 * 
 * }</pre>
 */
public class Task2_Minimum_Speed_to_Arrive_on_Time {
    
    public int minSpeedOnTime(int[] dist, double hour) {
        var speed = Algorithms.bisection(1, 10000001, true, s -> {
            var h = calc(dist, (int) s);
            return h <= hour? +1: -1;
        });
        var time = calc(dist, (int)speed);
        if (time > hour) return -1;
        return (int) speed;
    }

    private double calc(int[] dist, int speed) {
        double time = 0;
        for (int i = 0; i < dist.length - 1; i++) {
            var t = MathUtils.ceilDiv(dist[i], speed);
            time += t;
        }
        time += Utils.last(dist) / (double)speed;
        return time;
    }

    @Test
    public void test() {
        assertEquals(2, minSpeedOnTime(new int[] {6,10,5,1,8,9,2}, 34.0));
        assertEquals(10000000, minSpeedOnTime(new int[] {1,1,100000}, 2.01));
        assertEquals(-1, minSpeedOnTime(new int[] {1,3,2}, 1.9));
        assertEquals(3, minSpeedOnTime(new int[] {1,3,2}, 2.7));
        assertEquals(1, minSpeedOnTime(new int[] {1,3,2}, 6));
    }
}
