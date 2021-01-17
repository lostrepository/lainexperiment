/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._223;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 09/01/2021
 * 
 * Problem: Decode XORed Array
 * Status: accepted
 * 
 * There is a hidden integer array arr that consists of n
 * non-negative integers.
 * 
 * It was encoded into another integer array encoded of
 * length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1].
 * For example, if arr = [1,0,2,1], then encoded = [1,2,3].
 * 
 * You are given the encoded array. You are also given an
 * integer first, that is the first element of arr, i.e. arr[0].
 * 
 * Return the original array arr. It can be proved that the
 * answer exists and is unique.
 *
 * Example 1:
 * 
encoded = [1,2,3], first = 1
 * 
 * Output
 * 
1,0,2,1
 * 
 * If arr = [1,0,2,1], then first = 1 and
 * encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * 
 * }</pre>
 */
public class Task1_Decode_XORed_Array {

    public int[] decode(int[] encoded, int first) {
        var res = new int[encoded.length + 1];
        res[0] = first;
        for (int i = 1; i < res.length; i++) {
            res[i] = encoded[i - 1] ^ res[i - 1];
        }
        return res;
    }

    @Test
    public void test() {
        assertEquals("[1, 0, 2, 1]", Arrays.toString(decode(new int[] {1,2,3}, 1)));
        assertEquals("[4, 2, 0, 7, 4]", Arrays.toString(decode(new int[] {6,2,7,3}, 4)));
    }
}
