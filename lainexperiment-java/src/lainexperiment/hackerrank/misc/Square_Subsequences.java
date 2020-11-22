/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.hackerrank.misc;

import static java.util.stream.IntStream.range;
import static java.util.stream.LongStream.of;
import static java.util.stream.Stream.generate;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <pre>{@code
 * Date: 13/04/2017
 *
 * Hacker rank
 * Problem: Square Subsequences
 * Status: accepted
 * 
 * Problem
 * 
 * A string is called a square string if it can be obtained 
 * by concatenating two copies of the same string. For example, 
 * "abab", "aa" are square strings, while "aaa", "abba" are not. 
 * Given a string, how many (non-empty) subsequences of the 
 * string are square strings? A subsequence of a string can 
 * be obtained by deleting zero or more characters from it, 
 * and maintaining the relative order of the remaining 
 * characters.
 * 
 * Input Format
 * 
 * The first line contains the number of test cases, T.
 * T test cases follow. Each case contains a string, S.
 * 
 * Output Format
 * 
 * Output T lines, one for each test case, containing the 
 * required answer modulo 1000000007.
 * 
 * Input
 * 
3
aaa
abab
baaba
 * 
 * Output
 * 
3
3
6
 * 
 * }</pre>
 */
public class Square_Subsequences {

    static long add(long... a) {
        return of(a).reduce((n1, n2) -> (n1 + n2) % 1000000007).getAsLong();
    }

    static long sub(long... a) {
        return of(a).reduce((n1, n2) -> {
            long v = n1 - n2;
            return v < 0? 1000000007 + v: v;}).getAsLong();
    }

    static long countCommonSubsets(char[] a, char[] b) {
        //System.out.println(Arrays.toString(a));
        //System.out.println(Arrays.toString(b));
        long C[][] = new long[a.length + 1][b.length + 1];
        for (int i = 1; i < C.length; i++) {
            for (int j = 1; j < C[0].length; j++) {
                if (a[i - 1] == b[j - 1]) {
                    C[i][j] = add(C[i][j - 1], C[i - 1][j], 1);
                } else {
                    C[i][j] = add(C[i][j - 1], sub(C[i - 1][j], C[i - 1][j - 1]));
                }
            }
        }
        return C[a.length][b.length];
    }
    
    static long count(char[] a) {
        return range(1, a.length)
            .mapToLong(i -> {
                return sub(countCommonSubsets(Arrays.copyOf(a, i), Arrays.copyOfRange(a, i, a.length)),
                        countCommonSubsets(Arrays.copyOf(a, i), Arrays.copyOfRange(a, i + 1, a.length)));
            })
            .reduce(Square_Subsequences::add).orElse(0);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Square_Subsequences.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Square_Subsequences.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            generate(scanner::next)
                .limit(scanner.nextInt())
                //.peek(System.out::println)
                .map(String::toCharArray)
                .map(Square_Subsequences::count)
                .forEach(System.out::println);
        }
        scanner.close();
    }
    
}
