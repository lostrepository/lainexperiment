/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.hackerrank.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.SuffixArray;

/**
 * <pre>{@code
 * Date: 11/11/2020
 *
 * Hacker rank
 * Problem: Ashton and String
 * Status: accepted
 * 
 * Problem
 * 
 * Ashton appeared for a job interview and is asked the following question.
 * Arrange all the distinct substrings of a given string in lexicographical
 * order and concatenate them. Print the kth character of the concatenated
 * string. It is assured that given value of k will be valid i.e. there
 * will be a k character. Can you help Ashton out with this?
 * 
 * For example, given the string s = abcd, its distinct substrings are
 * a, ab, abc, abcd, b, bc, bcd, c, cd, d. Sorted and concatenated, they
 * make the string aababcabcdbbcbcdccdd. If k = 5 then, the answer is b,
 * the 5th character of the 1-indexed concatenated string.
 * 
 * Note: We have distinct substrings here, i.e. if string is aa, it's
 * distinct substrings are a and aa.
 *  
 * Write the function which will return the kth character from the
 * concatenated string, 1-based indexing. 
 * 
 * Input Format
 * 
 * The first line contains a single integer t the number of test cases.
 * 
 * The following t pairs of lines are as follows:
 * 
 * - The first line of each test case contains a string, s.
 * - The second line contains an integer, k.
 * 
 * Output Format
 * 
 * Print the kth character (1-based index) of the concatenation of the
 * ordered distinct substrings of s.
 * 
 * Input
 * 
1
dbac
3
 * 
 * Output
 * 
c
 * 
 * }</pre>
 */
public class Ashton_and_String {

    char solve(String str, int k) {
        char[] a = str.toCharArray();
        SuffixArray sa = new SuffixArray(a);
        // previous suffix
        String p = "";
        // iterate over all suffixes
        for (int i = 0; i < sa.SA.length; i++) {
            // for each suffix
            int s = sa.SA[i];
            String suffix = str.substring(s);
            // num of all possible postfixes of suffix
            long c = arithm(a.length - s);
            int offset = 0;
            int prefixLen = prefixMatch(suffix, p);
            // if suffix shares common prefix with p
            if (prefixLen > 0) {
                // ignore all postfixes of p
                c -= arithm(prefixLen);
                // and shift till all of them ends
                offset = prefixLen;
            }
            if (k <= c) {
                return findChar(a, s, offset, k - 1);
            }
            k -= c;
            p = suffix;
        }
        throw new RuntimeException("k is too big");
    }

    public int prefixMatch(String a, String b) {
        int min = Math.min(a.length(), b.length());
        for (int i = 0; i < min; i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        return min;
    }

    /**
     * Concatenates all postfixes of a string starting at s + offset
     * @param k index of char to return (starts from 0)
     */
    char findChar(char[] a, int s, int offset, int k) {
        for (int i = s + offset; i < a.length; i++) {
            if (k <= i - s) {
                return a[s + k];
            }
            k -= i - s + 1;
        }
        throw new RuntimeException();
    }

    /**
     * @return arithmetic progression for n
     */
    private long arithm(long n) {
        return n * (n + 1) / 2;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Ashton_and_String.class.getResourceAsStream(
                    "Ashton_and_String.in"));
        int t = scanner.nextInt();
        while (t-- > 0) {
            char ch = new Ashton_and_String().solve(scanner.next(), scanner.nextInt());
            System.out.println(ch);
        }
        scanner.close();
    }

    @Test
    public void test_prefixMatch() {
        assertEquals(2, prefixMatch("abc", "abds"));
        assertEquals(0, prefixMatch("zabc", "abds"));
        assertEquals(3, prefixMatch("abc", "abc"));
        assertEquals(0, prefixMatch("", "abc"));
    }

    @Test
    public void test_findChar() {
        String str = "abcdfe";
        assertEquals('c', findChar(str.toCharArray(), 2, 0, 0));
        assertEquals('c', findChar(str.toCharArray(), 2, 0, 1));
        assertEquals('d', findChar(str.toCharArray(), 2, 0, 2));
        assertEquals('c', findChar(str.toCharArray(), 2, 0, 3));
        assertEquals('d', findChar(str.toCharArray(), 2, 0, 4));
        assertEquals('e', findChar(str.toCharArray(), 2, 0, 9));
    }

    @Test
    public void test_solver1() {
        String str = "abcd";
        String concat = "aababcabcdbbcbcdccdd";
        for (int i = 1; i < concat.length() + 1; i++) {
            System.out.println(i);
            assertEquals(concat.charAt(i - 1), solve(str, i));
        }
    }

    @Test
    public void test_solver2() {
        String str = "banana";
        String concat = "aananaananananabbabanbanabananbananannanannana";
        for (int i = 1; i < concat.length() + 1; i++) {
            System.out.println(i);
            assertEquals(concat.charAt(i - 1), solve(str, i));
        }
    }
    
    @Test
    public void test_solver3() {
        String str = "abazaz";
        String concat = "aababaabazabazaabazazazazaazazb";
        for (int i = 1; i < concat.length() + 1; i++) {
            System.out.println(i);
            assertEquals(concat.charAt(i - 1), solve(str, i));
        }
    }
}
