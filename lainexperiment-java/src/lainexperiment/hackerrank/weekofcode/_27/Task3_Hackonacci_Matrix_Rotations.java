/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._27;

import static java.lang.Math.pow;
import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;
import static java.util.Arrays.setAll;
import static java.util.stream.IntStream.range;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * <pre>{@code
 * 
 * Date: 21/12/2016
 * 
 * Hacker rank
 * Week of code 27
 * Problem: Hackonacci Matrix Rotations
 * Status: accepted
 * 
 * We define a hackonacci series as follows:
 * 
 * hackonacci(n) = 1 * hackonacci(n - 1) 
 *   + 2 * hackonacci(n - 2)
 *   + 3 * hackonacci(n - 3)
 * hackonacci(1) = 1
 * hackonacci(2) = 2
 * hackonacci(3) = 3
 * 
 * We define a Hackonacci Matrix to be an n x n matrix where 
 * the rows and columns are indexed from 1 to n, and the 
 * top-left cell is (1, 1). Each cell (i, j) must contains 
 * either the character X or the character Y. If 
 * hackonacci((i * j)^2) is even, it's X; otherwise, it's Y.
 * Next, we want to perform q queries where each query i 
 * consists of an integer, anglei. Each anglei is a multiple 
 * of 90 degrees and describes the angle by which you must 
 * rotate the matrix in the clockwise direction. For each 
 * anglei, we want to count the number of cells that are 
 * different after the rotation. For example, the diagram 
 * below depicts the 270 rotation of a Hackonacci Matrix 
 * when n = 2:
 *  
 * YX    XX
 * XX -> YX
 * 
 * As you can see, there are two cells whose values change 
 * after the rotation. Note that we filled each initial 
 * cell using the Hackonacci formula given above:
 * 
 * (1, 1): hackonacci((1 * 1)^2) = hackonacci(1) = 1
 * Because this is an odd number, we mark this cell with a Y.
 * 
 * (1, 2): hackonacci((1 * 2)^2) = hackonacci(4)
 * => 1 * hackonacci(3) + 2 * hackonacci(2) + 3 * hackonacci(1)
 * => 1 * 3 + 2 * 2 + 3 * 1 = 10
 * Because this is an even number, we mark this cell with an X.
 * 
 * (2, 1): hackonacci((2 * 1)^2) = hackonacci(4) = 10
 * Because this is an even number, we mark this cell with an X.
 * 
 * (2, 2): hackonacci((2 * 2)^2) = hackonacci(16) = 296578
 * Because this is an even number, we mark this cell with an X.
 * 
 * Given the value of n and q queries, construct a 
 * Hackonacci Matrix and answer the queries. For each 
 * query i, print an integer on a new line denoting the 
 * number of cells whose values differ from the initial 
 * Hackonacci Matrix when it's rotated by anglei degrees 
 * in the clockwise direction. 
 * 
 * Input Format
 * 
 * The first line contains two space-separated integers 
 * describing the respective values of n and q.
 * Each line i of the q subsequent lines contains an 
 * integer denoting anglei.
 * 
 * Output Format
 * 
 * For each anglei, print a single integer on a new 
 * line denoting the number of different cells that 
 * differ between the initial matrix and the matrix 
 * rotated by anglei degrees.
 * 
 * 
 * Sample Input
 * 
4 3
90
180
270
 *
 * Sample Output
 * 
10
6
10
 *
 * }</pre>
 */
public class Task3_Hackonacci_Matrix_Rotations {
 
    static final BigInteger ZERO = BigInteger.ZERO;
    static final BigInteger TWO = BigInteger.valueOf(2);
    static final BigInteger THREE = BigInteger.valueOf(3);
    
    static boolean[][] M;
    
    static BigInteger hackonacci(int n) {
        if (n <= 3) return BigInteger.valueOf(n);
        BigInteger[] a = new BigInteger[n];
        a[0] = BigInteger.ONE;
        a[1] = TWO;
        a[2] = THREE;
        for (int i = 3; i < a.length; i++) {
            a[i] = a[i - 1].add(a[i - 2].multiply(a[1])).add(a[i - 3].multiply(a[2]));
        }
        return a[n - 1];
    }
    
    static boolean isEven(long n) {
//        System.out.println(n);
        boolean[] pre9 = {false, true, false, true, true, false, false, false};
        if (n < pre9.length) return pre9[(int)(n - 1)];
        boolean[] rest = {true, false, true, true, false, false, false};
        return rest[(int)((n - 9) % 7)];
    }
    
    static void analyze() {
        String s = range(1, 100).mapToObj(Task3_Hackonacci_Matrix_Rotations::hackonacci)
                .map(i -> "" + (i.remainder(TWO) == ZERO? "+": "-")/* + " " + i.toString()*/)
                .collect(Collectors.joining());
        String s2 = range(1, 100).mapToObj(Task3_Hackonacci_Matrix_Rotations::isEven)
                .map(i -> i? "+": "-")
                .collect(Collectors.joining());
        System.out.println(s);
        System.out.println(s2);
        System.out.println(s.equals(s2));
    }
    
    static void build() {
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                M[i][j] = isEven((long) pow((i + 1) * (j + 1), 2));
            }
        }
    }

    static boolean[][] R;
    static void rotateCW() 
    {
        int n = R.length;
        for (int layer = 0; layer < n / 2; ++layer) 
        {
            int first = layer;
            int last = n - 1 - layer;
            for(int i = first; i < last; ++i) 
            {
                int offset = i - first;
                // save top
                boolean top = R[first][i];
                // left -> top
                R[first][i] =
                        R[last-offset][first];    
                // bottom -> left
                R[last-offset][first] =
                        R[last][last - offset]; 
                // right -> bottom
                R[last][last - offset] =
                        R[i][last]; 
                // top -> right
                // right <- saved top
                R[i][last] = top;
            }
        }
    }
    
    static int[] MEMO;
    
    static void solve(int d) {
        d %= 360;
        int t = d / 90;
        if (MEMO[t] != -1) {
            System.out.println(MEMO[t]);
            return;
        }
        R = new boolean[M.length][];
        setAll(R, i -> copyOf(M[i], M[i].length));
        range(0, t)
            .forEach(i -> rotateCW());
        int c = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] != R[i][j]) c++;
            }
        }
        MEMO[t] = c;
        System.out.println(c);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "Task3.in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task3_Hackonacci_Matrix_Rotations.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            M = new boolean[n][];
            setAll(M, i -> new boolean[n]);
            MEMO = new int[4];
            fill(MEMO, -1);
            build();
            int q = scanner.nextInt();
            for (int i = 0; i < q; i++) {
                solve(scanner.nextInt());
            }
        }
        scanner.close();
    }

}
