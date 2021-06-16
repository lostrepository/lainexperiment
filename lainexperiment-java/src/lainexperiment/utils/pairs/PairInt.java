/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils.pairs;

import java.util.Objects;

/**
 * Unfortunately {@code Pair<Integer, Integer> } is not an
 * option because it would not be safe to compare elements of
 * two different pairs with == instead you will need to use equals().
 */
public class PairInt {

    public int a;
    public int b;

    public PairInt() {
    }

    public PairInt(int a, int b) {
        this.a = a; this.b = b;
    }
    
    public PairInt(PairInt p) {
        this.a = p.a; this.b = p.b;
    }
    
    public int getA() {
        return a;
    }
    
    public int getB() {
        return b;
    }
    
    public static int compareByA(PairInt p1, PairInt p2) {
        return p1.a - p2.a;
    }
    
    public static int compareByB(PairInt p1, PairInt p2) {
        return p1.b - p2.b;
    }

    public static int compareByAB(PairInt p1, PairInt p2) {
        int r = compareByA(p1, p2);
        return (r == 0)?  compareByB(p1, p2): r;
    }
    
    public static int reverseCompareByAB(PairInt p1, PairInt p2) {
        return -compareByAB(p1, p2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) return false;
        PairInt o = (PairInt) obj;
        return a == o.a && b == o.b;
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", a, b);
    }
}
