/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 16/08/2015
 * 
 * Problem
 * 
 * Fill matrix with numbers in zigzag order
 *
 * Input
 * 
5
 *
 * Output
 * 
[1,   3,  4, 10, 11]
[2,   5,  9, 12, 19]
[6,   8, 13, 18, 20]
[7,  14, 17, 21, 24]
[15, 16, 22, 23, 25]
 * 
 */

package misc;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FillMatrixInZigZagOrder {

    static int C = 1;
    
    static void print(int[][] a) {
        IntStream.range(0, a.length).forEach((i) -> System.out.println(Arrays.toString(a[i])));
    }
    
    static void fill(int[][] a, int r, int c, int rows, int cols, int d) {
        while (r >= 0 && r < rows &&
                c >= 0 && c < cols) {
            a[r][c] = C++;
            r += d;
            c += -d;
        }
    }

    static int[][] zigzag(int len) {
        int[][] a = new int[len][len];
        boolean down = true;
        for (int i = 0; i < len; ++i) {
            if (!down)
                fill(a, i, 0, len, len, -1);
            else
                fill(a, 0, i, len, len, 1);
            down = !down;
        }
        for (int i = 1; i < len; ++i) {
            if (!down)
                fill(a, len - 1, i, len, len, -1);
            else
                fill(a, i, len - 1, len, len, 1);
            down = !down;
        }
        return a;
    }
    
    public static void main(String[] args) {
        print(zigzag(5));
    }

}
