/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._214;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 02/20/2021
 * 
 * Problem: Minimum Deletions to Make Character Frequencies Unique
 * Status: accepted
 * 
 * A string s is called good if there are no two different characters
 * in s that have the same frequency.
 * 
 * Given a string s, return the minimum number of characters you need
 * to delete to make s good.
 * 
 * The frequency of a character in a string is the number of times it
 * appears in the string. For example, in the string "aab", the
 * frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 * Example 1:
 * 
aaabbbcc
 * 
 * Output
 * 
2
 * 
 * You can delete two 'b's resulting in the good string "aaabcc".
 * 
 * }</pre>
 */
public class Task2_Minimum_Deletions_to_Make_Character_Frequencies_Unique {

    public int minDeletions(String s) {
        var a = s.chars()
            .boxed().collect(groupingBy(i -> i, counting()))
            .values().stream().sorted(Comparator.reverseOrder())
            .collect(toList());
        a.add(-1L);
        System.out.println(a);
        long p = a.get(0);
        int c = 0;
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) >= p) {
                if (a.get(i) == p) c++;
                else if (p == 0) c += a.get(i);
                else c += a.get(i) - p + 1;
                if (p > 0)
                    p--;
            } else {
                p = a.get(i);
            }
        }
        return c;
    }

    @Test
    public void test() {
        assertEquals(0, minDeletions("aab"));
        assertEquals(1, minDeletions("aabb"));
        assertEquals(2, minDeletions("aaabbbcc"));
        assertEquals(2, minDeletions("ceabaacb"));
        assertEquals(1, minDeletions("accdcdadddbaadbc"));
        assertEquals(2, minDeletions("bbcebab"));
        assertEquals(3, minDeletions("abcabc"));
    }
}
