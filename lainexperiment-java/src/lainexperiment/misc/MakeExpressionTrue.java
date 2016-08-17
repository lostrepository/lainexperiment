/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 28/05/2016
 * 
 * Problem
 * 
 * You are given expression like: 2 2 2 2 = 9 and you need to add in 
 * it operators -, +, *, / or parentheses in such way to make it true.
 *
 * Input
 * 
2 2 2 2
-2
 * 
 * Output
 * 
(((2/2)-2)*2)
 * 
 */

package lainexperiment.misc;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.stream.Collectors;

public class MakeExpressionTrue {
    
    static int i;
    
    static int readInt(char[] a) {
        int x = 0;
        while (i < a.length && (a[i] >= '0') &&
                (a[i] <= '9'))
        {
            x = 10 * x + (a[i++] - '0');
        }
        return x;
    }
    
    static int eval(char[] a, StringBuilder infixExp) {
        int x = 0;
        if (i == a.length) return 0;
        while (a[i] == ' ')
            i++;
        if (a[i] >= '0' && a[i] <= '9') {
            int num = readInt(a);
            infixExp.append(num);
            return num;
        }
        char op = a[i++];
        infixExp.append("(");
        int i1 = eval(a, infixExp);
        infixExp.append(op);
        int i2 = eval(a, infixExp);
        infixExp.append(")");
        switch (op) {
        case '+': 
            return i1 + i2; 
        case '*':
            return i1 * i2; 
        case '/':
            return i2 == 0? 0: i1 / i2;
        case '-':
            return i1 - i2; 
        }
        return x;
    }

    static int calc(char[] a, StringBuilder infixExp) {
        i = 0;
        return eval(a, infixExp);
    }
    
    static boolean isTrue(int[] a, int res, int p, int c, String exp) {
        if (c == 0) {
            exp += range(p, a.length).mapToObj(i -> 
                Integer.toString(a[i])).collect(Collectors.joining(" "));
            StringBuilder buf = new StringBuilder();
            int r = calc(exp.toCharArray(), buf);
            if (res == r) System.out.format("%3$s\n", exp, r, buf.toString());
            return r == res;
        }
        if (c >= a.length - p) return false;
        char[] ops = {'*', '-', '+', '/'};
        for (char op: ops) {
            String nexp = exp + op + " ";
            if (isTrue(a, res, p, c - 1, nexp)) return true;
            if (c == 1) continue;
            nexp += a[p] + " ";
            if (isTrue(a, res, p + 1, c - 1, nexp)) return true;
            nexp += a[p + 1] + " ";
            if (isTrue(a, res, p + 2, c - 1, nexp)) return true;
        }
        return false;
    }
    
    static boolean isTrue(int[] a, int res) {
        return isTrue(a, res, 0, a.length - 1, "");
    }
    
    public static void main(String[] args) {
        assertTrue(isTrue(new int[]{2, 2, 2, 2}, -2));
        assertTrue(isTrue(new int[]{2, 3, 4, 5, 76, 65, 32, 22}, 528));
        assertFalse(isTrue(new int[]{2, 2, 2, 2}, 9));
    }

}
