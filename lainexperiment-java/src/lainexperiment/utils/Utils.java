package lainexperiment.utils;

import java.util.List;

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
}
