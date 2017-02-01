/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 01/02/2017
 *
 * Hacker rank
 * Problem: String Similarity
 * Status: accepted / timeout
 * 
 * Problem
 * 
 * For two strings A and B, we define the similarity of 
 * the strings to be the length of the longest prefix 
 * common to both strings. For example, the similarity 
 * of strings "abc" and "abd" is 2, while the similarity 
 * of strings "aaa" and "aaab" is 3.
 * 
 * Calculate the sum of similarities of a string S with 
 * each of it's suffixes.
 * 
 * Input Format
 * 
 * The first line contains the number of test cases T. 
 * Each of the next T lines contains a string each.
 * 
 * Output Format
 * 
 * Output T lines containing the answer for the corresponding 
 * test case.
 * 
 * Input
 * 
2
ababaa  
aa
 * 
 * Output
 * 
11  
3
 * 
 */
package lainexperiment.hackerrank.misc;

import static java.util.Arrays.fill;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class SuffixArray {

    static class Prefix implements Comparable<Prefix> {
        // a - index of the previous group
        // b - index of the current group
        int a, b;
        // index where prefix starts in A
        int p;
        @Override
        public int compareTo(Prefix o) {
            if (a == o.a) return b - o.b;
            return a - o.a;
        }
        @Override
        public String toString() {
            return String.format("%d<%d,%d>", p, a, b);
        }
    }
    
    private static final int MAX = 100_000;
    char[] A;
    
    // maps suffixes of the input array A to their
    // positions in SA
    static int[] RANK = new int[MAX];
    // suffix array
    static int[] SA = new int[MAX];
    
    SuffixArray(char[] a) {
        A = a;
        fill(RANK, 0);
        suffixArray(a, 1);
        invertedIndex();
    }
    
    private int[] invertedIndex() {
        for (int i = 0; i < A.length; i++) {
            SA[RANK[i]] = i;
        }
        return SA;
    }

    /**
     * Prefix doubling implementation.
     * 
     * @param n length of a new prefix
     */
    private static Prefix[] P = new Prefix[MAX];
    private void suffixArray(char[] A, int n) {
        if (n >= A.length * 2) {
            return;
        }
        for (int i = 0; i < A.length; ++i) {
            Prefix p = new Prefix();
            p.a = RANK[i];
            int j = i + n / 2;
            if (j < A.length)
                p.b = n == 1? A[i]: RANK[j];
            else
                p.b = -i;
            p.p = i;
            P[i] = p;
        }
        Arrays.sort(P, 0, A.length);
        int a = P[0].a;
        int b = P[0].b;
        int c = 0;
        for (int i = 0; i < A.length; ++i) {
            RANK[P[i].p] = c;
            if (P[i].a == a && P[i].b == b)
                continue;
            RANK[P[i].p] = ++c;
            a = P[i].a;
            b = P[i].b;
        }
        suffixArray(A, n * 2);
    }

}

@SuppressWarnings("static-access")
public class String_Similarity {
    
    /**
     * Builds longest common prefix for given suffix array. 
     */
    static int[] kasai(SuffixArray sa) {
        int n = sa.A.length;
        int k = 0;
        int[] lcp = new int[n];
        for (int i = 0; i < n; i++) {
            if (sa.RANK[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = sa.SA[sa.RANK[i] + 1];
            while (i + k < n && j + k < n && sa.A[i + k] == sa.A[j + k]) 
                k++;
            lcp[sa.RANK[i]] = k;
            k--;
            if (k < 0) k = 0;
        }
        return lcp;
    }

    static void count(char[] a) {
        SuffixArray sa = new SuffixArray(a);
//        System.out.println(Arrays.toString(sa.SA));
        int[] lcp = kasai(sa);
//        System.out.println(Arrays.toString(lcp));
        long c = a.length;
        int s = sa.RANK[0] - 1;
        int min = Integer.MAX_VALUE;
        while (s >= 0) {
            if (lcp[s] == 0)
                break;
            min = Math.min(lcp[s], min);
            c += min;
            s--;
        }
        s = sa.RANK[0];
        min = Integer.MAX_VALUE;
        while (s < a.length) {
            if (lcp[s] == 0)
                break;
            min = Math.min(lcp[s], min);
            c += min;
            s++;
        }
        System.out.println(c);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = String_Similarity.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int t = scanner.nextInt();
            for (int i = 0; i < t; i++) {
                count(scanner.next().toCharArray());
            }
        }
        scanner.close();
    }

}
