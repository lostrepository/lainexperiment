/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 19/04/2015
 * 
 * Problem
 * 
 * Check if string present in the stream of characters
 *
 * Input
 * 
 * ABC ABCDAB ABCDABCDABDE
 * ABCDABD
 * 
 * Output
 * 
 * true
 * 
 */

package lainexperiment.misc;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;

public class Match_the_string_with_stream {

    static boolean match(Reader s, int i, char[] p, 
            StringBuilder b) throws IOException 
    {
        if (i == b.length() && i == p.length)
            return true;
        if (i == 0) {
            if (b.charAt(i) != p[i] || 
                !match(s, i + 1, p, b))
            {
                b.deleteCharAt(0);
                int c = s.read();
                if (c == -1)
                    return false;
                b.append((char)c);
                return match(s, 0, p, b);
            }
            return true;
        }
        return b.charAt(i) == p[i] &&
                match(s, i + 1, p, b);
    }
    
    static boolean match(char[] a, char[] p) throws IOException {
        Reader s = new StringReader(new String(a));
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < p.length; ++i) {
            int c = s.read();
            if (c == -1)
                return false;
            b.append((char)c);
        }
        return match(s, 0, p, b);
    }
    
    public static void main(String[] args) throws IOException {
        Assert.assertTrue(match("ABC ABCDAB ABCDABCDABDE".toCharArray(), "ABCDABD".toCharArray()));
    }

}
