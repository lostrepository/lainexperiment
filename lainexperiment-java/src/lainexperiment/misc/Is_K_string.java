/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

/**
 * <pre>{@code
 * Date: 14/02/2015
 * 
 * Problem
 * 
 * Check if string formed by concatenation of another string k times.
 *
 * Input
 * 
 * ababcababcababc
 * 
 * Output
 * 
 * true
 * 
 * }</pre>
 */
public class Is_K_string {

    static boolean iskstring(char[] a) {
        int l = 0;
        for (int i = 1; i < a.length; ++i) {
            if (a[i] == a[l])
                l++;
            else
                l = 0;
        }
        if (l == 0)
            return false;
        if (l == a.length - 1)
            return true;
        l = a.length - l;
        if (a.length % l != 0)
            return false;
        for (int i = 0, k = 0; i < a.length; i++) {
            if (a[i] != a[k])
                return false;
            k++;
            if (k == l)
                k = 0;
        }
        return true;
    }
    
    public static void main(String[] args) {
        assertEquals(iskstring("a".toCharArray()), false);
        assertEquals(iskstring("aaa".toCharArray()), true);
        assertEquals(iskstring("abab".toCharArray()), true);
        assertEquals(iskstring("ababc".toCharArray()), false);
        assertEquals(iskstring("ababcababcababc".toCharArray()), true);
        assertEquals(iskstring("abcdabcd".toCharArray()), true);
        assertEquals(iskstring("abcabcabc".toCharArray()), true);
        assertEquals(iskstring("zzxzzxzzx".toCharArray()), true);
        assertEquals(iskstring("abcdabcd".toCharArray()), true);
        assertEquals(iskstring("abcabcdabcabcf".toCharArray()), false);
        assertEquals(iskstring("abcabcdabcabcd".toCharArray()), true);
        assertEquals(iskstring("abcdabbd".toCharArray()), false);
        assertEquals(iskstring("abcabcefg".toCharArray()), false);
        assertEquals(iskstring("zzxzzyzzx".toCharArray()), false);
        assertEquals(iskstring("abcdfgabc".toCharArray()), false);
    }
    
    static boolean iskstring2(char[] a) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c: a) {
            m.merge(c, 1, (v1, v2) -> v1 + v2);
        }
        int min = m.values().stream().min(Comparator.naturalOrder()).get();
        if (m.size() == 1)
            return true;
        if (min == 1)
            return false;
        Predicate<Entry<Character, Integer>> l = (e) ->
            e.getValue() % min > 0? true: false;
        if (m.entrySet().stream().filter(l).count() > 0)
            return false;
        int len = a.length / 2;
        for (int i = 0, k = 0; i < a.length; i++) {
            if (a[i] != a[k])
                return false;
            k++;
            if (k == len)
                k = 0;
        }
        return true;
    }
    
}
