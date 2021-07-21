/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils.tuples;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

/**
 * Array of integers with equals support which can be used
 * with Java collections. Plus it has toString support for easy
 * debugging.
 *
 * Unfortunately Java collections does not work well with
 * ordinary arrays as an elements due to equals (examples
 * are distinct, groupingBy).
 */
public class TupleInt {

    public int[] a;

    public TupleInt(int...a) {
        this.a = a;
    }
    
    public TupleInt(Map.Entry<? extends Number, ? extends Number> e) {
        a = new int[2];
        a[0] = e.getKey().intValue(); a[1] = e.getValue().intValue();
    }
    
    public int a() {
        return a[0];
    }
    
    public int b() {
        return a[1];
    }
    
    public void a(int v) {
        a[0] = v;
    }

    public void b(int v) {
        a[1] = v;
    }

    public static Comparator<TupleInt> compareByA() {
        return (TupleInt p1, TupleInt p2) -> p1.a[0] - p2.a[0];
    }
    
    public static Comparator<TupleInt> compareByB() {
        return (TupleInt p1, TupleInt p2) -> p1.a[1] - p2.a[1];
    }

    public static Comparator<TupleInt> compareByC() {
        return (TupleInt p1, TupleInt p2) -> p1.a[2] - p2.a[2];
    }
    
    public static Comparator<TupleInt> compareByAB() {
        return compareByA().thenComparing(compareByB());
    }
    
    public static Comparator<TupleInt>  compareByABC() {
        return compareByA().thenComparing(compareByB()).thenComparing(compareByC());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(a);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) return false;
        TupleInt o = (TupleInt) obj;
        return Arrays.equals(a, o.a);
    }
    
    @Override
    public String toString() {
        return Arrays.toString(a);
    }

}
