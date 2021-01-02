/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._221;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/26/2020
 * 
 * Problem: Maximum Number of Eaten Apples
 * Status: accepted
 * 
 * There is a special kind of apple tree that grows apples every
 * day for n days. On the ith day, the tree grows apples[i] apples
 * that will rot after days[i] days, that is on day i + days[i]
 * the apples will be rotten and cannot be eaten. On some days,
 * the apple tree does not grow any apples, which are denoted by
 * apples[i] == 0 and days[i] == 0.
 * 
 * You decided to eat at most one apple a day (to keep the doctors
 * away). Note that you can keep eating after the first n days.
 * 
 * Given two integer arrays days and apples of length n, return
 * the maximum number of apples you can eat.
 *
 * Example 1:
 * 
apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 
 * Output
 * 
7
 * 
 * You can eat 7 apples:
 * - On the first day, you eat an apple that grew on the first day.
 * - On the second day, you eat an apple that grew on the second day.
 * - On the third day, you eat an apple that grew on the second day.
 *   After this day, the apples that grew on the third day rot.
 * - On the fourth to the seventh days, you eat apples that grew on the fourth day.
 * 
 * }</pre>
 */
public class Task2_Maximum_Number_of_Eaten_Apples {

    public int eatenApples(int[] apples, int[] days) {
        class X {
            int end, c;
            public X(int end, int c) {
                this.end = end;
                this.c = c;
            }
            @Override
            public String toString() {
                return "end " + end + " c " + c;
            }
            
        };
        var q = new PriorityQueue<X>((x1, x2) -> x1.end - x2.end);
        int day = 0;
        int i = 0;
        while (true) {
            if (i < days.length) {
                q.add(new X(day + days[i], apples[i]));
                i++;
            }
            X x = null;
            while (!q.isEmpty()) {
                x = q.poll();
                if (x.end > day) break;
                x = null;
            }
            if (x == null) {
                if (i < days.length) continue;
                break;
            }
            day++;
            x.c--;
            if (x.c > 0)
                q.add(x);
        }
        return day;
    }

    @Test
    public void test() {
        assertEquals(7, eatenApples(new int[] {1,2,3,5,2}, new int[] {3,2,1,4,2}));
        assertEquals(5, eatenApples(new int[] {3,0,0,0,0,2}, new int[] {3,0,0,0,0,2}));
    }
}
