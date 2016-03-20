/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment;

public class Utils {

    public static class Point {
        public short x, y;
        public Point(int x, int y) {
            this.x = (short) x;
            this.y = (short) y;
        }
    }

    public static class Holder<T> {
        T v;
        public void set(T v) { this.v = v; }
        public T get() { return v; }
    }

    public static void reverse(int[] a) {
        for(int i = 0; i < a.length / 2; i++)
        {
            int temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
    }

}
