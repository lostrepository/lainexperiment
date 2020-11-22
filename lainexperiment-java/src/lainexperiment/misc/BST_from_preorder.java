/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import java.util.stream.IntStream;

/**
 * <pre>{@code
 * Date: 14/01/2015
 * 
 * Problem
 * 
 * Build BST from its preorder traversal.
 * 
 * Input
 * 
 * 7, 2, 1, 3, 4, 15, 11, 17
 * 
 * Output
 * 
 * 7
 *  2
 *   1
 *   3
 *    4
 *  15
 *   11
 *   17
 * 
 * }</pre>
 */
public class BST_from_preorder {

    static class Node {
        Node l, r;
        int v;
    }
    
    private static void print(Node n, int offset) {
        if (n == null) 
            return;
        IntStream.range(0, offset).forEach((i) -> System.out.print(' '));
        System.out.println(n.v);
        offset++;
        print(n.l, offset);
        print(n.r, offset);
    }
    
    static int i = 0;
    
    static Node bst(int[] a, Integer p) {
        if (i == a.length)
            return null;
        if (p != null && a[i] > p)
            return null;
        Node n = new Node();
        n.v = a[i];
        i++;
        n.l = bst(a, n.v);
        if (p != null && a[i] > p)
            return n;
        n.r = bst(a, p);
        return n;
    }
 
    public static void main(String[] args) {
        Node n = bst(new int[]{7, 2, 1, 3, 4, 15, 11, 17}, null);
        print(n, 0);
    }
}
