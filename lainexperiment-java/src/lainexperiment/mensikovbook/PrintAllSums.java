/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.mensikovbook;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>{@code
 * Date: 05/10/2015
 * 
 * Problem
 * 
 * Print all unique sums which forms given number.
 * 
 * Input
 * 
4
 * 
 * Output
 * 
1 + 3
1 + 1 + 2
1 + 1 + 1 + 1
2 + 2
 * 
 * }</pre>
 */
public class PrintAllSums 
{

    static void findSumsDp(int n, int p, String s) {
        if (n == 0) {
            System.out.println(s.substring(3));
            return;
        }
        if (p <= n && !s.isEmpty())
            findSumsDp(0, n, s + " + " + n);
        for (int i = p; i <= n / 2; ++i) {
            int d = n - i;
            findSumsDp(d, i, s + " + " + i);
        }
    }
    
    static void findSums(int n) {
        assertTrue(n != 0);
        findSumsDp(n, 1, "");
    }
    
    public static void main(String[] args) {
        findSums(4);
        findSums(0);
    }

}
