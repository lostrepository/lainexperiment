/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 19/04/2015
 * 
 * Problem
 * 
 * You are given a string and a dictionary. You need to split the given string 
 * on words according to the dictionary.
 *
 * Input
 * 
 * Dictionary: cat, dog, a
 * catdog
 * catadog
 * 
 * Output
 * 
 * 0, 3
 * 0, 3, 4
 * 
 */

package misc;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class SplitTheString {

    static String split(char[] a, Set<String> d) {
        Stack<Integer> t = new Stack<>();
        int e = 0;
        t.push(0);
        while (!t.isEmpty()) {
            int s = t.peek();
            if (e >= a.length) {
                e = t.pop();
                continue;
            }
            String w = new String(a, s, e - s + 1);
            if (d.contains(w)) {
                e++;
                if (e == a.length)
                    return t.toString();
                t.push(e);
                continue;
            }
            e++;
        }
        return "";
    }
    
    public static void main(String[] args) {
        Set<String> d = new HashSet<String>();
        d.add("aa");
        d.add("a");
        d.add("ab");
        d.add("aab");
        d.add("ba");
        d.add("cat");
        d.add("dog");
        d.add("ok");
        d.add("ad");
        assertEquals("[0, 1, 2, 3, 4]", split("aaaaab".toCharArray(), d));
        assertEquals("[0, 3, 4]", split("catadog".toCharArray(), d));
        assertEquals("[0, 3, 5]", split("catadok".toCharArray(), d));
    }
    
}
