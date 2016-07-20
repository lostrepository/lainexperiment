/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 29/06/2016
 * 
 * Cimpress Codility
 * Problem: Task 1
 * Status: passed
 * 
 * Problem
 * 
 * Given a string with reverse polish notation need to calculate the
 * result. Available operators are + and *. Only single digit numbers
 * supported.
 * 
 * Sample Input
 * 
13+62*7+*
 * 
 * Sample Output
 * 
76
 * 
 */

package lainexperiment.codility.cimpress;

import static org.junit.Assert.assertEquals;

import java.util.Stack;
import java.util.function.IntBinaryOperator;

public class Task1 {

    private static final int MAX = 1 << 13;

    private static final IntBinaryOperator ADD = (a, b) -> a + b;
    private static final IntBinaryOperator MUL = (a, b) -> a * b;
    
    private final Stack<Integer> s = new Stack<>();
    
    public int solution(String S) {
        int c = 0;
        s.clear();
        while (c < S.length()) {
            char ch = S.charAt(c++);
            switch (ch) {
            case '+':
                if (!calc(ADD)) return -1;
                break;
            case '*':
                if (!calc(MUL)) return -1;
                break;
            default:
                if (ch < '0' || ch > '9') return -1;
                s.push(ch - '0');
            }
        }
        return s.size() == 0? -1: s.pop();
    }
    
    private boolean calc(IntBinaryOperator oper) {
        if (s.size() < 2) return false;
        int a = s.pop();
        int b = s.pop();
        int r = oper.applyAsInt(a, b);
        if (r >= MAX) return false;
        s.push(r);
        return true;
    }
    
    public static int solve(String S) {
        return new Task1().solution(S);
    }
    
    public static void main(String[] args) {
        assertEquals(76, solve("13+62*7+*"));
        assertEquals(-1, solve("11++"));
        assertEquals(-1, solve("1+"));
        assertEquals(4, solve("11+11++"));
        assertEquals(-1, solve("11+11++q"));
//        assertEquals(3, solve("22+"));
    }
    
}
