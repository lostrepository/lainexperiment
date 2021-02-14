/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._212;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/02/2020
 * 
 * Problem: Slowest Key
 * Status: accepted
 * 
 * A newly designed keypad was tested, where a tester pressed a
 * sequence of n keys, one at a time.
 * 
 * You are given a string keysPressed of length n, where
 * keysPressed[i] was the ith key pressed in the testing sequence,
 * and a sorted list releaseTimes, where releaseTimes[i] was the
 * time the ith key was released. Both arrays are 0-indexed. The
 * 0th key was pressed at the time 0, and every subsequent key
 * was pressed at the exact time the previous key was released.
 * 
 * The tester wants to know the key of the keypress that had the
 * longest duration. The ith keypress had a duration of
 * releaseTimes[i] - releaseTimes[i - 1], and the 0th keypress
 * had a duration of releaseTimes[0].
 * 
 * Note that the same key could have been pressed multiple times
 * during the test, and these multiple presses of the same key may
 * not have had the same duration.
 * 
 * Return the key of the keypress that had the longest duration.
 * If there are multiple such keypresses, return the lexicographically
 * largest key of the keypresses.
 *
 * Input
 * 
releaseTimes = [9,29,49,50], keysPressed = "cbcd"
 * 
 * Output
 * 
c
 * 
 * }</pre>
 */
public class Task1_Slowest_Key {

    public char slowestKey(int[] r, String keys) {
        char key = keys.charAt(0);
        int m = r[0];
        for (int i = 1; i < keys.length(); i++) {
            int d = r[i] - r[i-1];
            if (d < m) continue;
            char k = keys.charAt(i);
            if (d == m) {
                key = (char) Math.max(key, k);
                continue;
            }
            m = d;
            key = k;
        }
        return key;
    }
    
    @Test
    public void test() {
        assertEquals('a', slowestKey(new int[] {9,29,49,50}, "aaaa"));
        assertEquals('b', slowestKey(new int[] {9,29,49,150}, "aaab"));
        assertEquals('c', slowestKey(new int[] {9,29,49,50}, "cbcd"));
        assertEquals('c', slowestKey(new int[] {0,20,40,50}, "ccaa"));
        assertEquals('q', slowestKey(new int[] {23,34,43,59,62,80,83,92,97}, "qgkzzihfc"));
        assertEquals('a', slowestKey(new int[] {12,23,36,46,62}, "spuda"));
    }
}
