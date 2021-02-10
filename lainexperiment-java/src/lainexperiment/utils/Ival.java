/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

/**
 * <pre>{@code
 * Interval
 * }</pre>
 */
public class Ival {

    /**
     * Start
     */
    public long s;
    
    /**
     * End
     */
    public long e;

    public Ival(long s, long e) {
        this.s = s;
        this.e = e;
    }

    public Ival(long s) {
        this.s = s;
        this.e = s;
    }

    public static int compareByStart(Ival a, Ival b) {
        return Long.compare(a.s, b.s);
    }
    
    @Override
    public String toString() {
        return String.format("%d-%d", s, e);
    }

    public boolean contains(long n) {
        return s <= n && n <= e;
    }
}
