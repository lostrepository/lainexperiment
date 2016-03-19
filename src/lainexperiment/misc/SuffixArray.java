/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 04/07/2015
 * 
 * Given a string build a suffix array for it.
 * 
 * Input Format
 * 
 * String
 * 
 * Output Format
 * 
 * Suffix array
 * 
 * Sample Input
 * 
banana
 * 
 * Sample Output
 * 
4, 3, 6, 2, 5, 1
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class SuffixArray {

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
    
    // suffix array found so far
    static int[] SA;
    
    /**
     * Prefix doubling implementation.
     * 
     * @param n length of a new prefix
     */
    static void suffixArray(char[] A, int n) {
        if (n >= A.length * 2) {
            return;
        }
        Prefix[] P = new Prefix[SA.length];
        for (int i = 0; i < SA.length; ++i) {
            Prefix p = new Prefix();
            p.a = SA[i];
            int j = i + n / 2;
            if (j < A.length)
                p.b = n == 1? A[i]: SA[j];
            else
                p.b = -i;
            p.p = i;
            P[i] = p;
        }
        Arrays.sort(P);
        int a = P[0].a;
        int b = P[0].b;
        int c = 0;
        for (int i = 0; i < P.length; ++i) {
            SA[P[i].p] = c;
            if (P[i].a == a && P[i].b == b)
                continue;
            SA[P[i].p] = ++c;
            a = P[i].a;
            b = P[i].b;
        }
        suffixArray(A, n * 2);
    }
    
    static int[] suffixArray(char[] a) {
        SA = new int[a.length];
        suffixArray(a, 1);
        return SA;
    }
    
    public static void main(String[] args) {
        assertEquals("[3, 2, 5, 1, 4, 0]", Arrays.toString(suffixArray("banana".toCharArray())));
        assertEquals("[0, 5, 1, 6, 2, 3, 4]", Arrays.toString(suffixArray("bobocel".toCharArray())));
        assertEquals("[4, 3, 10, 8, 2, 9, 7, 1, 6, 5, 0]", Arrays.toString(suffixArray("mississippi".toCharArray())));
        assertEquals("[6, 5, 4, 3, 2, 1, 0]", Arrays.toString(suffixArray("aaaaaaa".toCharArray())));
        assertEquals("[1, 6, 4, 2, 0, 5, 3]", Arrays.toString(suffixArray("attcatg".toCharArray())));
        assertEquals("[1, 3, 6, 5, 2, 4, 7, 0]", Arrays.toString(suffixArray("aacbaada".toCharArray())));
        System.out.println(Arrays.toString(suffixArray("aadcaabe".toCharArray())));
    }

}
