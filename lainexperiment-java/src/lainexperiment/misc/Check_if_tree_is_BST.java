/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 28/01/2017
 * 
 * Problem
 * 
 * Check if tree is binary search tree (ignore balanced it or not).
 * 
 * Input
 * 
1, 2, 3, 4, 5, 6, 7, 8, 9, 1
 * 
 * Output
 * 
false
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;

import org.junit.Test;

public class Check_if_tree_is_BST {

    static class Node {
        Node l, r;
        int v;
        @Override
        public String toString() {
            return "" + v;
        }
    }

    static int c;
    static boolean isBstInternal(Node r) {
        if (r == null) return true;
        boolean lt = isBstInternal(r.l);
        if (!lt) return lt;
        if (c > r.v)
            return false;
        c = r.v;
        return isBstInternal(r.r);
    }

    static boolean isBst(Node t) {
        c = Integer.MIN_VALUE;
        return isBstInternal(t);
    }
    
    static void print(Node n, int offset) 
    {
        if (n == null) 
            return;
        IntStream.range(0, offset).forEach((i) -> 
            System.out.print(' '));
        System.out.println(n.v);
        offset++;
        print(n.l, offset);
        print(n.r, offset);
    }

    static Node makeBST(int[] a, 
            int s, int e) 
    {
        if (e >= a.length)
            return null;
        if (e < s) return null;
        int mid = (s + e) / 2;
        Node node = new Node();
        node.v = a[mid];
        node.l = makeBST(a, s, mid - 1);
        node.r = makeBST(a, mid + 1, e);
        return node;
    }
    
    static Node makeBST(int[] a) {
        return makeBST(a, 0, a.length - 1);
    }
    
    @Test
    public void test() {
        Node t = null;
        
        t = makeBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 12});
        print(t, 0);
        assertTrue(isBst(t));
        
        t = makeBST(new int[]{1, 2, 3, 4, 5, 6, 3, 8, 9, 12});
        print(t, 0);
        assertTrue(!isBst(t));

        t = makeBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 1});
        print(t, 0);
        assertTrue(!isBst(t));

        t = makeBST(new int[]{1, 2, 3, 4, 3, 6, 7, 8, 9, 12});
        print(t, 0);
        assertTrue(!isBst(t));

        t = makeBST(new int[]{1, 2, 3, 4, 5, 6, 7, 6, 9, 12});
        print(t, 0);
        assertTrue(!isBst(t));

        t = makeBST(new int[]{1, 2, 3, 4});
        print(t, 0);
        assertTrue(isBst(t));

        t = makeBST(new int[]{1, 2, 3, 2});
        print(t, 0);
        assertTrue(!isBst(t));

        t = makeBST(new int[]{1, 2, 3, 4, 3});
        print(t, 0);
        assertTrue(!isBst(t));

        t = makeBST(new int[]{1, 0, 3, 4, 5});
        print(t, 0);
        assertTrue(!isBst(t));

    }

}
