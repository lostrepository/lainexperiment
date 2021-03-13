/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import java.util.Objects;

/**
 * Why not just Map.Entry? Because it has no setKey only setValue
 * and so SimpleEntry.
 */
public class Pair<A, B> {

    public A a;
    public B b;

    public Pair(A a, B b) {
        this.a = a; this.b = b;
    }
    
    public Pair(Pair<A, B> p) {
        this.a = p.a; this.b = p.b;
    }
    
    public A getA() {
        return a;
    }
    
    public B getB() {
        return b;
    }
    
    public static <A extends Comparable<A>> int compareByA(Pair<A, ?> p1, Pair<A, ?> p2) {
        return p1.a.compareTo(p2.a);
    }
    
    public static <B extends Comparable<B>> int compareByB(Pair<?, B> p1, Pair<?, B> p2) {
        return p1.b.compareTo(p2.b);
    }

    public static <A extends Comparable<A>, B extends Comparable<B>> int compareByAB(Pair<A, B> p1, Pair<A, B> p2) {
        int r = compareByA(p1, p2);
        return (r == 0)?  compareByB(p1, p2): r;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) return false;
        Pair o = (Pair) obj;
        return Objects.equals(a, o.a) && Objects.equals(b, o.b);
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", a, b);
    }
}