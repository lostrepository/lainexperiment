package lainexperiment.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*
 * Date: 01/08/2016
 * 
 * Problem
 * 
 * Replace each word which index div by 3 with Fizz 
 * and which div by 5 with Buzz. If index div by 
 * both then to FizzBuzz.
 *
 * Input
 * 
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 * 
 * Output
 * 
1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz
 * 
 */

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;


public class FizzBuzz {

    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    
    public static String convert(String s) {
        if (s == null)
            throw new IllegalArgumentException();
        final String delim = " ";
        String[] a = s.split(delim);
        for (int i = 1; i <= a.length; i++) {
            if (i % 3 == 0) {
                a[i - 1] = FIZZ;
            } else if (i % 5 == 0) {
                a[i - 1] = BUZZ;
            }
            if (i % 15 == 0) {
                a[i - 1] = FIZZ + BUZZ;
            }
        }
        return Arrays.stream(a)
                .collect(Collectors.joining(delim));
    }
    
    @Test
    public void testNull() {
        boolean isThrowed = false;
        
        try {
            convert(null);    
        } catch (IllegalArgumentException e) {
            isThrowed = true;
        }
        
        assertTrue(isThrowed);
        
    }

    @Test
    public void testFizz() {
        assertEquals("one two Fizz", convert("one two three"));
    }
    
    @Test
    public void testFizzBuzz() {
        assertEquals("1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz", 
                convert("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15"));
        
        assertEquals("1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz", 
                convert("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15"));
    }
    
    @Test
    public void testFizzAndBuzz() {
        assertEquals("one two Fizz four Buzz Fizz", convert("one two three four five six"));
        assertEquals("one two Fizz four Buzz", convert("one two three four five"));
        assertEquals("one two Fizz four Buzz Fizz seven eight Fizz Buzz", convert("one two three four five six seven eight nine ten"));
    }
    
    public static void main(String[] args) {
        
    }

}
