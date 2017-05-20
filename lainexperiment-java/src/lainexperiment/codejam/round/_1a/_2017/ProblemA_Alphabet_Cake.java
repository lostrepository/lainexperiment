/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 07/06/2017
 *
 * Code jam 2017
 * Problem: Problem A. Alphabet Cake
 * Status: accepted
 * 
 * Problem
 * 
 * You are catering a party for some children, and you are 
 * serving them a cake in the shape of a grid with R rows 
 * and C columns. Your assistant has started to decorate 
 * the cake by writing every child's initial in icing on 
 * exactly one cell of the cake. Each cell contains at 
 * most one initial, and since no two children share the 
 * same initial, no initial appears more than once on the 
 * cake.
 *
 * Each child wants a single rectangular (grid-aligned) 
 * piece of cake that has their initial and no other child's 
 * initial(s). Can you find a way to assign every blank 
 * cell of the cake to one child, such that this goal is 
 * accomplished? It is guaranteed that this is always 
 * possible. There is no need to split the cake evenly 
 * among the children, and one or more of them may even 
 * get a 1-by-1 piece; this will be a valuable life 
 * lesson about unfairness. 
 * 
 * Input Format
 * 
 * The first line of the input gives the number of test 
 * cases, T. T test cases follow. Each begins with one 
 * line with two integers R and C. Then, there are R more 
 * lines of C characters each, representing the cake. Each 
 * character is either an uppercase English letter (which 
 * means that your assistant has already added that letter 
 * to that cell) or ? (which means that that cell is blank). 
 * 
 * Output Format
 * 
 * For each test case, output one line containing 
 * Case #x: and nothing else. Then output R more lines of C 
 * characters each. Your output grid must be identical to 
 * the input grid, but with every ? replaced with an 
 * uppercase English letter, representing that that cell 
 * appears in the slice for the child who has that initial. 
 * You may not add letters that did not originally appear 
 * in the input. In your grid, for each letter, the region 
 * formed by all the cells containing that letter must be 
 * a single grid-aligned rectangle.
 * 
 * If there are multiple possible answers, you may output 
 * any of them. 
 * 
 * Input
 * 
3
3 3
G??
?C?
??J
3 4
CODE
????
?JAM
2 2
CA
KE
 * 
 * Output
 * 
Case #1:
GGJ
CCJ
CCJ
Case #2:
CODE
COAE
JJAM
Case #3:
CA
KE
 * 
 */

package lainexperiment.codejam.round._1a._2017;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ProblemA_Alphabet_Cake {
    
    static char[][] A;

    static char[][] rotate(char[][] a, boolean toLeft) {
        char[][] b = new char[a[0].length][a.length];
        for (int r = 0; r < a.length; r++) {
            for (int c = 0; c < a[0].length; c++) {
                if (toLeft)
                    b[c][b[0].length - r - 1] = a[r][c];
                else
                    b[b.length - c - 1][r] = a[r][c];
            }
        }
        return b;
    }
    
    static void runWave() {
        for (int r = 0; r < A.length; r++) {
            char ch = '?';
            for (int c = 0; c < A[0].length; c++) {
                if (A[r][c] == '?') A[r][c] = ch;
                ch = A[r][c];
            }
        }
    }

    static void solve() {
        runWave();
        A = rotate(A, true);
        A = rotate(A, true);
        runWave();
        A = rotate(A, true);
        A = rotate(A, true);
        
        A = rotate(A, true);
        runWave();
        A = rotate(A, true);
        A = rotate(A, true);
        runWave();
        A = rotate(A, true);
        
        Arrays.stream(A)
            .map(String::new)
            .forEach(System.out::println);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = ProblemA_Alphabet_Cake.class.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(ProblemA_Alphabet_Cake.class.getResourceAsStream(inputFile));
        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            A = new char[scanner.nextInt()][scanner.nextInt()];
            Arrays.setAll(A, i -> scanner.next().toCharArray());
            System.out.format("Case #%d:\n", t + 1);
            solve();
        }
        scanner.close();
    }

}
