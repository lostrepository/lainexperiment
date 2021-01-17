/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._223;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 09/01/2021
 * 
 * Problem: Swapping Nodes in a Linked List
 * Status: accepted
 * 
 * You are given the head of a linked list, and an integer k.
 * 
 * Return the head of the linked list after swapping the
 * values of the kth node from the beginning and the kth node
 * from the end (the list is 1-indexed).
 *
 * Example 1:
 * 
head = [1,2,3,4,5], k = 2
 * 
 * Output
 * 
1,4,3,2,5
 * 
 * }</pre>
 */
public class Task2_Swapping_Nodes_in_a_Linked_List {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapNodes(ListNode head, int k) {
        ListNode first = null;
        var n = head;
        for (int i = 0; i < k - 1; i++) {
            n = n.next;
        }
        first = n;
        ListNode last = head;
        n = n.next;
        while (n != null) {
            last = last.next;
            n = n.next;
        }
        var t = first.val;
        first.val = last.val;
        last.val = t;
        return head;
    }

    List<Integer> toList(ListNode n) {
        var res = new ArrayList<Integer>();
        while (n != null) {
            res.add(n.val);
            n = n.next;
        }
        return res;
    }
    
    ListNode fromArray(int[] a) {
        ListNode n = new ListNode(a[0]);
        var head = n;
        for (int i = 1; i < a.length; i++) {
            n.next = new ListNode(a[i]);
            n = n.next;
        }
        return head;
    }

    @Test
    public void test() {
        assertEquals("[1, 4, 3, 2, 5]", toList(swapNodes(fromArray(new int[] {1,2,3,4,5}), 2)).toString());
        assertEquals("[7, 9, 6, 6, 8, 7, 3, 0, 9, 5]", toList(swapNodes(fromArray(new int[] {7,9,6,6,7,8,3,0,9,5}), 5)).toString());
        assertEquals("[1]", toList(swapNodes(fromArray(new int[] {1}), 1)).toString());
        assertEquals("[2, 1]", toList(swapNodes(fromArray(new int[] {1,2}), 1)).toString());
        assertEquals("[1, 2, 3]", toList(swapNodes(fromArray(new int[] {1,2,3}), 2)).toString());
    }
}
