/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._219;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/12/2020
 * 
 * Problem: Stone Game
 * Status: accepted
 * 
 * Alice and Bob take turns playing a game, with Alice starting first.
 * 
 * There are n stones arranged in a row. On each player's turn, they
 * can remove either the leftmost stone or the rightmost stone from
 * the row and receive points equal to the sum of the remaining
 * stones' values in the row. The winner is the one with the higher
 * score when there are no stones left to remove.
 * 
 * Bob found that he will always lose this game (poor Bob, he always
 * loses), so he decided to minimize the score's difference. Alice's
 * goal is to maximize the difference in the score.
 * 
 * Given an array of integers stones where stones[i] represents the
 * value of the ith stone from the left, return the difference in
 * Alice and Bob's score if they both play optimally.
 *
 * Example 1:
 * 
stones = [5,3,1,4,2]
 * 
 * Output
 * 
6
 * 
 * }</pre>
 */
public class Task3_Stone_Game {

    class Score {
        int a,b;
        Score(){}
        Score(Score s) {
            a = s.a; b = s.b;
        }
        int diff () { return a - b; }
    }
    
    Score[][] M = new Score[1000][1000];
    
    public Score stoneGameVII(int[] stones, int l, int r, int total, boolean alice) {
        if (r - l + 1 == 1) {
            return new Score();
        }
        if (M[l][r] != null)
            return M[l][r];
        Score lsum = new Score(stoneGameVII(stones, l + 1, r, total - stones[l], !alice));
        Score rsum = new Score(stoneGameVII(stones, l, r - 1, total - stones[r], !alice));
        Score sum;
        if (alice) {
            lsum.a += total - stones[l];
            rsum.a += total - stones[r];
            sum = (lsum.diff() < rsum.diff())? rsum: lsum;
        } else {
            lsum.b += total - stones[l];
            rsum.b += total - stones[r];
            sum = (lsum.diff() > rsum.diff())? rsum: lsum;
        }
        M[l][r] = sum;
        return sum;
    }
    
    public int stoneGameVII(int[] stones) {
        int total = IntStream.of(stones).sum();
        Score s = stoneGameVII(stones, 0, stones.length - 1, total, true);
        return s.diff();
    }
    
    @Test
    public void test() {
        assertEquals(6, stoneGameVII(new int[] {5,3,1,4,2}));
        assertEquals(122, stoneGameVII(new int[] {7,90,5,1,100,10,10,2}));
    }

}
