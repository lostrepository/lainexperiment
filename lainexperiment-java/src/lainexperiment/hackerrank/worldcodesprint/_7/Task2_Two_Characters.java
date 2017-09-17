/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 24/09/2016
 * 
 * Hacker rank
 * World CodeSprint #7
 * Problem: Two Characters
 * Status: accepted
 * 
 * String t always consists of two distinct alternating characters. 
 * For example, if string t's two distinct characters are x and y, 
 * then t could be xyxyx or yxyxy but not xxyy or xyyx.
 * You can convert some string s to string t by deleting characters 
 * from s. When you delete a character from s, you must delete all 
 * occurrences of it in s. For example, if s = abaacdabd and you 
 * delete the character a, then the string becomes bcdbd.
 * Given s, convert it to the longest possible string t. Then print 
 * the length of string t on a new line; if no string t can be formed 
 * from s, print 0 instead.
 * 
 * Input Format
 * 
 * The first line contains a single integer denoting the length of s.
 * The second line contains string s.
 * 
 * Output Format
 * 
 * Print a single integer denoting the maximum length of t for the 
 * given s; if it is not possible to form string t, print 0 instead.
 * 
 * 
 * Sample Input
 * 
10
beabeefeab
 *
 * Sample Output
 * 
5
 *
 */

package lainexperiment.hackerrank.worldcodesprint._7;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Task2_Two_Characters {

    static boolean isAlternating(List<Integer> a, List<Integer> b) 
    {
        if (abs(a.size() - b.size()) > 1)
            return false;
        List<Integer> min = a.get(0) < b.get(0)? a: b;
        List<Integer> max = min == a? b: a;
        if (min.size() < max.size())
            return false;
        int c = -1;
        int size = min(a.size(), b.size());
        for (int i = 0; i < size; ++i) {
            if (min.get(i) < c)
                return false;
            if (min.get(i) > max.get(i))
                return false;
            c = max.get(i);
        }
        if (min.size() > max.size())
            return c < min.get(min.size() - 1);
        return true;
    }

    static void solve(List<List<Integer>> l) {
        int m = -1;
        for (int i = 0; i < l.size(); ++i) {
            for (int j = i + 1; j < l.size(); ++j) {
                if (!isAlternating(l.get(i), l.get(j)))
                    continue;
                m = max(m, l.get(i).size() + l.get(j).size());
            }
        }
        System.out.println(m < 2? 0: m);
    }

    static void solve(char[] a) {
        List<List<Integer>> l = range(0, a.length)
            .boxed()
            .collect(groupingBy(i -> a[i],
                mapping(i -> i, toList())))
            .values().stream()
            .collect(toList());
        solve(l);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task2_Two_Characters.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            solve(scanner.next().substring(0, n)
                  .toCharArray());
        }
        scanner.close();
    }

}
