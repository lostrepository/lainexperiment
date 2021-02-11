/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 11/20/2020
 * 
 * Problem: Design an Ordered Stream
 * Status: accepted
 * 
 * There is a stream of n (id, value) pairs arriving in an
 * arbitrary order, where id is an integer between 1 and n and
 * value is a string. No two pairs have the same id.
 * 
 * Design a stream that returns the values in increasing order
 * of their IDs by returning a chunk (list) of values after each
 * insertion. The concatenation of all the chunks should result
 * in a list of the sorted values.
 * 
 * Implement the OrderedStream class:
 * 
 * - OrderedStream(int n) Constructs the stream to take n values.
 * - String[] insert(int id, String value) Inserts the pair (id,
 * value) into the stream, then returns the largest possible
 * chunk of currently inserted values that appear next in the
 * order.
 *
 * Example 1:
 * 
os.insert(3, "ccccc");
os.insert(1, "aaaaa");
os.insert(2, "bbbbb");
os.insert(5, "eeeee");
os.insert(4, "ddddd");
 * 
 * Output
 * 
[]
["aaaaa"]
["bbbbb", "ccccc"]
[]
["ddddd", "eeeee"]
 * 
 */

package lainexperiment.leetcode.weeklycontest._215;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

/**
 * <pre>{@code
 * Date: 26/11/2020
 * 
 * Problem: Determine if Two Strings Are Close
 * Status: accepted
 * 
 * Two strings are considered close if you can attain one from
 * the other using the following operations:
 * 
 * Operation 1: Swap any two existing characters.
 * For example, abcde -> aecdb
 * Operation 2: Transform every occurrence of one existing
 * character into another existing character, and do the same
 * with the other character.
 * 
 * For example, aacabb -> bbcbaa (all a's turn into b's, and all
 * b's turn into a's)
 * 
 * You can use the operations on either string as many times as
 * necessary.
 * 
 * Given two strings, word1 and word2, return true if word1 and
 * word2 are close, and false otherwise.
 *
 * Example 1:
 * 
word1 = "abc"
word2 = "bca"
 * 
 * Output
 * 
true
 * 
 * You can attain word2 from word1 in 2 operations.
 * 
 * Apply Operation 1: "abc" -> "acb"
 * Apply Operation 1: "acb" -> "bca"
 * 
 * }</pre>
 */
public class Task2_Determine_if_Two_Strings_Are_Close {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
       int[] m1 = hist(word1);
       int[] m2 = hist(word2);
       System.out.println(Arrays.toString(m1));
       if (Arrays.equals(m1, m2)) return true;
       for (int i = 0; i < m1.length; i++) {
           if (m1[i] == -1 && m2[i] == -1) continue;
           if (m1[i] == -1) return false;
           if (m2[i] == -1) return false;
       }
       return transform(m1, m2, 0);
    }

    private boolean transform(int[] m1, int[] m2, int i) {
        if (i == m1.length) return true;
        for (; i < m1.length; i++) {
            if (m1[i] == m2[i]) continue;
            for (int j = i; j < m2.length; j++) {
                if (m1[i] == m2[j]) {
                    int t = m2[i];
                    m2[i] = m2[j];
                    m2[j] = t;
                    //if (transform(m1, m2, i + 1)) return true;
                    return transform(m1, m2, i + 1);
                }
            }
            return false;
        }
        return true;
    }

    int[] hist(String s) {
        s = s.toLowerCase();
        int[] m = new int[28];
        Arrays.fill(m, -1);
        for (char ch: s.toCharArray()) {
            m[ch - 'a']++;
        }
        return m;
    }

    public static void main(String[] args) {
        Task2_Determine_if_Two_Strings_Are_Close os = new Task2_Determine_if_Two_Strings_Are_Close();
        assertFalse(os.closeStrings("aa", "a"));
        assertTrue(os.closeStrings("abc", "bca"));
        assertTrue(os.closeStrings("aabbbc", "aabccc"));
        assertTrue(os.closeStrings("cabbba", "abbccc"));
        assertFalse(os.closeStrings("cabbba", "aabbss"));
        assertFalse(os.closeStrings("uau", "ssx"));
    }
}
