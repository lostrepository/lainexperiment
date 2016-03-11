/* 
 * LynX Source Materials
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 17/01/2015
 * 
 * Problem
 * 
 * Output top N positive integer in string comparison order. 
 *
 * Input
 * 
 * 1000 
 * 
 * Output
 * 
 * 1, 10, 100, 1000, 101, 102, ... 109, 11, 110, ...
 * 
 */

package misc;

public class PrintNumbersInComparisonOrder {

    private static void print(int n, int k) {
        for (int i = 0; i <= 9; ++i) {
            int nk = k * 10 + i;
            if (k == nk)
                continue;
            if (nk > n)
                return;
            System.out.println(nk);
            print(n, nk);
        }
    }
    
    public static void main(String[] args) {
        print(1000, 0);
    }

}
