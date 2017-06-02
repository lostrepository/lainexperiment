/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 02/06/2017
 *
 * Code jam 2017
 * Problem: Problem A. Ample Syrup
 * Status: accepted
 * 
 * Problem
 * 
 * The kitchen at the Infinite House of Pancakes has just 
 * received an order for a stack of K pancakes! The chef 
 * currently has N pancakes available, where N ≥ K. Each 
 * pancake is a cylinder, and different pancakes may have 
 * different radii and heights.
 * 
 * As the sous-chef, you must choose K out of the N available 
 * pancakes, discard the others, and arrange those K 
 * pancakes in a stack on a plate as follows. First, 
 * take the pancake that has the largest radius, and lay 
 * it on the plate on one of its circular faces. (If 
 * multiple pancakes have the same radius, you can use 
 * any of them.) Then, take the remaining pancake with the 
 * next largest radius and lay it on top of that pancake, 
 * and so on, until all K pancakes are in the stack and 
 * the centers of the circular faces are aligned in a line 
 * perpendicular to the plate.
 * 
 * You know that there is only one thing your diners love as 
 * much as they love pancakes: syrup! It is best to maximize 
 * the total amount of exposed pancake surface area in the 
 * stack, since more exposed pancake surface area means 
 * more places to pour on delicious syrup. Any part of a 
 * pancake that is not touching part of another pancake 
 * or the plate is considered to be exposed.
 * 
 * If you choose the K pancakes optimally, what is the 
 * largest total exposed pancake surface area you can 
 * achieve? 
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test 
 * cases, T. T test cases follow. Each begins with one 
 * line with two integers N and K: the total number of 
 * available pancakes, and the size of the stack that the 
 * diner has ordered. Then, N more lines follow. Each 
 * contains two integers Ri and Hi: the radius and height 
 * of the i-th pancake, in millimeters. 
 * 
 * Output Format
 * 
 * For each test case, output one line containing Case #x: y, 
 * where x is the test case number (starting from 1) and 
 * y is the maximum possible total exposed pancake surface 
 * area, in millimeters squared. y will be considered 
 * correct if it is within an absolute or relative error 
 * of 10-6 of the correct answer. 
 * 
 * Input
 * 
4
2 1
100 20
200 10
2 2
100 20
200 10
3 2
100 10
100 10
100 10
4 2
9 3
7 1
10 1
8 4
 * 
 * Output
 * 
Case #1: 138230.076757951
Case #2: 150796.447372310
Case #3: 43982.297150257
Case #4: 625.176938064
 * 
 */
package lainexperiment.codejam.round._1c._2017;

import static java.lang.Math.PI;
import static java.lang.Math.min;
import static java.lang.Math.pow;
import static java.util.Arrays.sort;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.of;
import static java.util.stream.Stream.generate;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ProblemA_Ample_Syrup {

    static double topArea(int r) {
        return PI * pow(r, 2);
    }

    static double area(int[] pair) {
        return topArea(pair[0]) + 2 * PI * pair[0] * pair[1];
    }

    static double sideArea(int[] p) {
        return area(p) - topArea(p[0]);
    }
    
    static double[] join(double[] acc, int[] pair) {
        acc[0] = ((acc[0] == 0)? 0: acc[0] - topArea(pair[0])) + area(pair);
        return acc;
    }

    static double solve(int[][] a, int k) {
        sort(a, comparingInt((int[] p) -> p[0]).reversed());
        LinkedList<int[]> q = new LinkedList<>();
        if (k == 1) 
            return area(stream(a)
                    .max((p1, p2) -> area(p1) - area(p2) < 0? -1: 1)
                    .get());
        for (int i = 0; i < a.length; i++) {
            if (q.size() < k) {
                q.add(a[i]);
                continue;
            }
            int[] p = q.subList(1, q.size()).stream()
                    .min((p1, p2) -> sideArea(p1) - sideArea(p2) < 0? -1: 1)
                    .get();
            double firstArea = area(q.get(0)) - topArea(q.get(1)[0]);
            double nonFirstArea = sideArea(p);
            if (sideArea(a[i]) < min(firstArea, nonFirstArea)) 
                continue;
            if (firstArea < nonFirstArea)
                q.remove(0);
            else
                q.remove(p);
            q.add(a[i]);
        }
        return q.stream()
                .collect(() -> new double[1], ProblemA_Ample_Syrup::join, 
                        (a1, a2) -> {})[0];
    }

    public static void main(String[] args) throws IOException {
        Class<?> clazz = ProblemA_Ample_Syrup.class;
        String inputFile = clazz.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(Paths.get("/tmp/in")): 
            new Scanner(ProblemA_Ample_Syrup.class.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        scanner.nextLine();
        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[][] a = generate(() -> of(scanner.nextInt(), scanner.nextInt()))
                    .limit(n)
                    .map(IntStream::toArray)
                    .collect(toList()).toArray(new int[0][0]);
            System.out.format("Case #%d: %f\n", t + 1, solve(a, k));
        }
        scanner.close();
    }

}
