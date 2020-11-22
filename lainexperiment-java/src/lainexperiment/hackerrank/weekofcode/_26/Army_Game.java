/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._26;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 30/11/2016
 * 
 * Hacker rank
 * Week of code 26
 * Problem: Army Game
 * Status: accepted
 * 
 * Luke is daydreaming in Math class. He has a sheet of graph paper 
 * with n rows and m columns, and he imagines that there is an army base 
 * in each cell for a total of n x m bases. He wants to drop supplies at 
 * strategic points on the sheet, marking each drop point with a red 
 * dot. If a base contains at least one package inside or on top of 
 * its border fence, then it's considered to be supplied.
 * Given and , what's the minimum number of packages that Luke must 
 * drop to supply all of his bases?
 * 
 * Input Format
 * 
 * Two space-separated integers describing the respective values of 
 * n and m.
 * 
 * Output Format
 * 
 * Print a single integer denoting the minimum number of supply packages 
 * Luke must drop. 
 * 
 * 
 * Sample Input
 * 
2 2
 *
 * Sample Output
 * 
1
 *
 * }</pre>
 */
public class Army_Game {
 
    static int c(int n) {
        if (n == 1) return 0;
        return n / 2 + ((n & 1) == 1? 1: 0);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Army_Game.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Army_Game.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int r = 0;
            if (m == n && n == 1) {
                r = 1;
            } else if (min(n, m) <= 2) {
                r = c(max(n, m));
            } else {
                r = c(n) * c(m);
            }
            System.out.println(r);
        }
        scanner.close();
    }

}
