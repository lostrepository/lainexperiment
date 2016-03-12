/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 18/04/2015
 * 
 * Problem
 * 
 * Check if strings are isomorphic.
 *
 * Input
 * 
 * aaa
 * bba
 * 
 * Output
 * 
 * false
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class IsIsomorphic {

    static boolean isIsomorphic(char[] a1, char[] a2) {
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        if (a1.length != a2.length)
            return false;
        int c = 0;
        for (int i = 0; i < a1.length; ++i) {
            if (m1.containsKey(a1[i])) {
                if (m1.get(a1[i]) == m2.get(a2[i]))
                    continue;
                return false;
            }
            m1.put(a1[i], c);
            m2.put(a2[i], c);
            c++;
        }
        return true;
    }
    
    public static void main(String[] args) {
        assertEquals(isIsomorphic("aaa".toCharArray(), "bba".toCharArray()), false);
        assertEquals(isIsomorphic("456".toCharArray(), "123".toCharArray()), true);
        assertEquals(isIsomorphic("abbacbdc".toCharArray(), "12213243".toCharArray()), true);
    }
    
}
