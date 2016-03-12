/* 
 * LynX Source Materials
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

public class DeepIterator {

    static class DIterator<V> {
        Iterator<?>[] iters;
        
        DIterator(List<?> list, int n) {
            iters = new Iterator[n];
            iters[0] = list.iterator();
            for (int i = 1; i < n; ++i) {
                iters[i] = ((List<?>)iters[i - 1].next()).iterator();
            }
        }

        @SuppressWarnings("unchecked")
        V next() {
            return (V)iters[iters.length - 1].next();
        }
        
        boolean hasNext() {
            if (iters[iters.length - 1].hasNext())
                return true;
            return update(iters.length - 1);
        }

        private boolean update(int n) {
            if (n == -1)
                return false;
            if (iters[n].hasNext()) {
                if (n != iters.length - 1)
                    iters[n + 1] = ((List<?>)iters[n].next()).iterator();
                return true;
            }
            boolean has = update(n - 1);
            if (!has)
                return false;
            return update(n);
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
        DIterator<Integer> di = new DIterator<>(l, 3);
        while (di.hasNext())
            System.out.println(di.next());
    }

}
