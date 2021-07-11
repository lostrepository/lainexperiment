/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils.pairs;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

/**
 * Unfortunately {@code Pair<Integer, Integer> } is not an
 * option because it would not be safe to compare elements of
 * two different pairs with == instead you will need to use equals().
 * 
 * And using int[2] instead will not work good with Java collections
 * due to equals.
 */
public class PairInt {

    public int a;
    public int b;

    public PairInt() {
    }

    public PairInt(Map.Entry<? extends Number, ? extends Number> e) {
        this.a = e.getKey().intValue(); this.b = e.getValue().intValue();
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
    
    public static Comparator<PairInt> compareByA() {
        return (PairInt p1, PairInt p2) -> p1.a - p2.a;
    }
    
    public static Comparator<PairInt> compareByB() {
        return (PairInt p1, PairInt p2) -> p1.b - p2.b;
    }

    public static Comparator<PairInt> compareByAB() {
        return compareByA().thenComparing(compareByB());
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
