/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <pre>{@code
 * Date: 28/05/2018
 * 
 * Problem
 * 
 * You are to design a program to assist an architect in drawing 
 * the skyline of a city given the locations of the buildings in the city.
 * 
 * For city like that:
 *        ___
 *      _|_  |
 *     |  _|_|_
 *    _|_|_|_|_|_
 *   |   | | | | |
 * ----------------
 * 0 1 2 3 4 5 6 7 8
 * 
 * The skyline would be:
 *        ___
 *      _|   |
 *     |     |_
 *    _|       |_
 *   |           |
 * ----------------
 * 0 1 2 3 4 5 6 7 8
 * 
 * Input Format
 * 
 * The first line contains the number of buildings N.
 *   
 * The following lines contain building dimensions x1, x2, height
 *   
 * Output Format
 * 
 * The output must contain the skyline of the city
 * 
 * Input
 * 
5
1 7 1
3 6 2
4 6 2
2 4 3
3 5 4
 * 
 * Output
 * 
x1=1 x2=2 h=1
x1=2 x2=3 h=3
x1=3 x2=5 h=4
x1=5 x2=6 h=2
x1=6 x2=7 h=1
 * 
 * }</pre>
 */
public class Skyline {

    static class BoundingBox
    {
        int s, e, h;
        public BoundingBox(int s, int e, int h) {
            this.s = s; this.e = e; this.h = h;
        }
        @Override
        public String toString() {
            return format("x1=%d x2=%d h=%d", s, e, h);
        }
    }

    static class Side implements Comparable<Side> {
        int x, h;
        boolean isStart;
        public Side(int x, int h, boolean isStart) {
            this.x = x; this.h = h; this.isStart = isStart;
        }
        @Override
        public int compareTo(Side other) {
            if (x == other.x)
                return other.h - h;
            return x - other.x;
        }
    }

    static void solve(Queue<Side> sides) {
        List<BoundingBox> skyline = new LinkedList<>();
        SortedMap<Integer, Integer> heights = new TreeMap<>();
        BoundingBox cur = createPartially(sides.poll());
        heights.computeIfAbsent(cur.h, h -> 1);
        while (!sides.isEmpty()) {
            Side s = sides.poll();
            if (s.isStart) {
                heights.computeIfPresent(s.h, (h, c) -> c + 1);
                heights.computeIfAbsent(s.h, h -> 1);
                if (s.h < cur.h)
                    continue;
                cur.e = s.x;
                skyline.add(cur);
                cur = createPartially(s);
            } else {
                heights.computeIfPresent(s.h, (h, c) -> c - 1);
                if (heights.get(s.h) == 0)
                    heights.remove(s.h);
                if (s.h < cur.h)
                    continue;
                cur.e = s.x;
                if (cur.s != cur.e)
                    skyline.add(cur);
                if (!heights.isEmpty())
                    cur = createPartially(new Side(s.x, heights.lastKey(), false));
            }
        }
        skyline.forEach(System.out::println);
    }

    private static BoundingBox createPartially(Side s) {
        return new BoundingBox(s.x, -1, s.h);
    }

    public static void main(String[] args) {
        Class<?> clazz = Skyline.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
                new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            Queue<Side> q = new PriorityQueue<>();
            int n = scanner.nextInt();
            for (int i = 0; i < n; i++) {
                int x1 = scanner.nextInt();
                int x2 = scanner.nextInt();
                int h = scanner.nextInt();
                Side s1 = new Side(x1, h, true);
                Side s2 = new Side(x2, h, false);
                q.addAll(Arrays.asList(s1, s2));
            }
            solve(q);
        }
        scanner.close();
    }
}
