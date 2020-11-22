/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.codejam.round._1b._2017;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.of;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <pre>{@code
 * Date: 20/05/2017
 *
 * Code jam 2017
 * Problem: Problem B. Stable Neigh-bors
 * Status: accepted
 * 
 * Problem
 * 
 * You are lucky enough to own N pet unicorns. Each of your 
 * unicorns has either one or two of the following kinds of 
 * hairs in its mane: red hairs, yellow hairs, and blue hairs. 
 * The color of a mane depends on exactly which sorts of 
 * colored hairs it contains:
 *
 * - A mane with only one color of hair appears to be that 
 * color. For example, a mane with only blue hairs is blue.
 * - A mane with red and yellow hairs appears orange.
 * - A mane with yellow and blue hairs appears green.
 * - A mane with red and blue hairs appears violet.
 * 
 * You have R, O, Y, G, B, and V unicorns with red, orange, 
 * yellow, green, blue, and violet manes, respectively.
 *
 * You have just built a circular stable with N stalls, 
 * arranged in a ring such that each stall borders two 
 * other stalls. You would like to put exactly one of 
 * your unicorns in each of these stalls. However, unicorns 
 * need to feel rare and special, so no unicorn can be next 
 * to another unicorn that shares at least one of the hair 
 * colors in its mane. For example, a unicorn with an orange 
 * mane cannot be next to a unicorn with a violet mane, since 
 * both of those manes have red hairs. Similarly, a unicorn 
 * with a green mane cannot be next to a unicorn with a yellow 
 * mane, since both of those have yellow hairs.
 *
 * Is it possible to place all of your unicorns? If so, 
 * provide any one arrangement. 
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test cases, T. 
 * T test cases follow. Each consists of one line with seven 
 * integers: N, R, O, Y, G, B, and V. 
 * 
 * Output Format
 * 
 * For each test case, output one line containing Case #x: y, 
 * where x is the test case number (starting from 1) and y is 
 * IMPOSSIBLE if it is not possible to place all the unicorns, 
 * or a string of N characters representing the placements of 
 * unicorns in stalls, starting at a point of your choice and 
 * reading clockwise around the circle. Use R to represent 
 * each unicorn with a red mane, O to represent each unicorn 
 * with an orange mane, and so on with Y, G, B, and V. This 
 * arrangement must obey the rules described in the statement 
 * above.
 * If multiple arrangements are possible, you may print any 
 * of them. 
 * 
 * Input
 * 
6
7 4 0 1 2 0 0
4 0 0 2 0 0 2
6 2 0 2 0 2 0
3 1 0 2 0 0 0
6 2 0 1 1 2 0
4 0 0 2 0 0 2
 * 
 * Output
 * 
Case #1: IMPOSSIBLE
Case #2: YVYV
Case #3: RYRBYB
Case #4: IMPOSSIBLE
Case #5: BRGRBY
Case #6: YVYV
 * 
 * }</pre>
 */
public class ProblemB_Stable_Neighbors {
    
    static String uniqAdjacentLetters(int[] b, int[] r, int[] y) {
        char[] a = new char[b[1] + r[1] + y[1]];
        if (a.length == 0) return "";
        List<int[]> q = of(b, r, y)
                .filter(p -> p[1] > 0)
                .sorted((a1, a2) -> a2[1] - a1[1])
                .collect(Collectors.toList());
        IntPredicate odds = i -> (i % 2) == 0;
        int size = q.size();
        IntConsumer putNext = i -> {
            a[i] = (char)q.get(0)[0];
            q.get(0)[1]--;
            if (q.get(0)[1] == 0) q.remove(0);  
        };
        range(0, a.length)
            .filter(odds)
            .forEach(putNext);
        if (size == q.size()) return null;
        range(0, a.length)
            .filter(odds.negate())
            .forEach(putNext);
        if (!q.isEmpty()) return null;
        return new String(a);
    }
    
    static String insert(String s, String suffix) {
        if (suffix.isEmpty()) return s;
        int pos = s.indexOf(suffix.charAt(0));
        if (pos < 0) return s.isEmpty()? suffix: null;
        return s.substring(0, pos) + suffix + s.substring(pos);
    }
    
    static String arrange(int[] r, int[] o, int[] y, int[] g, int[] b, int[] v) {

        if (g[0] > r[0])
            return null;
        r[0] -= g[0];
        String rg = String.join("", Collections.nCopies(g[0], "RG"));
        
        if (v[0] > y[0])
            return null;
        y[0] -= v[0];
        String yv = String.join("", Collections.nCopies(v[0], "YV"));
        
        if (o[0] > b[0])
            return null;
        b[0] -= o[0];
        String bo = String.join("", Collections.nCopies(o[0], "BO"));

        String s = uniqAdjacentLetters(
                new int[]{'R', r[0]},
                new int[]{'Y', y[0]},
                new int[]{'B', b[0]});

        if (s == null || s.isEmpty()) {
            return of(rg, yv, bo)
                .filter(Predicate.isEqual(null).negate())
                .collect(joining());
        }
        
        for (String suff: Arrays.asList(rg, yv, bo)) {
            s = insert(s, suff);
            if (s == null) return null;            
        }
        
        return s.endsWith("" + s.charAt(0))? null: s;
    }
    
    public static void main(String[] args) throws IOException {
        String inputFile = ProblemB_Stable_Neighbors.class.getSimpleName() + ".in";
        boolean useResource = true;
        Scanner scanner = !useResource? new Scanner(Paths.get("/tmp/in")): 
            new Scanner(ProblemB_Stable_Neighbors.class.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int n = scanner.nextInt();
            String s = arrange(
                    new int[] {scanner.nextInt()},
                    new int[] {scanner.nextInt()},
                    new int[] {scanner.nextInt()},
                    new int[] {scanner.nextInt()},
                    new int[] {scanner.nextInt()},
                    new int[] {scanner.nextInt()}
            );
            System.out.format("Case #%d: %s\n", t + 1, 
                    (s == null || s.length() != n)? "IMPOSSIBLE": s);
        }
        scanner.close();
    }

}
