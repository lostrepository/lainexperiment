/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._236;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 10/04/2021
 * 
 * Problem: Find the Winner of the Circular Game
 * Status: accepted
 * 
 * There are n friends that are playing a game. The friends
 * are sitting in a circle and are numbered from 1 to n in
 * clockwise order. More formally, moving clockwise from the
 * ith friend brings you to the (i+1)th friend for 1 <= i < n,
 * and moving clockwise from the nth friend brings you to the
 * 1st friend.
 * 
 * The rules of the game are as follows:
 * 
 * - Start at the 1st friend.
 * - Count the next k friends in the clockwise direction including
 *   the friend you started at. The counting wraps around the circle
 *   and may count some friends more than once.
 * - The last friend you counted leaves the circle and loses the
 *   game.
 * - If there is still more than one friend in the circle, go back
 *   to step 2 starting from the friend immediately clockwise of the
 *   friend who just lost and repeat.
 * - Else, the last friend in the circle wins the game.
 * 
 * Given the number of friends, n, and an integer k, return the
 * winner of the game.
 * 
 * Input
 * 
n = 5, k = 2
 * 
 * Output
 * 
3
 * 
 * }</pre>
 */
public class Task2_Find_the_Winner_of_the_Circular_Game {
    
    public int findTheWinner(int n, int k) {
        var l = IntStream.range(1, n + 1)
                .boxed()
                .collect(toList());
        int i = 0;
        while (l.size() > 1) {
            for (int j = 1; j < k; j++) {
                i++;
                System.out.println(i);
                if (i == l.size()) i = 0;
            }
            System.out.println("remove " + i);
            l.remove(i);
            if (i == l.size()) i = 0;
        }
        return l.get(0);
    }

    @Test
    public void test() {
        assertEquals(1, findTheWinner(6, 5));
        assertEquals(1, findTheWinner(6, 5));
        assertEquals(3, findTheWinner(5, 2));
    }
}
