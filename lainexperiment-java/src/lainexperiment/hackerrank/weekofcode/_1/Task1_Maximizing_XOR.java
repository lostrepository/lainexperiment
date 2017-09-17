/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/09/2017
 * 
 * Hacker rank
 * Week of code 1
 * Problem: Maximizing XOR
 * Status: correct
 * 
 * Given two integers, L and R, find the maximal value of A xor B, where
 * A and B satisfy the following condition:
 *  L <= A <= B <= R
 * 
 * Input Format
 * 
 * The input contains two lines; L is present in the first line and R in the 
 * second line. 
 * 
 * Output Format
 * 
 * The maximal value as mentioned in the problem statement.
 * 
 * Sample Input
 * 
10
15
 *
 * Sample Output
 * 
7
 *
 */

package lainexperiment.hackerrank.weekofcode._1;

import static java.lang.System.in;
import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.util.Scanner;

public class Task1_Maximizing_XOR {
    
    static int MAX_LEN = 32;
    static char[] A = new char[MAX_LEN];
    static char[] B = new char[MAX_LEN];
    
    static void parse(int n, char[] a) {
        char[] s = Integer.toBinaryString(n).toCharArray();
        int offset = MAX_LEN - s.length;
        range(0, offset)
            .forEach(i -> a[i] = '0');
        System.arraycopy(s, 0, a, offset, s.length);
    }
    
    static int fromBinaryString(String s) {
        int i = 0;
        int m = 1;
        for (int j = s.length() - 1; j >= 0; j--) {
            if (s.charAt(j) == '0')
                i |= m;
            m <<= 1;
        }
        return i;
    }
    
    static void solve() {
        char[] a = new char[MAX_LEN];
        char[] b = new char[MAX_LEN];
        int i = 0;
        while (A[i] == B[i]) {
            a[i] = A[i];
            b[i] = A[i];
            i++;
        }
        boolean isLess = false;
        for (; i < MAX_LEN; i++) {
            if (A[i] != B[i]) {
                a[i] = A[i];
                b[i] = B[i];
                isLess = !isLess && A[i] < B[i];
                continue;
            }
            if (A[i] == '1') {
                if (!isLess) {
                    a[i] = '0';
                    b[i] = '1';
                    isLess = true;
                } else {
                    a[i] = '1';
                    b[i] = '0';
                }
            } else  {
                if (isLess) {
                    a[i] = '1';
                    b[i] = '0';
                } else {
                    a[i] = '0';
                    b[i] = '1';
                    isLess = true;
                }
            }
        }
        System.out.println(fromBinaryString(new String(a)) ^ fromBinaryString(new String(b)));
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = Task1_Maximizing_XOR.class;
        String inputFile = clazz.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(in): 
            new Scanner(Task1_Maximizing_XOR.class.getResourceAsStream(inputFile));
        parse(scanner.nextInt(), A);
        parse(scanner.nextInt(), B);
        solve();
        scanner.close();
    }
    
}