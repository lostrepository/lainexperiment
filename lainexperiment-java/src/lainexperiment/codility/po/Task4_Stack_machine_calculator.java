/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 25/07/2016
 * 
 * PO Codility
 * Problem: Stack machine calculator
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

package lainexperiment.codility.po;

import static org.junit.Assert.assertEquals;

import java.util.Stack;
import java.util.function.IntBinaryOperator;

public class Task4_Stack_machine_calculator {

    private static final int MAX = 1 << 13;
    private static final int ERROR = -1;

    private static final IntBinaryOperator MUL = (a, b) -> a * b;
    private static final IntBinaryOperator ADD = (a, b) -> a + b;
    
    private final Stack<Integer> stack = new Stack<>();
    
    private boolean calc(IntBinaryOperator operation) {
        
        if (stack.size() < 2) 
            return false;
        
        int a = stack.pop();
        int b = stack.pop();
        int r = operation.applyAsInt(a, b);
        
        if (r >= MAX) 
            return false;
        
        stack.push(r);
        
        return true;
        
    }
    
    public int solution(String S) {
        
        stack.clear();
        
        int cnt = 0;
        
        while (cnt < S.length()) {
            
            char ch = S.charAt(cnt++);
            
            switch (ch) {
            
            case '*':
                if (!calc(MUL)) 
                    return ERROR;
                break;
            
            case '+':
                if (!calc(ADD)) 
                    return ERROR;
                break;

            default:
                if (ch < '0' || ch > '9') 
                    return ERROR;
                stack.push(ch - '0');
            
            }
        }
        
        return stack.size() == 0? ERROR: stack.pop();
        
    }
    
    public static int solve(String S) {
        return new Task4_Stack_machine_calculator().solution(S);
    }
    
    public static void main(String[] args) {
        assertEquals(76, solve("13+62*7+*"));
        assertEquals(-1, solve("11++"));
        assertEquals(-1, solve("1+"));
        assertEquals(4, solve("11+11++"));
        assertEquals(-1, solve("11+11++q"));
        assertEquals(4, solve("22+"));
    }
    
}
