/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/11/2016
 * 
 * Hacker rank
 * 101 Hack 43
 * Problem: Minimizing the Max-Min Difference
 * Status: accepted
 * 
 * Consider a sequence of n integers, A = {a0, .. an-1}. 
 * We want to delete exactly one element, ai, such that 
 * the difference between the smallest and largest elements 
 * (i.e., max(A) - min(A)) in the sequence is minimal. Then 
 * print the minimal absolute difference between the maximal 
 * and minimal elements on a new line.
 * 
 * Input Format
 * 
 * The first line contains an integer, n, denoting the 
 * number of integers in the sequence.
 * The second line contains n space-separated integers 
 * describing the respective values of a0, .. an-1.
 * 
 * Output Format
 * 
 * Print a single integer denoting the minimal absolute 
 * difference between max(A) and min(A) after removing 
 * exactly one element.
 * 
 * 
 * Sample Input
 * 
5
7 4 3 1 3
10
5 4 0 8 3 8 4 1 1 8
 *
 * Sample Output
 * 
3
7
 *
 */

package lainexperiment.hackerrank.hack101._2016.nov;

import static java.lang.Math.min;
import static java.util.Arrays.sort;
import static java.util.stream.LongStream.generate;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1_Minimizing_the_MaxMin_Difference {
    
    static long[] A;
    
    static void solve() {
        sort(A);
        long r = min(A[A.length - 2] - A[0], A[A.length - 1] - A[1]);
        System.out.println(r);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task1_Minimizing_the_MaxMin_Difference.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNextLine()) {
            A = generate(scanner::nextLong).limit(scanner.nextInt()).toArray();
            solve();
        }
        scanner.close();
    }

}
