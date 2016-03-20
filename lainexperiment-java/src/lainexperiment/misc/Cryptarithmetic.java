/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 13/02/2015
 * 
 * Problem
 * 
 * Solve cryptarithmetic puzzle.
 *
 * Input
 * 
 * TWO
 * TWO
 * FOUR
 * 
 * Output
 * 
 * F-1
 * O-4
 * R-8
 * T-7
 * U-6
 * W-3
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cryptarithmetic {

    static String add(char[] a, char[] b, char[] c, int i, int carry, int[] s, Map<Character, Integer> m) 
    {
        if (i >= a.length && i >= b.length) {
            if (i == c.length)
                return m.keySet().stream().sorted().
                    map((k) -> k + "-" + m.get(k) + '\n').
                    collect(Collectors.joining());
            if (i + 1 != c.length)
                return null;
            if (carry == 0)
                return null;
            if (!m.containsKey(c[i])) {
                if (s[carry] == 1)
                    return null;
                m.put(c[i], carry);
                s[carry] = 1;
                return add(b, a, c, i + 1, carry, s, m);
            }
            if (m.get(c[i]) == 1)
                return add(b, a, c, i + 1, carry, s, m);
            return null;
        }
        if (i >= a.length)
            return add(b, a, c, i, carry, s, m);
        if (!m.containsKey(a[i])) {
            for (int d = 0; d < 10; ++d) {
                if (s[d] == 1)
                    continue;
                if (i == a.length - 1 && d == 0)
                    continue;
                s[d] = 1;
                m.put(a[i], d);
                String r = add(b, a, c, i, carry, s, m);
                if (r != null)
                    return r;
                s[d] = 0;
                m.remove(a[i]);
            }
            return null;
        }
        int d1 = m.get(a[i]);
        if (i >= b.length)
            return add(a, b, c, i + 1, carry, s, m);
        if (!m.containsKey(b[i]))
            return add(b, a, c, i, carry, s, m);
        int d2 = m.get(b[i]);
        if (!m.containsKey(c[i])) {
            int r = d1 + d2 + carry;
            if (s[r % 10] == 1)
                return null;
            s[r % 10] = 1;
            m.put(c[i], r % 10);
            String res = add(a, b, c, i + 1, r / 10, s, m);
            if (res != null)
                return res;
            s[r % 10] = 0;
            m.remove(c[i]);       
            return null;
        }
        int r = d1 + d2 + carry;
        if (r % 10 == m.get(c[i])) 
            return add(a, b, c, i + 1, r / 10, s, m);
        return null;
    }
    
    public static void main(String[] args) {
        assertEquals(
                "F-1\nO-4\nR-8\nT-7\nU-6\nW-3\n", 
                add(
                    new StringBuilder("TWO").reverse().toString().toCharArray(), 
                    new StringBuilder("TWO").reverse().toString().toCharArray(),
                    new StringBuilder("FOUR").reverse().toString().toCharArray(),
                    0,
                    0,
                    new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    new HashMap<>()));
        assertEquals(
                "D-7\nE-5\nM-1\nN-6\nO-0\nR-8\nS-9\nY-2\n",
                add(
                        new StringBuilder("SEND").reverse().toString().toCharArray(), 
                        new StringBuilder("MORE").reverse().toString().toCharArray(),
                        new StringBuilder("MONEY").reverse().toString().toCharArray(),
                        0,
                        0,
                        new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        new HashMap<>()));
        System.out.println(add(
                        new StringBuilder("EVER").reverse().toString().toCharArray(), 
                        new StringBuilder("SINCE").reverse().toString().toCharArray(),
                        new StringBuilder("DARWIN").reverse().toString().toCharArray(),
                        0,
                        0,
                        new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        new HashMap<>()));
    }

}
