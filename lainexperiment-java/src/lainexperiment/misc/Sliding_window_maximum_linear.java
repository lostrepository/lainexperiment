/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 27/01/2019
 * 
 * Problem
 * 
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right. You can only see the k
 * numbers in the window. Each time the sliding window moves right by one
 * position. Return the max sliding window.
 * 
 * Input Format
 * 
 * The first line contains the number of tests N.
 * First line of every test contains size of an array N and K.
 * Second line of every test contains the input array
 *   
 * Output Format
 * 
 * For every window position output max value
 * 
 * Input
 * 
nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * 
 * Output
 * 
[3,3,5,5,6,7] 
 * 
 */

package lainexperiment.misc;

import static java.lang.Math.max;

import java.util.Arrays;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class Sliding_window_maximum_linear {

    static int K;

    static class Pair {
        int n;
        int m;
        Pair(int l, int v) {
            this.n = l;
            this.m = v;
        }
        @Override
        public String toString() {
            return n + "-" + m;
        }
    }

    static Stack<Pair> R = new Stack<>();
    static Stack<Pair> W = new Stack<>();

    static int peekMax(Stack<Pair> s) {
        return s.isEmpty()? 0: s.peek().m;
    }

    static void add(int n) {
        if (!R.isEmpty()) R.pop();
        if (W.size() < Sliding_window_maximum_linear.K) {
            int m = peekMax(W);
            W.push(new Pair(n, max(m, n)));
            return;
        }
        while (!W.isEmpty()) {
            int m = peekMax(R);
            Pair p = W.pop();
            R.push(new Pair(p.n, max(m, p.n)));
        }
        add(n);
    }

    static int getMax() {
        //System.out.println(W);
        //System.out.println(R);
        return max(peekMax(R), peekMax(W));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        K = k;
        R.clear();
        W.clear();
        if (k <= 1) return nums;
        return Arrays.stream(nums)
            .peek(Sliding_window_maximum_linear::add)
            .map(ii -> getMax())
            .skip(K - 1)
            .toArray();
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(new int[]{3,3,5,5,6,7}, maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }
}
