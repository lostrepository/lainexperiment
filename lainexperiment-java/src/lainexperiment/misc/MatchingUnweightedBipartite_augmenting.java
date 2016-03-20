/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 03/10/2015
 * 
 * Problem
 * 
 * Given list of edges in bipartite unweighted graph, need to find maximum matching
 * for it. 
 * 
 * Input
 * 
 * 0 3
 * 1 4
 * 2 5
 * 
 * Output
 * 
 * 3
 * 
 */
package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MatchingUnweightedBipartite_augmenting {
    
    static boolean[] visited;
    static int[] M;
    static List<Integer>[] G;
    
    @SuppressWarnings("unchecked")
    static List<Integer>[] buildGraph(int[] x, int[] y, int l) {
        List<Integer>[] g = new List[l];
        Arrays.setAll(g, (i) -> new ArrayList<>());
        IntStream.range(0, x.length).forEach((i) -> {
            g[x[i]].add(y[i]);
            g[y[i]].add(x[i]);
        });
        return g;
    }
    
    static boolean augment(int v)
    {
        if (visited[v]) return false;
        visited[v] = true;
        for(int a: G[v])
        {
            if (visited[a]) continue;
            if (M[a] == -1 || augment(M[a]))
            {
                M[a] = v;
                M[v] = a;
                return true;
            }   
        }
        return false;
    }
    
    static int[] match(int[] X, int[] Y, int l) {
        G = buildGraph(X, Y, l);
        M = new int[G.length];
        Arrays.fill(M, -1);
        for (int v = 0; v < G.length; ++v) 
        {
            visited = new boolean[G.length];
            if (M[v] == -1)
                augment(v);
        }
        return M;
    }
    
    public static void main(String[] args){
        assertEquals("[2, 5, 0, 4, 3, 1]", Arrays.toString(match(
                new int[]{0, 1, 2, 3, 4, 5},
                new int[]{2, 2, 3, 4, 5, 1},
                6
        )));
        assertEquals("[3, 2, 1, 0, 5, 4]", Arrays.toString(match(
                new int[]{0, 1, 2, 3, 4, 5},
                new int[]{3, 2, 3, 4, 5, 1},
                6
        )));
        assertEquals("[4, 5, 3, 2, 0, 1]", Arrays.toString(match(
                new int[]{0, 1, 2, 3, 4, 5},
                new int[]{4, 2, 3, 4, 5, 1},
                6
        )));
        assertEquals("[5, 2, 1, 4, 3, 0]", Arrays.toString(match(
                new int[]{0, 1, 2, 3, 4, 5},
                new int[]{5, 2, 3, 4, 5, 1},
                6
        )));
        assertEquals("[1, 0, 3, 2, 5, 4]", Arrays.toString(match(
                new int[]{0, 1, 2, 3, 4, 5},
                new int[]{1, 2, 3, 4, 5, 1},
                6
        )));
        assertEquals("[1, 0, 3, 2]", Arrays.toString(match(
                new int[]{0, 1, 0, 3},
                new int[]{2, 2, 1, 2},
                4
        )));
        assertEquals("[2, 3, 0, 1]", Arrays.toString(match(
                new int[]{0, 1, 0, 3},
                new int[]{2, 2, 1, 1},
                4
        )));
        assertEquals("[3, 2, 1, 0]", Arrays.toString(match(
                new int[]{0, 1, 0, 3},
                new int[]{2, 2, 1, 0},
                4
        )));
        assertEquals("[2, -1, 0]", Arrays.toString(match(
                new int[]{0, 1, 0},
                new int[]{2, 2, 1},
                3
        )));
        assertEquals("[1, 0, -1]", Arrays.toString(match(
                new int[]{0, 1, 2},
                new int[]{1, 2, 0},
                3
        )));
        assertEquals("[4, 2, 1, -1, 0]", Arrays.toString(match(
                new int[]{1, 2, 3, 0},
                new int[]{2, 3, 4, 4},
                5
        )));
        assertEquals("[3, 4, 5, 0, 1, 2]", Arrays.toString(match(
                new int[]{0, 1, 2},
                new int[]{3, 4, 5},
                6
        )));
        assertEquals("[3, 4, 5, 0, 1, 2]", Arrays.toString(match(
                new int[]{0, 0, 1, 1, 2},
                new int[]{3, 4, 4, 5, 5},
                6
        )));
        assertEquals("[7, 4, 5, -1, 1, 2, -1, 0]", Arrays.toString(match(
                new int[]{0, 0, 1, 2, 3},
                new int[]{5, 7, 4, 5, 7},
                8
        )));
        assertEquals("[7, -1, 6, 8, 9, 11, 2, 0, 3, 4, -1, 5]", Arrays.toString(match(
                new int[]{0, 0, 2, 2, 3, 4, 4, 5},
                new int[]{7, 8, 6, 9, 8, 9, 8, 11},
                12
        )));
        assertEquals("[5, 6, 7, 9, -1, 0, 1, 2, -1, 3]", Arrays.toString(match(
                new int[]{0, 0, 1, 2, 2, 2, 3, 3, 4, 4},
                new int[]{5, 6, 6, 5, 7, 8, 6, 9, 6, 9},
                10
        )));
        assertEquals("[-1, 20, 13, 21, 16, -1, 15, 18, -1, 12, 17, -1, 9, 2, -1, 6, 4, 10, 7, -1, 1, 3]", Arrays.toString(match(
                new int[]{1,   1,  2,  2,  3,  4,  6,  6,  7,  7,  9,  9,  9, 10, 10, 10},
                new int[]{16, 20, 13, 20, 21, 16, 13, 15, 18, 20, 12, 13, 19, 17, 19, 20},
                22
        )));
    }
}
