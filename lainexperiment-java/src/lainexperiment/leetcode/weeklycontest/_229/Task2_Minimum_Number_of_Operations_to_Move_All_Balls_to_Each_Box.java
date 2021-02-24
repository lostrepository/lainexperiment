/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._229;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 20/02/2021
 * 
 * Problem: Minimum Number of Operations to Move All Balls to Each Box
 * Status: accepted
 * 
 * You have n boxes. You are given a binary string boxes of length n,
 * where boxes[i] is '0' if the ith box is empty, and '1' if it contains
 * one ball.
 * 
 * In one operation, you can move one ball from a box to an adjacent box.
 * Box i is adjacent to box j if abs(i - j) == 1. Note that after doing
 * so, there may be more than one ball in some boxes.
 * 
 * Return an array answer of size n, where answer[i] is the minimum number
 * of operations needed to move all the balls to the ith box.
 * 
 * Each answer[i] is calculated considering the initial state of the boxes.
 *
 * Input
 * 
110
 * 
 * Output
 * 
1,1,3
 * 
 * }</pre>
 */
public class Task2_Minimum_Number_of_Operations_to_Move_All_Balls_to_Each_Box {
    
    public int[] minOperations(String boxes) {
        char[] a = boxes.toCharArray();
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (i == j) continue;
                if (a[j] != '1') continue;
                c[i] += Math.abs(i - j);
            }
        }
        return c;
    }
    
    @Test
    public void test() {
        assertEquals("[1, 1, 3]", Arrays.toString(minOperations("110")));
        assertEquals("[11, 8, 5, 4, 3, 4]", Arrays.toString(minOperations("001011")));
        assertEquals("[2, 2, 2]", Arrays.toString(minOperations("101")));
        assertEquals("[2, 1, 0]", Arrays.toString(minOperations("001")));
    }
}
