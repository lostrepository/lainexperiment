/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._235;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 03/04/2021
 * 
 * Problem: Finding the Users Active Minutes
 * Status: accepted
 * 
 * You are given the logs for users' actions on LeetCode,
 * and an integer k. The logs are represented by a 2D
 * integer array logs where each logs[i] = [ID_i, time_i]
 * indicates that the user with IDi performed an action
 * at the minute time_i.
 * 
 * Multiple users can perform actions simultaneously, and
 * a single user can perform multiple actions in the same
 * minute.
 * 
 * The user active minutes (UAM) for a given user is defined
 * as the number of unique minutes in which the user performed
 * an action on LeetCode. A minute can only be counted once,
 * even if multiple actions occur during it.
 * 
 * You are to calculate a 1-indexed array answer of size k
 * such that, for each j (1 <= j <= k), answer[j] is the
 * number of users whose UAM equals j.
 * 
 * Return the array answer as described above.
 * 
 * Input
 * 
[0,5],[1,2],[0,2],[0,5],[1,3]
k = 5
 * 
 * Output
 * 
0,2,0,0,0
 * 
 * }</pre>
 */
public class Task2_Finding_the_Users_Active_Minutes {
    
    
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        var res = new int[k];
        var cnt = new HashMap<Long, Set<Long>>();
        for (var e: logs) {
            cnt.putIfAbsent((long) e[0], new HashSet<>());
            cnt.get((long)e[0]).add((long) e[1]);
        }
        cnt.entrySet().stream()
            .map(e -> Map.entry(e.getKey(), e.getValue().size()))
            .forEach(e -> {
                res[e.getValue() - 1]++;
            });
        return res;
    }

    @Test
    public void test() {
        assertEquals("[1, 1, 0, 0]", Arrays.toString(findingUsersActiveMinutes(new int[][] {
            {1,1},{2,2},{2,3}
        }, 4)));
        assertEquals("[0, 2, 0, 0, 0]", Arrays.toString(findingUsersActiveMinutes(new int[][] {
            {0,5},{1,2},{0,2},{0,5},{1,3}
        }, 5)));
    }
}
