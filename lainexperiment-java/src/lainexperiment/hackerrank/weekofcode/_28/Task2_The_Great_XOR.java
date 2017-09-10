/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 10/01/2017
 * 
 * Hacker rank
 * Week of code 28
 * Problem: The Great XOR
 * Status: accepted
 * 
 * Given a long integer, x, count the number of values of a 
 * satisfying the following conditions: 
 * 
 * a ^ x > x
 * 0 < a < x
 * 
 * where a and x are long integers and ^ is the bitwise 
 * XOR operator.
 * 
 * You are given q queries, and each query is in the form 
 * of a long integer denoting x. For each query, print the 
 * total number of values of a satisfying the conditions 
 * above on a new line.
 * 
 * Input Format
 * 
 * The first line contains an integer, q, denoting the 
 * number of queries.
 * Each of the q subsequent lines contains a long integer 
 * describing the value of x for a query. 
 * 
 * Output Format
 * 
 * For each query, print the number of values of a satisfying 
 * the given conditions on a new line.
 * 
 * 
 * Sample Input
 * 
2
2
10
 *
 * Sample Output
 * 
1
5
 *
 */

package lainexperiment.hackerrank.weekofcode._28;

import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2_The_Great_XOR {
 
    static long count(int[] a, int i, boolean isGreater) {
        if (i == a.length) return isGreater? 1: 0;
        if (isGreater)
            return 1L << (a.length - i);
        if (a[i] == 1) return count(a, i + 1, isGreater);
        return count(a, i + 1, false) + count(a, i + 1, true);
    }
    
    static long count(long l) {
        return count(Long.toBinaryString(l).chars().map(i -> i - '0').toArray(), 1, false);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task2_The_Great_XOR.class.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource?
            new Scanner(System.in): new Scanner(Task2_The_Great_XOR.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            range(0, scanner.nextInt())
                .mapToLong(i -> scanner.nextLong())
                .map(Task2_The_Great_XOR::count)
                .forEach(System.out::println);
        }
        scanner.close();
    }

}
