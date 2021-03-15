/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._210;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 13/03/2020
 * 
 * Problem: Maximal Network Rank
 * Status: accepted
 * 
 * There is an infrastructure of n cities with some number of
 * roads connecting these cities. Each roads[i] = [ai, bi]
 * indicates that there is a bidirectional road between cities
 * ai and bi.
 * 
 * The network rank of two different cities is defined as the
 * total number of directly connected roads to either city. If
 * a road is directly connected to both cities, it is only
 * counted once.
 * 
 * The maximal network rank of the infrastructure is the
 * maximum network rank of all pairs of different cities.
 * 
 * Given the integer n and the array roads, return the maximal
 * network rank of the entire infrastructure.
 *
 * Input
 * 
n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
 * 
 * Output
 * 
5
 * 
 * }</pre>
 */
public class Task2_Maximal_Network_Rank {

    public int maximalNetworkRank(int n, int[][] roads) {
        var c = new HashMap<Integer, Integer>();
        var adj = new HashMap<Integer, Set<Integer>>();
        for (int i = 0; i < roads.length; i++) {
            int v = roads[i][0];
            c.merge(v, 1, Integer::sum);
            int u = roads[i][1];
            c.merge(u, 1, Integer::sum);
            adj.putIfAbsent(v, new HashSet<Integer>());
            adj.get(v).add(u);
            adj.putIfAbsent(u, new HashSet<Integer>());
            adj.get(u).add(v);
        }
        var l = c.entrySet().stream()
                .collect(toList());
        int max = 0;
        for (int i = 0; i < l.size(); i++) {
            for (int j = i + 1; j < l.size(); j++) {
                var v = l.get(i);
                var u = l.get(j);
                int m = v.getValue() + u.getValue();
                if (adj.getOrDefault(v.getKey(), Set.of()).contains(u.getKey())) m--;
                max = Math.max(max, m);
            }
        }
        return max;
    }
    
    @Test
    public void test() {
        assertEquals(5, maximalNetworkRank(8, new int[][] {
            {0,1},{1,2},{2,3},{2,4},{5,6},{5,7}}));
        assertEquals(5, maximalNetworkRank(5, new int[][] {
            {0,1},{0,3},{1,2},{1,3},{2,3},{2,4}}));
        assertEquals(4, maximalNetworkRank(4, new int[][] {
            {0,1},{0,3},{1,2},{1,3}}));
    }
}
