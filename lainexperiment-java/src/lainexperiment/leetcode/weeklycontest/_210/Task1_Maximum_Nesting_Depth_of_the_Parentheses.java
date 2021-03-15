/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._210;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 13/03/2020
 * 
 * Problem: Maximum Nesting Depth of the Parentheses
 * Status: accepted
 * 
 * A string is a valid parentheses string (denoted VPS) if it
 * meets one of the following:
 * 
 * - It is an empty string "", or a single character not equal
 *   to "(" or ")",
 * - It can be written as AB (A concatenated with B), where A
 *   and B are VPS's, or
 * - It can be written as (A), where A is a VPS.
 * 
 * We can similarly define the nesting depth depth(S) of any
 * VPS S as follows:
 * 
 * - depth("") = 0
 * - depth(C) = 0, where C is a string with a single character
 *   not equal to "(" or ")".
 * - depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
 * - depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
 * 
 * For example, "", "()()", and "()(()())" are VPS's (with nesting
 * depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
 * 
 * Given a VPS represented as string s, return the nesting depth of s.
 *
 * Input
 * 
(1)+((2))+(((3)))
 * 
 * Output
 * 
3
 * 
 * }</pre>
 */
public class Task1_Maximum_Nesting_Depth_of_the_Parentheses {

    public int maxDepth(String s) {
        var a = s.toCharArray();
        int max = 0;
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == ')') {
                c--;
                if (c < 0) throw new RuntimeException();
                continue;
            }
            if (a[i] == '(') {
                c++;
                max = Math.max(max, c);
                continue;
            }
        }
        return max;
    }
    
    @Test
    public void test() {
        assertEquals(1, maxDepth("1+(2*3)/(2-1)"));
        assertEquals(3, maxDepth("(1)+((2))+(((3)))"));
        assertEquals(3, maxDepth("(1+(2*3)+((8)/4))+1"));
        assertEquals(4, maxDepth("(dd(sss)(ss((ff))))"));
        assertEquals(0, maxDepth(""));
        assertEquals(1, maxDepth("()"));
    }
}
