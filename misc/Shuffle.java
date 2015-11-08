/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 08/11/2015
 * 
 * Problem
 * 
 * Suppose we are given an array which consists of two parts: 
 * - the first part contains sorted characters
 * - the second part contains sorted digits (in fact instead 
 * of characters and digits we can have anything else)
 * For example: abcdefg1234567
 * We need to shuffle it so it becomes: a1b2c3d4e5f6g7
 * 
 * Input
 *
abcdefg1234567
 * 
 * Output
 * 
a1b2c3d4e5f6g7
 * 
 */

/*
 * This is linear time solution. It will traverse the input array 
 * twice and would not touch any letters more than two times.
 */

package misc;

import static org.junit.Assert.assertEquals;

public class Shuffle {
    
    static int next(int from, int mid, int len) {
        int to = from;
        if (from < mid) {
            if (from <= len - mid) {
                to *= 2;
            } else {
                to += len - mid;
            }
        } else {
            to = (from - mid) * 2 + 1;
        }
        return to;
    }

    static void update(char[] a, int mid, int p) {
        char moveWhat = a[p];
        int to = next(p, mid, a.length);
        while (true) {
//            System.out.println(new String(a));
            char ch = moveWhat;
            moveWhat = a[to];
            a[to] = ch;
            if (to == p)
                break;
            int n = next(to, mid, a.length);
            to = n;
        }        
    }
    
    static void reorderLinear(char[] a, int mid) {
        boolean isChar = false;
        for (int i = 0; i < mid; ++i) {
            isChar = !isChar;
            if (isChar && Character.isLetter(a[i]))
                continue;
            if (!isChar && Character.isDigit(a[i]))
                continue;
            update(a, mid, i);
        }
    }
    
    public static void main(String[] args) {
        char[] a = null;
        
        a = "abcd1234".toCharArray();
        reorderLinear(a, 4);
        assertEquals("a1b2c3d4", new String(a));
        
        a = "abcdefg1234567".toCharArray();
        reorderLinear(a, 7);
        assertEquals("a1b2c3d4e5f6g7", new String(a));
        
        a = "abcdefg12345".toCharArray();
        reorderLinear(a, 7);
        assertEquals("a1b2c3d4e5fg", new String(a));
//        
        a = "a1".toCharArray();
        reorderLinear(a, 1);
        assertEquals("a1", new String(a));
        
        a = "a123".toCharArray();
        reorderLinear(a, 1);
        assertEquals("a123", new String(a));
        
        a = "abcde12".toCharArray();
        reorderLinear(a, 5);
        assertEquals("a1b2cde", new String(a));
    }

}
