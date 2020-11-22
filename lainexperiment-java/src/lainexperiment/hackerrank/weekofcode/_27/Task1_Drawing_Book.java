/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._27;

import static java.lang.Math.min;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 19/12/2016
 * 
 * Hacker rank
 * Week of code 27
 * Problem: Drawing Book
 * Status: accepted
 * 
 * Brie’s Drawing teacher asks her class to open their n-page book 
 * to page number p. Brie can either start turning pages from the 
 * front of the book (i.e., page number 1) or from the back of the 
 * book (i.e., page number n), and she always turns pages one-by-one 
 * (as opposed to skipping through multiple pages at once). When she 
 * opens the book, page 1 is always on the right side.
 * Each page in the book has two sides, front and back, except for 
 * the last page which may only have a front side depending on the 
 * total number of pages of the book (see the Explanation sections 
 * below for additional diagrams). 
 * Given n and p, find and print the minimum number of pages Brie  
 * must turn in order to arrive at page p.
 * 
 * Input Format
 * 
 * The first line contains an integer, n, denoting the number of pages 
 * in the book.
 * The second line contains an integer, p, denoting the page that Brie's 
 * teacher wants her to turn to.
 * 
 * Output Format
 * 
 * Print an integer denoting the minimum number of pages Brie must turn 
 * to get to page p. 
 * 
 * 
 * Sample Input
 * 
5
4
 *
 * Sample Output
 * 
0
 *
 * }</pre>
 */
public class Task1_Drawing_Book {
 
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "Task1.in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task1_Drawing_Book.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int p = scanner.nextInt();
            System.out.println(min(p / 2, (n - p + ((n & 1) == 1? 0: 1)) / 2));
        }
        scanner.close();
    }

}
