/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._211;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.pairs.PairInt;

/**
 * <pre>{@code
 * Date: 10/03/2020
 * 
 * Problem: Best Team With No Conflicts
 * Status: failed
 * 
 * You are the manager of a basketball team. For the upcoming tournament,
 * you want to choose the team with the highest overall score. The score
 * of the team is the sum of scores of all the players in the team.
 * 
 * However, the basketball team is not allowed to have conflicts. A
 * conflict exists if a younger player has a strictly higher score
 * than an older player. A conflict does not occur between players
 * of the same age.
 * 
 * Given two lists, scores and ages, where each scores[i] and ages[i]
 * represents the score and age of the ith player, respectively, return
 * the highest overall score of all possible basketball teams.
 *
 * Input
 * 
scores = [4,5,6,5], ages = [2,1,2,1]
 * 
 * Output
 * 
16
 * 
 * }</pre>
 */
public class Task3_Best_Team_With_No_Conflicts {

    public int bestTeamScore(int[] scores, int[] ages) {
        var l = IntStream.range(0, ages.length)
            .mapToObj(i -> new PairInt(ages[i], scores[i]))
            .collect(toList());
        l.sort(PairInt::compareByAB);
        var memo = new TreeMap<Integer, List<Integer>>();
        int max = 0;
        for (var p: l) {
            if (!memo.containsKey(p.b)) {
                memo.put(p.b, new ArrayList<Integer>());
            }
            for (var e: memo.entrySet()) {
                if (e.getKey() > p.b) break;
                var score = p.b;
                for (var s: e.getValue()) {
                    if (s > p.b) continue;
                    score += s;
                }
                max = Math.max(max, score);
                e.getValue().add(p.b);
            }
            System.out.println();
        }
        return max;
    }
    
    @Test
    public void test() {
        assertEquals(29, bestTeamScore(new int[] {1,3,7,3,2,4,10,7,5}, new int[] {4,5,2,1,1,2,4,1,4}));
        assertEquals(6, bestTeamScore(new int[] {1,2,3,5}, new int[] {8,9,10,1}));
        assertEquals(16, bestTeamScore(new int[] {4,5,6,5}, new int[] {2,1,2,1}));
        assertEquals(34, bestTeamScore(new int[] {1,3,5,10,15}, new int[] {1,2,3,4,5}));
    }
}
