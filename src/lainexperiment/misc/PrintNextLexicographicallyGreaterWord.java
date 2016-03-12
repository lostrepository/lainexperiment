/* 
 * LynX Source Materials
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
 * Given a number print a new number which will be greater and will contain same
 * numbers
 *
 * Input
 * 
 * 1421
 * 
 * Output
 * 
 * 2114
 * 
 */

package lainexperiment.misc;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class PrintNextLexicographicallyGreaterWord {

    static char[] next(char[] a) {
        if (a.length == 1) {
            return "no answer".toCharArray();
        }
        for (int i = a.length - 2; i >= 0; --i) {
            for (int j = a.length - 1; j >= i; --j) {
                if (a[i] < a[j]) {
                    char tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                    Arrays.sort(a, i + 1, a.length);
                    return a;
                }
            }
        }
        return "no answer".toCharArray();
    }

    public static void main(String[] args) {
        char[] a = null;
        
        a = next("1421".toCharArray());
        assertEquals("2114", String.valueOf(a));
    }

}
