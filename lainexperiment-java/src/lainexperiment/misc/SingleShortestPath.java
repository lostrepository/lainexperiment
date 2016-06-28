/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 28/06/2015
 * 
 * Problem
 * 
 * Given graph and source vertex print sum of shortest paths
 * to all vertices.
 *
 * Input Format
 * 
 * The first line contains three space-separated positive integers, n
 * (num of vertices), m (num of edges) and s (source vertex). 
 * Each i line of the subsequent m lines contains the respective values
 * of v, u, and w describing edge from v to u with weight w.
 * 
 * Input
 * 
5 6 1
1 3 32
4 5 1
2 1 8
3 2 2
4 3 16
4 2 4
 * 
 * Output
 * 
43
 * 
 */

package lainexperiment.misc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SingleShortestPath {

    static List<Integer>[] G;
    static List<Integer>[] W;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    static void buildGraph(int[] from, int[] to, int[] w, int maxNumOfNodes) {
        G = new List[maxNumOfNodes];
        W = new List[maxNumOfNodes];
        Arrays.setAll(G, i -> new ArrayList());
        Arrays.setAll(W, i -> new ArrayList());
        IntStream.range(0, from.length).forEach((i) -> {
            G[from[i]].add(to[i]);
            G[to[i]].add(from[i]);
            W[from[i]].add(w[i]);
            W[to[i]].add(w[i]);
        });
    }

    static int dijkstra(int src)
    {
        int[] d = new int[G.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        boolean[] visited = new boolean[G.length];
        d[src] = 0;
        Queue<Integer> q = new PriorityQueue<>(G.length, (v, u) -> d[v] - d[u]);
        q.add(src);
        while (!q.isEmpty()) {
            int v = q.poll();
            List<Integer> adj = G[v];
            for (int i = 0; i < adj.size(); ++i) {
                int u = adj.get(i);
                if (visited[u]) continue;
                if (d[v] + W[v].get(i) < d[u]) {
                    q.remove(u);
                    d[u] = d[v] + W[v].get(i);
                    q.add(u);
                }
            }
            visited[v] = true;
        }
        return Arrays.stream(d).sum();
    }
    
    static void solve(int s) {
        System.out.println(dijkstra(s));
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(SingleShortestPath.class.getResourceAsStream("SingleShortestPath.in"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        int[] x = new int[m];
        int[] y = new int[m];
        int[] w = new int[m];
        for (int i = 0; i < m; i++) {
            x[i] = scanner.nextInt() - 1;
            y[i] = scanner.nextInt() - 1;
            w[i] = scanner.nextInt();
        }
        buildGraph(x, y, w, n);
        solve(s - 1);
        scanner.close();
    }

}
