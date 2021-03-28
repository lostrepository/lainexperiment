/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils.pairs;

/**
 * Short name for {@code Pair<Integer, Integer> }
 */
public class PairInt extends Pair<Integer, Integer> {

    public PairInt(int a, int b) {
        super(a, b);
    }
    
    public PairInt(PairInt p) {
        super(p.a, p.b);
    }

    public PairInt() {
        super(0, 0);
    }
    
    public static int compareByAB(PairInt p1, PairInt p2) {
        int r = p1.a - p2.a;
        return (r == 0)? p1.b - p2.b: r;
    }
}
