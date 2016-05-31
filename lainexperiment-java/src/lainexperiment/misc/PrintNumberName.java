/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 28/01/2015
 * 
 * Problem
 * 
 * Given a number print its name 
 *
 * Input
 * 
 * 1234
 * 
 * Output
 * 
 * one thousand two hundred thirty four
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

public class PrintNumberName {

    static String name(int i, int base) {
        if (i == 0) return "";
        i--;
        String[] nums = {
            "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        };
        String[] tens = {
            "twenty", "thirty", "fourty", "fivety", "sixty", "seventy", "eighty", "ninety"
        };
        if (base == 1) return nums[i] + " ";
        if (base == 10) return tens[i - 1] + " ";
        if (base == 100) return nums[i] + " hundred ";
        return "";
    }
    
    static String name(long n) {
        String num = Long.toString(n);
        int f = num.length() % 3;
        if (f != 0)
            num = String.format("%" + (3 - f) + "s", "").replace(' ', '0') + num;
        String name = "";
        String[] groups = {"", "thousand", "million", "billion"};
        int g = 0;
        for (int i = num.length() - 1; i > 0; i -= 3) {
            String t = name(num.charAt(i - 2) - '0', 100);
            if (num.charAt(i - 1) == '1') {
                String[] teens = {
                    "ten", "eleven", "twelve", "thirteen", "fourteen", "fiveteen", "sixteen", "seventeen", "eighteen", "nineteen"
                };
                t += teens[num.charAt(i) - '0'];
            } else {
                t += name(num.charAt(i - 1) - '0', 10);
                t += name(num.charAt(i - 0) - '0', 1);
            }
            name = g == 0? t: t + groups[g] + " " + name;
            g++;
        }
        return name.trim();
    }
    
    public static void main(String[] args) {
        assertEquals("one thousand two hundred thirty four", name(1234));
        assertEquals("one hundred ninety one million two hundred thirty two thousand eight hundred ninety one", name(191_232_891));
        assertEquals("four hundred eight thousand one hundred sixty three", name(408_163L));
        assertEquals("seven hundred eighty five billion six hundred fourty nine million four hundred eight thousand one hundred sixty three", name(785_649_408_163L));
    }

}
