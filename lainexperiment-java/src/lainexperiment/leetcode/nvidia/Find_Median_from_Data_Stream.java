/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.leetcode.nvidia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 19/04/2021
 *
 * Problem: Find Median from Data Stream
 * Status: accepted (my initial version was based on enhanced BST which was
 * sooo lame. Here is a rework of it with two PriorityQueue instead.
 * 
 * The median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value and the median is the mean of the two
 * middle values.
 * 
 * Implement the MedianFinder class:
 * 
 * - MedianFinder() initializes the MedianFinder object.
 * - void addNum(int num) adds the integer num from the data stream to the data
 * structure.
 * - double findMedian() returns the median of all elements so far. Answers within
 * 10^5 of the actual answer will be accepted.
 * 
 * Input
 * 
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
 * 
 * Output
 * 
[null, null, null, 1.5, null, 2.0]
 * 
 * 
 * }</pre>
 */
public class Find_Median_from_Data_Stream {

    class MedianFinder {

        PriorityQueue<Integer> lo, hi;
        double sum;

        public MedianFinder() {
            lo = new PriorityQueue<Integer>(Comparator.reverseOrder());
            hi = new PriorityQueue<Integer>();
        }
        
        public void addNum(int num) {
            if (hi.size() != 0 && hi.element() <= num) {
                hi.add(num);
            } else {
                lo.add(num);
            }
            
            if (hi.size() == lo.size()) {
                return;
            }
            if (lo.size() - hi.size() > 1) {
                hi.add(lo.poll());
            }
            if (hi.size() > lo.size()) {
                lo.add(hi.poll());
            }
            
            sum += num;
        }
        
        public double findMedian() {
            if (hi.size() < lo.size()) {
                return lo.element();
            } else {
                //return sum / (lo.size() + hi.size());
                return (lo.element() + hi.element()) / 2.;
            }
        }
    }

    List<Double> solve(List<String> cmds, List<Integer> args) {
        MedianFinder mf = null;
        int i = 0;
        var out = new ArrayList<Double>();
        while (i < cmds.size()) {
            Double med = null;
            switch (cmds.get(i)) {
            case "MedianFinder":
                mf = new MedianFinder();
                break;
            case "addNum":
                mf.addNum(args.get(i));
                break;
            case "findMedian":
                med = mf.findMedian();
                break;
            }
            out.add(med);
            i++;
        }
        return out;
    }

    @Test
    public void test() {
        assertEquals("[null, null, 20.0, null, 10.5, null, 11.0, null, 15.0, null, 19.0, null, 18.0, null, 17.0]", solve(List.of(
            "MedianFinder", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"),
            Arrays.asList(null, 20, null, 1, null, 11, null, 19, null, 21, null, 17, null, 6, null)).toString());
        assertEquals("[null, null, 3.0, null, 6.5, null, 5.0, null, 7.5, null, 7.0, null, 6.5]", solve(List.of(
            "MedianFinder", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian", "addNum", "findMedian"),
            Arrays.asList(null, 3, null, 10, null, 5, null, 20, null, 7, null, 6, null)).toString());
        assertEquals("[null, null, null, 1.5, null, 2.0]", solve(List.of("MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"),
            Arrays.asList(null, 1, 2, null, 3, null)).toString());
    }
}
