/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.worldcodesprint._7;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

class Ival {
    long s, e;
    public Ival(long s, long e) {
        this.s = s; 
        this.e = e;
    }
    @Override
    public String toString() {
        return String.format("%d %d", s, e);
    }
}

/**
 * <pre>{@code
 * 
 * Date: 25/09/2016
 * 
 * Hacker rank
 * World CodeSprint #7
 * Problem: Gridland Metro
 * Status: accepted
 * 
 * The city of Gridland is represented as an n x m matrix where 
 * the rows are numbered from 1 to n and the columns are numbered 
 * from 1 to m.
 * Gridland has a network of train tracks that always run in straight 
 * horizontal lines along a row. In other words, the start and end 
 * points of a train track are (r, c1) and (r, c2), where r represents 
 * the row number, c1 represents the starting column, and c2 represents 
 * the ending column of the train track. 
 * The mayor of Gridland is surveying the city to determine the number 
 * of locations where lampposts can be placed. A lamppost can be 
 * placed in any cell that is not occupied by a train track.
 * Given a map of Gridland and its k train tracks, find and print the 
 * number of cells where the mayor can place lampposts.
 * A train track may (or may not) overlap other train tracks within 
 * the same row. 
 * 
 * Input Format
 * 
 * The first line contains three space-separated integers describing 
 * the respective values of n (the number of rows), m (the number of 
 * columns), and k (the number of train tracks).
 * Each line i of the k subsequent lines contains three space-separated 
 * integers describing the respective values of r, c1, and c2 that 
 * define a train track.
 * 
 * Output Format
 * 
 * Print a single integer denoting the number of cells where the mayor
 * can install lampposts.
 * 
 * 
 * Sample Input
 * 
4 4 3
2 2 3
3 1 4
4 4 4
 *
 * Sample Output
 * 
9
 *
 * }</pre>
 */
public class Task3_Gridland_Metro {

    static long COLS, ROWS;
    static Map<Long, List<Ival>> IVALS;

    static long free(List<Ival> l) {
        l.sort((i1, i2) -> i1.s < i2.s? -1: 1);
        Ival ival = l.get(0);
        long c = 0;
        for (int i = 0; i < l.size(); ++i) {
            Ival cur = l.get(i);
            if (cur.e < ival.e)
                continue;
            if (cur.s <= ival.e)
                c += cur.s - ival.s;
            else
                c += ival.e - ival.s + 1;
            ival = cur;
        }
        c += ival.e - ival.s + 1;
        //System.out.format("%s %d]\n", l, c);
        return COLS - c;
    }

    static void solve() {
        //System.out.println(IVALS);
        long r = (ROWS - IVALS.size()) * COLS;
        r += IVALS.entrySet().stream()
            .map(e -> e.getValue())
            .mapToLong(l -> free(l))
            .sum();
        System.out.println(r);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task3_Gridland_Metro.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            ROWS = scanner.nextLong();
            COLS = scanner.nextLong();
            int k = scanner.nextInt();
            IVALS = range(0, k)
                .boxed()
                .collect(Collectors.groupingBy(
                    i -> scanner.nextLong(),
                    Collectors.mapping(i -> 
                        new Ival(scanner.nextLong(), 
                            scanner.nextLong()), 
                        toList())));
            solve();
        }
        scanner.close();
    }

}
