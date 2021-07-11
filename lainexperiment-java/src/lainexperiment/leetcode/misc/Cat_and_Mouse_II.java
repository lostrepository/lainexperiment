/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.GridUtils;
import lainexperiment.utils.tuples.TupleInt;

/**
 * <pre>{@code
 * Date: 25/02/2021
 * 
 * Problem: Cat and Mouse II
 * Status: accepted
 * 
 * A game is played by a cat and a mouse named Cat and Mouse.
 * 
 * The environment is represented by a grid of size rows x cols, where
 * each element is a wall, floor, player (Cat, Mouse), or food.
 * 
 * - Players are represented by the characters 'C'(Cat),'M'(Mouse).
 * - Floors are represented by the character '.' and can be walked on.
 * - Walls are represented by the character '#' and cannot be walked on.
 * - Food is represented by the character 'F' and can be walked on.
 * - There is only one of each character 'C', 'M', and 'F' in grid.
 * 
 * Mouse and Cat play according to the following rules:
 * 
 * - Mouse moves first, then they take turns to move.
 * - During each turn, Cat and Mouse can jump in one of the four directions
 *   (left, right, up, down). They cannot jump over the wall nor outside of
 *   the grid.
 * - catJump, mouseJump are the maximum lengths Cat and Mouse can jump at
 *   a time, respectively. Cat and Mouse can jump less than the maximum length.
 * - Staying in the same position is allowed.
 * - Mouse can jump over Cat.
 * 
 * The game can end in 4 ways:
 * 
 * - If Cat occupies the same position as Mouse, Cat wins.
 * - If Cat reaches the food first, Cat wins.
 * - If Mouse reaches the food first, Mouse wins.
 * - If Mouse cannot get to the food within 1000 turns, Cat wins.
 * 
 * Given a rows x cols matrix grid and two integers catJump and mouseJump,
 * return true if Mouse can win the game if both Cat and Mouse play
 * optimally, otherwise return false.
 * 
 *
 * Input:
 * 
grid = ["####F",
        "#C...",
        "M...."]
catJump = 1, mouseJump = 2
 * 
 * Output
 * 
true
 * 
 * Input:
 * 
grid = [".......",
        "F..#C.M",
        "......."]
catJump = 1, mouseJump = 2
 *
 * Output
 * 
 true
 *
 * Here mouse can win by jumping up/down causing cat to follow
 * and then jumping backward and escaping the cat.
 * 
 * }</pre>
 */
public class Cat_and_Mouse_II {

    private int cols;
    private int rows;
    private char[][] G;
    private TupleInt cat;
    private TupleInt mouse;
    private int mlen, clen;
    private Boolean[][][][][][] M;
    private int step;
    private boolean isDebug = false;
    private int STEPS = 100;

