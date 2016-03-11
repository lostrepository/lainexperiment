/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 24/03/2015
 * 
 * Problem
 * 
 * You have been given the job of forming the quiz teams for the next ‘MCA CPCI Quiz Championship’. 
 * There are 2*N students interested to participate and you have to form N teams, each team consisting
 * of two members. Since the members have to practice together, all the students want their member’s 
 * house as near as possible. Let x1 be the distance between the houses of group 1, x2 be the distance 
 * between the houses of group 2 and so on. You have to make sure the summation (x1 + x2 + x3 + ... + xn)
 * is minimized.
 *
 * Input
 * 
 * There will be many cases in the input file. Each case starts with an integer N (N <= 8). The next 
 * 2*N lines will given the information of the students. Each line starts with the student’s name, followed 
 * by the x coordinate and then the y coordinate. Both x, y are integers in the range 0 to 1000. Students 
 * name will consist of lowercase letters only and the length will be at most 20.
 * Input is terminated by a case where N is equal to 0.
 * 
 * Output
 * 
 * For each case, output the case number followed by the summation of the distances, rounded to 2 decimal 
 * places. Follow the sample for exact format.
 *
 *
 * Sample
 * 
 * Input
 *
 * 5
 * sohel 10 10
 * mahmud 20 10
 * sanny 5 5
 * prince 1 1
 * per 120 3
 * mf 6 6
 * kugel 50 60
 * joey 3 24
 * limon 6 9
 * manzoor 0 0
 * 1
 * derek 9 9
 * jimmy 10 10
 * 0
 * 
 * Output
 * 
 * Case 1: 118.40
 * Case 2: 1.41
 * 
 */

package misc;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FormingQuizTeams {

    static float split(int[] X, int[] Y, BitSet S, int n, float sum, Map<String, Float> M) {
        if (n == X.length) {
//            System.out.println(sum);
            return sum;
        }
        if (S.get(n))
            return split(X, Y, S, n + 1, sum, M);
        String key = n + S.toString();
        if (M.containsKey(key))
            return sum + M.get(key);
        float min = Float.MAX_VALUE;
        S.set(n);
        int i = 0;
        while ((i = S.nextClearBit(i + 1)) != X.length) {
            S.set(i);
            float m = split(X, Y, S, n + 1, sum + (float)Point2D.distance(X[i], Y[i], X[n], Y[n]), M);
            S.clear(i);
            min = Math.min(m, min);
        }
        S.clear(n);
        M.put(key, min - sum);
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner s = new Scanner(new File("/tmp/in"));
        int t = 1;
        while (s.hasNext()) {
            int N = s.nextInt();
            if (N == 0)
                break;
            int[] X = new int[N * 2];
            int[] Y = new int[N * 2];
            IntStream.range(0, N * 2).forEach((i) -> {s.next(); X[i] = s.nextInt(); Y[i] = s.nextInt();});
            System.out.format("Case %d: %f\n", t++, split(X, Y, new BitSet(N * 2), 0, 0, new HashMap<>()));
        }
        s.close();
    }

}
