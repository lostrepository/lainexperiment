/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * <pre>{@code
 * Date: 24/03/2017
 * 
 * Problem
 * 
 * Given an n-digit positive integer, count and print the 
 * number of consecutive subsequences formed by 
 * concatenating the given number's digits that are divisible 
 * by 8. As the result can be large, print the result 
 * modulo 10^9 + 7. 
 * 
 * Input
 * 
2
08
 * 
 * Output
 * 
3
 * 
 * }</pre>
 */
public class Numbers_divisible_by_eight {
    
    static int append(int n, int a) {
        n %= 100;
        n *= 10;
        n += a;
        return n;
    }
    
    static long count(int[] n) {
        int k = 0;
        long r = 0;
        for (int i = 0; i < n.length; i++) {
            k = append(k, n[i]);
            for (int c = 1; c < 4 && i - c + 1 >= 0; c++) {
                int d = (int) pow(10, c);
                int rk = k % d;
                if ((rk % 8) != 0) continue;
                r++;
                if (c == 3)
                    r += max(i - 3, 0);
            }
            r %= 1000000007;
        }
        return r;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Numbers_divisible_by_eight.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Numbers_divisible_by_eight.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            scanner.nextLine();
            System.out.println(count(scanner.nextLine()
                    .chars()
                    .map(i -> i - '0')
                    .toArray()));
        }
        scanner.close();
    }
    
}

