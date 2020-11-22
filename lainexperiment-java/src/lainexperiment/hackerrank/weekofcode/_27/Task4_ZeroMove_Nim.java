/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.hackerrank.weekofcode._27;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.generate;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <pre>{@code
 * 
 * Date: 18/03/2017
 * 
 * Hacker rank
 * Week of code 27
 * Problem: Zero-Move Nim
 * Status: accepted
 * 
 * John and Kate modified Nim by adding the following rule, 
 * which they call a Zero-Move:
 * 
 * For each non-empty pile, either player can remove 0 items 
 * from that pile and have it count as their move; however, this 
 * move can only be performed once per pile by either player. 
 * For example, let's say pile i initially has items pi = 2 
 * in it. If John decides to use a Zero-Move on pile i, then 
 * neither John nor Kate can perform another Zero-Move on pile i; 
 * that said, either player is free to perform a Zero-Move on 
 * any other non-empty pile that hasn't had a Zero-Move 
 * performed on it yet.
 * 
 * John and Kate play g games of Zero-Move Nim. Given the number 
 * of items in each pile for each game, determine whether or 
 * not John can win the game if he always moves first and each 
 * player always moves optimally (i.e., never makes a move that 
 * causes them to lose if some better, winning move exists). 
 * For each game, print W on a new line if John can win; 
 * otherwise, print L instead.
 * 
 * Input Format
 * 
 * The first line contains an integer, g, denoting the number of 
 * games. The 2 * g subsequent lines describe each game over two 
 * lines:
 * 
 *  - The first line contains an integer, n, denoting the number 
 *  of heaps.
 *  - The second line contains n space-separated integers 
 *  describing p0, p1, ..., pn-1.
 * 
 * Output Format
 * 
 * For each game, print W on a new line if John will win; 
 * otherwise, print L instead.
 * 
 * 
 * Sample Input
 * 
2
2
1 2
2
2 2
 *
 * Sample Output
 * 
W
L
 *
 * }</pre>
 */
public class Task4_ZeroMove_Nim {
 
    static int[] P;
    
    static int grundy(int n) {
        return ((n & 1) == 0)? n - 1: n + 1;
    }
    
    static boolean canWin() {
        return stream(P)
                .map(Task4_ZeroMove_Nim::grundy)
                .reduce((a, b) -> a ^ b)
                .getAsInt() != 0;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        String inputFile = "Task4.in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): new Scanner(Task4_ZeroMove_Nim.class.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            int g = scanner.nextInt();
            for (int i = 0; i < g; i++) {
                P = generate(scanner::nextInt)
                        .limit(scanner.nextInt())
                        .toArray();
                System.out.println(canWin()? "W": "L");
            }
        }
        scanner.close();
    }

}
