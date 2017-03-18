/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 20/12/2016
 * 
 * Hacker rank
 * Week of code 27
 * Problem: Tailor Shop
 * Status: accepted
 * 
 * Jaime the Tailor's new customer wants to add n distinct 
 * clusters of buttons to her dress, where each cluster is 
 * filled with a distinct number of buttons of a certain 
 * color.
 * The customer also has specific ideas about how much she 
 * wants to pay for each cluster in that for some cluster i, 
 * she wants to pay at least ai dollars. In addition, she 
 * wants each cluster to contain a distinct number of buttons. 
 * And, finally, she wants to minimize her total cost.
 * Jaime stocks an infinite number of buttons in an infinite 
 * number of colors at his shop, and each button costs p dollars. 
 * Given n, p, and the amount of money that the customer wants 
 * to pay for each respective cluster, help Jaime by finding and 
 * printing a long integer denoting the minimum number of buttons 
 * he can use to satisfy her request.
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers 
 * denoting the respective values of n and p.
 * The second line contains n space-separated integers where 
 * each integer i denotes the value of ai (i.e., the minimum 
 * amount of money she wants to spend on cluster i).
 * 
 * Output Format
 * 
 * Print a single long integer denoting the minimum number of 
 * buttons required for Jaime to satisfy his customer's request.
 * 
 * 
 * Sample Input
 * 
3 2
4 6 6
 *
 * Sample Output
 * 
9
 *
 */

package lainexperiment.hackerrank.weekofcode._27;

import static java.util.Arrays.stream;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.IntStream.generate;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Task2_Tailor_Shop {
 
    static int[] A;
    static int P;
    
    static long makeClusters(long[] acc, int totalPrice, long num) {
        long clusterSize = (totalPrice + P - 1) / P;
        if (acc[0] > clusterSize)
            clusterSize = acc[0];
        acc[0] = clusterSize + num;
        return num * clusterSize + num * (num - 1) / 2;
    }
    
    static void solve() {
        Map<Integer, Integer> hist = stream(A)
            .boxed()
            .collect(groupingBy(identity(), 
                    summingInt(i -> 1)));
        long[] acc = new long[1];
        long res = hist.entrySet().stream()
            .sorted(Comparator.comparingInt(Entry::getKey))
            .mapToLong(e -> makeClusters(acc, e.getKey(), e.getValue()))
            .sum();
        System.out.println(res);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "Task2.in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task2_Tailor_Shop.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            P = scanner.nextInt();
            A = generate(scanner::nextInt)
                .limit(n)
                .toArray();
            solve();
        }
        scanner.close();
    }

}
