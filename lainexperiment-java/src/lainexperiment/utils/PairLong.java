/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

/**
 * Short name for {@code Pair<Long, Long> }
 */
public class PairLong extends Pair<Long, Long> {

    public PairLong(long a, long b) {
        super(a, b);
    }
    
    public PairLong(PairLong p) {
        super(p.a, p.b);
    }

    public PairLong() {
        super(0L, 0L);
    }
}
