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
 * Date: 07/02/2015
 * 
 * Problem
 * 
 * You are given a string FOOFIGHTERS. You have to come up with an algorithm that 
 * will compress this string.
 *
 * Input
 * 
 * FOOFIGHTERS
 * 
 * Output
 * 
 * F2OFIGHTERS
 * 
 * }</pre>
 */
public class Compress_the_string {

    static int compress(char[] a) {
        int k = 0;
        int i = 0;
        while (i < a.length) {
            if (a[i] != a[k]) {
                a[k++] = a[i++];
                continue;
            }
            while (i < a.length && a[i] == a[k])
                i++;
            if (i - k > 1) {
                a[k] = (char) ('0' + (i - k));
                k++;
            }
            k++;
        }
        return k;
    }

    public static void main(String[] args) {
        char[] a = null;
        int k = 0;
        
        a = "1421".toCharArray();
        k = compress(a);
        assertEquals("1421", String.valueOf(a, 0, k));

        a = "FOOFIGHTERS".toCharArray();
        k = compress(a);
        assertEquals("F2OFIGHTERS", String.valueOf(a, 0, k));

        a = "BWWWWWWWW".toCharArray();
        k = compress(a);
        assertEquals("B8W", String.valueOf(a, 0, k));
    
    }

}
