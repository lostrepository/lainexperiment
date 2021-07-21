/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._243;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 29/05/2021
 * 
 * Problem: Check if Word Equals Summation of Two Words
 * Status: accepted
 * 
 * The letter value of a letter is its position in the alphabet starting
 * from 0 (i.e. 'a' -> 0, 'b' -> 1, 'c' -> 2, etc.).
 * 
 * The numerical value of some string of lowercase English letters s is the
 * concatenation of the letter values of each letter in s, which is then
 * converted into an integer.
 * 
 * For example, if s = "acb", we concatenate each letter's letter value,
 * resulting in "021". After converting it, we get 21.
 * 
 * You are given three strings firstWord, secondWord, and targetWord, each
 * consisting of lowercase English letters 'a' through 'j' inclusive.
 * 
 * Return true if the summation of the numerical values of firstWord and
 * secondWord equals the numerical value of targetWord, or false otherwise.
 * 
 * Input
 * 
firstWord = "acb", secondWord = "cba", targetWord = "cdb"
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Check_if_Word_Equals_Summation_of_Two_Words {
    
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return asnum(firstWord) + asnum(secondWord) == asnum(targetWord);
    }

    private int asnum(String s) {
        return Integer.parseInt(s.chars()
            .map(i -> i - 'a')
            .mapToObj(Integer::toString)
            .collect(joining()));
    }

    @Test
    public void test() {
        assertEquals(true, isSumEqual("aaa", "a", "aaaa"));
        assertEquals(false, isSumEqual("aaa", "a", "aab"));
        assertEquals(21, asnum("acb"));
        assertEquals(true, isSumEqual("acb", "cba", "cdb"));
    }
}
