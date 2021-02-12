/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils;

import java.util.Arrays;

public class Utils {

    public static void printArray(int[][] a) {
        System.out.println(Arrays.deepToString(a).replace("],", "]\n"));
    }
}
