/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.function.IntBinaryOperator;

class SegmentTree {
    
    private static final double LOG2 = Math.log(2);
    
    int NOVAL;
    int[] A;
    IntBinaryOperator merge;
    int[] ST;
    
    public SegmentTree(int[] a, int noval, IntBinaryOperator merge) {
        int d = (int) (Math.ceil(Math.log(a.length) / LOG2));
        ST = new int[(int) (2 * Math.pow(2, d))];
        NOVAL = noval;
        Arrays.fill(ST, NOVAL);
        A = a;
        this.merge = merge;
        build(0, 0, A.length - 1);
    }
    
    public int query(int l, int r) {
        return query(0, l, r, 0, A.length - 1);
    }
    
    public int update(int p, int v) {
        return update(0, p, v, 0, A.length - 1);
    }
    
    private int query(int n, int qs, int qe, int s, int e) {
        if (qe < s || qs > e)
            return NOVAL;
        if (s == e)
            return ST[n];
        if (qs <= s && e <= qe)
            return ST[n];
        int m = (s + e) / 2;
        return merge.applyAsInt(query(n * 2 + 1, qs, qe, s, m),
                query(n * 2 + 2, qs, qe, m + 1, e));
    }

    private int update(int n, int p, int v, int s, int e) {
        int m = (s + e) / 2;
        if (s == e && s == p) {
            ST[n] = v;
            return ST[n];
        }
        if (p <= m) {
            ST[n] = merge.applyAsInt(update(n * 2 + 1, p, v, s, m), ST[n * 2 + 2]);
            return ST[n];
        }
        ST[n] = merge.applyAsInt(update(n * 2 + 2, p, v, m + 1, e), ST[n * 2 + 1]);
        return ST[n];
    }

    private int build(int n, int s, int e) {
//        System.out.format("%d %d %d\n", s, e, n);
        if (s == e) {
            ST[n] = A[s];
            return ST[n];
        }
        int m = (s + e) / 2;
        int l = build(n * 2 + 1, s, m);
        int r = build(n * 2 + 2, m + 1, e);
        ST[n] = merge.applyAsInt(l, r);
        return ST[n];
    }
    
}

/**
 * <pre>{@code
 * Date: 10/07/2016
 * 
 * Problem
 * 
 * Given an array arr[0 .. n-1] implement operation which will 
 * efficiently find the minimum value from the given range [l, r].
 *
 * Input
 * 
1, 3, 5, 7, 1, 11, 0
3 - 5
 * 
 * Output
 * 
1
 * 
 * }</pre>
 */
public class RangeMinimumQuery {

    public static void main(String[] args) throws FileNotFoundException {
        SegmentTree st = new SegmentTree(new int[]{1, 3, 5, 7, 1, 11, 0}, 
                Integer.MAX_VALUE, (a, b) -> Math.min(a, b));

        assertEquals(1, st.query(3, 5));
        assertEquals(1, st.query(0, 5));
        assertEquals(0, st.query(0, 6));
        assertEquals(1, st.query(0, 3));
        assertEquals(1, st.query(1, 4));

        st.update(4, -1);
        assertEquals(-1, st.query(3, 5));
        assertEquals(-1, st.query(0, 5));
        assertEquals(-1, st.query(0, 6));
        assertEquals(1, st.query(0, 3));
        assertEquals(-1, st.query(1, 4));
        assertEquals(3, st.query(1, 3));
        
        st.update(3, 2);
        assertEquals(2, st.query(1, 3));
        
        st = new SegmentTree(new int[]{0, 0, 0, 0, 1}, 
                0, (a, b) -> a + b);
        assertEquals(0, st.query(0, 2));
        assertEquals(0, st.query(0, 3));
        assertEquals(0, st.query(1, 3));
        
        new SegmentTree(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 
                0, (a, b) -> a + b);
    }
    
}
