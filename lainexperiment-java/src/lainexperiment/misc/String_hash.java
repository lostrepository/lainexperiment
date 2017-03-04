/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 11/01/2015
 *
 * Problem
 * 
 * There is a parking lot with n slots and n - 1 cars with 1 0 (free) slot. 
 * Given an initial and final arrangements need to arrange them accordingly
 * using a free slot as a buffer. It is allowed to move one car at a time only.
 * 
 * Input
 * 
 * 2, 5, 3, 1, 0, 4
 * 5, 0, 2, 4, 1, 3
 * 
 * Output
 * 
 * none
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;

import java.util.Set;

/*
 * Supports composed hash codes. Based on modular
 * hash function.
 */
public class String_hash {
    
    // to decrease collisions must be prime
    private static final long M1 = 4294967291L;
    private static final long M2 = 4294967279L;
    private static long[] P1, P2;
    
    private long h1, h2;
    int len = 0;
    
    static void init(int maxSize) {
        P1 = new long[maxSize];
        P2 = new long[maxSize];
        P1[0] = 1;
        P2[0] = 1;
        for (int i = 1; i < maxSize; i++) {
            P1[i] = (P1[i - 1] * 25) % M1;
            P2[i] = (P2[i - 1] * 25) % M2;
        }
    }
    
    String_hash() {
    }
    
    String_hash(String s) {
        this(s.toCharArray());
    }
    
    String_hash(char[] a) {
        add(a, 0, a.length);
    }

    String_hash(char[] a, int s) {
        add(a, s, a.length);
    }

    String_hash(char[] a, int s, int e) {
        add(a, s, e);
    }
    
    String_hash(String_hash sh) {
        h1 = sh.h1;
        h2 = sh.h2;
        len = sh.len;
    }
    
    void clear() {
        h1 = h2 = len = 0;
    }
    
    String_hash add(String s) {
        return add(s.toCharArray(), 0, s.length());
    }
    
    String_hash add(String_hash sh) {
        h1 = (Long.remainderUnsigned(h1 * P1[sh.len], M1) + sh.h1) % M1;
        h2 = (Long.remainderUnsigned(h2 * P2[sh.len], M2) + sh.h2) % M2;
        len += sh.len;
        return this;
    }
    
    String_hash add(char[] a, int s, int e) {
        for (int i = s; i < e; i++) {
            int v = a[i] - 'a';
            h1 = ((h1 * 25) % M1 + v) % M1;
            h2 = ((h2 * 25) % M2 + v) % M2;
            len++;
        }
        return this;
    }
    
    long getHashCode() {
        return (h1 << 31) | h2;
    }

    static void fill(String s, Set<Long> set) {
        if (s.length() == 10) {
//            System.out.println(s);
            long h = new String_hash(s).getHashCode();
            if (set.contains(h)) {
                throw new RuntimeException("Collission");
            }
            set.add(h);
            return;
        }
        for (int i = 'a'; i < 'z'; i++) {
            fill(s + (char)i, set);
        }
    }

    public static void main(String[] args) {
        String_hash.init(1200);
        
        assertEquals(new String_hash("abcd").getHashCode(),
                new String_hash("ab").add(new String_hash("c")).add("d").getHashCode());
        
        assertEquals(new String_hash("abc").getHashCode(),
                new String_hash("ab").add(new String_hash("c")).getHashCode());
        
        assertEquals(new String_hash("abcd").getHashCode(),
                new String_hash("ab").add(new String_hash("cd")).getHashCode());
        
        assertEquals(new String_hash("aaaaaaaaaaaaaaaaaabbbbbbbbb").getHashCode(),
                new String_hash("aaaaaaaaaaaaaaaaaa").add(new String_hash("bbbbbbbbb")).getHashCode());
        
        String s = String.format("%1200s", "").replace(' ', 'j');
        assertEquals(new String_hash(s).getHashCode(),
                new String_hash(s.substring(0, 433)).add(new String_hash(s.substring(433))).getHashCode());
        
        String_hash sh = new String_hash("abcd");
        assertEquals(new String_hash(sh).add("efg").getHashCode(),
                sh.add("efg").getHashCode());

        sh = new String_hash(s.substring(0, 200));
        assertEquals(new String_hash(sh).add(new String_hash(s.substring(200))).getHashCode(),
                sh.add(s.substring(200)).getHashCode());

        sh = new String_hash(s.substring(0, 200));
        assertEquals(new String_hash(sh).add(new String_hash(s.substring(200, 800)).add(s.substring(800))).getHashCode(),
            sh.add(s.substring(200)).getHashCode());
        
//        Set<Long> set = new HashSet<>();
//        try {
//            fill("", set);
//        } catch (Throwable e) {
//            System.out.println(set.size());
//        }
    }
}
