/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import java.util.Objects;

/**
 * Why not just Map.Entry? Because it has no setKey() :(
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
    
    public A getX() {
        return a;
    }
    
    public B getY() {
        return b;
    }
    
    public static <X extends Comparable<X>> int compareByX(Pair<X, ?> p1, Pair<X, ?> p2) {
        return p1.a.compareTo(p2.a);
    }
    
    public static <Y extends Comparable<Y>> int compareByY(Pair<?, Y> p1, Pair<?, Y> p2) {
        return p1.b.compareTo(p2.b);
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