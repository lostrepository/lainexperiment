/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._228;

import static lainexperiment.utils.MathUtils.ceilDiv;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 13/02/2021
 * 
 * Problem: Minimum Limit of Balls in a Bag
 * Status: partially accepted (Wrong answer)
 * 
 * You are given an integer array nums where the ith bag contains
 * nums[i] balls. You are also given an integer maxOperations.
 * 
 * You can perform the following operation at most maxOperations times:
 * 
 * - Take any bag of balls and divide it into two new bags with a positive
 * number of balls.
 * 
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls,
 * or two new bags of 2 and 3 balls.
 * 
 * Your penalty is the maximum number of balls in a bag. You want to
 * minimize your penalty after the operations.
 * 
 * Return the minimum possible penalty after performing the operations.
 *
 * Input
 * 
nums = [7,17], maxOperations = 2
 * 
 * Output
 * 
7
 * 
 * }</pre>
 */
public class Task3_Minimum_Limit_of_Balls_in_a_Bag {
    
    public int minimumSize(int[] a, int maxOperations) {
        var q = new PriorityQueue<Integer>(Comparator.reverseOrder());
        IntStream.of(a).forEach(q::add);
        q.add(0);
        while (maxOperations > 0) {
            var n = q.poll();
            if (n <= 1) return n;
            int i = 1;
            while (i <= maxOperations) {
                int d = i + 1;
                int r = ceilDiv(n, d);
                int next = q.peek();
                if (d != n && r > next) {
                    i++;
                    if (i > maxOperations) {
                        System.out.println(1222);
                        split(q, n, d);
                        return q.peek();
                    }
                    continue;
                }
                split(q, n, d);
                maxOperations -= i;
                break;
            }
        }
        System.out.println(1);
        return q.peek();
    }
    
    void split(Queue<Integer> q, Integer n, int d) {
        int r = n / d;
        if (n % d == 0) {
            q.addAll(Collections.nCopies(d, r));
        } else {
            if (d > 2) {
                q.addAll(Collections.nCopies(d - 2, r));
                n -= r * (d - 2);
            }
            q.add(n / 2);
            q.add(ceilDiv(n, 2));                    
        }
    }

    @Test
    public void test() {
        assertEquals(5, minimumSize(new int[] {14}, 2));
        assertEquals(34, minimumSize(new int[] {100}, 2));
        assertEquals(50, minimumSize(new int[] {100}, 1));
        assertEquals(2, minimumSize(new int[] {7}, 5));
        assertEquals(1, minimumSize(new int[] {7}, 6));
        assertEquals(1, minimumSize(new int[] {7}, 7));
        assertEquals(3, minimumSize(new int[] {7}, 2));
        assertEquals(4, minimumSize(new int[] {7}, 1));
        assertEquals(129, minimumSize(new int[] {431,922,158,60,192,14,788,146,788,775,772,792,68,143,376,375,877,516,595,82,56,704,160,403,713,504,67,332,26}, 80));
        assertEquals(1, minimumSize(new int[] {3, 3, 3}, 6));
        assertEquals(7, minimumSize(new int[] {17, 7}, 2));
        assertEquals(2, minimumSize(new int[] {3, 3, 3}, 5));
        assertEquals(2, minimumSize(new int[] {3}, 1));
        assertEquals(2, minimumSize(new int[] {3, 3, 3}, 3));
        assertEquals(3, minimumSize(new int[] {3, 3, 3}, 2));
        assertEquals(3, minimumSize(new int[] {3, 3, 3}, 1));
        assertEquals(1, minimumSize(new int[] {2}, 1));
        assertEquals(2, minimumSize(new int[] {2,4,8,2}, 4));
        assertEquals(3, minimumSize(new int[] {9}, 2));
        assertEquals(1, minimumSize(new int[] {2}, 2));
    }
}
