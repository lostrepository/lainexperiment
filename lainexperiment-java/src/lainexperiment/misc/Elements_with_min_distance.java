/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 14/02/2015
 * 
 * Problem
 * 
 * Given sorted matrix need to return minimal distance between
 * elements from each row.
 *
 * Input
 * 
 * 5, 9 , 17
 * 4, 13, 18
 * 8, 19, 21
 * 
 * Output
 * 
 * 2
 * 
 */

package lainexperiment.misc;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class Elements_with_min_distance {

    static int findMinDist(int[][] a) {
        class Entry {
            int i;
            int r;
            Entry(int r, int i) {
                this.r = r;
                this.i = i;
            }
        };
        int[] cur = new int[a.length];
        int m = Integer.MAX_VALUE;
        Queue<Entry> q = new PriorityQueue<>(
                (e1, e2) -> a[e1.r][e1.i] - a[e2.r][e2.i]);
        IntStream.range(0, a.length).forEach(
                (i) -> q.add(new Entry(i, 0)));
        while (!q.isEmpty()) {
            Entry e = q.poll();
            cur[e.r] = a[e.r][e.i];
            e.i++;
            if (e.i < a[e.r].length)
                q.add(e);
            int min = Arrays.stream(cur).min().getAsInt();
            int max = Arrays.stream(cur).max().getAsInt();
            int d = max - min;
            m = d < m? d: m;
        }
        return m;
    }
    
    public static void main(String[] args) {
        System.out.println(findMinDist(new int[][]{
                {5, 9 , 17 },
                {4, 13, 18 },
                {8, 19, 21 },
        }));
    }
    
}
