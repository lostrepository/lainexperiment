/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/09/2016
 * 
 * Hacker rank
 * Week of code 23
 * Problem: Gears of War
 * Status: accepted
 * 
 * Alex is preparing for a programming contest and decides 
 * the gears turning in his head are at war with his own 
 * laziness. He imagines chains of n circularly linked gears 
 * trying to turn in his head, and wonders under which 
 * circumstances they might be able to turn together in a 
 * single chain and when they might be locked (i.e., unable 
 * to rotate together). 
 * Alex decides to set a problem for himself by creating q
 * queries where each query takes the form of an integer, n, 
 * denoting some number of circularly linked gears. For 
 * each query, print Yes on a new line if the gears can 
 * turn together; otherwise, print No.
 * 
 * Input Format
 * 
 * The first line contains single integer, q, denoting the 
 * number of queries.
 * Each line i of the q subsequent lines contains a single 
 * integer, n, denoting the number of gears for that query.
 * 
 * Output Format
 * 
 * For each query, print Yes on a new line if it is possible 
 * to rotate all n gears simultaneously; otherwise, print No.
 * 
 * 
 * Sample Input
 * 
2
3
4
 *
 * Sample Output
 * 
No
Yes
 *
 */

package lainexperiment.hackerrank.weekofcode._23;

import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1_Gears_of_War {

    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task1_Gears_of_War.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task1_Gears_of_War.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            range(0, scanner.nextInt())
                .map(i -> scanner.nextInt())
                .mapToObj(n -> n <= 2 || (n & 1) == 0? "Yes": "No")
                .forEach(System.out::println);
        }
        scanner.close();
    }

}
