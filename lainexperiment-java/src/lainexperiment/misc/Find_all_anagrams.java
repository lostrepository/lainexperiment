/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 18/12/2016
 * 
 * Problem
 * 
 * Given strings haystack and needle return indices from
 * haystack where anagrams of needle occur.
 *
 * Input
 * 
abdcghbaabcdij bcda
 * 
 * Output
 * 
0, 8
 * 
 * }</pre>
 */
public class Find_all_anagrams {

    static List<Integer> indices(String haystack, String needle) {
        if (haystack.length() < needle.length())
            return Collections.emptyList();
        if (needle.isEmpty())
            return Collections.emptyList();
        List<Integer> res = new ArrayList<Integer>();
        char[] p = needle.toCharArray();
        Arrays.sort(p);
        int len = haystack.length() - needle.length() + 1;
        for (int i = 0; i < len; i++) {
            char[] a = haystack.substring(i, i + needle.length()).toCharArray();
            Arrays.sort(a);
            if (Arrays.equals(a, p))
                res.add(i);
        }
        return res;
    }
    
    @Test
    public void test() {
        assertEquals("[0, 8]", indices("abdcghbaabcdij", "bcda").toString());
        assertEquals("[2, 3, 4, 5, 6, 9]", indices("bbbababaaabbbb", "ab").toString());
        assertEquals("[2, 3, 4, 5, 6, 9]", indices("bbbababaaabbbb", "ba").toString());
        assertEquals("[0, 1, 2]", indices("bbb", "b").toString());
        assertEquals("[0]", indices("aab", "baa").toString());
        assertEquals("[]", indices("a", "b").toString());
        assertEquals("[]", indices("abc", "bababab").toString());
        assertEquals("[0]", indices("abc", "ba").toString());
        assertEquals("[]", indices("abc", "baca").toString());
        assertEquals("[3]", indices("abcdba", "bad").toString());
        assertEquals("[]", indices("abcdba", "bAd").toString());
        assertEquals("[]", indices("abcdba", "").toString());
    }
}
