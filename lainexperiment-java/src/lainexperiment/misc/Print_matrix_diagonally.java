/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

/**
 * <pre>{@code
 * Date: 07/02/2015
 * 
 * Problem
 * 
 * Print matrix diagonally
 *
 * Input
 * 
 * 1 2 3
 * 4 5 6
 * 7 8 9
 *
 * Output
 * 
 * 1
 * 2 4
 * 3 5 7
 * 6 8
 * 9 
 * 
 * }</pre>
 */
public class Print_matrix_diagonally {

    static void print(int[][] a) {
        for (int r = 0; r < a.length; ++r) {
            print(a, r, 0);
        }
        for (int c = 1; c < a[0].length; ++c) {
            print(a, a.length - 1, c);
        }
    }

    private static void print(int[][] a, int r, int c) {
        while (r >= 0 && c < a[0].length)
            System.out.print(a[r--][c++]);
        System.out.println();
    }

    public static void main(String[] args) {
        print(new int[][] {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        });
    }

}
