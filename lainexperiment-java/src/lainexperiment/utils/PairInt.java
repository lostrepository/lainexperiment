/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

/**
 * Short name for Pair<Integer, Integer>
 */
public class PairInt extends Pair<Integer, Integer> {

    public PairInt(int a, int b) {
        super(a, b);
    }
    
    public PairInt(PairInt p) {
        super(p.a, p.b);
    }

}
