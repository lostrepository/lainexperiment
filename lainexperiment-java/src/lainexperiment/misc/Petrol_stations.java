/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;

/**
 * <pre>{@code
 * Date: 16/05/2016
 * 
 * Problem
 * 
 * There are n petrol stations located in a circle. We have a 
 * truck which runs 1 km per 1 liter (mileage 1kmpl). Two arrays 
 * are given. The distances between petrol stations are given in one 
 * array. Other array contains the no of liters available at each petrol 
 * station. We have to find the starting point such that if we start 
 * at that point, we would able to visit entire circle without running 
 * out of fuel. Initially truck has no fuel.
 *
 * Input
 * 
1, 1, 1, 1, 1
5, 0, 0, 0, 0
 * 
 * Output
 * 
0
 * 
 * }</pre>
 */
public class Petrol_stations {

    static int petrol(int[] d, int[] p) {
        int c = 0;
        for (int i = 0; i < d.length; ++i) {
            c += p[i] - d[i];
        }
        if (c < 0) return -1;
        int s = 0;
        int e = 0;
        int b = p[s];
        for (int i = 0; i < p.length; i++) {
            if (b < d[e]) {
                s--;
                s = s < 0? p.length - 1: s;
                b += p[s] - d[s];
                continue;
            }
            b -= d[e++];
            e = e == p.length? 0: e;
            b += p[e];
        }
        if (b < 0) return -1;
        return s;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        assertEquals(0, petrol(new int[]{1, 1, 1, 1, 1}, new int[]{5, 0, 0, 0, 0}));
        assertEquals(2, petrol(new int[]{1, 1, 1, 1, 1}, new int[]{0, 0, 5, 0, 0}));
        assertEquals(4, petrol(new int[]{1, 1, 1, 1, 1}, new int[]{0, 0, 0, 0, 5}));
        assertEquals(-1, petrol(new int[]{6, 4, 1, 2, 3, 2}, new int[]{2, 3, 1, 3, 1, 2}));
        assertEquals(2, petrol(new int[]{5, 2, 1, 1, 2, 1}, new int[]{2, 1, 5, 1, 2, 2}));
        assertEquals(3, petrol(new int[]{6, 5, 6, 2}, new int[]{3, 5, 1, 10}));
        assertEquals(-1, petrol(new int[]{2, 14, 6, 9}, new int[]{6, 3, 7, 11}));
        assertEquals(0, petrol(new int[]{1, 2, 1}, new int[]{2, 2, 0}));
    }
    
}
