/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 31/05/2015
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

/*
 * Modified version of Hungarian algorithm for bipartite unweighted graphs. Should be
 * easy to extend for weighted graphs by adding relabeling routine.
 */

package lainexperiment.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class MatchingUnweightedBipartite {
    
    static int[] M; // M[y] = x
    static boolean[] exposedY;
    static List<Integer>[] G;
    
    static List<Integer>[] buildGraph(int[] x, int[] y, int l) {
        List[] g = new List[l];
        Arrays.setAll(g, (i) -> new ArrayList());
        IntStream.range(0, x.length).forEach((i) -> {
            g[x[i]].add(y[i]);
            g[y[i]].add(x[i]);
        });
        return g;
    }
    
    static void augment(int x) {
        Set S = new HashSet<>();
        S.add(x);
        Set T = new HashSet<>();
        int y = 0;
        while ((y = nextVertex(S, T)) != -1) {
            if (exposedY[y]) {
                exposedY[y] = false;
                updateMatching(S, T, x, y, true);
                break;
            }
            int z = M[y];
            S.add(z);
            T.add(y);
        }
    }
    
    static void updateMatching(Set S, Set T, int x, int y, boolean merge) 
    {
        int k = -1;
        for (int v: G[y]) {
            if (!S.remove(v) && !T.remove(v))
                continue;
            M[y] = merge? v: -1;
            k = v;
            break;
        }
        if (k == x) {
            return;
        }
        if (k != -1)
            updateMatching(S, T, x, k, !merge);
        else
            throw new RuntimeException();
    }

    static int nextVertex(Set<Integer> S, Set T) 
    {
        for (int x: S) {
            for (int y: G[x]) {
                if (!T.contains(y))
                    return y;
            }
        }
        return -1;
    }

    static int[] match(int[] X, int[] Y, int l) {
        G = buildGraph(X, Y, l);
        M = new int[G.length];
        Arrays.fill(M, -1);
        exposedY = new boolean[G.length];
        Arrays.fill(exposedY, true);
        for (int x = 0; x < l / 2; ++x)
            augment(x);
        return M;
    }
    
    public static void main(String[] args){
        assertEquals("[-1, -1, 1, -1, 0]", Arrays.toString(match(
                new int[]{1, 2, 3, 4},
                new int[]{2, 3, 4, 0},
                5
        )));
        assertEquals("[-1, -1, -1, 0, 1, 2]", Arrays.toString(match(
                new int[]{0, 1, 2},
                new int[]{3, 4, 5},
                6
        )));
        assertEquals("[-1, -1, -1, 0, 1, 2]", Arrays.toString(match(
                new int[]{0, 0, 1, 1, 2},
                new int[]{3, 4, 4, 5, 5},
                6
        )));
        assertEquals("[-1, -1, -1, -1, 1, 2, -1, 0]", Arrays.toString(match(
                new int[]{0, 0, 1, 2, 3},
                new int[]{5, 7, 4, 5, 7},
                8
        )));
        assertEquals("[-1, -1, -1, -1, -1, -1, 2, 0, 3, 4, -1, 5]", Arrays.toString(match(
                new int[]{0, 0, 2, 2, 3, 4, 4, 5},
                new int[]{7, 8, 6, 9, 8, 9, 8, 11},
                12
        )));
        assertEquals("[-1, -1, -1, -1, -1, 0, 1, 2, -1, 3]", Arrays.toString(match(
                new int[]{0, 0, 1, 2, 2, 2, 3, 3, 4, 4},
                new int[]{5, 6, 6, 5, 7, 8, 6, 9, 6, 9},
                10
        )));
        assertEquals("[-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 9, 2, -1, 6, 4, 10, 7, -1, 1, 3]", Arrays.toString(match(
                new int[]{1,   1,  2,  2,  3,  4,  6,  6,  7,  7,  9,  9,  9, 10, 10, 10},
                new int[]{16, 20, 13, 20, 21, 16, 13, 15, 18, 20, 12, 13, 19, 17, 19, 20},
                22
        )));
    }
}
