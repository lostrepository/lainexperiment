/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.util.Arrays.fill;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * <pre>{@code
 * Date: 14/02/2017
 * 
 * Problem
 * 
 * To brighten up the gala dinner of the IOI '98 we have a set 
 * of N (10 ≤ N ≤ 100) coloured lamps numbered from 1 to N.
 * 
 * The lamps are connected to four buttons:
 * 
 *   Button 1: When this button is pressed, all the lamps 
 *   change their state: those that are ON are turned OFF 
 *   and those that are OFF are turned ON.
 *  
 *   Button 2: Changes the state of all the odd numbered 
 *   lamps.
 *  
 *   Button 3: Changes the state of all the even numbered 
 *   lamps.
 *  
 *   Button 4: Changes the state of the lamps whose number 
 *   is of the form 3K+1 (with K ≥ 0), i.e., 1, 4, 7, ...
 * 
 * There is a counter C which records the total number of button 
 * presses.
 * 
 * When the party starts, all the lamps are ON and the counter 
 * C is set to zero.
 * 
 * You are given the value of counter C (0 ≤ C ≤ 10 000) and 
 * information on the final state of some of the lamps. Write a 
 * program to determine all the possible final configurations of 
 * the N lamps that are consistent with the given information, 
 * without repetitions.
 * 
 * Input Format
 * 
 * The input will have four lines, describing the number N of 
 * lamps available, the number C of button presses, and the state 
 * of some of the lamps in the final configuration.
 * 
 * The first line contains the number N.
 *   
 * The second line contains the final value of counter C.
 *   
 * The third line lists the lamp numbers you are informed to 
 * be ON in the final configuration, separated by one space 
 * and terminated by the integer -1.
 *   
 * The fourth line lists the lamp numbers you are informed to 
 * be OFF in the final configuration, separated by one space 
 * and terminated by the integer -1.
 * 
 * 
 * Output Format
 * 
 * The output must contain all the possible final configurations 
 * (without repetitions) of all the lamps. Each possible 
 * configuration must be written on a different line. Each line 
 * has N characters, where the first character represents the 
 * state of lamp 1 and the last character represents the state of 
 * lamp N. A 0 (zero) stands for a lamp that is OFF, and a 1 (one) 
 * stands for a lamp that is ON. You may output the lines in any 
 * order.
 * 
 * If there are no possible configurations, output a single line 
 * with the word "IMPOSSIBLE" (without quotes).
 * 
 * Input
 * 
10
1
-1
7 -1
 * 
 * Output
 * 
0110110110
0101010101
0000000000
 * 
 * }</pre>
 */
public class Party_Lamps {

    static int N;
    static int C;
    static int[] L;
    static Set<BitSet> M = new HashSet<>();
    static boolean isPossible;

    static void solve() {
        isPossible = false;
        BitSet buf = new BitSet(N + 1);
        buf.set(0, N + 1);
        M.clear();
        printAllStates(0, 0, buf);
        if (!isPossible)
            System.out.println("IMPOSSIBLE");
    }
    
    static boolean matches(int[] a, BitSet b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == -1) continue;
            if (b.get(i) && a[i] != 1) return false;
            if (!b.get(i) && a[i] != 0) return false;
        }
        isPossible = true;
        return true;
    }
    
    static void print(BitSet buf) {
        for (int i = 0; i < buf.length() - 1; i++) 
            System.out.print(buf.get(i)? "1": "0");
        System.out.println();
    }
    
    static boolean isOdd(int i) {
        return (i & 1) == 1;
    }
    
    static void printAllStates(int button, int numOfPresses, BitSet buf) {
        if (button == 4) {
            if (numOfPresses > C) return;
            if (isOdd(C) != isOdd(numOfPresses))
                return;
            if (matches(L, buf) && !M.contains(buf)) {
                M.add((BitSet) buf.clone());
                print(buf);
            }
            return;
        }
        printAllStates(button + 1, numOfPresses, buf);
        toggleSwitch(button, buf);
        printAllStates(button + 1, numOfPresses + 1, buf);
        toggleSwitch(button, buf);
    }

    private static void toggleSwitch(int num, BitSet buf) {
        switch (num) {
        case 0:
            for (int i = 0; i < buf.length() - 1; i++) 
                buf.flip(i);
            break;
        case 1: // odd
            for (int i = 0; i < buf.length() - 1; i += 2) 
                buf.flip(i);
            break;
        case 2: // even
            for (int i = 1; i < buf.length() - 1; i += 2) 
                buf.flip(i);
            break;
        case 3:
            for (int k = 0; ; k++) {
                int i = 3 * k;
                if (i >= buf.length() - 1) break;
                buf.flip(i);
            }
            break;
        }
    }

    public static void main(String[] args) {
        Class<?> clazz = Party_Lamps.class;
        String inputFile = clazz.getSimpleName() + ".in";
        Scanner scanner = System.getProperty("local") == null?
            new Scanner(System.in): 
            new Scanner(clazz.getResourceAsStream(inputFile));
        while (scanner.hasNext()) {
            N = scanner.nextInt();
            C = scanner.nextInt();
            L = new int[N];
            fill(L, -1);
            while (scanner.hasNextInt()) {
                int i = scanner.nextInt() - 1;
                if (i == -2) break;
                L[i] = 1;
            }
            while (scanner.hasNextInt()) {
                int i = scanner.nextInt() - 1;
                if (i == -2) break;
                L[i] = 0;
            }        
            solve();
            if (scanner.hasNext())
                System.out.println();
        }
        scanner.close();
    }
}
