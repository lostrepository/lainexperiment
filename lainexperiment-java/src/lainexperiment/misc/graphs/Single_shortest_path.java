/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc.graphs;

import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import lainexperiment.utils.G;
import lainexperiment.utils.V;

/**
 * <pre>{@code
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
 * }</pre>
 */
public class Single_shortest_path {

    int dijkstra(G g, V src)
    {
        Queue<V> q = new PriorityQueue<>(g.size(), (v, u) -> v.d - u.d);
        src.d = 0;
        q.add(src);
        while (!q.isEmpty()) {
            V v = q.poll();
            // iterate over adjacent vertices
            for (V u: v)
                relax(v, u, q);
        }
        return g.vertices.stream()
                .mapToInt(V::distance)
                .sum();
    }
    
    void relax(V v, V u, Queue<V> q) 
    {
        if (v.d + v.weight(u) < u.d) {
            u.d = v.d + v.weight(u);
            u.p = v;
            if (!u.visited) q.add(u);
            u.visited = true;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Single_shortest_path.class.getResourceAsStream(
                    "SingleShortestPath.in"));
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int s = scanner.nextInt();
        G g = new G(n);
        for (int i = 0; i < m; i++) {
            int v = scanner.nextInt() - 1;
            int u = scanner.nextInt() - 1;
            int w = scanner.nextInt();
            g.connect(v, u, w, false);
        }
//        System.out.println(g);
        int r =new Single_shortest_path().dijkstra(g, g.get(s - 1));
        System.out.println(r);
        scanner.close();
    }

}
