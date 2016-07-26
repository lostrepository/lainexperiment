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
public class StringHash {
    
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
    
    StringHash() {
    }
    
    StringHash(String s) {
        this(s.toCharArray());
    }
    
    StringHash(char[] a) {
        add(a, 0, a.length);
    }

    StringHash(char[] a, int s) {
        add(a, s, a.length);
    }

    StringHash(char[] a, int s, int e) {
        add(a, s, e);
    }
    
    StringHash(StringHash sh) {
        h1 = sh.h1;
        h2 = sh.h2;
        len = sh.len;
    }
    
    void clear() {
        h1 = h2 = len = 0;
    }
    
    StringHash add(String s) {
        return add(s.toCharArray(), 0, s.length());
    }
    
    StringHash add(StringHash sh) {
        h1 = (Long.remainderUnsigned(h1 * P1[sh.len], M1) + sh.h1) % M1;
        h2 = (Long.remainderUnsigned(h2 * P2[sh.len], M2) + sh.h2) % M2;
        len += sh.len;
        return this;
    }
    
    StringHash add(char[] a, int s, int e) {
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
            long h = new StringHash(s).getHashCode();
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
        StringHash.init(1200);
        
        assertEquals(new StringHash("abcd").getHashCode(),
                new StringHash("ab").add(new StringHash("c")).add("d").getHashCode());
        
        assertEquals(new StringHash("abc").getHashCode(),
                new StringHash("ab").add(new StringHash("c")).getHashCode());
        
        assertEquals(new StringHash("abcd").getHashCode(),
                new StringHash("ab").add(new StringHash("cd")).getHashCode());
        
        assertEquals(new StringHash("aaaaaaaaaaaaaaaaaabbbbbbbbb").getHashCode(),
                new StringHash("aaaaaaaaaaaaaaaaaa").add(new StringHash("bbbbbbbbb")).getHashCode());
        
        String s = String.format("%1200s", "").replace(' ', 'j');
        assertEquals(new StringHash(s).getHashCode(),
                new StringHash(s.substring(0, 433)).add(new StringHash(s.substring(433))).getHashCode());
        
        StringHash sh = new StringHash("abcd");
        assertEquals(new StringHash(sh).add("efg").getHashCode(),
                sh.add("efg").getHashCode());

        sh = new StringHash(s.substring(0, 200));
        assertEquals(new StringHash(sh).add(new StringHash(s.substring(200))).getHashCode(),
                sh.add(s.substring(200)).getHashCode());

        sh = new StringHash(s.substring(0, 200));
        assertEquals(new StringHash(sh).add(new StringHash(s.substring(200, 800)).add(s.substring(800))).getHashCode(),
            sh.add(s.substring(200)).getHashCode());
        
//        Set<Long> set = new HashSet<>();
//        try {
//            fill("", set);
//        } catch (Throwable e) {
//            System.out.println(set.size());
//        }
    }
}
