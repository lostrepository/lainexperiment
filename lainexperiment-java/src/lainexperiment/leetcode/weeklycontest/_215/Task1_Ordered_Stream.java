/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._215;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import lainexperiment.utils.Ival;
import lainexperiment.utils.tree.IvalTree;

/**
 * <pre>{@code
 * Date: 20/11/2020
 * 
 * Problem: Design an Ordered Stream
 * Status: accepted
 * 
 * There is a stream of n (id, value) pairs arriving in an
 * arbitrary order, where id is an integer between 1 and n and
 * value is a string. No two pairs have the same id.
 * 
 * Design a stream that returns the values in increasing order
 * of their IDs by returning a chunk (list) of values after each
 * insertion. The concatenation of all the chunks should result
 * in a list of the sorted values.
 * 
 * Implement the OrderedStream class:
 * 
 * - OrderedStream(int n) Constructs the stream to take n values.
 * - String[] insert(int id, String value) Inserts the pair (id,
 * value) into the stream, then returns the largest possible
 * chunk of currently inserted values that appear next in the
 * order.
 *
 * Example 1:
 * 
os.insert(3, "ccccc");
os.insert(1, "aaaaa");
os.insert(2, "bbbbb");
os.insert(5, "eeeee");
os.insert(4, "ddddd");
 * 
 * Output
 * 
[]
["aaaaa"]
["bbbbb", "ccccc"]
[]
["ddddd", "eeeee"]
 * 
 * }</pre>
 */
public class Task1_Ordered_Stream {

    IvalTree tree = new IvalTree();
    String[] stream;
    int ptr;

    public Task1_Ordered_Stream(int n) {
        stream = new String[n];
    }

    public String[] insert(int n, String s) {
        n--;
        stream[n] = s;
        tree.insert(n);
        var ival = tree.floor(new Ival(ptr));
        if (ival == null || !ival.contains(ptr)) return new String[0];
        int lo = ptr;
        int hi = (int) (ival.e + 1);
        ptr = (int) (ival.e + 1);
        return Arrays.copyOfRange(stream, lo, hi);
    }

    public static void main(String[] args) {
        Task1_Ordered_Stream os = new Task1_Ordered_Stream(5);
        assertArrayEquals(new String[0], os.insert(3, "ccccc"));
        assertArrayEquals(new String[] {"aaaaa"}, os.insert(1, "aaaaa"));
        assertArrayEquals(new String[] {"bbbbb", "ccccc"}, os.insert(2, "bbbbb"));
        assertArrayEquals(new String[0], os.insert(5, "eeeee"));
        assertArrayEquals(new String[] {"ddddd", "eeeee"}, os.insert(4, "ddddd"));
    }
}
