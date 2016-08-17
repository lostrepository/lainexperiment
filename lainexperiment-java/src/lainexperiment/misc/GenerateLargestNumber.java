/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 13/06/2015
 * 
 * Problem
 * 
 * Given numbers, need to join them to form another number in such order that the 
 * new number will be the largest possible. 
 *
 * Input
 * 
 * 98, 9, 4
 * 
 * Output
 * 
 * 9, 98, 4
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class GenerateLargestNumber {

    static int compare(int a, int b) {
        a = Integer.parseInt(new StringBuilder(String.valueOf(a)).reverse().toString());
        b = Integer.parseInt(new StringBuilder(String.valueOf(b)).reverse().toString());
        return cmp(a, b);
    }
    
    static int cmp(int a, int b) {
        if (a == b)
            return 0;
        int i, j;
        i = a % 10;
        j = b % 10;
        if (i < j)
            return 1;
        else if (i > j)
            return -1;
        if (a / 10 != 0)
            a /= 10;
        if (b / 10 != 0)
            b /= 10;
        return compare(a, b);
    }
    
    public static void main(String[] args) {
        int[] a;

        a = new int[]{92, 99, 95, 96, 9};
        assertEquals("[99, 9, 96, 95, 92]", 
                Arrays.toString(Arrays.stream(a).boxed().
                        sorted(GenerateLargestNumber::compare).toArray()));

        a = new int[]{5, 8, 32, 65, 8765};
        assertEquals("[8, 8765, 65, 5, 32]", 
                Arrays.toString(Arrays.stream(a).boxed().
                        sorted(GenerateLargestNumber::compare).toArray()));
        
    }

}
