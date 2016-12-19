/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/12/2016
 * 
 * Hacker rank
 * World CodeSprint #8
 * Problem: Roads and Libraries
 * Status: accepted
 * 
 * The Ruler of HackerLand believes that every citizen of the 
 * country should have access to a library. Ufortunately, HackerLand 
 * was hit by a tornado that destroyed all of its libraries and 
 * obstructed its roads! As you are the greatest programmer of 
 * HackerLand, the ruler wants your help to repair the roads and 
 * build some new libraries efficiently. 
 * HackerLand has n cities numbered from 1 to n. The cities are 
 * connected by m bidirectional roads. A citizen has access to a 
 * library if:
 * 
 * - Their city contains a library.
 * - They can travel by road from their city to a city containing 
 * a library.
 * 
 * The cost of repairing any road is croad dollars, and the cost 
 * to build  a library in any city is clib dollars.
 * 
 * You are given q queries, where each query consists of a map of 
 * HackerLand and value of clib and croad. 
 * 
 * For each query, find the minimum cost of making libraries 
 * accessible to all the citizens and print it on a new line.
 * 
 * Input Format
 * 
 * The first line contains a single integer, q, denoting the number 
 * of queries. The subsequent lines describe each query in the 
 * following format:
 * 
 * - The first line contains four space-separated integers describing 
 * the respective values of n (the number of cities), m (the number 
 * of roads), clib (the cost to build a library), and croad (the 
 * cost to repair a road).
 * 
 * - Each line i of the m subsequent lines contains two space-separated 
 * integers, ui and vi, describing a bidirectional road connecting 
 * cities ui and vi.
 * 
 * Output Format
 * 
 * For each query, print an integer denoting the minimum cost of 
 * making libraries accessible to all the citizens on a new line.
 * 
 * 
 * Sample Input
 * 
2
3 3 2 1
1 2
3 1
2 3
6 6 2 5
1 3
3 4
2 4
1 2
2 3
5 6
 *
 * Sample Output
 * 
4
12
 *
 */

package lainexperiment.hackerrank.hackathon.worldcodesprint._8;

import static java.lang.Math.min;
import static java.util.stream.Stream.generate;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task3_Roads_and_Libraries {

    static List<List<Integer>> G;
    static long L, R;
    static boolean[] visited;
    
    static int dfs(int v) {
        if (visited[v]) return 0;
        visited[v] = true;
        return G.get(v).stream().mapToInt(u -> dfs(u)).sum() + 1;
    }
    
    static void solve() {
        visited = new boolean[G.size()];
        long c = 0;
        for (int v = 0; v < G.size(); v++) {
            if (visited[v]) continue;
            int n = dfs(v);
            c += min(n * L, L + (n - 1) * R);
        }
        System.out.println(c);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task3_Roads_and_Libraries.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        int q = scanner.nextInt();
        while (q-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            L = scanner.nextLong();
            R = scanner.nextLong();
            G = generate(ArrayList<Integer>::new)
                .limit(n)
                .collect(Collectors.toList());
            for (int i = 0; i < m; i++) {
                int v = scanner.nextInt() - 1;
                int u = scanner.nextInt() - 1;
                G.get(v).add(u);
                G.get(u).add(v);
            }
            solve();
        }
        scanner.close();
    }

}
