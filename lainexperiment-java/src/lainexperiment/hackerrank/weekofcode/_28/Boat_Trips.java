/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 09/01/2017
 * 
 * Hacker rank
 * Week of code 28
 * Problem: Boat Trips
 * Status: accepted
 * 
 * Alice owns a company that transports tour groups between
 * two islands. She has n trips booked, and each trip i has 
 * pi passengers. 
 * Alice has m boats for transporting people, and each boat's 
 * maximum capacity is c passengers.
 * Given the number of passengers going on each trip, determine 
 * whether or not Alice can perform all n trips using no more 
 * than m boats per individual trip. 
 * If this is possible, print Yes; otherwise, print No.
 * 
 * Input Format
 * 
 * The first line contains three space-separated integers 
 * describing the respective values of n (number of trips), 
 * c (boat capacity), and m (total number of boats).
 * The second line contains space-separated integers describing 
 * the values of p0, .., pn-1.
 * 
 * Output Format
 * 
 * Print Yes if Alice can perform all booked trips using no more 
 * than boats per trip; otherwise, print No.
 * 
 * 
 * Sample Input
 * 
5 2 2
1 2 1 4 3
 *
 * Sample Output
 * 
Yes
 *
 */

package lainexperiment.hackerrank.weekofcode._28;

import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.generate;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Boat_Trips {
 
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Boat_Trips.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Boat_Trips.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int c = scanner.nextInt();
            int m = scanner.nextInt();
            int[] a = generate(scanner::nextInt)
                    .limit(n)
                    .toArray();
            out.println((stream(a).max().getAsInt() <= c * m)? "Yes": "No");
        }
        scanner.close();
    }

}
