/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.lang.Math.abs;
import static java.util.Arrays.binarySearch;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * <pre>{@code
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
 * }</pre>
 */
public class Print_next_lexicographically_greater_word {
    
    static void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    static char[] nextLexicographic(char[] a) {
        int i = a.length - 2;
        while (i >= 0) {
            if (a[i] < a[i + 1])
                break;
            i--;
        }
        if (i < 0)
            return a;
        Arrays.sort(a, i + 1, a.length);
        int p = binarySearch(a, i + 1, a.length, (char)(a[i] + 1));
        if (p < 0)
            p = abs(p) - 1;
        swap(a, i, p);
        return a;
    }

    public static void main(String[] args) {
        assertEquals("2114", String.valueOf(nextLexicographic("1421".toCharArray())));
        assertEquals("ba", String.valueOf(nextLexicographic("ab".toCharArray())));
        assertEquals("bb", String.valueOf(nextLexicographic("bb".toCharArray())));
        assertEquals("hegf", String.valueOf(nextLexicographic("hefg".toCharArray())));
        assertEquals("bacd", String.valueOf(nextLexicographic("adcb".toCharArray())));
        assertEquals("adbc", String.valueOf(nextLexicographic("acdb".toCharArray())));
    }

}
