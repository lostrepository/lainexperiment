/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>{@code
 * Date: 17/01/2015
 * 
 * Problem
 * 
 * Given a pattern and a string input - find if the string follows the same pattern and 
 * return 0 or 1.
 *
 * Input
 * 
 * abba, redbluebluered
 * aaaa, asdasdasdasd
 * aabb, xyzabcxzyabc 
 * 
 * Output
 * 
 * 1
 * 1
 * 0
 * 
 * }</pre>
 */
public class MatchTheString {

    private static boolean matches(char[] a, int ai, char[] p, int pi, 
            Map<Character, String> m) 
    {
        if (ai == a.length && pi == p.length)
            return true;
        if (pi == p.length)
            return false;
        if (m.containsKey(p[pi])) {
            String s = m.get(p[pi]);
            int i = ai;
            for (char c: s.toCharArray()) {
                if (i == a.length)
                    return false;
                if (c != a[i++])
                    return false;
            }
            return matches(a, i, p, pi + 1, m);
        }
        for (int i = ai + 1; i < a.length; ++i) {
            m.put(p[pi], new String(a, ai, i - ai));
            if (matches(a, i, p, pi + 1, m))
                return true;
        }
        m.remove(p[pi]);
        return false;
    }
    
    public static void main(String[] args) {
        assertEquals(true, matches("redbluebluered".toCharArray(), 0, 
                "abba".toCharArray(), 0, new HashMap<>()));
        assertEquals(false, matches("redblueblue".toCharArray(), 0, 
                "abba".toCharArray(), 0, new HashMap<>()));
        assertEquals(true, matches("redblueblue".toCharArray(), 0, 
                "abb".toCharArray(), 0, new HashMap<>()));
        assertEquals(true, matches("redred".toCharArray(), 0, 
                "aa".toCharArray(), 0, new HashMap<>()));
        assertEquals(true, matches("asdasdasdasd".toCharArray(), 0, 
                "aaaa".toCharArray(), 0, new HashMap<>()));
        assertEquals(false, matches("redredj".toCharArray(), 0, 
                "aa".toCharArray(), 0, new HashMap<>()));
        assertEquals(false, matches("xyzabcxzyabc".toCharArray(), 0, 
                "aabb".toCharArray(), 0, new HashMap<>()));
    }

}
