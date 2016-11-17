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
 * Problem: Stone Division
 * Status: accepted
 * 
 * Consider the following game:
 * 
 * - There are two players, First and Second, sitting in 
 * front of a pile of n stones. First always plays first.
 * 
 * - There is a set, S, of m distinct integers defined as
 * S = {s0, .., sm-1}.
 * 
 * - The players move in alternating turns. During each 
 * turn, a player chooses some si from S and splits one 
 * of the piles into exactly si smaller piles of equal 
 * size. If no si exists that will split one of the 
 * available piles into exactly si equal smaller piles, 
 * the player loses.
 * 
 * - Both players always play optimally.
 * 
 * Given n, m, and the contents of S, find and print the 
 * winner of the game. If First wins, print First; 
 * otherwise, print Second.
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers 
 * describing the respective values of n (the size of 
 * the initial pile) and m (the size of the set).
 * The second line contains m distinct space-separated 
 * integers describing the respective values of 
 * s0, .., sm-1.
 * 
 * Output Format
 * 
 * Print First if the First player wins the game; 
 * otherwise, print Second.
 * 
 * 
 * Sample Input
 * 
15 3
5 2 3
 *
 * Sample Output
 * 
Second
 *
 */

package lainexperiment.hackerrank.weekofcode._25;

import static java.util.stream.LongStream.generate;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task4_Stone_Division {

    static long N;
    static long[] S;
    static Map<Long, Boolean> M = new HashMap<>();
    
    static boolean count(boolean isFirst, long n) {
        long key = pack(isFirst, n);
        if (M.containsKey(key))
            return M.get(key);
        boolean res = false;
        for (int i = 0; i < S.length; i++) {
            if ((n % S[i]) != 0)
                continue;
            if (S[i] == n) {
                res = true;
                break;
            }
            boolean isWin = !count(!isFirst, n / S[i]);
            if (isWin && ((n / S[i]) & 1) == 1) {
                res = true;
                break;
            }
            if (!isWin && ((n / S[i]) & 1) == 0) {
                res = true;
                break;
            }
        }
        M.put(pack(isFirst, n), res);
        return res;
    }

    private static Long pack(boolean isFirst, long n) {
        return n | ((isFirst? 1: 0) << Long.BYTES);
    }

    static boolean isFirstWins() {
        M.clear();
        return count(true, N);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Task4_Stone_Division.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task4_Stone_Division.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            N = scanner.nextLong();
            int s = scanner.nextInt();
            S = generate(scanner::nextLong).limit(s).toArray();
            System.out.println(isFirstWins()? "First": "Second");
        }
        scanner.close();
    }

}
