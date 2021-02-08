/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._227;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 08/02/2021
 * 
 * Problem: Maximum Score From Removing Stones
 * Status: accepted
 * 
 * You are playing a solitaire game with three piles of stones of
 * sizes a​​​​​​, b,​​​​​​ and c​​​​​​ respectively. Each turn you choose two different
 * non-empty piles, take one stone from each, and add 1 point to your
 * score. The game stops when there are fewer than two non-empty piles
 * (meaning there are no more available moves).
 * 
 * Given three integers a​​​​​, b,​​​​​ and c​​​​​, return the maximum score you
 * can get.
 *
 * Input
 * 
a = 2, b = 4, c = 6
a = 4, b = 4, c = 6
 * 
 * Output
 * 
6
7
 * 
 * }</pre>
 */
public class Task2_Maximum_Score_From_Removing_Stones {
    
    public int maximumScore(int a, int b, int c) {
        var ar = new int[] {a, b, c};
        Arrays.sort(ar);
        if (ar[0] + ar[1] > ar[2]) {
            int d = ar[0] + ar[1] - ar[2];
            d /= 2;
            ar[0] -= d;
            ar[1] -= d;
            return d + ar[2];
        } else {
            return ar[0] + ar[1];
        }
    }
    
    @Test
    public void test() {
        assertEquals(6, maximumScore(2,4,6));
        assertEquals(7, maximumScore(4,4,6));
        assertEquals(8, maximumScore(1,8,8));
        assertEquals(9, maximumScore(5,6,7));
        assertEquals(3, maximumScore(1,2,3));
    }
}
