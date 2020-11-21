/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.TreeSet;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.Ival;

/**
 * Interval tree.
 * Does not support ivals overlap.
 */
public class IvalTree {

    TreeSet<Ival> tree = new TreeSet<>(Ival::compareByStart);

    public void insert(int n) {
        var ival = new Ival(n);
        Ival l = tree.floor(ival);
        if (l == null) {
            l = ival;
        }
        Ival r = tree.ceiling(ival);
        if (r == null) {
            r = ival;
        }
        if (l.e + 1 == ival.s) {
            ival.s = l.s;
            tree.remove(l);
        }
        if (ival.e + 1 == r.s) {
            ival.e = r.e;
            tree.remove(r);
        }
        tree.add(ival);
    }

    public Ival floor(Ival i) {
        return tree.floor(i);
    }

    public Ival ceiling(Ival i) {
        return tree.ceiling(i);
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    @Test
    public void test_insert() {
        var t = new IvalTree();
        t.insert(5);
        assertEquals("[5-5]", t.toString());

        t.insert(2);
        assertEquals("[2-2, 5-5]", t.toString());

        t.insert(1);
        assertEquals("[1-2, 5-5]", t.toString());

        t.insert(6);
        assertEquals("[1-2, 5-6]", t.toString());

        t.insert(3);
        assertEquals("[1-3, 5-6]", t.toString());

        t.insert(4);
        assertEquals("[1-6]", t.toString());

        t.insert(0);
        assertEquals("[0-6]", t.toString());

        t.insert(7);
        assertEquals("[0-7]", t.toString());

        t.insert(12);
        assertEquals("[0-7, 12-12]", t.toString());
    }
}
