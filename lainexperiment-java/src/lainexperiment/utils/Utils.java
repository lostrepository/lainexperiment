package lainexperiment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Utils {

    public static <T> T last(List<T> l) {
        return l.get(l.size() - 1);
    }

    public static int last(int[] a) {
        return a[a.length - 1];
    }

    public static long last(long[] a) {
        return a[a.length - 1];
    }
    
    public static char last(char[] a) {
        return a[a.length - 1];
    }
    
    public static char last(String s) {
        return s.charAt(s.length() - 1);
    }
    
    /**
     * Swap a[i] with a[j]
     */
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    /**
     * Swap a[i] with a[j]
     */
    public static void swap(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    /**
     * Converts string representation of 2d array "[[1,2],[3,4]]"
     * into int[][]
     */
    public static int[][] as2DIntArray(String str) {
        var a = str.split("\\]\\s*,\\s*\\[");
        var r = new int[a.length][];
        for (int i = 0; i < r.length; i++) {
            r[i] = asIntArray(a[i]);
        }
        return r;
    }

    /**
     * Converts string representation of array "[1,2]" into int[]
     */
    public static int[] asIntArray(String str) {
        str = str.replaceAll("[^\\d,]+", "");
        var a = str.split(",");
        var r = new int[a.length];
        for (int i = 0; i < r.length; i++) {
            r[i] = Integer.parseInt(a[i]);
        }
        return r;
    }

    @Test
    public void test_as2DIntArray() {
        var str = "[[7, 10], [7, 12], [7, 5], [7, 4], [7, 2]]";
        var a = as2DIntArray(str);
        assertEquals(str, Arrays.deepToString(a));
    }
}
