/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codejam.qualification._2017;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * <pre>{@code
 * Date: 08/04/2017
 *
 * Code jam 2017
 * Problem: Problem B. Tidy Numbers
 * Status (small): accepted
 * Status (large): timeout
 * 
 * Problem
 * 
 * A certain bathroom has N + 2 stalls in a single row; 
 * the stalls on the left and right ends are permanently 
 * occupied by the bathroom guards. The other N stalls 
 * are for users.
 * 
 * Whenever someone enters the bathroom, they try to
 * choose a stall that is as far from other people as 
 * possible. To avoid confusion, they follow deterministic 
 * rules: For each empty stall S, they compute two values 
 * LS and RS, each of which is the number of empty stalls 
 * between S and the closest occupied stall to the left 
 * or right, respectively. Then they consider the set of 
 * stalls with the farthest closest neighbor, that is, 
 * those S for which min(LS, RS) is maximal. If there is 
 * only one such stall, they choose it; otherwise, they 
 * choose the one among those where max(LS, RS) is maximal. 
 * If there are still multiple tied stalls, they choose 
 * the leftmost stall among those.
 * 
 * K people are about to enter the bathroom; each one 
 * will choose their stall before the next arrives. 
 * Nobody will ever leave.
 * 
 * When the last person chooses their stall S, what will 
 * the values of max(LS, RS) and min(LS, RS) be? 
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test cases,
 * T. T lines follow. Each line describes a test case with two
 * integers N and K, as described above. 
 * 
 * Output Format
 * 
 * For each test case, output one line containing Case #x: y z, 
 * where x is the test case number (starting from 1), y is 
 * max(LS, RS), and z is min(LS, RS) as calculated by the last 
 * person to enter the bathroom for their chosen stall S. 
 * 
 * Input
 * 
5
4 2
5 2
6 2
1000 1000
1000 1
 * 
 * Output
 * 
Case #1: 1 0
Case #2: 1 0
Case #3: 1 1
Case #4: 0 0
Case #5: 500 499
 * 
 * }</pre>
 */
public class ProblemC_Bathroom_Stalls {
    
    static Map<Long, Pair> M = new HashMap<>();
    static Queue<Pair> Q = new PriorityQueue<>(
            Comparator.comparingLong((Pair p) -> p.n).reversed());
    
    static class Pair {
        public long n, c;
        public Pair(long n) {this.n = n; }
        @Override
        public String toString() {
            return "" + n + ":" + c;
        }
    }
    
    static Pair update(long n) {
        Pair p = M.get(n);
        if (p == null) {
            p = new Pair(n);
            Q.add(p);
            M.put(n, p);
        }
        p.c++;
        return p;
    }
    
    static void solve(long n, long k) {
        M.clear();
        Q.clear();
        update(n);
        while (k > 1) {
            Pair p = Q.peek();
            p.c--;
            long v = p.n / 2;
            Pair pp = update(v);
            if ((p.n & 1) == 1)
                pp.c++;
            else
                update(v - 1);
            if (p.c == 0)
                Q.poll();
            k--;
        }
        long v = Q.poll().n;
        if ((v & 1) == 1)
            System.out.format("%d %d\n", v / 2, v / 2);
        else
            System.out.format("%d %d\n", v / 2, v / 2 - 1);
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = ProblemC_Bathroom_Stalls.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(Paths.get("/tmp/in")): new Scanner(clazz.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            System.out.format("Case #%d: ", i + 1);
            solve(scanner.nextLong(), scanner.nextLong());
        }
        scanner.close();
    }

}
