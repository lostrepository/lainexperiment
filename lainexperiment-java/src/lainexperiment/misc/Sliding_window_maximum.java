/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 27/01/2019
 * 
 * Problem
 * 
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 * 
 * Input Format
 * 
 * The first line contains the number of tests N.
 * First line of every test contains size of an array N and K.
 * Second line of every test contains the input array
 *   
 * Output Format
 * 
 * For every window position output max value
 * 
 * Input
 * 
2
7 3
4 5 3 7 1 2 3
10 3
1 2 3 4 5 6 7 1 2 10
 * 
 * Output
 * 
5
7
7
7
3
3
4
5
6
7
7
7
10
 * 
 */

package lainexperiment.misc;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Sliding_window_maximum {

    static SortedSet<Integer> bst = new TreeSet<>();
    static Queue<Integer> q = new LinkedList<>();
    static int K;
    static int MAX;

    static void add(int n) {
        if (q.size() == K) {
            int m = q.poll();
            bst.remove(m);
            if (MAX == m) MAX = bst.last();
        }
        bst.add(n);
        q.add(n);
        if (MAX < n) MAX = n;
    }

    static int max() {
        return MAX;
    }

    public static void main(String[] args) {
        Class<?> clazz = Sliding_window_maximum.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
                new Scanner(clazz.getResourceAsStream(inputFile));
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            K = scanner.nextInt();
            IntStream.generate(scanner::nextInt)
                .limit(n)
                .peek(Sliding_window_maximum::add)
                .map(ii -> max())
                .skip(K - 1)
                .forEach(out::println);
        }
        scanner.close();
    }
}
