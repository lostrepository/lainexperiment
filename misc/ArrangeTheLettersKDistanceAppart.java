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

package misc;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class ArrangeTheLettersKDistanceAppart {

    static boolean arrange(char[] a, int K) {
        int k = 0;
        boolean noSwaps = true;
        LinkedHashSet<Character> s = new LinkedHashSet<>();
        int i = 0;
        while (i <= a.length - 1) {
            if (!s.contains(a[k])) {
                s.add(a[k]);
                swap(a, i, k);
                noSwaps = false;
                if (s.size() >= K) {
                    Iterator<Character> iter = s.iterator();
                    iter.next();
                    iter.remove();
                }
                i++;
            } else {
                k++;
                if (k == a.length) {
                    if (noSwaps || i == k - 1)
                        return false;
                    k = i + 1;
                    noSwaps = true;
                }
            }
        }
        return true;
    }
    
    private static void swap(char[] a, int i, int k) {
        char t = a[i];
        a[i] = a[k];
        a[k] = t;
    }

    public static void main(String[] args) {
        char[] a = null;
        
        a = "aabbcc".toCharArray();
        
        assertTrue(arrange(a, 2) == false);
        System.out.println(a);
    }

}
