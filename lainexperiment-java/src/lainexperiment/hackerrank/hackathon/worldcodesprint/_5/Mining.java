/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 05/08/2016
 * 
 * Hacker rank
 * World CodeSprint #5
 * Problem: Mining
 * Status: accepted
 * 
 * There are n gold mines along a river, and each mine i produces 
 * wi tons of gold. In order to collect the mined gold, we want to 
 * redistribute and consolidate it amongst exactly k mines where it 
 * can be picked up by trucks. We do this according to the following 
 * rules:
 * - You can move gold between any pair of mines (i.e., i and j, where
 * 1 <= i < j <= n).
 * - All the gold at some pickup mine i must either stay at mine i or be 
 * completely moved to some other mine, j.
 * - Move w tons of gold between the mine at location xi and the mine at 
 * location xj at a cost of |xi - xj| * w.
 * Given n, k, and the amount of gold produced at each mine, find and 
 * print the minimum cost of consolidating the gold into k pickup locations 
 * according to the above conditions.
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers describing the 
 * respective values of n (the number of mines) and k (the number of 
 * pickup locations).
 * Each line i of the n subsequent lines contains two space-separated 
 * integers describing the respective values of xi (the mine's distance 
 * from the mouth of the river) and wi (the amount of gold produced in 
 * tons) for mine i.
 * Note: It is guaranteed that the mines are will be given in order of 
 * ascending location.
 * 
 * Output Format
 * 
 * Print a single line with the minimum cost of consolidating the mined 
 * gold amongst k different pickup sites according to the rules stated 
 * above.
 * 
 * 
 * Sample Input
 * 
3 1
20 1
30 1
40 1
 *
 * Sample Output
 * 
20
 *
 *
 */

package lainexperiment.hackerrank.hackathon.worldcodesprint._5;

import static java.util.Arrays.fill;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Mining {

    static long[] D, W;
    static long[] LC, RC;
    static long[] LW, RW;
    static int K;
    static int N;

    static void preproc() {
        LC = new long[N];
        LW = new long[N];
        LC[0] = 0;
        LW[0] = 0;
        for (int i = 1; i < N; i++) {
            LW[i] = LW[i - 1] + W[i - 1];
            LC[i] = LC[i - 1] + LW[i] * (D[i] - D[i - 1]);
        }
        RC = new long[N];
        RW = new long[N];
        RC[N - 1] = 0;
        RW[N - 1] = 0;
        for (int i = N - 2; i >= 0; i--) {
            RW[i] = RW[i + 1] + W[i + 1];
            RC[i] = RC[i + 1] + RW[i] * (D[i + 1] - D[i]);
        }
    }
    
    static void printMatrix(long[][] a) {
        IntStream.range(0, a.length).forEach((i) -> System.out.println(Arrays.toString(a[i])));
    }
    
    static void solve() {
        preproc();
        long[][] G = new long[N][N];
        int[][] GI = new int[N][N];
        for (long[] a: G) fill(a, Long.MAX_VALUE);
        for (int s = 0; s < N; s++) {
            G[s][s] = 0;
            GI[s][s] = s;
            for (int e = s + 1; e < N; e++) {
                GI[s][e] = GI[s][e - 1];
                int k = GI[s][e];
                G[s][e] = G[s][e - 1] + (D[e] - D[k]) * W[e];
                while (++k <= e) {
                    long t = LC[k] - LC[s] - LW[s] * (D[k] - D[s]);
                    t += RC[k] - RC[e] - RW[e] * (D[e] - D[k]);
                    if (G[s][e] < t) 
                        break;
                    G[s][e] = t;
                    GI[s][e] = k;
                }
            }
        }
        long[][] F = new long[N][K];
        for (long[] a: F) fill(a, Long.MAX_VALUE);
        for (int k = 0; k < K; k++) {
            for (int i = 0; i <= k; i++) {
                F[i][k] = 0;
            }
        }
        for (int i = 0; i < N; i++) {
            F[i][0] = G[0][i];
            int J = 0;
            for (int k = 1; k < K; k++) {
                if (i > k)
                    F[i][k] = RC[k] - RC[i] - RW[i] * (D[i] - D[k]);
                for (int j = J; j < i; j++) {
                    long c = F[j][k - 1] + G[j + 1][i];
                    if (F[i][k] > c) {
                        F[i][k] = c;
                        J = j;
                    }
                }
            }
        }
        System.out.println(F[N - 1][K - 1]);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Mining.class.getResourceAsStream("Mining.in"));
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            D = new long[N];
            W = new long[N];
            K = scanner.nextInt();
            for (int i = 0; i < N; i++) {
                D[i] = scanner.nextLong();
                W[i] = scanner.nextLong();
            }
            solve();
        }
        scanner.close();
    }

}
