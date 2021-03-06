/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

/**
 * <pre>{@code
 * Date: 11/01/2015
 *
 * Problem
 * 
 * Given an array of integers where each element represents the
 * max number of steps that can be made forward from that element. 
 * Write a function to return the minimum number of jumps to reach the 
 * end of the array.
 * 
 * Input
 * 
 * 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9
 * 
 * Output
 * 
 * 3 (1, 3, 8, 9)
 * 
 * }</pre>
 */
public class Min_number_of_jumps {
    
    static int minJumps(int[] a, int n) {
        if (n >= a.length)
            return 0;
        int m = Integer.MAX_VALUE;
        for (int i = 1; i <= a[n]; ++i) {
            int c = minJumps(a, n + i);
            m = Math.min(c, m);
        }
        return m + 1;
    }
    
    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}, 0));
    }
    
}
