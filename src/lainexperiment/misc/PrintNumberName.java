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

    static String name(long i)
    {
        int d = 0;
        String s = "";
        while (i > 0) {
            String postf = "";
            switch (d) {
            case 1:
                postf = "thousand";
                break;
            case 2:
                postf = "million";
                break;
            case 3:
                postf = "billion";
                break;
            }
            s = nameGroup100((int)(i % 1000)) + " " + postf + " " + s;
            i /= 1000;
            d++;
        }
        return s.trim();
    }
    
    private static String nameGroup100(int i) {
        int ii = i % 100;
        if (ii >= 10 && ii < 20) {
            String[] teens = {
                    "ten", "eleven", "twelve", "thirteen", "fourteen", "fiveteen", "sixteen", "seventeen", "eighteen", "nineteen"
            };
            return nameGroup100(i/100, 2) + " " + teens[ii - 10];
        }
        if (ii >= 0 && ii < 10) {
            return nameGroup100(i/100, 2) + " " + nameGroup100(ii, 0);
        }
        return nameGroup100(i, 0);
    }

    static String nameGroup100(int i, int d)
    {
        if (i == 0) return "";
        String s = nameGroup100(i/10, d + 1);
        s = s.isEmpty()? "": s + " "; 
        String[] num = {
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        };
        String[] tens = {
                "twenty", "thirty", "fourty", "fivety", "sixty", "seventy", "eighty", "ninety"
        };
        int n = i % 10;
        if (d == 0)
            s += num[n - 1];
        if (d == 1)
            s += tens[n - 2];
        if (d == 2)
            s += num[n - 1] + " hundred";
        return s;
    }

    public static void main(String[] args) {
        assertEquals("one thousand two hundred thirty four", name(1234));
        assertEquals("one hundred ninety one million two hundred thirty two thousand eight hundred ninety one", name(191_232_891));
        assertEquals("four hundred eight thousand one hundred sixty three", name(408_163L));
        assertEquals("seven hundred eighty five billion six hundred fourty nine million four hundred eight thousand one hundred sixty three", name(785_649_408_163L));
    }

}
