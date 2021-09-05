/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._256;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.BitUtils;
import lainexperiment.utils.Utils;

/**
 * <pre>{@code
 * Date: 28/08/2021
 * 
 * Problem: Minimum Number of Work Sessions to Finish the Tasks
 * Status: accepted
 * 
 * There are n tasks assigned to you. The task times are represented
 * as an integer array tasks of length n, where the ith task takes
 * tasks[i] hours to finish. A work session is when you work for at
 * most sessionTime consecutive hours and then take a break.
 * 
 * You should finish the given tasks in a way that satisfies the
 * following conditions:
 * 
 * - If you start a task in a work session, you must complete it in
 *   the same work session.
 * - You can start a new task immediately after finishing the previous one.
 * - You may complete the tasks in any order.
 * 
 * Given tasks and sessionTime, return the minimum number of work
 * sessions needed to finish all the tasks following the conditions above.
 * 
 * The tests are generated such that sessionTime is greater than or
 * equal to the maximum element in tasks[i].
 * 
 * Input
 * 
tasks = [3,1,3,1,1], sessionTime = 8
 * 
 * Output
 * 
2
 * 
 * Constraints:
 * - n == tasks.length
 * - 1 <= n <= 14
 * - 1 <= tasks[i] <= 10
 * - max(tasks[i]) <= sessionTime <= 15
 *
 * }</pre>
 */
public class Task3_Minimum_Number_of_Work_Sessions_to_Finish_the_Tasks {
    
    public int minSessions(int[] tasks, int sessionTime) {
        int len = tasks.length;
        int[][] M = new int[1<<len][sessionTime + 1];
        Arrays.stream(M).forEach(a -> Arrays.fill(a, Integer.MAX_VALUE));
        return calc(tasks, sessionTime, 0, sessionTime, M);
    }
    
    private int calc(int[] a, int leftTime, int usedSet, int sessionTime, int[][] M) {
        if (usedSet == M.length - 1) return sessionTime == leftTime? 0: 1;
        if (M[usedSet][leftTime] != Integer.MAX_VALUE) return M[usedSet][leftTime];
        for (int i = 0; i < a.length; i++) {
            if (BitUtils.isSet(usedSet, i)) continue;
            int time = leftTime;
            int c = 0; // cost (num of sessions)
            time -= a[i];
            if (time == 0) {
                c++;
                time = sessionTime;
            } else if (time < 0) {
                c++;
                time = sessionTime - a[i];
            }
            c += calc(a, time, BitUtils.set(usedSet, i), sessionTime, M);
            M[usedSet][leftTime] = Math.min(M[usedSet][leftTime], c);
        }
        return M[usedSet][leftTime];
    }

    @Test
    public void test() {
        assertEquals(3, minSessions(Utils.asIntArray("[9,8,8,4,6]"), 14));
        assertEquals(2, minSessions(Utils.asIntArray("[1, 1, 1, 1]"), 3));
        assertEquals(1, minSessions(Utils.asIntArray("[1,2,3,4,5]"), 15));
        assertEquals(2, minSessions(Utils.asIntArray("[3,1,3,1,1]"), 8));
        assertEquals(2, minSessions(Utils.asIntArray("[1,2,3]"), 3));

    }
}
