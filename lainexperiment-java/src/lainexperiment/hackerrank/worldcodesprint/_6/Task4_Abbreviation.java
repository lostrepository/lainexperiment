/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.worldcodesprint._6;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 27/08/2016
 * 
 * Hacker rank
 * World CodeSprint #6
 * Problem: Abbreviation
 * Status: accepted
 * 
 * You can perform the following operation on some string, a:
 * - Capitalize zero or more of a's lowercase letters at some 
 * index i (i.e., make them uppercase).
 * - Delete all of the remaining lowercase letters in a.
 * Given q queries in the form of two strings, a and b, determine 
 * if it's possible to make a equal to b by performing the above 
 * operation on a. If a can be transformed into b, print YES on a 
 * new line; otherwise, print NO.
 * 
 * Input Format
 * 
 * The first line contains a single integer, q, denoting the number 
 * of queries. The 2q subsequent lines describe each query in the 
 * following format: 
 * - The first line of each query contains a single string, a.
 * - The second line of each query contains a single string, b.
 * 
 * Output Format
 * 
 * For each query, print YES on a new line if it's possible to make 
 * string a equal to string b by performing the operation specified 
 * above; otherwise, print NO.
 * 
 * 
 * Sample Input
 * 
1
daBcd
ABC
 *
 * Sample Output
 * 
YES
 *
 *
 * }</pre>
 */
public class Task4_Abbreviation {

    static char[] FROM, TO;
    
    static int LCS(char[] x, char[] y) {
        int rows = x.length + 1;
        int cols = y.length + 1;
        int[][] c = new int[rows][cols];
        for (int i = 0; i < rows; ++i)
            c[i][0] = 0;
        for (int i = 0; i < cols; ++i)
            c[0][i] = 0;
        for (int i = 1; i < rows; ++i) {
            for (int j = 1; j < cols; ++j) {
                if (x[i - 1] == y[j - 1]) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                } else { 
                    c[i][j] = c[i][j - 1];
                }
            }
        }
        return c[rows - 1][cols - 1];
    }
    
    static void solve() {
        String s = "";
        for (int i = 0; i < FROM.length; i++) {
            if (Character.isUpperCase(FROM[i]))
                s += FROM[i];
            FROM[i] = Character.toUpperCase(FROM[i]);
        }
        if (LCS(s.toCharArray(), TO) != s.length())
            System.out.println("NO");
        else
            System.out.println(LCS(FROM, TO) == TO.length? "YES": "NO");
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task4_Abbreviation.class;
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(clazz.getResourceAsStream(clazz.getSimpleName() + ".in"));
        while (scanner.hasNext()) {
            int q = scanner.nextInt();
            for (int i = 0; i < q; i++) {
                FROM = scanner.next().toCharArray();
                TO = scanner.next().toCharArray();
                solve();
            }
        }
        scanner.close();
    }

}
