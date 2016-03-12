/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 11/01/2015
 *
 * Problem
 * 
 * There is a parking lot with n slots and n - 1 cars with 1 0 (free) slot. 
 * Given an initial and final arrangements need to arrange them accordingly
 * using a free slot as a buffer. It is allowed to move one car at a time only.
 * 
 * Input
 * 
 * 2, 5, 3, 1, 0, 4
 * 5, 0, 2, 4, 1, 3
 * 
 * Output
 * 
 * none
 * 
 */

package lainexperiment.misc;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Parking {
    
    static void print(int[] a) {
        for (int r = 0; r < a.length; ++r) {
            System.out.print(a[r] + " ");
        }
        System.out.println();
    }
    
    private static void arrange(int[] a1, int[] a2) {
        Map<Integer, Integer> m = new HashMap<>();
        IntStream.range(0, a1.length).forEach((i) -> m.put(a1[i], i));
        for (int i = 0; i < a2.length; ++i) {
            if (a2[i] == 0 || a2[i] == a1[i])
                continue;
            if (a1[i] == 0)
                swap(a1, i, m.get(a2[i]), m);
            else {
                swap(a1, i, m.get(0), m);
                swap(a1, i, m.get(a2[i]), m);
            }
        }
    }
    
    private static void swap(int[] a, int i, int j,
            Map<Integer, Integer> m) 
    {
        int v1 = a[i];
        int v2 = a[j];
        a[i] = v2;
        a[j] = v1;
        m.put(v1, j);
        m.put(v2, i);
    }
    
    public static void main(String[] args) {
        int[] a1 = new int[]{2, 5, 3, 1, 0, 4};
        int[] a2 = new int[]{5, 0, 2, 4, 1, 3};
        arrange(a1, a2);
        print(a1);
    }
    
}
