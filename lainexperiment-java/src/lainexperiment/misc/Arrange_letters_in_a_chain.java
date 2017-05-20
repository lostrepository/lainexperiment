/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 20/05/2017
 * 
 * Problem
 * 
 * Given input string S arrange it so that there will 
 * be no adjacent equal letters.
 *
 * Input
 * 
aaaabbbbbcc
 * 
 * Output
 * 
babababcbca
 * 
 */

package lainexperiment.misc;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.of;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;

public class Arrange_letters_in_a_chain {

    static String arrangeLinearitmic(int[] b, int[] r, int[] y) {
        Comparator<int[]> cmp = (a1, a2) -> a2[1] - a1[1];
        Queue<int[]> q = of(b, r, y)
            .filter(p -> p[1] != 0)
            .collect(toCollection(() -> new PriorityQueue<>(cmp)));
        StringBuilder res = new StringBuilder();
        while (q.size() > 1) {
            Stream.of(q.poll(), q.poll())
                .peek(p -> {
                    res.append((char)p[0]);
                    p[1]--;
                })
                .filter(p -> p[1] != 0)
                .forEach(q::add);
        }
        if (!q.isEmpty() && q.peek()[1] > 1)
            return null;
        if (!q.isEmpty())
            res.append((char)q.peek()[0]);
        return res.toString();
    }

    static String arrangeLinear(int[] b, int[] r, int[] y) {
        char[] a = new char[b[1] + r[1] + y[1]];
        if (a.length == 0) return "";
        List<int[]> q = of(b, r, y)
                .filter(p -> p[1] > 0)
                .sorted((a1, a2) -> a2[1] - a1[1])
                .collect(Collectors.toList());
        IntPredicate odds = i -> (i % 2) == 0;
        int size = q.size();
        IntConsumer putNext = i -> {
            a[i] = (char)q.get(0)[0];
            q.get(0)[1]--;
            if (q.get(0)[1] == 0) q.remove(0);  
        };
        range(0, a.length)
            .filter(odds)
            .forEach(putNext);
        if (size == q.size()) return null;
        range(0, a.length)
            .filter(odds.negate())
            .forEach(putNext);
        if (!q.isEmpty()) return null;
        return new String(a);
    }

    public static void main(String[] args) {
        Assert.assertEquals("bababacbabc", arrangeLinearitmic( 
                new int[]{'a', 4}, new int[]{'b', 5}, new int[]{'c', 2}));
        Assert.assertEquals("bcbababcb", arrangeLinearitmic( 
                new int[]{'a', 2}, new int[]{'b', 5}, new int[]{'c', 2}));
        Assert.assertEquals(null, arrangeLinearitmic( 
                new int[]{'a', 1}, new int[]{'b', 5}, new int[]{'c', 2}));
        Assert.assertEquals("a", arrangeLinearitmic( 
                new int[]{'a', 1}, new int[]{'b', 0}, new int[]{'c', 0}));
        Assert.assertEquals("abababcbabca", arrangeLinearitmic( 
                new int[]{'a', 5}, new int[]{'b', 5}, new int[]{'c', 2}));
        
        Assert.assertEquals("babababcbca", arrangeLinear( 
                new int[]{'a', 4}, new int[]{'b', 5}, new int[]{'c', 2}));
        Assert.assertEquals("bababcbcb", arrangeLinear( 
                new int[]{'a', 2}, new int[]{'b', 5}, new int[]{'c', 2}));
        Assert.assertEquals(null, arrangeLinear( 
                new int[]{'a', 1}, new int[]{'b', 5}, new int[]{'c', 2}));
        Assert.assertEquals("a", arrangeLinear( 
                new int[]{'a', 1}, new int[]{'b', 0}, new int[]{'c', 0}));
        Assert.assertEquals("ababababacbc", arrangeLinear( 
                new int[]{'a', 5}, new int[]{'b', 5}, new int[]{'c', 2}));
    }

}
