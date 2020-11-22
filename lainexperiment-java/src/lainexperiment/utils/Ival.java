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
    public int s;
    
    /**
     * End
     */
    public int e;

    public Ival(int s, int e) {
        this.s = s;
        this.e = e;
    }

    public Ival(int s) {
        this.s = s;
        this.e = s;
    }

    public static int compareByStart(Ival a, Ival b) {
        return a.s - b.s;
    }
    
    @Override
    public String toString() {
        return String.format("%d-%d", s, e);
    }

    public boolean contains(int n) {
        return s <= n && n <= e;
    }
}
