/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 03/02/2015
 * 
 * Problem
 * 
 * Trim the string.
 *
 * Input
 * 
 *   I   live   on     earth  
 * 
 * Output
 * 
 * I live on earth
 * 
 */

package lainexperiment.misc;

public class TrimTheString {

    static String trim(char[] a) {
        int k = 0;
        boolean addSpace = false;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] == ' ') {
                addSpace = true;
                continue;
            }
            if (addSpace && k != 0) {
                a[k++] = ' ';
                addSpace = false;
            }
            a[k++] = a[i];
        }
        return new String(a, 0, k);
    }

    static String trim2(char[] a) {
        int k = 0, i= 0;
        boolean skip = true;
        while (i < a.length) {
            if (a[i] == ' ' && skip) {
                i++;
                continue;
            }
            skip = a[i] == ' ';
            char t = a[i];
            a[i] = a[k];
            a[k] = t;
            k++;
            i++;
        }
        if (a[k] == ' ')
            k--;
        return new String(a, 0, k);
    }
    
    public static void main(String[] args) {
        System.out.println(trim("  I   live   on     earth  ".toCharArray()) + ']');
    }
    
}
