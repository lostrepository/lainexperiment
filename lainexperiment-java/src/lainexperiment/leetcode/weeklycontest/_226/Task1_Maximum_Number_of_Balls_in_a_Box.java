/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._226;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import static java.util.stream.IntStream.rangeClosed;

/**
 * <pre>{@code
 * Date: 30/01/2021
 * 
 * Problem: Maximum Number of Balls in a Box
 * Status: accepted
 * 
 * You are working in a ball factory where you have n balls numbered
 * from lowLimit up to highLimit inclusive (i.e.,
 * n == highLimit - lowLimit + 1), and an infinite number of boxes
 * numbered from 1 to infinity.
 * 
 * Your job at this factory is to put each ball in the box with a
 * number equal to the sum of digits of the ball's number. For
 * example, the ball number 321 will be put in the box number
 * 3 + 2 + 1 = 6 and the ball number 10 will be put in the box
 * number 1 + 0 = 1.
 * 
 * Given two integers lowLimit and highLimit, return the number of
 * balls in the box with the most balls.
 * 
 * Input:
 * 
lowLimit = 1, highLimit = 10
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task1_Maximum_Number_of_Balls_in_a_Box {
    
    public int countBalls(int lowLimit, int highLimit) {
        return rangeClosed(lowLimit, highLimit)
            .map(this::digitsSum)
            .boxed()
            .collect(Collectors.groupingBy(i -> i, Collectors.counting())).entrySet().stream()
            .max((e1, e2) -> (int)(e1.getValue() - e2.getValue()))
            .get().getValue().intValue();
            
    }

    int digitsSum(int i) {
        int c = 0;
        while (i > 0) {
            c += i % 10;
            i /= 10;
        }
        return c;
    }
    
    @Test
    public void test() {
        assertEquals(2, countBalls(1, 10));
        assertEquals(2, countBalls(5, 15));
        assertEquals(2, countBalls(19, 28));
    }
}
