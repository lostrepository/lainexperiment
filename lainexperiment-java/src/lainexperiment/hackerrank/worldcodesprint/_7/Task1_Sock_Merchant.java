/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.worldcodesprint._7;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <pre>{@code
 * 
 * Date: 24/09/2016
 * 
 * Hacker rank
 * World CodeSprint #7
 * Problem: Sock Merchant
 * Status: accepted
 * 
 * John's clothing store has a pile of n loose socks where each 
 * sock i is labeled with an integer, ci, denoting its color. He 
 * wants to sell as many socks as possible, but his customers will
 * only buy them in matching pairs. Two socks, i and j, are a single 
 * matching pair if ci = cj. 
 * Given n and the color of each sock, how many pairs of socks can 
 * John sell?
 * 
 * Input Format
 * 
 * The first line contains an integer, n, denoting the number of 
 * socks.
 * The second line contains n space-separated integers describing 
 * the respective values of co, c1, .., cn.
 * 
 * Output Format
 * 
 * Print the total number of matching pairs of socks that John can 
 * sell.
 * 
 * 
 * Sample Input
 * 
9
10 20 20 10 10 30 50 10 20
 *
 * Sample Output
 * 
3
 *
 * }</pre>
 */
public class Task1_Sock_Merchant {

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task1_Sock_Merchant.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int r = Stream.generate(scanner::nextInt)
                .limit(n)
                .collect(Collectors.groupingBy(i -> i))
                .values().stream()
                .mapToInt(i -> i.size() / 2)
                .sum();
            System.out.println(r);
        }
        scanner.close();
    }

}
