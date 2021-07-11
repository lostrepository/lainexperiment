/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._237;

import static lainexperiment.utils.Utils.as2DIntArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.tuples.TupleInt;

/**
 * <pre>{@code
 * Date: 11/07/2021
 * 
 * Problem: Single-Threaded CPU
 * Status: accepted
 * 
 * You are given n​​​​​​ tasks labeled from 0 to n - 1 represented by a 2D integer
 * array tasks, where tasks[i] = [enqueueTime, processingTime] means that
 * the i​​​​​​th​​​​ task will be available to process at enqueueTime and will take
 * processingTime to finish processing.
 * 
 * You have a single-threaded CPU that can process at most one task at a time
 * and will act in the following way:
 * 
 * - If the CPU is idle and there are no available tasks to process, the CPU
 *   remains idle.
 * - If the CPU is idle and there are available tasks, the CPU will choose
 *   the one with the shortest processing time. If multiple tasks have the same
 *   shortest processing time, it will choose the task with the smallest index.
 * - Once a task is started, the CPU will process the entire task without stopping.
 * - The CPU can finish a task then start a new one instantly.
 * 
 * Return the order in which the CPU will process the tasks.
 * 
 * Input
 * 
[[1,2],[2,4],[3,2],[4,1]]
 * 
 * Output
 * 
0,2,3,1
 * 
 * }</pre>
 */
public class Task3_Single_Threaded_CPU {
    
    public int[] getOrder(int[][] a) {
        var tasks = IntStream.range(0, a.length)
                .mapToObj(i -> new TupleInt(a[i][0], a[i][1], i))
                .sorted(TupleInt.compareByA())
                .collect(Collectors.toCollection(LinkedList::new));
        Comparator<TupleInt> cmp = (t1, t2) -> {
            if (t1.a[1] == t2.a[1]) return t1.a[2] - t2.a[2];
            return t1.a[1] - t2.a[1];
        };
        var pending = new PriorityQueue<TupleInt>(cmp);
        var r = new ArrayList<Integer>();
        var task = tasks.peek();
        var curTime = task.a[0];
        while (!tasks.isEmpty() || !pending.isEmpty()) {
            task = next(tasks, pending, curTime);
            if (task == null) {
                curTime = tasks.peek().a[0];
                continue;
            }
            r.add(task.a[2]);
            curTime = Math.max(curTime, task.a[0]);
            curTime += task.a[1];

        }
        return r.stream().mapToInt(Integer::intValue).toArray();
    }

    private TupleInt next(Queue<TupleInt> tasks, PriorityQueue<TupleInt> pending, int curTime) {
        while (!tasks.isEmpty() && tasks.peek().a[0] <= curTime)
                pending.add(tasks.poll());
        return pending.poll();
    }

    @Test
    public void test() {
        assertEquals("[4, 3, 6, 1, 0, 2, 5]", Arrays.toString(getOrder(as2DIntArray("[[5,6],[9,4],[3,9],[3,7],[1,1],[6,9],[9,1]]"))));
        assertEquals("[5, 0, 1, 3, 2, 4]", Arrays.toString(getOrder(new int[][] {
            {5,2},{7,2},{9,4},{6,3},{5,10},{1,1}})));
        assertEquals("[6, 1, 2, 9, 4, 10, 0, 11, 5, 13, 3, 8, 12, 7]", Arrays.toString(getOrder(new int[][] {
            {19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}})));
        assertEquals("[0, 2, 3, 1]", Arrays.toString(getOrder(new int[][] {
            {1,2},{2,4},{3,2},{4,1}})));
        assertEquals("[4, 3, 2, 0, 1]", Arrays.toString(getOrder(as2DIntArray("[[7,10],[7,12],[7,5],[7,4],[7,2]]"))));
        assertEquals("[0, 2, 3, 1]", Arrays.toString(getOrder(as2DIntArray("[[1,2],[2,4],[3,2],[4,1]]"))));
    }
    
}
