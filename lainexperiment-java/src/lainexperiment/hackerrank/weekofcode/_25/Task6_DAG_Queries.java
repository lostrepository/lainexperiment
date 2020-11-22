/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._25;

import static java.lang.Math.min;
import static java.util.Arrays.fill;
import static java.util.Arrays.setAll;
import static java.util.Arrays.sort;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntConsumer;

class Node {
    int n;
    List<Node> adj = new ArrayList<>();
    Node(int n) { this.n = n; }
    @Override
    public String toString() {
        return String.format("%d: %s", n, 
                Arrays.toString(adj.stream().map(n -> n.n).toArray()));
    }
}

class DagClosure {
    
    final int BITS = Long.BYTES * 8;
    long[][] CLR;
    
    public DagClosure(Node[] G) {
        CLR = new long[G.length][];
        int s = G.length / BITS + 1;
        setAll(CLR, i -> new long[s]);
        for (int v = CLR.length - 1; v >= 0; v--) {
            reachable(v, v);
            for (Node a: G[v].adj) merge(v, a.n);
        }
    }

    void forAllReachable(int v, IntConsumer f) {
        int u = v - (v % BITS);
        for (int i = v / BITS; i < CLR[v].length; ++i) {
            long b = CLR[v][i];
            for (int j = 0; j < BITS; ++j) {
                if ((b & 1) == 1)
                    f.accept(u);
                u++;
                if (u == CLR.length)
                    return;
                b >>= 1;
                if (b == 0) {
                    u = (u / BITS + 1) * BITS;
                    break;
                }
            }
        }
    }
    
    // is vertex u reachable from v
    boolean isReachable(int u, int v) {
        return (CLR[v][u / BITS] & (1 << (u % BITS))) > 0;
    }
    
    // merge all vertices reachable from v to u
    private void merge(int v, int u) {
        range(0, CLR[v].length)
            .forEach(i -> CLR[v][i] |= CLR[u][i]);
    }
    
    // make u be reachable from v
    private void reachable(int u, int v) {
        CLR[v][u / BITS] |= Long.rotateLeft(1, u % BITS);
    }
}

/**
 * <pre>{@code
 * 
 * Date: 16/11/2016
 * 
 * Hacker rank
 * Week of code 25
 * Problem: DAG Queries
 * Status: timeout
 * 
 * You are given a Directed Acyclic Graph (DAG) with n
 * vertices and m edges. Each vertex v has an integer, av, 
 * associated with it and the initial value of av is 0 for 
 * all vertices. You must perform q queries on the DAG, 
 * where each query is one of the following types:
 * 
 * - 1 u x: Set av to x for all v such that there is a 
 * path in the DAG from u to v.
 * 
 * - 2 u x: Set av to x for all v such that there is a 
 * path from u to v and av > x.
 * 
 * - 3 u: Print the value of au on a new line.
 * 
 * Input Format
 * 
 * The first line contains three space-separated integers 
 * describing the respective values of n (the number of 
 * vertices in the DAG), m (the number of edges in the DAG),
 * and q (the number of queries to perform).
 * Each of the m subsequent lines contains two space-separated 
 * integers describing the respective values of u and v (where
 * 1 <= u, v <= n, u != v) denoting a directed edge from vertex 
 * u to vertex v in the graph.
 * Each of the q subsequent lines contains a query in one of 
 * the three formats described above.
 * 
 * Output Format
 * 
 * For each query of type 3 (i.e., 3 u), print the value of au 
 * on a new line.
 * 
 * 
 * Sample Input
 * 
6 5 18
1 2
1 3
3 4
2 4
5 6
1 1 3
3 1
3 2
3 3
3 4
1 2 2
3 1
3 2
3 3
3 4
2 6 7
3 5
3 6
2 1 3
3 1
3 2
3 3
3 4
 *
 * Sample Output
 * 
3
3
3
3
3
2
3
2
0
0
3
2
3
2
 *
 * }</pre>
 */
public class Task6_DAG_Queries {

    static class Query {
        // query type, vertex
        int t, v;
        long x;
        Query(int t, int v, long x) { this.t = t; this.x = x; this.v = v; }
    }
    
    static Node[] G;
    static long[] V;
    static List<Query> Q;
    static DagClosure CLR;
    
    static int[] T;
    static int C;
    static void dfs(int v) {
        if (T[v] != -1)
            return;
        T[v] = 0;
        for (Node a: G[v].adj) dfs(a.n);
        T[v] = C++;
    }
    
    /**
     * Does topological sorting for the graph G
     * T stores mapping from old vertex id to new one
     */
    static void topologicalSort() {
        T = new int[G.length];
        fill(T, -1);
        for (int v = 0; v < G.length; v++) {
            if (T[v] == -1) dfs(v);
        }
        sort(G, (n1, n2) -> T[n2.n] - T[n1.n]);
        for (int i = 0; i < G.length; i++) {
            T[G[i].n] = i;
        }
        for (Node n: G) {
            n.n = T[n.n];
        }
    }

    static void print(int v) {
        for (int qi = 0; qi < Q.size(); qi++) {
            final Query q = Q.get(qi);
            IntConsumer c1 = u -> V[u] = q.x;
            IntConsumer c2 = u -> V[u] = min(V[u], q.x);
            CLR.forAllReachable(q.v, q.t == 1? c1: c2);
        }
        Q.clear();
        System.out.println(V[v]);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task6_DAG_Queries.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        G = new Node[scanner.nextInt()];
        setAll(G, Node::new);
        int m = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < m; i++) {
            G[scanner.nextInt() - 1].adj
                .add(G[scanner.nextInt() - 1]);
            scanner.nextLine();
        }
        topologicalSort();
        V = new long[G.length];
        Q = new ArrayList<>();
        CLR = new DagClosure(G);            
        for (int i = 0; i < q; i++) {
            switch(scanner.nextInt()) {
            case 1:
                Q.add(new Query(1, T[scanner.nextInt() - 1], scanner.nextLong()));
                break;
            case 2:
                Q.add(new Query(2, T[scanner.nextInt() - 1], scanner.nextLong()));
                break;
            case 3:
                print(T[scanner.nextInt() - 1]);
                break;
            }
            if (scanner.hasNextLine())
                scanner.nextLine();
        }
        scanner.close();
    }

}
