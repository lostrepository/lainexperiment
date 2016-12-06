/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 01/12/2016
 * 
 * Hacker rank
 * Week of code 26
 * Problem: Music on the Street
 * Status: accepted
 * 
 * There is a big music festival happening on Main street in 
 * Bytetown! We can consider this street to be along an infinite 
 * number line where, at every point on the line, some genre of 
 * music playing. More precisely, there are n points, 
 * a0, a1, ..., an-1, which are borders between different genres 
 * of music. So, at every point from -inf to +inf, the first genre 
 * of music is playing (say, Techno). At every point from a0 to 
 * a1, the second genre of music is playing (say, Disco). This 
 * continues until the last genre of music (say, House), which is 
 * playing from an-1 to +inf. All coordinates are given in miles. 
 * 
 * Conor wants to visit this event. He plans to walk exactly m 
 * miles at a constant speed of 1 mile per hour in the positive 
 * direction. For each genre of music he passes, he wants to 
 * listen to it for a minimum of hmin hours (to determine whether 
 * he likes it or not) and a a maximum of hmax hours (so he will 
 * not get bored). 
 * 
 * Given n integers denoting the respective border points for 
 * each music genre and the values of m, hmin, and hmax, find and 
 * print an integer denoting the optimal location for Conor to start 
 * his walk through the festival such that all of his above requirements 
 * are satisfied.
 * 
 * Input Format
 * 
 * The first line contains a single integer, n, denoting the number 
 * of border points.
 * The second line contains n distinct space-separated integers 
 * describing the respective values of a0, .., an-1.
 * The third line contains three space-separated integers describing 
 * the respective values of m, hmin, and hmax. 
 * 
 * Output Format
 * 
 * Print a single integer denoting the possible start point for Conor's 
 * walk. If there are several solutions, print any one of them.
 * 
 * 
 * Sample Input
 * 
2
1 3
7 2 3
 *
 * Sample Output
 * 
-2
 *
 */

package lainexperiment.hackerrank.weekofcode._26;

import static java.util.stream.LongStream.generate;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Music_on_the_Street {

    static long[] A;
    static long M, LO, HI;
    
    static long fits(int i, long len) {
        if (len > HI) return Long.MAX_VALUE;
        if (len <= 0) return A[i];
        if (i == 0) return Long.MAX_VALUE;
        if (A[i] - A[i - 1] >= len)
            return A[i] - len;
        return Long.MAX_VALUE;
    }
    
    static void fill() {
        long[] b = new long[A.length + 2];
        b[0] = A[0] - HI;
        b[b.length - 1] = A[A.length - 1] + HI;
        System.arraycopy(A, 0, b, 1, A.length);
        A = b;
    }
    
    static long solve() {
        fill();
        long d = 0;
        int s = 0;
        for (int i = 1; i < A.length; i++) {
            long t = A[i] - A[i - 1];
            if (LO <= t && t <= HI) {
                d += t;
                long k = fits(s, M - d);
                if (k != Long.MAX_VALUE)
                    return k;
            } else {
                if (t > HI)
                    d += HI;
                long k = fits(s, M - d);
                if (k != Long.MAX_VALUE)
                    return k;
                s = i;
                d = 0;
            }
        }
        throw new RuntimeException();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = Music_on_the_Street.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Music_on_the_Street.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            A = generate(scanner::nextLong)
                .limit(a)
                .toArray();
            M = scanner.nextLong();
            LO = scanner.nextLong();
            HI = scanner.nextLong();
            System.out.println(solve());
        }
        scanner.close();
    }

}
