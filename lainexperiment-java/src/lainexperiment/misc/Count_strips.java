/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

/**
 * <pre>{@code
 * Date: 14/01/2015
 * 
 * Problem
 * 
 * There are two kind of strips blue which can be only vertical and red which 
 * are only horizontal. When this strips crossed they produce green. 
 * Given a matrix with colors find minimum numbers of strips which are 
 * needed to color it.
 * 
 * Input
 * 
 * ..B..
 * ..GRR
 * ..B..
 * B....
 * B...G
 * 
 * Output
 * 
 * 5
 * 
 * }</pre>
 */
public class Count_strips {

    static void fill(char[][] a, char c, int i, int j, int di, int dj) {
        while (j < a[0].length && i < a.length && a[i][j] != '.') {
            if (a[i][j] == c)
                a[i][j] = '.';
            else
                a[i][j] = c == 'B'? 'R': 'B';
            i = i + di;
            j = j + dj;
        }
    }
    
    static int count(char[][] a) {
        int s = 0;
        for (int i = 0; i < a.length; ++i) {
            for (int j = 0; j < a[0].length; ++j) {
                if (a[i][j] == 'B' || a[i][j] == 'G') {
                    fill(a, 'B', i, j, 1, 0);
                    s++;
                }
                if (a[i][j] == 'R') {
                    fill(a, 'R', i, j, 0, 1);
                    s++;
                }                
            }
        }
        return s;
    }
    
    public static void main(String[] args){
        System.out.println(count(new char[][]{
                "..B..".toCharArray(),
                "..GRR".toCharArray(),
                "..B..".toCharArray(),
                "R....".toCharArray(),
                "R....".toCharArray(),
        }));
        System.out.println(count(new char[][]{
                "..B..".toCharArray(),
                "..GRR".toCharArray(),
                "..B..".toCharArray(),
                "B....".toCharArray(),
                "B...G".toCharArray(),
        }));
        System.out.println(count(new char[][]{
                "..B..".toCharArray(),
                "..GRR".toCharArray(),
                "..B..".toCharArray(),
                "B....".toCharArray(),
                "B..GG".toCharArray(),
        }));
        System.out.println(count(new char[][]{
                "GGGGG".toCharArray(),
                "GGGGG".toCharArray(),
        }));
    }
}
