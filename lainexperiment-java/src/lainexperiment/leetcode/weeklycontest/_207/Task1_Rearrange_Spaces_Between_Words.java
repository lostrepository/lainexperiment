/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 19/03/2020
 * 
 * Problem: Rearrange Spaces Between Words
 * Status: accepted
 * 
 * You are given a string text of words that are placed among some
 * number of spaces. Each word consists of one or more lowercase
 * English letters and are separated by at least one space. It's
 * guaranteed that text contains at least one word.
 * 
 * Rearrange the spaces so that there is an equal number of spaces
 * between every pair of adjacent words and that number is maximized.
 * If you cannot redistribute all the spaces equally, place the extra
 * spaces at the end, meaning the returned string should be the same
 * length as text.
 * 
 * Return the string after rearranging the spaces.
 *
 * Input
 * 
" practice   makes   perfect"
 * 
 * Output
 * 
"practice   makes   perfect "
 * 
 * 
 * }</pre>
 */
public class Task1_Rearrange_Spaces_Between_Words {

    public String reorderSpaces(String text) {
        int spaces = (int) text.chars()
            .filter(i -> i == ' ')
            .count();
        var words = text.trim().split("\\s+");
        int d = words.length - 1;
        if (d == 0) d = 1;
        int n = spaces / d;
        String res = "";
        for (var w: words) {
            res += w;
            res += " ".repeat(n);
        }
        if (spaces % d != 0)
            res += " ";
        res = res.substring(0, text.length());
        return res;
    }
    
    @Test
    public void test() {
        assertEquals("a   ", reorderSpaces("   a"));
        assertEquals("a", reorderSpaces("a"));
        assertEquals("walks  udp  package  into  bar  a ", reorderSpaces("  walks  udp package   into  bar a"));
        assertEquals("hello   world", reorderSpaces("hello   world"));
        assertEquals("practice   makes   perfect ", reorderSpaces(" practice   makes   perfect"));
        assertEquals("this   is   a   sentence", reorderSpaces("  this   is  a sentence "));
        assertEquals("a   b   g   b", reorderSpaces(" a   b  g   b"));
    }
}
