/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codility.challenges.silver._2020;

import static java.util.stream.Collectors.counting;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <pre>{@code
 * 
 * Date: 18/10/2020
 * 
 * The Silver Challenge 
 * Problem: Task 1 Rectangles Strip
 * Status: 70% TIMEOUT ERROR ( 0.132 sec., time limit: 0.100 sec.)
 * 
 * Problem
 * 
 * There are N rectangles numbered from 0 to N-1. The K-th rectangle has size A[K] × B[K].
 * 
 * We want to arrange as many rectangles as possible into a strip. The rectangles can be
 * arranged into a strip if they all share a side of the same length (which becomes the
 * height of the strip). Note that rectangles can be rotated.
 * 
 * Write a function:
 * 
 *     class Solution { public int solution(int[] A, int[] B); }
 * 
 * that, given two arrays A and B of N integers each, returns the maximum number of
 * rectangles that can be arranged into a strip.
 * 
 * Examples:
 * 
 * 1. Given A = [2, 3, 2, 3, 5] and B = [3, 4, 2, 4, 2], the function should return 3.
 * Choosing the 0th, 2nd and 4th rectangles we can obtain the strip of height 2 (note
 * that the 0th rectangle is rotated).
 * 
 * We can also choose the 0th, 1st and 3rd rectangles to obtain a strip of height 3.
 * Here we have rotated 1st and 3rd rectangles.
 * 
 * 2. Given A = [2, 3, 1, 3] and B = [2, 3, 1, 3], the function should return 2.
 * We can choose the 1st and 3rd rectangles.
 * 
 * 3. Given A = [2, 10, 4, 1, 4] and B = [4, 1, 2, 2, 5], the function should return 3.
 * We can choose the 0th, 2nd and 4th rectangles to obtain a strip of height 4.
 * 
 * Write an efficient algorithm for the following assumptions:
 * 
 * - N is an integer within the range [1..100,000];
 * - arrays A and B both consist of N integers;
 * - each element of arrays A, B is an integer within the range [1..1,000,000,000].
 * 
 * }</pre>
 */
public class Task1_RectanglesStrip {

    public int solution(int[] A, int[] B) {
        Map<Integer, Long> histogram = IntStream.concat(IntStream.of(A), IntStream.of(B))
            .boxed()
            .collect(Collectors.groupingBy(Integer::intValue, counting()));
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i])
                histogram.compute(A[i], (k, v) -> v - 1);
        }
        return histogram.entrySet().stream()
            .sorted(Comparator.comparingLong(Map.Entry<?, Long>::getValue).reversed())
            .findFirst()
            .get()
            .getValue()
            .intValue();
    }

    public static int solve(int[] A, int[] B) {
        return new Task1_RectanglesStrip().solution(A, B);
    }
    
    public static void main(String[] args) {
        assertEquals(3, solve(new int[]{2, 3, 2, 3, 5}, new int[]{3, 4, 2, 4, 2}));
        assertEquals(2, solve(new int[]{2, 3, 1, 3}, new int[]{2, 3, 1, 3}));
        assertEquals(3, solve(new int[]{2, 10, 4, 1, 4}, new int[]{4, 1, 2, 2, 5}));
        assertEquals(1, solve(new int[]{2}, new int[]{4}));
        assertEquals(2, solve(new int[]{1, 1}, new int[]{2, 2}));
    }
    
}
