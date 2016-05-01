/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 28/05/2016
 * 
 * Problem
 * 
 * Matrix contains values 0 - room, B - obstacle and G - guard.
 * Need to calculate the steps from a room to nearest guard.
 *
 * Input
 * 
0 0 0
B G G
B 0 0
 * 
 * Output
 * 
2 1 1
B G G
B 1 1
 * 
 */

package lainexperiment.misc;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ShortPathToGuard {

    static int[][] A;
    static int B = -2, G = -1, X = Integer.MAX_VALUE;
    
    static IntPredicate isValidX = x -> x >= 0 && x < A[0].length;
    static IntPredicate isValidY = y -> y >= 0 && y < A.length;

    static void bfs(Point p, int radius, IntBinaryOperator oper) {
        BiConsumer<Integer, Integer> update = (x, y) -> {
            if (A[y][x] != B && A[y][x] != G)
                A[y][x] = oper.applyAsInt(x, y);
        };
        IntStream.rangeClosed(p.x - radius, p.x + radius)
            .filter(isValidX)
            .forEach(x -> 
                IntStream.of(p.y - radius, p.y + radius)
                    .filter(isValidY)
                    .forEach(y -> update.accept(x, y)));
        IntStream.of(p.x - radius, p.x + radius)
            .filter(isValidX)
            .forEach(x -> 
                IntStream.rangeClosed(p.y - radius, p.y + radius)
                    .filter(isValidY)
                    .forEach(y -> update.accept(x, y)));
    }
    
    static void bfs(Point p)
    {
        IntBinaryOperator fn = (x, y) ->
            A[y][x] = Math.min(A[y][x], Math.abs(p.x - x) + Math.abs(p.y - y));
        for (int r = 1; r < Math.max(A.length, A[0].length); ++r)
            bfs(p, r, fn);
    }
    
    static void solve() {
        for (int y = 0; y < A.length; y++)
        {
            for (int x = 0; x < A[0].length; x++)
            {
                if (A[y][x] == G)
                    bfs(new Point(x, y));
            }
        }
        Arrays.stream(A).map(Arrays::toString)
            .map(s -> s.replace("" + B, "B").replace("" + G, "G"))
            .forEach(System.out::println);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        A = new int[][]{
            {X, X, X, X, X, X},
            {B, G, G, X, X, X},
            {B, X, G, X, X, X},
        };
        solve();
    }

}
