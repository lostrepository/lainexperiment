/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._225;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 23/01/2021
 * 
 * Problem: Latest Time by Replacing Hidden Digits
 * Status: accepted
 * 
 * You are given a string time in the form of hh:mm, where some
 * of the digits in the string are hidden (represented by ?).
 * 
 * The valid times are those inclusively between 00:00 and 23:59.
 * 
 * Return the latest valid time you can get from time by replacing the hidden digits.
 * 
 * Example 1:
 * 
2?:?0
 * 
 * Output
 * 
23:50
 * 
 * }</pre>
 */
public class Task1_Latest_Time_by_Replacing_Hidden_Digits {

    public String maximumTime(String time) {
        String a = "00";
        String regex = time.substring(0, 2).replace('?', '.');
        for (int i = 0; i < 24; i++) {
            var s = String.format("%2d", i).replace(' ', '0');
            if (Pattern.matches(regex, s))
                a = s;
        }
        String b = "00";
        regex = time.substring(3).replace('?', '.');
        for (int i = 0; i < 60; i++) {
            var s = String.format("%2d", i).replace(' ', '0');
            if (Pattern.matches(regex, s))
                b = s;
        }
        return a + ":" + b;
    }

    @Test
    public void test() {
        assertEquals("09:39", maximumTime("0?:3?"));
        assertEquals("19:22", maximumTime("1?:22"));
        assertEquals("23:59", maximumTime("??:??"));
    }
}
