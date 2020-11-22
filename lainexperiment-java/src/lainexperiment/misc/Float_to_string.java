/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

/**
 * <pre>{@code
 * Date: 14/02/2015
 * 
 * Problem
 * 
 * Convert float to string (precise as possible).
 *
 * Input
 * 
 * 33.333332
 * 
 * Output
 * 
 * 33.3333333333333333333323333320
 * 
 * }</pre>
 */
public class Float_to_string {

    static String floatToString(float f) {
        StringBuilder buf = new StringBuilder();
        int i = (int)f;
        while (i > 0) {
            buf.insert(0, i % 10);
            i /= 10;
        }
        buf.append('.');
        f = f - (int)f;
        f *= 10;
        while (f - (int)f > 0) {
            buf.append((int)f);
            f *= 10;
        }
        return buf.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(100F/3);
        System.out.println(floatToString(100F/3));
    }
    
}
