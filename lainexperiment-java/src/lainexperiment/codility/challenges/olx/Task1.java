/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codility.challenges.olx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lainexperiment.utils.Algorithms;

/**
 * <pre>{@code
 * 
 * Date: 14/11/2020
 * 
 * The OLX Challenge
 * Problem: Task 1 Multivitamin
 * Status: correctness 87%
 * 
 * Problem
 * 
 * Rick is really fond of fruit juices, but he is bored of their traditional
 * flavours. Therefore, he has decided to mix as many of them as possible to
 * obtain something entirely new as a result.
 * 
 * He has N glasses, numbered from 0 to N-1, each containing a different kind
 * of juice. The J-th glass has capacity[J] units of capacity and contains
 * juice[J] units of juice. In each glass there is at least one unit of juice.
 * 
 * Rick want to create a multivitamin mix in one of the glasses. He is going
 * to do it by pouring juice from several other glasses into the chosen one.
 * Each of the used glasses must be empty at the end (all of the juice from
 * each glass has to be poured out).
 * 
 * What is the maximum number of flavours that Rick can mix?
 * 
 * - N is an integer within the range [2..100,000];
 * - each element of arrays juice, capacity is an integer within the range [1..1,000,000,000];
 * - arrays juice and capacity have the same length, equal to N;
 * - for each J juice[J] ≤ capacity[J].
 * 
 * Sample Input
 * 
juice = [1, 1, 5]
capacity = [6, 5, 8],
 * 
 * Sample Output
 * 
3
 * 
 * Rick can mix all juices in the 2nd glass.
 * 
 * }</pre>
 */
public class Task1 {

    public int solution(int[] juice, int[] capacity) {
        return solution(IntStream.of(juice)
                .boxed()
                .collect(toList()), IntStream.of(capacity)
                .boxed()
                .collect(toList()));
    }

    int solution(List<Integer> juice, List<Integer> capacity) {
        // rank[i] - juice number with rank i
        List<Integer> rank = IntStream.range(0, juice.size())
                .boxed()
                .collect(toList());
        rank.sort((a, b) -> {
            return juice.get(a) - juice.get(b);
        });
        // ps[i] - sum of all juices from u(0..i)
        List<Integer> ps = new ArrayList<>(rank);
        int prev = 0;
        for (int i = 0; i < rank.size(); i++) {
            ps.set(i, prev + juice.get(rank.get(i)));
            prev = ps.get(i);
        }
        // max amount found so far
        int M = 0;
        for (int i = 0; i < rank.size(); i++) {
            // cur cup number
            int n = rank.get(i);
            int free = capacity.get(n) - juice.get(n);
            // max amount of juice in ith cap
            int max = free >= ps.get(i)? capacity.get(n): free;
            int k = (int) Algorithms.bisection(0, ps.size() - 1, j -> {
                if (ps.get((int) j) > max) return 1;
                return -1;
            });
            if (free == max) k++; 
            k++;
            M = Math.max(k, M);
        }
        return M;
    }

    public static int solve(int[] juice, int[] capacity) {
        return new Task1().solution(juice, capacity);
    }
    
    @Test
    public void test_solution() {
        assertEquals(2, solve(new int[] {10, 2, 1, 1}, new int[] {10, 3, 2, 2}));
        assertEquals(3, solve(new int[] {1, 2, 3, 4}, new int[] {3, 6, 4, 4}));
        assertEquals(1, solve(new int[] {2, 3}, new int[] {3, 4}));
        assertEquals(3, solve(new int[] {1, 1, 5}, new int[] {6, 5, 8}));
        assertEquals(2, solve(new int[] {1, 2}, new int[] {4, 3}));
        assertEquals(1, solve(new int[] {5, 3, 8}, new int[] {6, 4, 9}));
        assertEquals(1, solve(new int[] {5, 3, 8}, new int[] {6, 4, 9}));
    }
    
}
