package lainexperiment.misc;
/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 01/08/2016
 * 
 * Problem
 * 
 * Check if given year leap year or not. 
 *
 * Input
 * 
1900
 * 
 * Output
 * 
false
 * 
 * }</pre>
 */
public class Leap_year {

    public static boolean isLeap(int year) {
        if (year % 400 == 0)
            return true;
        boolean divBy4 = year % 4 == 0;
        boolean divBy100 = year % 100 == 0;
        return divBy4 && !divBy100;
    }
    
    @Test
    public void testNonLeapYears() {
        assertFalse(isLeap(1900));
        assertFalse(isLeap(1800));
        assertFalse(isLeap(2014));
        assertFalse(isLeap(2015));
    }
    
    @Test
    public void testLeapYears() {
        assertTrue(isLeap(0));
        assertTrue(isLeap(800));
        assertTrue(isLeap(1980));
        assertTrue(isLeap(2000));
        assertTrue(isLeap(2012));
    }
    
    public static void main(String[] args) {
        
    }

}
