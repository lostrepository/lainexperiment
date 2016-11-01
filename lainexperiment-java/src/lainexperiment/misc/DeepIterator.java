/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 14/02/2015
 * 
 * Problem
 * 
 * Implement deep iterator
 *
 * Input
 * 
 * none
 * 
 * Output
 * 
 * none
 * 
 */

package lainexperiment.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class DeepIterator {

    @SuppressWarnings({"rawtypes"})
    static class DIterator {
       
        Stack<Iterator> q = new Stack<>();
        Object val;
        
        DIterator(List list) {
            q.add(list.iterator());
            expand();
        }
        
        // immutable operation
        boolean hasNext() {
            return val != null;
        }

        Object next() {
            if (q.isEmpty()) return null;
            Object ret = val;
            if (q.peek().hasNext()) {
                val = q.peek().next();
                return ret;
            }
            expand();
            return ret;
        }

        private void expand() {
            while (!q.isEmpty()) {
                if (!q.peek().hasNext()) {
                    q.pop();
                    continue;
                }
                Object v = q.peek().next();
                if (!(v instanceof List)) {
                    val = v;
                    break;
                }
                q.add(((List) v).iterator());
            }
        }
    }
    
    static int c = 0;
    
    /*
     * Creates a list of lists of depth d.
     * On each level will be only 3 entities.
     */
    @SuppressWarnings("unchecked")
    static List<?> makeNestedList(int i/*cur depth*/, int d) {
        if (i == d) {
            Integer[] a = new Integer[]{c + 1, c + 2, c + 3};
            c += 3;
            return Arrays.asList(a);
        }
        @SuppressWarnings("rawtypes")
        List l = new ArrayList<>();
        l.add(makeNestedList(i + 1, d));
        l.add(makeNestedList(i + 1, d));
        l.add(makeNestedList(i + 1, d));
        return l;
    }
    
    public static void main(String[] args) {
        DIterator di = new DIterator(Arrays.asList(new Integer[]{1, 2, 3}));
        di = new DIterator(Arrays.asList(new Integer[]{1, 2, 3}));
        Stream.generate(di::next).peek(System.out::println).anyMatch(v -> v == null);

        List<?> l = makeNestedList(0, 2);
        di = new DIterator(l);
        Stream.generate(di::next).peek(System.out::println).anyMatch(v -> v == null);
    }

}
