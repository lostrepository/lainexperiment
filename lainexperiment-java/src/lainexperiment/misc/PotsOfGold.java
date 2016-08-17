/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 14/05/2016
 * 
 * Problem
 * 
 * Pots of gold, each with varying number of coins are placed in
 * a single line. The rules of the game are:
 * 
 * - Players play turn by turn.
 * - On each turn a player can pick a pot of gold from either end
 * of the line. He gets all the gold in that pot. The next pot of
 * gold on that end is now available for picking.
 * 
 * What is the maximum number of gold can the first player get
 * considering that second player plays optimally.
 *
 * Input
 * 
12, 32, 4, 23, 6, 42, 16, 3, 85, 23, 4, 7, 3, 5, 45, 34, 2, 1
 * 
 * Output
 * 
177
 * 
 */

package lainexperiment.misc;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

import java.util.Stack;

public class PotsOfGold {

    static int[] max = new int[2];
    
    static void printAll(int[] pots, int s, int e, Stack<Integer> a, Stack<Integer> b) {
        if (s > e) {
            max[0] = max(max[0], a.stream().mapToInt(Integer::intValue).sum());
            System.out.println(a);
            max[1] = max(max[1], b.stream().mapToInt(Integer::intValue).sum());
            System.out.println(b);
            return;
        };
        a.add(pots[s]);
        printAll(pots, s + 1, e, b, a);
        a.pop();
        if (s == e) {
            return;
        }
        a.add(pots[e]);
        printAll(pots, s, e - 1, b, a);
        a.pop();
    }
    
    static int solve(int[] pots, int s, int e, boolean isFirst) {
        if (s > e) return 0;
        int l = solve(pots, s + 1, e, !isFirst);
        int r = solve(pots, s, e - 1, !isFirst);
        if (isFirst)
            return max(l + pots[s], r + pots[e]);
        else
            return min(l, r);
    }
    
    public static void main(String[] args)
    {
        int[] a = null;
        
        a = new int[]{12, 32, 4, 23, 6, 42, 16, 3, 85, 23, 4, 7, 3, 5, 45, 34, 2, 1};
        assertEquals(177, solve(a, 0, a.length - 1, true));
        
        a = new int[] {2,1,0};
        assertEquals(2, solve(a, 0, a.length - 1, true));

    }
    
}
