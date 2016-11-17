/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 01/11/2016
 * 
 * Hacker rank
 * Week of code 25
 * Problem: Append and Delete
 * Status: accepted
 * 
 * You have a string, s, of lowercase English alphabetic letters. 
 * You can perform two types of operations on s:
 * 
 * - Append a lowercase English alphabetic letter to the end of 
 * the string.
 * - Delete the last character in the string. Performing this 
 * operation on an empty string results in an empty string.
 * 
 * Given an integer, k, and two strings, s and t, determine 
 * whether or not you can convert s to t by performing exactly k
 * of the above operations on s. If it's possible, print Yes; 
 * otherwise, print No.
 * 
 * Input Format
 * 
 * The first line contains a string, s, denoting the initial 
 * string. 
 * The second line contains a string, t, denoting the desired 
 * final string. The third line contains an integer, k, denoting 
 * the desired number of operations.
 * 
 * Output Format
 * 
 * Print Yes if you can obtain string by performing exactly 
 * operations on ; otherwise, print No.
 * 
 * 
 * Sample Input
 * 
hackerhappy
hackerrank
9
 *
 * Sample Output
 * 
Yes
 *
 */

package lainexperiment.hackerrank.weekofcode._25;

import static java.lang.Math.min;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2_Append_and_Delete {

    static String S, T;
    static int K;
    
    static boolean canConvert() {
        int c = 0;
        int min = min(S.length(), T.length());
        int plen = range(0, min)
            .filter(i -> S.charAt(i) != T.charAt(i))
            .findFirst().orElse(min);
        c = S.length() - plen;
        c += T.length() - plen;
        if ((K - c) % 2 == 0)
            return c <= K;
        return K >= S.length() + T.length();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task2_Append_and_Delete.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task2_Append_and_Delete.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            S = scanner.next();
            T = scanner.next();
            K = scanner.nextInt();
            System.out.println(canConvert()? "Yes": "No");
        }
        scanner.close();
    }

}
