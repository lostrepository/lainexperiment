/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 24/01/2015
 * 
 * Problem
 * 
 * Given a max number and sorted array of numbers which must be skipped. Print all numbers
 * from 0 to max except numbers which needs to be skipped. 
 *
 * Input
 * 
 * 10 
 * 3, 4, 5
 * 10
 * 1, 3, 4, 5, 9
 * 
 * Output
 * 
 * 0126789
 * 02678
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

public class SkipTheNumbers {

    static String skip(int max, int[] skip) {
        int j = 0;
        int i = 0;
        String res = "";
        while (i < max && j < skip.length) {
            if (i < skip[j])
                res += i;
            if (i++ == skip[j])
                ++j;
        }
        while (i < max) 
            res += i++;
        return res;
    }
    
    public static void main(String[] args) {
        assertEquals("0126789", skip(10, new int[] {3, 4, 5}));
        assertEquals("02678", skip(10, new int[] {1, 3, 4, 5, 9}));
    }

}
