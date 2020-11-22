/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codility.tesla;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * <pre>{@code
 * 
 * Date: 15/10/2020
 * 
 * Tesla Codility
 * Problem: Task 2
 * Status: failed 18%
 * 
 * Problem
 * 
 * You are given string S. Deletion of the K-th letter of S costs C[k]. For example for
 * S = "ab" and C = [1, 3] after deleting 'a' deletion of 'b' will still cost 3.
 * 
 * You want to delete some letters from S to obtain a string without 2 identical letters
 * next to each other. What is min total cost of deletion to achieve such a string.
 * 
 * - S and C have length equal to N
 * - N within the range [1.100_000]
 * - each element of C is within [0..1_000]
 * 
 * Sample Input
 * 
abccbd
aabbcc
 * 
 * Sample Output
 * 
2
3
 * 
 * For first case the final string is "abcbd" and for second it is "abc"
 * }</pre>
 */
public class Task2 {

    private int[] M;
    
    public int solution(String S, int[] C) {
        if (S.length() == 1) return 0;
        M = new int[S.length()];
        Arrays.fill(M, Integer.MAX_VALUE);
        return calc(S.toCharArray(), C, 1, "");
    }
    
    private int calc(char[] a, int[] C, int i, String buf) {
        if (i == a.length) {
            System.out.println(buf);
            return 0;
        }
        if (M[i] != Integer.MAX_VALUE) {
            return M[i];
        }
        if (a[i - 1] == a[i]) {
            int m = C[i] + calc(a, C, i + 1, buf);
            m = C[i - 1] + Math.min(m, calc(a, C, i + 1, buf));
            M[i] = m;
            return m;
        }
        return calc(a, C, i + 1, buf + a[i]);
    }
    
    public static int solve(String S, int[] C) {
        return new Task2().solution(S, C);
    }
    
    public static void main(String[] args) {
        assertEquals(2, solve("abccbd", new int[]{0, 1, 2, 3, 4, 5}));
        assertEquals(3, solve("aabbcc", new int[]{1, 2, 1, 2, 1, 2}));
        assertEquals(12, solve("aaaa", new int[]{3, 4, 5, 6}));
        assertEquals(0, solve("ababa", new int[]{10, 5, 10, 5, 10}));
    }
    
}
