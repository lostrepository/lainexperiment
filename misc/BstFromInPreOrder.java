/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 14/01/2015
 * 
 * Problem
 * 
 * Build BST from its inorder and preorder traversals.
 * 
 * Input
 * 
 * 2, 8, 4, 14, 1, 7, 16, 9, 10, 3 
 * 16, 14, 8, 2, 4, 7, 1, 10, 9, 3
 * 
 * Output
 * 
 * 16
 *  14
 *   8
 *    2
 *    4
 *   7
 *    1
 *  10
 *   9
 *   3
 * 
 */

package misc;

import java.util.stream.IntStream;

public class BstFromInPreOrder {
    
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
    
    static int pi = 0;
    
    static Node buildTree(int[] in, int[] pre, int s, int e) {
        Node n = new Node();
        n.v = pre[pi++];
        for (int i = s; i <= e; ++i) {
            if (in[i] == n.v) {
                if (i > s)
                    n.l = buildTree(in, pre, s, i - 1);
                if (i < e)
                    n.r = buildTree(in, pre, i + 1, e);
                return n;
            }
        }
        return n;
    }
    
    public static void main(String[] args) {
        Node n = buildTree(new int[]{2, 8, 4, 14, 1, 7, 16, 9, 10, 3}, 
                new int[]{16, 14, 8, 2, 4, 7, 1, 10, 9, 3}, 0, 9);
        print(n, 0);
    }
    
}
