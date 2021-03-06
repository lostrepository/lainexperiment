/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <pre>{@code
 * Date: 19/04/2015
 * 
 * Problem
 * 
 * Given a number reverse it. 
 *
 * Input
 * 
 * 12345
 * 
 * Output
 * 
 * 54321
 * 
 * }</pre>
 */
public class ReverseInteger {

    static int reverse(int n) {
        int r = 0;
        while (n > 0) {
            r = n % 10 + r * 10;
            n /= 10;
        }
        return r;
    }

    public static void main(String[] args) {
        assertEquals(12345, reverse(54321));
        assertEquals(21, reverse(12));
    }

}
