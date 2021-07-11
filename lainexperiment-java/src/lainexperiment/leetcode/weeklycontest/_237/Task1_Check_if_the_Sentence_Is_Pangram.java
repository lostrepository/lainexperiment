/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._237;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 17/04/2021
 * 
 * Problem: Check if the Sentence Is Pangram
 * Status: accepted
 * 
 * A pangram is a sentence where every letter of the English alphabet
 * appears at least once.
 * 
 * Given a string sentence containing only lowercase English letters,
 * return true if sentence is a pangram, or false otherwise.
 * 
 * Input
 * 
thequickbrownfoxjumpsoverthelazydog
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Check_if_the_Sentence_Is_Pangram {
    
    public boolean checkIfPangram(String w) {
        int[] c = new int['z' - 'a' + 1];
        for (var ch: w.toCharArray()) {
            c[ch - 'a']++;
        }
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 0) return false;
        }
        return true;
    }

    @Test
    public void test() {
        assertEquals(false, checkIfPangram("thequickbrownfoxjumpsoverthelazydoz"));
        assertEquals(false, checkIfPangram("leetcode"));
        assertEquals(false, checkIfPangram("abc"));
        assertEquals(true, checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    }
}
