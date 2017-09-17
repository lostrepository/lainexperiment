/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 17/12/2016
 * 
 * Hacker rank
 * World CodeSprint #8
 * Problem: Climbing the Leaderboard
 * Status: accepted
 * 
 * Alice is playing an arcade game and wants to climb to the 
 * top of the leaderboard. Can you help her track her ranking 
 * as she beats each level? The game uses Dense Ranking, so 
 * its leaderboard works like this:
 * 
 * - The player with the highest score is ranked number 1 on 
 * the leaderboard.
 * 
 * - Players who have equal scores receive the same ranking 
 * number, and the next player(s) receive the immediately 
 * following ranking number.
 * 
 * For example, four players have the scores 100, 90, 90, 
 * and 80. Those players will have ranks 1, 2, 2, and 3, 
 * respectively.
 * 
 * When Alice starts playing, there are already n people on the 
 * leaderboard. The score of each player i is denoted by si. Alice 
 * plays for m levels, and we denote her total score after passing 
 * each level j as alicej. After completing each level, Alice wants 
 * to know her current rank.
 * 
 * You are given an array, scores, of decreasing leaderboard scores, 
 * and another array, alice, of Alice's cumulative scores for each 
 * level of the game. You must print m integers. The jth integer 
 * should indicate the current rank of alice after passing the jth 
 * level.
 * 
 * Input Format
 * 
 * The first line contains an integer, n, denoting the number of 
 * players already on the leaderboard.
 * The next line contains n space-separated integers describing 
 * the respective values of scores0, scores1, .., scoresn-1. 
 * The next line contains an integer, m, denoting the number of 
 * levels Alice beats.
 * The last line contains m space-separated integers describing 
 * the respective values of alice0, alice1, .. alicem-1.
 * 
 * Output Format
 * 
 * Print m integers. The jth integer should indicate the rank of 
 * alice after passing the jth level.
 * 
 * 
 * Sample Input
 * 
7
100 100 50 40 40 20 10
4
5 25 50 120
 *
 * Sample Output
 * 
6
4
2
1
 *
 */

package lainexperiment.hackerrank.worldcodesprint._8;

import static java.util.Arrays.binarySearch;
import static java.util.stream.IntStream.generate;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2_Climbing_the_Leaderboard {

    static Integer[] P, A;
    
    static void solve() {
        for (int i = 0; i < A.length; i++) {
            int p = binarySearch(P, A[i], (a, b) -> b - a);
            if (p < 0)
                p = 0 - p;
            else
                p++;
            System.out.println(p);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task2_Climbing_the_Leaderboard.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            P = generate(scanner::nextInt)
                    .limit(scanner.nextInt())
                    .boxed()
                    .distinct()
                    .toArray(s -> new Integer[s]);
            A = generate(scanner::nextInt)
                    .limit(scanner.nextInt())
                    .boxed()
                    .toArray(s -> new Integer[s]);
            solve();
        }
        scanner.close();
    }

}
