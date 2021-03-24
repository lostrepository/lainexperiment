/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._208;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * <pre>{@code
 * Date: 22/03/2020
 * 
 * Problem: Crawler Log Folder
 * Status: accepted
 * 
 * The Leetcode file system keeps a log each time some user performs
 * a change folder operation.
 * 
 * The operations are described below:
 * 
 * - "../" : Move to the parent folder of the current folder. (If you
 * are already in the main folder, remain in the same folder).
 * 
 * - "./" : Remain in the same folder.
 * 
 * - "x/" : Move to the child folder named x (This folder is guaranteed
 * to always exist).
 * 
 * You are given a list of strings logs where logs[i] is the operation
 * performed by the user at the ith step.
 * 
 * The file system starts in the main folder, then the operations in
 * logs are performed.
 * 
 * Return the minimum number of operations needed to go back to the
 * main folder after the change folder operations.
 *
 * Input
 * 
["d1/","d2/","../","d21/","./"]
 * 
 * Output
 * 
2
 * 
 * }</pre>
 */
public class Task1_Crawler_Log_Folder {

    public int minOperations(String[] logs) {
        int d = 0;
        for (int i = 0; i < logs.length; i++) {
            switch (logs[i]) {
            case "../": d = Math.max(0, d - 1); break;
            case "./": break;
            default: d++; break;
            }
        }
        return d;
    }

    @Test
    public void test() {
        assertEquals(0, minOperations(new String[] {"./", "./", "../"}));
        assertEquals(0, minOperations(new String[] {"./", "./", "./"}));
        assertEquals(0, minOperations(new String[] {"d1/","../","../","../"}));
        assertEquals(3, minOperations(new String[] {"d1/","d2/","./","d3/","../","d31/"}));
        assertEquals(2, minOperations(new String[] {"d1/","d2/","../","d21/","./"}));
    }
}
