/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._218;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 05/12/2020
 * 
 * Problem: Goal Parser Interpretation
 * Status: accepted
 * 
 * You own a Goal Parser that can interpret a string command.
 * The command consists of an alphabet of "G", "()" and/or "(al)"
 * in some order. The Goal Parser will interpret "G" as the string
 * "G", "()" as the string "o", and "(al)" as the string "al". The
 * interpreted strings are then concatenated in the original order.
 * 
 * Given the string command, return the Goal Parser's interpretation
 * of command.
 *
 * Example 1:
 * 
command = "G()(al)"
 * 
 * Output
 * 
Goal
 * 
 * }</pre>
 */
public class Task1_Goal_Parser_Interpretation {

    public String interpret(String command) {
        StringBuilder b = new StringBuilder();
        int l = 0;
        for (int i = 0; i < command.length(); i++) {
            char ch = command.charAt(i);
            switch (ch) {
            case 'G':
                b.append('G');
                break;
            case '(':
            case 'a':
            case 'l':
                l++;
                break;
            case ')':
                if (l == 1)
                    b.append('o');
                else
                    b.append("al");
                l = 0;
                break;
            }
        }
        return b.toString();
    }

    @Test
    public void test() {
        assertEquals("alGalooG", interpret("(al)G(al)()()G"));
        assertEquals("Gooooal", interpret("G()()()()(al)"));
        assertEquals("Goal", interpret("G()(al)"));
    }
}
