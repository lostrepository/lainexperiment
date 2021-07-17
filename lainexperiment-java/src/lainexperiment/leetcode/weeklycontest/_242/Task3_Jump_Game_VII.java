/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._242;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 16/07/2021
 * 
 * Problem: Jump Game VII
 * Status: accepted
 * 
 * You are given a 0-indexed binary string s and two integers minJump
 * and maxJump. In the beginning, you are standing at index 0, which
 * is equal to '0'. You can move from index i to index j if the
 * following conditions are fulfilled:
 * 
 * - i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * - s[j] == '0'
 * 
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 * 
 * Input
 * 
s = "011010", minJump = 2, maxJump = 3
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task3_Jump_Game_VII {
    
    public boolean canReach(String s, int minJump, int maxJump) {
        if (s.length() == 1) return s.charAt(0) == '0';
        var psum = new int[s.length()];
        psum[0] = s.charAt(0) == '0'? 1: 0;
        for (int i = 1; i < s.length(); i++) {
            System.out.format("i = %d\n", i);
            var prev = psum[i - 1];
            if (s.charAt(i) == '0') {
                if (canReach(psum, minJump, maxJump, i))
                    prev++;
            }
            psum[i] = prev;
        }
        return psum[psum.length - 1] - psum[psum.length - 2] > 0;
    }

    boolean canReach(int[] psum, int min, int max, int i) {
        int i1 = i - max - 1;
        int i2 = i - min;
        if (i2 < 0) return false;
        return psum[i2] - (i1 < 0? 0: psum[i1]) > 0;
    }
    
    @Test
    public void test() {
        assertEquals(false, canReach("0000000000", 8, 8));
        assertEquals(false, canReach("01110", 2, 3));
        assertEquals(false, canReach("001", 2, 3));
        assertEquals(false, canReach("100", 2, 3));
        assertEquals(true, canReach("000", 2, 3));
        assertEquals(false, canReach("00", 2, 3));
        assertEquals(false, canReach("01101110", 2, 3));
        assertEquals(true, canReach("011010", 2, 3));
    }
}
