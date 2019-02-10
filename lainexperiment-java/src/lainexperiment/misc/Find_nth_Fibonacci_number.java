/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 09/02/2019
 * 
 * Problem
 * 
 * Knowing only two random fibo numbers Fi and Fj and their
 * indices i and j find fibo number with index n where i < j < n.
 * 
 * Input Format
 * 
 * i, Fi, j, Fj, n
 *   
 * Output Format
 * 
 * nth fibo number
 * 
 * Input
 * 
3, 2, 8, 21, 15
 * 
 * Output
 * 
610
 * 
 */

package lainexperiment.misc;

import java.util.Optional;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;


public class Find_nth_Fibonacci_number {

    static int[] tmp = new int[2];
    static int[] fibo;
    static int[] idx;

    static int nextFibo() {
        int t = tmp[0] + tmp[1];
        tmp[0] = tmp[1];
        tmp[1] = t;
        return t;
    }

    static int distance(int n) {
        tmp[0] = fibo[0];
        tmp[1] = n;
        int i = idx[0] + 1;
        while (i != idx[1]) {
            nextFibo();
            i++;
        }
        return fibo[1] - tmp[1];        
    }

    static int nth_fibo(int i, int fi, int j, int fj, int k) {
        if (j < i) return nth_fibo(i, fi, j, fj, k);
        fibo = new int[] {fi, fj};
        idx = new int[] {i, j};
        return bisection(fibo[0], fibo[1], Find_nth_Fibonacci_number::distance)
                .map(t -> fibo(k - j))
                .orElse(-1);
    }

    static int fibo(int k) {
        return IntStream.generate(Find_nth_Fibonacci_number::nextFibo)
                .limit(k)
                .max()
                .getAsInt();
    }

    static Optional<Integer> bisection(int lo, int hi, IntUnaryOperator cmp) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int res = cmp.applyAsInt(mid);
            if (res < 0)
                hi = mid;
            else if (res > mid)
                lo = mid + 1;
            else
                return Optional.of(res);
        }
        return Optional.empty();
    }

    @Test
    public void test() {
//        fibo = new int[] {13, 55};
//        idx = new int[] {7, 10};
//        System.out.println(distance(20));
        Assert.assertEquals(610, nth_fibo(3, 2, 8, 21, 15));
        Assert.assertEquals(832040, nth_fibo(3, 2, 25, 75025, 30));
    }
}
