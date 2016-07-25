/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 05/08/2015
 * 
 * IMDB Codility
 * Problem: Task 2
 * Status: failed
 * 
 * Problem
 * 
 * Calculate number of full weeks between given months (inclusive).
 * Year is leap if it is div by 4.
 * 
 * Input Format
 * 
 * Year, starting month, ending month, weekday of 1st of January in 
 * the given year
 * 
 * Output Format
 * 
 * Number of full weeks 
 * 
 * Sample Input
 * 
2014, "April", "May", "Wednesday"
2014, "April", "April", "Saturday"
 * 
 * Sample Output
 * 
7
3
 * 
 */

package lainexperiment.codility.imdb;

import java.util.stream.IntStream;
import static org.junit.Assert.assertTrue;

public class Task2_Calculate_number_of_full_weeks {

    static final int DAYS_IN_WEEK = 7;
    static final int[] DAYS_IN_MONTH_NON_LEAP = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    static final String[] MONTHS = {
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
    };
    
    static final String[] WEEKDAYS = {
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
    };
    
    static boolean isLeap(int y) {
        return y % 4 == 0;
    }

    /**
     * @param m month id starting from 0
     * @param w week day of previous month
     * @param isLeap current year
     * @return
     */
    static int weekDayOfMonth(int m, int w, boolean isLeap) {
        int days = DAYS_IN_MONTH_NON_LEAP[m];
        if (isLeap && m == 1)
            days++;
        return (days - (DAYS_IN_WEEK - w)) % DAYS_IN_WEEK;
    }
    
    static int findIndex(String[] a, String s) {
        for (int i = 0; i< a.length; ++i) {
            if (a[i].equals(s))
                return i;
        }
        throw new RuntimeException("Wrong name");
    }
    
    static public int solution(int Y, String A, String B, String W) {
        int w = findIndex(WEEKDAYS, W);
        int a = findIndex(MONTHS, A);
        int b = findIndex(MONTHS, B);
        boolean isLeap = isLeap(Y);
        for (int m = 0; m < a; ++m) {
            w = weekDayOfMonth(m, w, isLeap);
        }
        int days = IntStream.rangeClosed(a, b).map((i) -> DAYS_IN_MONTH_NON_LEAP[i] + ((isLeap && i == 1)? 1: 0)).sum();
        return (days - (DAYS_IN_WEEK - w)) / DAYS_IN_WEEK;
    }
    
    public static void main(String[] args) {
        assertTrue(3 == solution(2014, "April", "April", "Saturday"));
        assertTrue(7 == solution(2014, "April", "May", "Wednesday"));
    }
    
}
