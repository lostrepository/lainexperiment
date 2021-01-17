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
public class Pair<X, Y> {

    public X x;
    public Y y;

    public Pair(X x, Y y) {
        this.x = x; this.y = y;
    }
    
    public Pair(Pair<X, Y> p) {
        this.x = p.x; this.y = p.y;
    }
    
    public X getX() {
        return x;
    }
    
    public Y getY() {
        return y;
    }
    
    public static <X extends Comparable<X>> int compareByX(Pair<X, ?> p1, Pair<X, ?> p2) {
        return p1.x.compareTo(p2.x);
    }
    
    public static <Y extends Comparable<Y>> int compareByY(Pair<?, Y> p1, Pair<?, Y> p2) {
        return p1.y.compareTo(p2.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) return false;
        Pair o = (Pair) obj;
        return Objects.equals(x, o.x) && Objects.equals(y, o.y);
    }
}