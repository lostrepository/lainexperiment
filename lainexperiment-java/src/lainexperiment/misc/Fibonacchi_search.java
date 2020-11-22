/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.util.stream.IntStream.range;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 10/02/2017
 * 
 * Problem
 * 
 * Find value in sorted array using Fibonacchi search.
 * 
 * Input
 * 
1, 2, 3, 5, 6, 8, 9, 10, 11, 14, 20, 22, 23
10
 * 
 * Output
 * 
7
 * 
 * }</pre>
 */
public class Fibonacchi_search {
    
    static void update(int[] f) {
        int t = f[0] - f[1];
        f[0] = f[1];
        f[1] = t;
    }
    
    static int[] low_bound_fibo(int n) {
        int[] r = new int[2];
        r[0] = 1;
        int s = 0;
        while (r[0] + s < n) {
            r[1] = r[0];
            r[0] = s;
            s = r[0] + r[1];
        }
        return r;
    }
    
    static int fibo_search(int[] a, int i, int v, int[] f) {
        if (f[0] == 0)
            return a[i] == v? i: -1;
        if (a[i] == v) return i;
        if (a[i] > v)
            i -= f[0];
        else
            i += f[0];
        update(f);
        return fibo_search(a, i, v, f);
    }
    
    static int fibo_search(int[] a, int v) {
        int[] f = low_bound_fibo(a.length);
        int i = f[0] + f[1];
        if (v > a[i]) {
            return range(i, a.length)
                .filter(j -> a[j] == v)
                .findFirst().orElse(-1);
        }
        return fibo_search(a, i, v, f);
    }
    
    @Test
    public void test() {
        int[] a = {1, 2, 3, 5, 6, 8, 9, 10, 11, 14, 20, 22, 23};
        range(0, a.length)
            .peek(i -> System.out.println("Searching " + a[i]))
            .forEach(i -> assertEquals(i, fibo_search(a, a[i])));
    }

}