    /**
     * DFS based solution with memoization
     */
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        cols = grid[0].length();
        rows = grid.length;
        G = new char[rows][cols];
        cat = null;
        mouse = null;
        mlen = mouseJump;
        clen = catJump;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length(); c++) {
                G[r][c] = grid[r].charAt(c);
                if (G[r][c] == 'M') {
                    mouse = new TupleInt(r, c);
                }
                if (G[r][c] == 'C') {
                    cat = new TupleInt(r, c);
                }
            }
        }
        step = 0;
        M = new Boolean[rows][cols][rows][cols][STEPS][2];
        return canWin(true);
    }

    List<TupleInt> findJumps(char[][] g, int r, int c, int len) {
        var cols = g[0].length;
        var rows = g.length;
        var res = new ArrayList<TupleInt>();
        res.add(new TupleInt(r, c));
        int[] x = {0, 0, 1, -1};
        int[] y = {1, -1, 0, 0};
        for (int k = 0; k < 4; k++) {
            for (int i = 1; i <= len; i++) {
                var next = new TupleInt(r + i * y[k], c + i * x[k]);
                if (next.a() < 0 || next.a() >= rows || next.b() < 0 || next.b() >= cols) 
                    break;
                if (g[next.a()][next.b()] == '#') break;
                res.add(next);
            }
        }
        return res;
    }
    
    boolean canWin(boolean isMouse) {
        if (M[mouse.a()][mouse.b()][cat.a()][cat.b()][step][isMouse? 0: 1] != null)
            return M[mouse.a()][mouse.b()][cat.a()][cat.b()][step][isMouse? 0: 1];
        if (isDebug ) {
            System.out.println("Step " + step);
            GridUtils.print(G);
            try { System.in.read(); } catch (IOException e) { e.printStackTrace(); }
        }
        if (step + 1 >= STEPS) {
            return false;
        }
        step++;
        TupleInt pos = isMouse? mouse: cat;
        G[pos.a()][pos.b()] = '.';
        boolean canMouseWin = false;
        List<TupleInt> jumps = null;
        jumps = findJumps(G, pos.a(), pos.b(), isMouse? mlen: clen);
        for (var jump: jumps) {
            // if cat finds food - mouse lose
            // if mouse finds food - it wins
            if (G[jump.a()][jump.b()] == 'F') {
                canMouseWin = isMouse;
                break;
            }
            // if there is a cat then it is mouse turn and it lose
            if (G[jump.a()][jump.b()] == 'C') {
                continue;
            }
            // if there is a mouse then it is cat turn and it wins
            if (G[jump.a()][jump.b()] == 'M') {
                canMouseWin = false;
                break;
            }
            // free spot - let's try
            if (G[jump.a()][jump.b()] == '.') {
                if (isMouse)
                    mouse = jump;
                else
                    cat = jump;
                G[jump.a()][jump.b()] = isMouse? 'M': 'C';
                canMouseWin = canWin(!isMouse);
                G[jump.a()][jump.b()] = '.';
                if (isMouse)
                    mouse = pos;
                else
                    cat = pos;
                if (isMouse && canMouseWin)
                    break;
                if (!isMouse && !canMouseWin)
                    break;
            }
        }
        G[pos.a()][pos.b()] = isMouse? 'M': 'C';
        if (isDebug) {
            System.out.println();
            System.out.println("Terminal step " + step);
            System.out.println("isMouse: " + isMouse);
            System.out.println("canMouseWin: " + canMouseWin);
        }
        step--;
        M[mouse.a()][mouse.b()][cat.a()][cat.b()][step][isMouse? 0: 1] = canMouseWin;
        return canMouseWin;
    }

    @Test
    public void test() {

        assertEquals(true, canMouseWin(new String[]{
            "........#",
            ".M.......",
            ".........",
            "....##...",
            "######..F",
            "######...",
            "....##...",
            ".........",
            ".C.......",
            "........#",}, 1, 2));

        assertEquals(true, canMouseWin(new String[]{
            "..#C",
            "...#",
            "..M.",
            "#F..",
            "...."}, 2, 1));

        assertEquals(false, canMouseWin(new String[]{
            "#..C...",
            "M....#.",
            "######.",
            "######.",
            "######.",
            "######F"}, 1, 5));

        assertEquals(false, canMouseWin(new String[]{
            "C...F",
            "M...#"}, 1, 2));

        // cat can reach food
        assertEquals(false, canMouseWin(new String[]{
            "......",
            "F.#C.M",
            "......"}, 1, 2));

        assertEquals(true, canMouseWin(new String[]{
            ".......",
            "F..#C.M",
            "......."}, 1, 2));

        assertEquals(true, canMouseWin(new String[]{
            "####F",
            ".C#..",
            "##M.."}, 1, 1));
        
        assertEquals(true, canMouseWin(new String[]{
            ".M...",
            "..#..",
            "#..#.",
            "C#.#.",
            "...#F"}, 3, 1));
        
        assertEquals(true, canMouseWin(new String[]{
            "####F",
            "#C...",
            "M...."}, 1, 2));
        
        assertEquals(false, canMouseWin(new String[]{
            "M.C...F"}, 1, 3));
        
        assertEquals(false, canMouseWin(new String[]{
            "C...#",
            "...#F",
            "....#",
            "M...."}, 2, 5));
        
        assertEquals(true, canMouseWin(new String[]{
            "####F",
            "#C.M.",
            "....."}, 1, 2));
    }
}
