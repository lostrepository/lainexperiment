/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 23/03/2018
 * 
 * Problem
 * 
 * Generate random state of "connect four" game and print
 * the winner if any. 
 *
 * Input
 * 
 * none
 * 
 * Output
 * 
###x###+++++x#xx+x+x#x+++x+x+xx+xx++xx++xx +
 * 
 */

package lainexperiment.misc;

import static java.lang.String.format;
import static java.util.Arrays.fill;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Supplier;

public class Generate_connect_four {

    static int COLS = 7;
    static int ROWS = 6;
    static int[] x, y;
    static int X = 'x';
    static int Y = '+';
    
    static String generate() {
        int[][] m = new int[ROWS][COLS];
        x = range(0, COLS).toArray();
        y = new int[COLS];
        fill(y, 0);
        int c = ROWS;
        int ch = X;
        Random rand = new Random();
        Supplier<String> asString = () -> range(0, ROWS)
                .boxed()
                .sorted(Comparator.<Integer>naturalOrder().reversed())
                .<int[]>map(r -> m[r])
                .map(a -> new String(a, 0, a.length))
                .map(s -> s.replace((char) 0, '#'))
                //.peek(a -> out.println(a))
                .collect(joining());
        while (c >= 0) {
            int i = rand.nextInt(COLS);
            if (y[i] == ROWS) continue;
            m[y[i]][x[i]] = ch;
            if (win(m, x[i], y[i])) {
                return format("%s %s", asString.get(), (char)ch, y[i], x[i]);
            }
            ch = ch == X? Y: X;
            y[i]++;
            if (y[i] == ROWS) c--;
        }
        return asString.get();
    }
    
    static boolean win(int[][] m, int x, int y) {
        int ch = m[y][x];
        int c = 0;
        for (int i = x; i < COLS && m[y][i] == ch; i++)
            c++;
        if (c >= 4) return true;
        
        for (int i = x - 1; i >= 0 && m[y][i] == ch; i--)
            c++;
        if (c >= 4) return true;

        c = 0;
        for (int i = y; i >= 0 && m[i][x] == ch; i--)
            c++;
        if (c == 4) return true;

        return false;
    }

    public static void main(String[] args) {
        range(0, 1000).mapToObj(i -> generate()).forEach(System.out::println);
    }

}
