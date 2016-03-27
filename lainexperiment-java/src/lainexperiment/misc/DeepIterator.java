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
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DeepIterator {

    @SuppressWarnings({"rawtypes"})
    static class DIterator {
       
        List<Iterator> iters = new LinkedList<>();
        Supplier<Iterator> tail = () -> iters.get(iters.size() - 1);
        Object next;
        
        DIterator(List list) {
            iters.add(list.iterator());
            next = expandTail();
        }
        
        // immutable operation
        boolean hasNext() {
            return next != null;
        }

        Object next() {
            if (next == null) return null;
            Object cur = next;
            if (tail.get().hasNext())
                next = tail.get().next();
            else
                next = update();
            return cur;
        }

        private Object update() {
            if (iters.isEmpty()) return null;
            if (tail.get().hasNext()) {
                return expandTail();
            }
            iters.remove(iters.size() - 1);
            return update();
        }
        
        private Object expandTail() {
            while (tail.get().hasNext()) {
                Object obj = tail.get().next();
                if (obj instanceof List)
                    iters.add(((List)obj).iterator());
                else
                    return obj;
            }
            return null;
        }
    }
    
    static int c = 0;
    
    @SuppressWarnings("unchecked")
    static List<?> makeNestedList(int i, int n) {
        if (i == n) {
            Integer[] a = new Integer[]{c + 1, c + 2, c + 3};
            c += 3;
            return Arrays.asList(a);
        }
        @SuppressWarnings("rawtypes")
        List l = new ArrayList<>();
        l.add(makeNestedList(i + 1, n));
        l.add(makeNestedList(i + 1, n));
        l.add(makeNestedList(i + 1, n));
        return l;
    }
    
    public static void main(String[] args) {
        List<?> l = makeNestedList(0, 2);
        DIterator di = new DIterator(Arrays.asList(new Integer[]{1, 2, 3}));
        di = new DIterator(Arrays.asList(new Integer[]{1, 2, 3}));
        Stream.generate(di::next).peek(System.out::println).anyMatch(v -> v == null);
        di = new DIterator(l);
        Stream.generate(di::next).peek(System.out::println).anyMatch(v -> v == null);
    }

}
