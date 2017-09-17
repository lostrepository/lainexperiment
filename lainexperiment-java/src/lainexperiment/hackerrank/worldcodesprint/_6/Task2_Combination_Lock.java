/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 27/08/2016
 * 
 * Hacker rank
 * World CodeSprint #6
 * Problem: Combination Lock
 * Status: accepted
 * 
 * Consider a 5-slot combination lock where each slot contains a dial 
 * numbered with the ten sequential decimal integers in the inclusive 
 * range from 0 to 9. In one operation, you can choose a slot and rotate 
 * the dial by one click, either in the positive direction (to increase 
 * the displayed number by 1) or the negative direction (to decrease the 
 * displayed number by 1). Note that, due to the cyclical nature of the 
 * dial, the next number after 9 is 0 and the number before 0 is 9). For 
 * example, if the number 0 is currently displayed on the dial, you can 
 * rotate the dial to either 1 (positive direction) or 9 (negative 
 * direction) in a single operation.
 * 
 * Given the initial configuration of numbers in each slot and some desired 
 * configuration of numbers, determine the minimum number of operations you 
 * must perform to change the lock's slots to the desired configuration.
 * 
 * Input Format
 * 
 * The first line contains 5 space-separated integers denoting the current 
 * slot configuration.
 * The second line contains 5 space-separated integers denoting the desired 
 * slot configuration.
 * 
 * Output Format
 * 
 * Print a single integer denoting the minimum number of moves to change 
 * this configuration to the correct one.
 * 
 * 
 * Sample Input
 * 
1 2 9 5 7
1 3 2 0 7 
 *
 * Sample Output
 * 
9
 *
 *
 */

package lainexperiment.hackerrank.worldcodesprint._6;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.setAll;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2_Combination_Lock {

    static int[] FROM = new int [5];
    static int[] TO = new int [5];
    
    static void solve() {
        int sum = 0;
        for (int i = 0; i < FROM.length; i++) {
            int a = min(TO[i], FROM[i]);
            int b = max(TO[i], FROM[i]);
            sum += min(b - a, (a + 1) + (9 - b));
        }
        System.out.println(sum);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Class<?> clazz = Task2_Combination_Lock.class;
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(clazz.getResourceAsStream(clazz.getSimpleName() + ".in"));
        while (scanner.hasNext()) {
            setAll(FROM, i -> scanner.nextInt());
            setAll(TO, i -> scanner.nextInt());
            solve();
        }
        scanner.close();
    }

}
