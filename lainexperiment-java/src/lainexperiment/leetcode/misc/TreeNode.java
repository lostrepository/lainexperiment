/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.leetcode.misc;

import static java.util.stream.Collectors.joining;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Extension of TreeNode from leetcode.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return "" + val;
    }
    
    /**
     * Converts tree to its string representation.
     */
    public static String asString(TreeNode n) {
        var buf = new StringBuilder();
        buildString(n, buf, 0);
        return buf.toString();
    }

    /**
     * Builds tree from the array
     */
    public static TreeNode buildTree(Integer[] a) {
        var r = new TreeNode(a[0]);
        buildTree(a, List.of(r), 1);
        return r;
    }

    private static void buildString(TreeNode n, StringBuilder buf, int offset) {
        if (n == null) return;
        buf.append(Collections.nCopies(offset, " ").stream()
            .collect(joining()));
        buf.append(n.val + "\n");
        buildString(n.left, buf, offset + 1);
        buildString(n.right, buf, offset + 1);
    }
    
    private static void buildTree(Integer[] a, List<TreeNode> q, int i) {
        if (i >= a.length) return;
        var nq = new LinkedList<TreeNode>();
        for (var n: q) {
            if (a[i] != null) {
                var l = new TreeNode(a[i]);
                nq.add(l);
                n.left = l;
            }
            if (++i == a.length) return;
            if (a[i] != null) {
                var r = new TreeNode(a[i]);
                nq.add(r);
                n.right = r;
            }
            if (++i == a.length) return;
        }
        buildTree(a, nq, i);
    }
}