/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._219;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/12/2020
 * 
 * Problem: Count of Matches in Tournament
 * Status: accepted
 * 
 * You are given an integer n, the number of teams in a tournament
 * that has strange rules:
 * 
 * - If the current number of teams is even, each team gets paired
 * with another team. A total of n / 2 matches are played, and n / 2
 * teams advance to the next round.
 * 
 * - If the current number of teams is odd, one team randomly advances
 * in the tournament, and the rest gets paired. A total of (n - 1) / 2
 * matches are played, and (n - 1) / 2 + 1 teams advance to the
 * next round.
 * 
 * Return the number of matches played in the tournament until a
 * winner is decided.
 *
 * Example 1:
 * 
n = 7
 * 
 * Output
 * 
6
 * 
 * }</pre>
 */
public class Task1_Count_of_Matches_in_Tournament {

    public int numberOfMatches(int n) {
        if (n <= 1) return 0;
        if (n % 2 == 0) {
            return n / 2 + numberOfMatches(n / 2);
        } else {
            return (n - 1) / 2 + numberOfMatches((n - 1) / 2 + 1);
        }
    }

    @Test
    public void test() {
        assertEquals(6, numberOfMatches(7));
        assertEquals(0, numberOfMatches(1));
    }
}
