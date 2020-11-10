/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 15/10/2020
 * 
 * Tesla Codility
 * Problem: Task 1
 * Status: passed
 * 
 * Problem
 * 
 * You are given a string S consisting of N letter 'a' and/or 'b'. In one move you can swap one
 * letter for the other ('a' for 'b' and vise-versa).
 *
 * Write a function that given such string S returns the minimum number of moves required to
 * obtain a string containing no instances of three identical consecutive letters.
 * 
 * N is an integer within the range [0..200_000] 
 * 
 * Sample Input
 * 
baaaaa
baaabbaabbba
 * 
 * Sample Output
 * 
1
2
 * 
 * In first case final string is "baabaa" and in second it is "bbaabbaabbaa"
 * 
 */

package lainexperiment.codility.tesla;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.function.IntSupplier;

public class Task1 {

    private int[][][] M;
    
    public int solution(String S) {
        M = new int[S.length()][3][3];
        for (var a2: M) {
            for (var a1: a2) {
                Arrays.fill(a1, Integer.MAX_VALUE);
            }
        }
        return calc(S.toCharArray(), 0, 0, 0, "");
    }
    
    private int calc(char[] str, int i, int a, int b, String buf) {
        if (str.length == i) {
            System.out.println(buf);
            return 0;
        }
        if (M[i][a][b] != Integer.MAX_VALUE) {
            return M[i][a][b];
        }
        char ch = str[i];
        IntSupplier swapA = () -> 1 + calc(str, i + 1, 0, 1, buf + 'b');
        IntSupplier swapB = () -> 1 + calc(str, i + 1, 1, 0, buf + 'a');
        // swap to b
        boolean isA = ch == 'a';
        if (isA && a == 2) {
            return swapA.getAsInt();
        } else if (!isA && b == 2) {
            return swapB.getAsInt();
        }
        // keep going
        int m = calc(str, i + 1, isA? a + 1: 0, !isA? b + 1: 0, buf + ch);
        m = Math.min(m, (isA? swapA.getAsInt(): swapB.getAsInt()));
        M[i][a][b] = m;
        return m;
    }

    public static int solve(String S) {
        return new Task1().solution(S);
    }
    
    public static void main(String[] args) {
        assertEquals(1, solve("baaaaa"));
        assertEquals(2, solve("baaabbaabbba"));
        assertEquals(0, solve("baabab"));
        assertEquals(0, solve(""));
        assertEquals(1, solve("aaa"));
        assertEquals(3, solve("aaaaaaaaa"));
    }
    
}
