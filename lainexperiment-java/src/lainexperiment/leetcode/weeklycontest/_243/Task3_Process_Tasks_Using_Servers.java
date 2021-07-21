/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._243;

import static java.util.stream.Collectors.toCollection;
import static lainexperiment.utils.Utils.asIntArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.tuples.TupleInt;

/**
 * <pre>{@code
 * Date: 20/07/2021
 * 
 * Problem: Process Tasks Using Servers
 * Status: accepted
 * 
 * You are given two 0-indexed integer arrays servers and
 * tasks of lengths n​​​​​​ and m​​​​​​ respectively. servers[i] is the
 * weight of the i​​​​​​th​​​​ server, and tasks[j] is the time needed
 * to process the j​​​​​​th​​​​ task in seconds.
 * 
 * Tasks are assigned to the servers using a task queue. Initially,
 * all servers are free, and the queue is empty.
 * 
 * At second j, the jth task is inserted into the queue (starting
 * with the 0th task being inserted at second 0). As long as there
 * are free servers and the queue is not empty, the task in the
 * front of the queue will be assigned to a free server with the
 * smallest weight, and in case of a tie, it is assigned to a free
 * server with the smallest index.
 * 
 * If there are no free servers and the queue is not empty, we wait
 * until a server becomes free and immediately assign the next task.
 * If multiple servers become free at the same time, then multiple
 * tasks from the queue will be assigned in order of insertion
 * following the weight and index priorities above.
 * 
 * A server that is assigned task j at second t will be free again
 * at second t + tasks[j].
 * 
 * Build an array ans​​​​ of length m, where ans[j] is the index of the
 * server the j​​​​​​th task will be assigned to.
 * 
 * Return the array ans​​​​.
 * 
 * Input
 * 
servers = [3,3,2], tasks = [1,2,3,2,1,2]
 * 
 * Output
 * 
2,2,0,2,1,2
 * 
 * }</pre>
 */
public class Task3_Process_Tasks_Using_Servers {
    
    public int[] assignTasks(int[] servers, int[] tasks) {
        var serverQ = IntStream.range(0, servers.length)
                .mapToObj(i -> new TupleInt(0, servers[i], i))
                .collect(toCollection(() -> new PriorityQueue<TupleInt>(TupleInt.compareByABC())));
        int time = 0;
        var res = new ArrayList<Integer>();
        var taskQ = new LinkedList<Integer>();
        var free = new PriorityQueue<TupleInt>(TupleInt.compareByABC());
        while (time < tasks.length || !taskQ.isEmpty()) {
            if (time < tasks.length)
                taskQ.add(tasks[time]);
            while (!serverQ.isEmpty() && serverQ.peek().a() <= time) {
                var s = serverQ.poll();
                s.a(0);
                free.add(s);
            }
            while (!taskQ.isEmpty()) {
                if (free.isEmpty()) break;
                var s = free.poll();
                s.a(time + taskQ.poll());
                res.add(s.a[2]);
                serverQ.add(s);
            }
            time++;
            if (time >= tasks.length && !serverQ.isEmpty())
                time = serverQ.peek().a();
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

    @Test
    public void test() {
        assertEquals("[1, 1, 0, 1, 1, 0]", Arrays.toString(assignTasks(asIntArray("[3,1]"), asIntArray("[1,2,3,2,1,2]"))));
        assertEquals("[0, 0, 1, 0, 0, 1]", Arrays.toString(assignTasks(asIntArray("[3,3]"), asIntArray("[1,2,3,2,1,2]"))));
        assertEquals("[1, 4, 1, 4, 1, 3, 2]", Arrays.toString(assignTasks(asIntArray("5,1,4,3,2"), asIntArray("2,1,2,4,5,2,1"))));
        assertEquals("[2, 2, 0, 2, 1, 2]", Arrays.toString(assignTasks(asIntArray("[3,3,2]"), asIntArray("[1,2,3,2,1,2]"))));
    }
}
