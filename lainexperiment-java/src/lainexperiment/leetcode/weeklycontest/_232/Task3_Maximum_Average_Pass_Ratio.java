/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._232;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.pairs.PairInt;

/**
 * <pre>{@code
 * Date: 13/03/2021
 * 
 * Problem: Maximum Average Pass Ratio
 * Status: accepted (failed to solve myself had to seek for help)
 * 
 * There is a school that has classes of students and each class
 * will be having a final exam. You are given a 2D integer array
 * classes, where classes[i] = [passi, totali]. You know beforehand
 * that in the ith class, there are totali total students, but only
 * passi number of students will pass the exam.
 * 
 * You are also given an integer extraStudents. There are another
 * extraStudents brilliant students that are guaranteed to pass
 * the exam of any class they are assigned to. You want to assign
 * each of the extraStudents students to a class in a way that
 * maximizes the average pass ratio across all the classes.
 * 
 * The pass ratio of a class is equal to the number of students
 * of the class that will pass the exam divided by the total
 * number of students of the class. The average pass ratio is
 * the sum of pass ratios of all the classes divided by the
 * number of the classes.
 * 
 * Return the maximum possible average pass ratio after assigning
 * the extraStudents students. Answers within 10-5 of the actual
 * answer will be accepted.
 * 
 * Input
 * 
classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 
 * Output
 * 
0.78333
 * 
 * }</pre>
 */
public class Task3_Maximum_Average_Pass_Ratio {
    
    public double maxAverageRatio(int[][] classes, int n) {
        var q = new PriorityQueue<PairInt>((p1, p2) ->
            Float.compare(next(p2), next(p1)));
        for (int i = 0; i < classes.length; i++) {
            q.add(new PairInt(classes[i][0], classes[i][1]));
        }
        while (n > 0) {
            var p = q.poll();
            p.a++;
            p.b++;
            n--;
            q.add(p);
        }
        double res = 0;
        for (var p: q) {
            res += div(p);
        }
        res /= classes.length;
        System.out.println(res);
        return res;
    }

    private float next(PairInt p) {
        if (p.a == p.b) return 0;
        return div(new PairInt(p.a + 1, p.b + 1)) - div(p);
    }
    
    private float div(PairInt p) {
        return (float)p.a / p.b;
    }

    @Test
    public void test() {
        assertEquals(0.6F, div(new PairInt(3, 5)));
        assertEquals(0.75F, div(new PairInt(3, 4)));
        assertEquals(0.5F, div(new PairInt(1, 2)));
        assertEquals(1, div(new PairInt(2, 2)));
        assertTrue(0.000001 < Math.abs(0.7 - maxAverageRatio(new int[][] {
            {1,2},{3,5},{2,2}}, 2)));
    }
}
