/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._239;

import static lainexperiment.utils.Algorithms.binarySearch;
import static lainexperiment.utils.Utils.swap;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 03/07/2021
 * 
 * Problem: Minimum Adjacent Swaps to Reach the Kth Smallest Number
 * Status: accepted
 * 
 * You are given a string num, representing a large integer, and
 * an integer k.
 * 
 * We call some integer wonderful if it is a permutation of the
 * digits in num and is greater in value than num. There can be
 * many wonderful integers. However, we only care about the
 * smallest-valued ones.
 * 
 * For example, when num = "5489355142":
 * - The 1st smallest wonderful integer is "5489355214".
 * - The 2nd smallest wonderful integer is "5489355241".
 * - The 3rd smallest wonderful integer is "5489355412".
 * - The 4th smallest wonderful integer is "5489355421".
 * 
 * Return the minimum number of adjacent digit swaps that needs to
 * be applied to num to reach the kth smallest wonderful integer.
 * 
 * The tests are generated in such a way that kth smallest wonderful
 * integer exists.
 * 
 * Input
 * 
num = "5489355142", k = 4
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task3_Minimum_Adjacent_Swaps_to_Reach_the_Kth_Smallest_Number {
    
    public int getMinSwaps(String num, int k) {
        var a = num.toCharArray();
        while (k > 0) {
            a = next(a);
            k--;
        }
        var b = num.toCharArray();
        var c = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) continue;
            for (int j = i + 1; j < b.length; j++) {
                swap(b, i, j);
                c++;
                if (a[i] == b[i]) break;
            }
        }
        return c;
    }
    
    char[] next(char[] a) {
        int i = a.length - 2;
        while (i >= 0) {
            if (a[i] < a[i + 1])
                break;
            i--;
        }
        if (i < 0) return a;
        Arrays.sort(a, i + 1, a.length);
        int p = binarySearch(a, i + 1, a.length, (char)(a[i] + 1));
        if (p < 0) p = 0 - p - 1;
        swap(a, i, p);
        return a;
    }

    @Test
    public void test() {
        assertEquals(13, getMinSwaps("788785540", 891));
        assertEquals(1, getMinSwaps("00123", 1));
        assertEquals(4, getMinSwaps("11112", 4));
        assertEquals(2, getMinSwaps("5489355142", 4));
    }
}
