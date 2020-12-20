/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._220;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/19/2020
 * 
 * Problem: Reformat Phone Number
 * Status: accepted
 * 
 * You are given a phone number as a string number. number consists
 * of digits, spaces ' ', and/or dashes '-'.
 * 
 * You would like to reformat the phone number in a certain manner.
 * Firstly, remove all spaces and dashes. Then, group the digits from
 * left to right into blocks of length 3 until there are 4 or fewer
 * digits. The final digits are then grouped as follows:
 * 
 * 2 digits: A single block of length 2.
 * 3 digits: A single block of length 3.
 * 4 digits: Two blocks of length 2 each.
 * 
 * The blocks are then joined by dashes. Notice that the reformatting
 * process should never produce any blocks of length 1 and produce at
 * most two blocks of length 2.
 * 
 * Return the phone number after formatting.
 *
 * Example 1:
 * 
number = "1-23-45 6"
 * 
 * Output
 * 
123-456
 * 
 * }</pre>
 */
public class Task1_Reformat_Phone_Number {

    public String reformatNumber(String number) {
        number = number.replaceAll("[\\s-]*", "");
        var res = "";
        int i = 0;
        var full = number.length() / 3;
        var rest = number.length() % 3;
        if (rest == 1) full--;
        while (full > 0) {
            res += number.substring(i, i + 3);
            i += 3;
            if (i != number.length()) {
                res+= "-";
            }
            full--;
        }
        if (i + 4 == number.length()) {
            res += number.substring(i, i + 2) + "-";
            i += 2;
        }
        if (i != number.length()) {
            res += number.substring(i);
        }
        return res;
    }

    @Test
    public void test() {
        assertEquals("123-45", reformatNumber("1 2-3 45"));
        assertEquals("123-456", reformatNumber("1-23-45 6"));
        assertEquals("123-45-67", reformatNumber("123 4-567"));
        assertEquals("123-456-78", reformatNumber("123 4-5678"));
    }
}
