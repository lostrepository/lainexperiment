/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codility.imdb;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <pre>{@code
 * 
 * Date: 05/08/2015
 * 
 * IMDB Codility
 * Problem: Task 1
 * Status: passed
 * 
 * Problem
 * 
 * Given a string find a longest substring in it which satisfies following
 * requirements:
 * 
 * - it should not contain digits
 * - it should contain at least one upper letter
 * 
 * Input Format
 * 
 * String
 * 
 * Output Format
 * 
 * Length of maximum substring 
 * 
 * Sample Input
 * 
a0ba
A
Ya0Uaa9aaaaaaaaaaaaaa
 * 
 * Sample Output
 * 
-1
1
3
 * 
 * }</pre>
 */
public class Task1_Longest_password_substring {

    static public int solution(String S) {
        int max = 0;
        boolean isValid = false;
        int s = 0;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (Character.isDigit(ch)) {
                if (isValid)
                    max = Math.max(max, i - s);
                s = i + 1;
                isValid = false;
            } else if (Character.isUpperCase(ch)) {
                isValid = true;
            }
        }
        if (isValid)
            max = Math.max(max, S.length() - s);
        return max == 0? -1: max;
    }
    
    public static void main(String[] args) {
        assertTrue(-1 == solution("a0ba"));
        assertTrue(-1 == solution(""));
        assertTrue(-1 == solution("0"));
        assertTrue(1 == solution("0Y"));
        assertTrue(3 == solution("Ya0Uaa9aaaaaaaaaaaaaa"));
        assertTrue(10 == solution("AAAAAAAAAA"));
        assertTrue(1 == solution("A"));
    }
    
}
