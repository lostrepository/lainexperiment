/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.codejam.qualification._2017;

import static java.util.stream.IntStream.range;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;

/**
 * <pre>{@code
 * Date: 08/04/2017
 *
 * Code jam 2017
 * Problem: Problem A. Oversized Pancake Flipper
 * Status: accepted
 * 
 * Problem
 * 
 * Last year, the Infinite House of Pancakes introduced a new 
 * kind of pancake. It has a happy face made of chocolate chips
 * on one side (the "happy side"), and nothing on the other side 
 * (the "blank side").
 * 
 * You are the head cook on duty. The pancakes are cooked in a 
 * single row over a hot surface. As part of its infinite efforts 
 * to maximize efficiency, the House has recently given you an 
 * oversized pancake flipper that flips exactly K consecutive 
 * pancakes. That is, in that range of K pancakes, it changes 
 * every happy-side pancake to a blank-side pancake, and vice 
 * versa; it does not change the left-to-right order of those 
 * pancakes.
 * 
 * You cannot flip fewer than K pancakes at a time with the 
 * flipper, even at the ends of the row (since there are raised 
 * borders on both sides of the cooking surface). For example, 
 * you can flip the first K pancakes, but not the first K - 1 
 * pancakes.
 * 
 * Your apprentice cook, who is still learning the job, just 
 * used the old-fashioned single-pancake flipper to flip some 
 * individual pancakes and then ran to the restroom with it, 
 * right before the time when customers come to visit the kitchen. 
 * You only have the oversized pancake flipper left, and you 
 * need to use it quickly to leave all the cooking pancakes 
 * happy side up, so that the customers leave feeling happy 
 * with their visit.
 * 
 * Given the current state of the pancakes, calculate the 
 * minimum number of uses of the oversized pancake flipper 
 * needed to leave all pancakes happy side up, or state that 
 * there is no way to do it. 
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test cases, T. 
 * T test cases follow. Each consists of one line with a string S 
 * and an integer K. S represents the row of pancakes: each of its 
 * characters is either + (which represents a pancake that is initially 
 * happy side up) or - (which represents a pancake that is initially 
 * blank side up). 
 * 
 * Output Format
 * 
 * For each test case, output one line containing Case #x: y, where 
 * x is the test case number (starting from 1) and y is either 
 * IMPOSSIBLE if there is no way to get all the pancakes happy 
 * side up, or an integer representing the the minimum number of 
 * times you will need to use the oversized pancake flipper to 
 * do it. 
 * 
 * Input
 * 
3
---+-++- 3
+++++ 4
-+-+- 4
 * 
 * Output
 * 
Case #1: 3
Case #2: 0
Case #3: IMPOSSIBLE
 * 
 * }</pre>
 */
public class ProblemA_Oversized_Pancake_Flipper {
    
    static void solve(char[] a, int k) {
        IntUnaryOperator flip = i -> a[i] = a[i] == '+'? '-': '+';
        IntConsumer flipK = i -> {
            for (int j = 0; j < k; j++) {
                a[i + j] = (char) flip.applyAsInt(i + j);
            }
        };
        int c = 0;
        for (int i = 0; i <= a.length - k; i++) {
            if (a[i] == '+') continue;
            c++;
            flipK.accept(i);
        }
        System.out.println(range(0, a.length)
            .allMatch(i -> a[i] == '+')? c: "IMPOSSIBLE");
    }
    
    public static void main(String[] args) throws IOException {
        Class<?> clazz = ProblemA_Oversized_Pancake_Flipper.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(Paths.get("/tmp/in")): new Scanner(clazz.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < T; i++) {
            System.out.format("Case #%d: ", i + 1);
            solve(scanner.next().toCharArray(), scanner.nextInt());
        }
        scanner.close();
    }

}
