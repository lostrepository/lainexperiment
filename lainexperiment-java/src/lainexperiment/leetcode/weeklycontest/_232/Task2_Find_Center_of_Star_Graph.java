/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._232;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.tuples.TupleInt;

/**
 * <pre>{@code
 * Date: 13/03/2021
 * 
 * Problem: Find Center of Star Graph
 * Status: accepted
 * 
 * There is an undirected star graph consisting of n nodes labeled
 * from 1 to n. A star graph is a graph where there is one center
 * node and exactly n - 1 edges that connect the center node with
 * every other node.
 * 
 * You are given a 2D integer array edges where each
 * edges[i] = [ui, vi] indicates that there is an edge between the
 * nodes ui and vi. Return the center of the given star graph.
 * 
 * Input
 * 
[1,2],[2,3],[4,2]
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task2_Find_Center_of_Star_Graph {
    
    public int findCenter(int[][] edges) {
        var c = new int[edges.length*2];
        for (int i = 0; i < edges.length; i++) {
            c[edges[i][0]]++;
            c[edges[i][1]]++;
        }
        var p = new TupleInt(0, 0);
        for (int i = 0; i < c.length; i++) {
            if (p.a() < c[i]) {
                p.a[0] = c[i];
                p.a[1] = i;
            }
        }
        return p.b();
    }

    @Test
    public void test() {
        assertEquals(2, findCenter(new int[][] {
            {1,2},{2,3},{4,2},{1,3}
        }));
        assertEquals(1, findCenter(new int[][] {
            {1,2},{5,1},{1,3},{1,4}
        }));
        assertEquals(2, findCenter(new int[][] {
            {1,2},{2,3},{4,2}
        }));
    }
}
