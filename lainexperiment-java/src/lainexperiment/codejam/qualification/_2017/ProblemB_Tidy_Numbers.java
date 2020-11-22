/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codejam.qualification._2017;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * <pre>{@code
 * Date: 08/04/2017
 *
 * Code jam 2017
 * Problem: Problem B. Tidy Numbers
 * Status: accepted
 * 
 * Problem
 * 
 * Given number N print greatest tidy number which is less or 
 * equal N. Tidy number it is number which digits are sorted order.
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test cases, T. 
 * T lines follow. Each line describes a test case with a single 
 * integer N. 
 * 
 * Output Format
 * 
 * For each test case, output one line containing Case #x: y, 
 * where x is the test case number (starting from 1) and y is 
 * the last tidy number. 
 * 
 * Input
 * 
4
132
1000
7
111111111111111110
 * 
 * Output
 * 
Case #1: 129
Case #2: 999
Case #3: 7
Case #4: 99999999999999999
 * 
 * }</pre>
 */
public class ProblemB_Tidy_Numbers {
    
    static char[] solve(char[] a) {
        for (int i = a.length - 1; i > 0 ; i--) {
            if (a[i - 1] <= a[i]) continue;
            int k = a[i - 1] - '0';
            k--;
            a[i - 1] = (char) ('0' + k);
            for (int j = i; j < a.length; j++)
                a[j] = '9';
            i = a.length;
        }
        return a;
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = ProblemB_Tidy_Numbers.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(Paths.get("/tmp/in")): new Scanner(clazz.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            System.out.format("Case #%d: %d\n", i + 1, 
                    Long.parseLong(new String(solve(scanner.nextLine().toCharArray()))));
        }
        scanner.close();
    }

}
