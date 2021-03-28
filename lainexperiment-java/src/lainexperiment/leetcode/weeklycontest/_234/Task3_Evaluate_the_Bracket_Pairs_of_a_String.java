/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._234;

import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 27/03/2021
 * 
 * Problem: Evaluate the Bracket Pairs of a String
 * Status: accepted
 * 
 * You are given a string s that contains some bracket pairs,
 * with each pair containing a non-empty key.
 * 
 * For example, in the string "(name)is(age)yearsold", there
 * are two bracket pairs that contain the keys "name" and "age".
 * 
 * You know the values of a wide range of keys. This is represented
 * by a 2D string array knowledge where each
 * knowledge[i] = [key_i, value_i] indicates that key key_i has a
 * value of value_i.
 * 
 * You are tasked to evaluate all of the bracket pairs. When
 * you evaluate a bracket pair that contains some key key_i,
 * you will:
 * 
 * - Replace key_i and the bracket pair with the key's
 *   corresponding value_i.
 * - If you do not know the value of the key, you will replace
 *   key_i and the bracket pair with a question mark "?" (without
 *   the quotation marks).
 *   
 * Each key will appear at most once in your knowledge. There
 * will not be any nested brackets in s.
 * 
 * Return the resulting string after evaluating all of the bracket pairs.
 * 
 * Input
 * 
s = "(a)(a)(a)aaa", knowledge = [["a","yes"]]
 * 
 * Output
 * 
yesyesyesaaa
 * 
 * }</pre>
 */
public class Task3_Evaluate_the_Bracket_Pairs_of_a_String {
    
    public String evaluate(String s, List<List<String>> knowledge) {
        var m = knowledge.stream()
                .collect(toMap(l -> l.get(0), l -> l.get(1)));
        var a = s.toCharArray();
        var b = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (a[i] != '(') {
                b.append(a[i]);
                continue;
            }
            i++;
            var k = "";
            while (a[i] != ')') {
                k += a[i++];
            }
            b.append(m.getOrDefault(k, "?"));
        }
        return b.toString();
    }

    @Test
    public void test() {
        assertEquals("ba", evaluate("(a)(b)", List.of(
            List.of("a","b"),
            List.of("b","a"))));

        assertEquals("yesyesyesaaa", evaluate("(a)(a)(a)aaa", List.of(
            List.of("a","yes"))));

        assertEquals("hi?", evaluate("hi(name)", List.of(
            List.of("a","b"))));
        assertEquals("bobis?yearsold", evaluate("(name)is(age)yearsold", List.of(
            List.of("name","bob"),
            List.of("s","two"))));
        assertEquals("bobistwoyearsold", evaluate("(name)is(age)yearsold", List.of(
            List.of("name","bob"),
            List.of("age","two"))));
    }
}
