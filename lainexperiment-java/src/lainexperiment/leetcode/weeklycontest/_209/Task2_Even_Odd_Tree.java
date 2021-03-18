/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.leetcode.weeklycontest._209;

import static lainexperiment.leetcode.misc.TreeNode.asString;
import static lainexperiment.leetcode.misc.TreeNode.buildTree;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import lainexperiment.leetcode.misc.TreeNode;

/**
 * <pre>{@code
 * Date: 13/03/2020
 * 
 * Problem: Even Odd Tree
 * Status: accepted
 * 
 * A binary tree is named Even-Odd if it meets the following conditions:
 * 
 * - The root of the binary tree is at level index 0, its children are at
 *   level index 1, their children are at level index 2, etc.
 * - For every even-indexed level, all nodes at the level have odd integer
 *   values in strictly increasing order (from left to right).
 * - For every odd-indexed level, all nodes at the level have even integer
 *   values in strictly decreasing order (from left to right).
 *   
 * Given the root of a binary tree, return true if the binary tree is
 * Even-Odd, otherwise return false.
 *
 * Input
 * 
1,10,4,3,null,7,9,12,8,6,null,null,2
 * 
 * Output
 * 
true
 * 
 * }</pre>
 */
public class Task2_Even_Odd_Tree {

    public boolean isEvenOddTree(TreeNode root) {
        return isEvenOddTree(new LinkedList<>(List.of(root)), false);
    }
    
    public boolean isEvenOddTree(Queue<TreeNode> q, boolean isEven) {
        if (q.isEmpty()) return true;
        var qn = new LinkedList<TreeNode>();
        var cur = isEven? Integer.MAX_VALUE: Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            var n = q.poll();
            if (!isEven) {
                if (cur >= n.val) return false;
                if (n.val % 2 == 0) return false;
                if (n.left != null) qn.add(n.left);
                if (n.right != null) qn.add(n.right);
                cur = n.val;
            } else {
                if (n.val >= cur) return false;
                if (n.val % 2 == 1) return false;
                if (n.left != null) qn.add(n.left);
                if (n.right != null) qn.add(n.right);
                cur = n.val;
            }
        }
        return isEvenOddTree(qn, !isEven);
    }
    
    @Test
    public void test() {
        assertEquals(true, isEvenOddTree(buildTree(new Integer[] {11})));
        assertEquals(true, isEvenOddTree(buildTree(new Integer[] {11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17})));
        assertEquals(false, isEvenOddTree(buildTree(new Integer[] {5,9,1,3,5,7})));
        assertEquals(false, isEvenOddTree(buildTree(new Integer[] {5,4,2,3,3,7})));
        assertEquals(true, isEvenOddTree(buildTree(new Integer[] {1,10,4,3,null,7,9,12,8,6,null,null,2})));
        var t = buildTree(new Integer[] {1,10,4,3,null,7,9,12,8,6,null,null,2});
        System.out.println(asString(t));
    }

}
