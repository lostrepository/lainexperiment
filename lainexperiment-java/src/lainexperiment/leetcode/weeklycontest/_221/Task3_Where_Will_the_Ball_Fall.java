/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._221;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 12/26/2020
 * 
 * Problem: Where Will the Ball Fall
 * Status: accepted
 * 
 * You have a 2-D grid of size m x n representing a box, and
 * you have n balls. The box is open on the top and bottom sides.
 * 
 * Each cell in the box has a diagonal board spanning two corners
 * of the cell that can redirect a ball to the right or to the left.
 * 
 * - A board that redirects the ball to the right spans the top
 * left corner to the bottom-right corner and is represented in the
 * grid as 1.
 * 
 * - A board that redirects the ball to the left spans the top
 * right corner to the bottom-left corner and is represented in
 * the grid as -1.
 * 
 * We drop one ball at the top of each column of the box. Each
 * ball can get stuck in the box or fall out of the bottom. A
 * ball gets stuck if it hits a "V" shaped pattern between two
 * boards or if a board redirects the ball into either wall of
 * the box.
 * 
 * Return an array answer of size n where answer[i] is the column
 * that the ball falls out of at the bottom after dropping the ball
 * from the ith column at the top, or -1 if the ball gets stuck in
 * the box.
 *
 * Example 1:
 * 
[ 1  1  1 -1 -1],
[ 1  1  1 -1 -1],
[-1 -1 -1  1  1],
[ 1  1  1  1 -1],
[-1 -1 -1 -1 -1]
 * 
 * Output
 * 
[1,-1,-1,-1,-1]
 * 
 * }</pre>
 */
public class Task3_Where_Will_the_Ball_Fall {

    public int[] findBall(int[][] grid) {
        return IntStream.range(0, grid[0].length)
            .map(i -> findExit(i, grid))
            .toArray();
    }

    public int findExit(int i, int[][] grid) {
        for (int j = 0; j < grid.length; j++) {
            if (grid[j][i] == 1) {
                if (i + 1 == grid[j].length)
                    return -1;
                if (grid[j][i + 1] == -1)
                    return -1;
                i++;
            } else if (grid[j][i] == -1) {
                if (i - 1 < 0)
                    return -1;
                if (grid[j][i - 1] == 1)
                    return -1;
                i--;
            }
        }
        return i;
    }

    @Test
    public void test() {
        assertEquals("[1, -1, -1, -1, -1]", Arrays.toString(findBall(new int[][] {
            {1,1,1,-1,-1},
            {1,1,1,-1,-1},
            {-1,-1,-1,1,1},
            {1,1,1,1,-1},
            {-1,-1,-1,-1,-1}
        })));
        assertEquals("[-1]", Arrays.toString(findBall(new int[][] {
            {-1}
        })));
    }
}
