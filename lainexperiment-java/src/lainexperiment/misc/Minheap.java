/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * Date: 27/10/2020
 * 
 * Problem
 * 
 * Implement min heap data structure to which user can add
 * integers and get current min integer seen so far. 
 * 
 * Input Format
 * 
 * List of integers to add.
 * 
 * Output Format
 * 
 * For each new integer we need to return current min
 * 
 */
package lainexperiment.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Minheap {

    int[] A;
    int size;
    
    Minheap(int n) {
        A = new int[n];
    }
    
    int getMin() {
        return A[0];
    }
    
    void add(int n) {
        if (size == A.length)
            throw new RuntimeException("heap is full");
        A[size++] = n;
        heapify(size - 1);
    }
    
    void heapify(int i) {
        if (i == 0) return;
        int p = parent(i);
        if (A[p] < A[i]) return;
        swap(p, i);
        heapify(p);
    }
    
    void swap(int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    
    int parent(int i) {
        return i / 2;
    }
    
    String getArray() {
        return Arrays.toString(A);
    }

    public static class MinheapTest {
        @Test
        public void test() {
            Minheap mh = new Minheap(7);
            mh.add(3);
            assertEquals("[3, 0, 0, 0, 0, 0, 0]", mh.getArray());
            mh.add(2);
            assertEquals("[2, 3, 0, 0, 0, 0, 0]", mh.getArray());
            mh.add(5);
            assertEquals("[2, 3, 5, 0, 0, 0, 0]", mh.getArray());
            mh.add(4);
            assertEquals("[2, 3, 5, 4, 0, 0, 0]", mh.getArray());
            mh.add(10);
            assertEquals("[2, 3, 5, 4, 10, 0, 0]", mh.getArray());
            mh.add(12);
            assertEquals("[2, 3, 5, 4, 10, 12, 0]", mh.getArray());
            mh.add(1);
            assertEquals("[1, 2, 5, 3, 10, 12, 4]", mh.getArray());
            assertEquals(1, mh.getMin());
        }
    }
}
