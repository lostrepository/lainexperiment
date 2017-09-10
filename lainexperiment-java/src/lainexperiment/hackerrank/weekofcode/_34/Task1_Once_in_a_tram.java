/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 30/07/2017
 * 
 * Hacker rank
 * Week of code 34
 * Problem: Once in a tram
 * Status: accepted
 * 
 * One day, Jack was going home by tram. When he got his 
 * ticket, he noticed that number on the ticket was not 
 * lucky. A lucky ticket is a six-digit number on the ticket 
 * in which sum of the first three digits is equal to the 
 * sum of the last three digits.
 * For example, number 165912 is lucky because sum of 
 * 1 + 6 + 5 = 12, and 9 + 1 + 2 = 12.
 * Since the number on the ticket wasn't lucky, Jack needs 
 * your help to find the next lucky ticket number.
 * For example, if Jack's ticket number is 165901, then the 
 * next lucky ticket number is 165903.
 * Given Jack's current ticket number, find and print the 
 * next lucky ticket number.
 * 
 * Input
 * 
 * The first line contains an integer, x, denoting the 6-digit
 * number on the ticket.
 * 
 * Output
 * 
 * For the given input find and print the next lucky ticket 
 * number.
 * 
 * Sample Input
 * 
555555
 *
 * Sample Output
 * 
555564
 *
 */

package lainexperiment.hackerrank.weekofcode._34;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.IntStream.generate;

import java.io.IOException;
import java.util.Scanner;

public class Task1_Once_in_a_tram {
 
    static int sum(int n) {
        return Integer.toString(n)
            .chars()
            .map(i -> i - '0')
            .sum();
    }

    static int solve(int n) {
        int a = n / 1000;
        int b = n % 1000 + 1;
        while (sum(a) != sum(b)) {
            b++;
            if (b > 999) {
                b = 0;
                a++;
            }
        }
        return a * 1000 + b;
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = Task1_Once_in_a_tram.class;
        String inputFile = clazz.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(in): 
            new Scanner(Task1_Once_in_a_tram.class.getResourceAsStream(inputFile));
        generate(() -> scanner.nextInt())
            .boxed()
            .mapToInt(Task1_Once_in_a_tram::solve)
            .peek(out::println)
            .filter(((s) -> !scanner.hasNextInt()))
            .findFirst();
        scanner.close();
    }

}
