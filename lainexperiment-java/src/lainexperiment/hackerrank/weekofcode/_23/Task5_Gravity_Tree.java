/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 24/09/2016
 * 
 * Hacker rank
 * Week of code 23
 * Problem: Gravity Tree
 * Status: accepted
 * 
 * Sarah is young scientist in Neverland who just invented 
 * a gravity tree with the following properties:
 *  
 * It's a tree where each vertex is numbered 1 from to n 
 * and vertex 1 always denotes the root. Each edge has a 
 * length of 1, so a node has a distance of 1 from its 
 * parent, a distance of 2 from its grandparent, and so on.
 * 
 * Some vertex, v, can be turned on, which results in all 
 * vertices (including v) of the subtree rooted at v beginning 
 * to attract the tree's other vertices. The attractive forces 
 * exerted on some vertex, u, is equal to the summation of 
 * squared distances between vertex u and all vertices that 
 * are currently switched on (i.e., all nodes in the subtree 
 * rooted at v). For example, if two nodes are switched on 
 * that have distances of 1 and 2 from node u, then the forces 
 * exerted on u would be 1^2 + 2^2 = 5.
 * 
 * Sarah wants to test the tree by carrying out a series of 
 * experiments on pairs of vertices, u and v. In each 
 * experiment, she turns on vertex v and measures the forces 
 * acting on vertex u before immediately turning v back off. 
 * To avoid undesirable consequences, she wants to know the 
 * value of this attractive forces in advance. 
 * 
 * Given the definition of her gravity tree and q experiments, 
 * print the value of the attractive forces between vertices u
 * and v for each experiment on a new line. Note that each 
 * experiment is independent and no experiment will affect 
 * the subsequent experiments.
 * 
 * Input Format
 * 
 * The first line contains a single integer, n, denoting the 
 * number of vertices in the tree.
 * The second line contains n - 1 space-separated integers 
 * where the kth integer denotes the parent vertex of vertex 
 * k + 1.
 * The next line contains an integer, q, denoting the number 
 * of experiments she plans to perform.
 * Each of the q subsequent lines contains two space-separated 
 * integers denoting the respective values of u and v for an 
 * experiment. 
 * 
 * Output Format
 * 
 * On a new line for each experiment, print a single integer 
 * denoting the expected value of the forces acting on vertex u
 * from all the nodes in the subtree rooted at vertex v.
 * 
 * 
 * Sample Input
 * 
5
1 2 2 4
2
2 1
1 4
 *
 * Sample Output
 * 
7 
13
 *
 */

package lainexperiment.hackerrank.weekofcode._23;

import static java.lang.Math.pow;
import static java.util.Arrays.setAll;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Node {
    
    int i, size, d;
    Node p;
    List<Node> adj = new ArrayList<>();
    long sum, sqrSum;
    
    Node(int i) { this.i = i; }
    
    static void bind(Node ch, Node p) {
        ch.p = p;
        p.adj.add(ch);
    }

    @Override
    public String toString() {
        String adj = this.adj.stream()
            .map(ch -> "" + ch.i)
            .collect(Collectors.joining(","));
        return String.format("index %s, sum %s, sqrsum %s, adj [%s], size %s", i, sum, sqrSum, adj, size);
    }
}

public class Task5_Gravity_Tree {

    static Node[] T;
    static int N;

    static void print(String s) {
        System.out.println(s);
    }

    static int preproc(Node n, int d) {
        n.d = d;
        for (Node a: n.adj) {
            n.size += preproc(a, d + 1);
            n.sum += a.sum;
            n.sqrSum += calcForce(
                a.sum, a.sqrSum, 1, a.size) + 1;
        }
        n.sum += n.size;
        return n.size + 1;
    }

    static long calcForce(long sum, long sqrSum, int len, 
        int size)
    {
        return sqrSum + 2 * len * sum + 
            size * (long)pow(len, 2);
    }

    static long calc(Node p, Node ch, int len) {
        long sum = p.sum - ch.sum - ch.size - 1;
        long sqrSum = p.sqrSum - (calcForce(ch.sum, ch.sqrSum, 1, ch.size) + 1);
        int size = p.size - ch.size - 1;
        //print(String.format("%d %d %d %d", p.i, sum, sqrSum, size));
        return calcForce(sum, sqrSum, len, size) + (long)pow(len, 2);
    }

    static long calcInner(Node ch, Node p) {
        long r = ch.sqrSum;
        int len = 1;
        while (ch.p != null && ch.p != p.p) {
            r += calc(ch.p, ch, len++);
            ch = ch.p;
        }
        return r;
    }

    static int dist(Node v, Node u) {
        Node min = v.d < u.d? v: u;
        Node max = min == v? u: v;
        int d = max.d - min.d;
        for (int i = 0; i < d; ++i)
            max = max.p;
        while (max != min) {
            max = max.p;
            min = min.p;
            d += 2;
        }
        d = d == 0? 2 * max.d: d;
        //print(String.format("dist %d", d));
        return d;
    }

    static boolean isParent(Node v, Node u) {
        return dist(v, u) == u.d - v.d;
    }

    /**
     * v - switched node
     * u - measured node
     */
    static void solve(int ui, int vi) {
        Node u = T[ui];
        Node v = T[vi];
        if (isParent(v, u) || v == u)
            System.out.println(calcInner(u, v));
        else {
            int d = dist(u, v);
            System.out.println(calcForce(v.sum, v.sqrSum,
                d, v.size) + (long)pow(d, 2));
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task5_Gravity_Tree.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            T = new Node[N];
            setAll(T, Node::new);
            range(1, N)
                .forEach(v -> Node.bind(T[v], T[scanner.nextInt() - 1]));
            preproc(T[0], 0);
            //of(T).forEach(n -> print(n.toString()));
            range(0, scanner.nextInt())
                .forEach(i -> solve(
                    scanner.nextInt() - 1, 
                    scanner.nextInt() - 1));
        }
        scanner.close();
    }

}
