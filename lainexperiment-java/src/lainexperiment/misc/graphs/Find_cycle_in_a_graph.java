/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 25/12/2019
 * 
 * Problem
 * 
 * Given a directed graph find if it has cycles or not.
 * 
 * Input Format
 * 
 * Given N lines. Each line i will contain n digits which are
 * adjacent vertices of the vertex i
 * 
 * Input
 * 
2
2 3

4
1 2 0
 * 
 * Output
 * 
true
 * 
 */

package lainexperiment.misc.graphs;

import static lainexperiment.utils.V.Color.B;
import static lainexperiment.utils.V.Color.G;
import static lainexperiment.utils.V.Color.W;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import lainexperiment.utils.G;
import lainexperiment.utils.V;

public class Find_cycle_in_a_graph {

    static boolean findCycle(G g) {
        g.color(W);
        for (V v: g) {
            if (dfs(v)) return true;
        }
        return false;
    }

    static boolean dfs(V v) {
        switch (v.color) {
        case B: return false;
        case G: return true;
        }
        v.color = G;
        for (V u: v) {
            if (dfs(u)) return true;
        }
        v.color = B;
        return false;
    }

    @Test
    public void test() {
        G g = new G(new int[][] {
            {2},
            {2, 3},
            {},
            {4},
            {1, 2, 0}});
        assertTrue(findCycle(g));

        g = new G(new int[][] {
            {2},
            {2},
            {},
            {4, 1},
            {1, 2, 0}});
        assertFalse(findCycle(g));
    }

}
