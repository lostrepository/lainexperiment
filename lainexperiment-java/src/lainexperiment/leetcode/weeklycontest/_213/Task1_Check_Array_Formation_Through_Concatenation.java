/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._213;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 11/02/2020
 * 
 * Problem: Check Array Formation Through Concatenation
 * Status: accepted
 * 
 * You are given an array of distinct integers arr and an array
 * of integer arrays pieces, where the integers in pieces are
 * distinct. Your goal is to form arr by concatenating the arrays
 * in pieces in any order. However, you are not allowed to reorder
 * the integers in each array pieces[i].
 * 
 * Return true if it is possible to form the array arr from pieces.
 * Otherwise, return false.
 *
 * Input
 * 
arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task1_Check_Array_Formation_Through_Concatenation {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        var l = new ArrayList<List<Integer>>();
        for (int i = 0; i < pieces.length; i++) {
            l.add(Arrays.stream(pieces[i]).boxed().collect(toList()));
        }
        return canFormArray(arr, l, 0);
    }

    boolean canFormArray(int[] a, List<List<Integer>> p, int i) {
        if (i == a.length) return true;
        var l = new ArrayList<Integer>();
        for (int j = i; j < a.length; j++) {
            l.add(a[j]);
            if (p.contains(l)) {
                if (canFormArray(a, p, j + 1)) return true;
            }
        }
        return false;
    }
    
    @Test
    public void test() {
        assertEquals(true, canFormArray(new int[] {85}, new int[][]{{85}}));
        assertEquals(true, canFormArray(new int[] {15,88}, new int[][]{{88},{15}}));
        assertEquals(false, canFormArray(new int[] {49,18,16}, new int[][]{{16,18,49}}));
        assertEquals(true, canFormArray(new int[] {49,18,16}, new int[][]{{16},{18},{49}}));
        assertEquals(true, canFormArray(new int[] {91,4,64,78}, new int[][]{{78},{4,64},{91}}));
        assertEquals(false, canFormArray(new int[] {1,3,5,7}, new int[][]{{2,4,6,8}}));
    }
}
