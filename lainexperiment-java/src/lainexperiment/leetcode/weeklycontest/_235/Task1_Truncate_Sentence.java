/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._235;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 03/04/2021
 * 
 * Problem: Truncate Sentence
 * Status: accepted
 * 
 * A sentence is a list of words that are separated by a single
 * space with no leading or trailing spaces. Each of the words
 * consists of only uppercase and lowercase English letters (no
 * punctuation).
 * 
 * For example, "Hello World", "HELLO", and "hello world hello world"
 * are all sentences.
 * 
 * You are given a sentence s​​​​​​ and an integer k​​​​​​. You want to
 * truncate s​​​​​​ such that it contains only the first k​​​​​​ words.
 * Return s​​​​​​ after truncating it.
 * 
 * Input
 * 
s = "What is the solution to this problem", k = 4
 * 
 * Output
 * 
What is the solution
 * 
 * }</pre>
 */
public class Task1_Truncate_Sentence {
    
    public String truncateSentence(String s, int k) {
        return Arrays.stream(s.split(" ")).limit(k)
            .collect(joining(" "));
    }

    @Test
    public void test() {
        assertEquals("chopper", truncateSentence("chopper", 15));
        assertEquals("chopper is not a tanuki", truncateSentence("chopper is not a tanuki", 5));
        assertEquals("What is the solution", truncateSentence("What is the solution to this problem", 4));
        assertEquals("Hello how are you", truncateSentence("Hello how are you Contestant", 4));
    }
}
