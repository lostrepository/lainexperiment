/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._226;

import static lainexperiment.utils.Ival.intersects;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.Ival;

/**
 * <pre>{@code
 * Date: 30/01/2021
 * 
 * Problem: Can You Eat Your Favorite Candy on Your Favorite Day
 * Status: accepted
 * 
 * You are given a (0-indexed) array of positive integers
 * candiesCount where candiesCount[i] represents the number of
 * candies of the ith type you have. You are also given a 2D array
 * queries where queries[i] = [favoriteTypei, favoriteDayi, dailyCapi].
 * 
 * You play a game with the following rules:
 * 
 * - You start eating candies on day 0.
 * - You cannot eat any candy of type i unless you have eaten all
 * candies of type i - 1.
 * - You must eat at least one candy per day until you have eaten
 * all the candies.
 * 
 * Construct a boolean array answer such that
 * answer.length == queries.length and answer[i] is true if you can
 * eat a candy of type favoriteTypei on day favoriteDayi without
 * eating more than dailyCapi candies on any day, and false otherwise.
 * Note that you can eat different types of candy on the same day,
 * provided that you follow rule 2.
 * 
 * Return the constructed array answer.
 *
 * Input:
 * 
candiesCount = [7,4,5,3,8], queries = [[0,2,2],[4,2,4],[2,13,1000000000]]
 * 
 * Output
 * 
true,false,true
 * 
 * }</pre>
 */
public class Task3_Can_You_Eat_Your_Favorite_Candy_on_Your_Favorite_Day {
    
    private long[] PSUM;

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        PSUM = new long[candiesCount.length];
        PSUM[0] = candiesCount[0];
        for (int i = 1; i < PSUM.length; i++) {
            PSUM[i] = PSUM[i - 1] + candiesCount[i];
        }
        System.out.println(Arrays.toString(PSUM));
        boolean[] res = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = canEat(candiesCount, queries[i][0], queries[i][1], queries[i][2]);
        }
        return res;
    }
    
    private boolean canEat(int[] a, int i, int d, int s) {
        var i1 = new Ival(d + 1L, s * (d + 1L));
        var i2 = new Ival();
        if (i == 0) {
            i2.s = 1;
            i2.e = PSUM[0];
        } else {
            i2.s = PSUM[i - 1]+1;
            i2.e = PSUM[i];
        }
        return intersects(i1, i2);
    }

    @Test
    public void test() {
        assertArrayEquals(new boolean[] {true, false, true}, canEat(new int[] {7,4,5,3,8}, new int[][] {
            {0,2,2},
            {4,2,4},
            {2,13,1000000000}
        }));
        assertArrayEquals(new boolean[] {false,true,true,false,false}, canEat(new int[] {5,2,6,4,1}, new int[][] {
            {3,1,2},
            {4,10,3},
            {3,10,100},
            {4,100,30},
            {1,3,1},
        }));
        
        assertArrayEquals(new boolean[] {true,true,false,true}, canEat(new int[] {5,2,6,4,1}, new int[][] {
            {0,0,10},
            {0,0,1},
            {1,0,1},
            {1,0,10},
        }));
        assertArrayEquals(new boolean[] {true}, canEat(new int[] {5,2,6,4,1}, new int[][] {
            {1,1,5},
        }));
        assertArrayEquals(new boolean[] {true}, canEat(new int[] {5,2,6,4,1}, new int[][] {
            {1,4,5},
        }));

    }
}
