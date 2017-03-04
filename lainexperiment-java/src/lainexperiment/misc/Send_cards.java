/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 04/04/2017
 * 
 * Problem
 * 
 * Consider the 52 cards of a deck. You generated a random 
 * sequence for these cards and want to send that sequence 
 * to a receiver. You want to minimize the communication 
 * between you and the receiver, i.e., minimize the number 
 * of bits required to send the sequence.
 * 
 * Input
 * 
43, 37, 8, 30, 16, 46, 10, 14, 24, 35, 12, 32, 41, 38, 54, 13, 9, 36, 18, 5, 47, 26, 51, 4, 44, 28, 3, 20, 53, 6, 50, 31, 23, 34, 52, 33, 22, 42, 39, 27, 11, 40, 25, 17, 49, 48, 15, 45, 19, 2, 21, 29, 7, 1
 * 
 * Output
 * 
1, 2, 3, 1, 2, 1, 7, 3, 4, 7, 12, 2, 11, 3, 1, 13, 9, 23, 18, 5, 15, 26, 21, 4, 17, 28, 3, 20, 7, 6, 2, 31, 23, 34, 52, 33, 22, 42, 39, 27, 11, 40, 25, 17, 49, 48, 15, 45, 19, 2, 21, 29, 7, 1
 * 
 */

package lainexperiment.misc;

import static java.util.stream.IntStream.rangeClosed;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class Send_cards {

    static int[] prepare(int[] a) {
        int i = a.length;
        int[] buf = new int[i];
        int[] k = {i, 32, 16, 8, 4, 2, 1, 0};
        //int[] k = {15, 5, 0};
        Map<Integer, Integer> m = new HashMap<>();
        for (int j = 1; j < k.length; j++) {
            while (i > k[j]) {
                int n = a[i - 1];
                m.put(i, a[i - 1]);
                while (n > k[j - 1]) {
                    System.out.println(n);
                    n = m.get(n);
                }
                buf[buf.length - i] = n;
                i--;
            }
        }
        return buf;
    }
    
    static int[] receive(int[] a) {
        int i = a.length;
        int[] buf = new int[i];
        int[] k = {i, 32, 16, 8, 4, 2, 1, 0};
        //int[] k = {15, 5, 0};
        Map<Integer, Integer> m = new HashMap<>();
        for (int j = 1; j < k.length; j++) {
            while (i > k[j]) {
                int n = a[i - 1];
                while (m.containsKey(n)) {
                    System.out.println(n);
                    n = m.get(n);
                }
                buf[buf.length - i] = n;
                m.put(n, i);
                i--;
            }
        }
        return buf;
    }
    
    static void reverse(int[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

    @Test
    public void test() {
        List<Integer> l = rangeClosed(1, 54).boxed().collect(Collectors.toList());
        Collections.shuffle(l);
        int[] input = l.stream().mapToInt(i -> i).toArray();
        //int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 52, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 54, 9, 53};
        //int[] input = {4, 7, 9, 10, 2, 8, 1, 5, 6, 3};
        System.out.println(Arrays.toString(input));
        
        int[] a = prepare(input);
        reverse(a);
        System.out.println(Arrays.toString(a));
        
        a = receive(a);
        reverse(a);
        System.out.println(Arrays.toString(a));
        
        assertTrue(Arrays.equals(input, a));
    }

}
