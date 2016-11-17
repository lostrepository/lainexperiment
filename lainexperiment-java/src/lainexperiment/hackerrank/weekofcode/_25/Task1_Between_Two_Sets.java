/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 01/11/2016
 * 
 * Hacker rank
 * Week of code 25
 * Problem: Between Two Sets
 * Status: accepted
 * 
 * Consider two sets of positive integers, A = {a0, a1, .., an-1} 
 * and B = {b0, b1, .., bn-1}. We say that a positive integer, x, 
 * is between sets A and B if the following conditions are satisfied:
 * - All elements in A are factors of B.
 * - x is a factor of all elements in B.
 * Given A and B, find and print the number of integers (i.e., 
 * possible x's) that are between the two sets.
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers describing 
 * the respective values of n (the number of elements in set A) and 
 * m (the number of elements in set B).
 * The second line contains n distinct space-separated integers 
 * describing a0, a1,.. an-1.
 * The third line contains m distinct space-separated integers 
 * describing b0, b1, .., bm-1. 
 * 
 * Output Format
 * 
 * Print the number of integers that are considered to be between A
 * and B.
 * 
 * 
 * Sample Input
 * 
2 3
2 4
16 32 96
 *
 * Sample Output
 * 
3
 *
 */

package lainexperiment.hackerrank.weekofcode._25;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.generate;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Task1_Between_Two_Sets {

    static int[] A, B;
    
    static void solve() {
        Arrays.sort(A);
        Arrays.sort(B);
        int c = 0;
        for (int i = A[0]; i <= B[0]; i += A[0]) {
            final int i_ = i;
            if (!stream(A).allMatch(v -> i_ % v == 0))
                continue;
            if (!stream(B).allMatch(v -> v % i_ == 0))
                continue;
            c++;
        }
        System.out.println(c);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task1_Between_Two_Sets.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task1_Between_Two_Sets.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            A = generate(scanner::nextInt).limit(a).toArray();
            B = generate(scanner::nextInt).limit(b).toArray();
            solve();
        }
        scanner.close();
    }

}
