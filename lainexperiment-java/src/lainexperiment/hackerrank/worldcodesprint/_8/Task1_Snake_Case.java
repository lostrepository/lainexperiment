/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.worldcodesprint._8;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 17/12/2016
 * 
 * Hacker rank
 * World CodeSprint #8
 * Problem: Snake Case
 * Status: accepted
 * 
 * Alice is learning English and wants to keep track of all of the 
 * new words she learns. Being a programmer, Alice decides to put 
 * all of her words into one string, s, where each word is separated 
 * by a single underscore character. This is known as Snake Case. 
 * Can you determine the number of words in Alice's string?
 * Given s, print the total number of underscore-delimited words 
 * it contains on a new line.
 * Note: You simply need to count the number of words; do not concern 
 * yourself with whether or not the words are distinct.
 * 
 * Input Format
 * 
 * A single line containing string s.
 * 
 * Output Format
 * 
 * Print the number of underscore-delimited words in string s.
 * 
 * 
 * Sample Input
 * 
have_a_nice_day
 *
 * Sample Output
4
 *
 * }</pre>
 */
public class Task1_Snake_Case {

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task1_Snake_Case.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            System.out.println(scanner.next().split("_").length);
        }
        scanner.close();
    }

}
