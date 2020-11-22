/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.codility.zalando;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <pre>{@code
 * 
 * Date: 27/07/2016
 * 
 * Zalando Codility
 * Problem: Task 1
 * Status: passed 75%
 * 
 * Problem
 * 
 * Given location of ships and hits return string "i,j" 
 * where i - number of sunk and j - number of nonsunk ships.
 * Location is given by coords of left upper and right lower
 * corners of the ships. 
 * 
 * Sample Input
 * 
1B 2C,2D 4D
2B 2D 3D 4D 4A
 * 
 * Sample Output
 * 
1,1
 * 
 * }</pre>
 */
public class Task1_Battleship {

    private static int[] parseCoord(String s) {
        int[] res = new int[2];
        int k = s.length() - 1;
        res[1] = s.charAt(k) - 'A' + 1;
        res[0] = Integer.parseInt(s.substring(0, k));
        return res;
    }
    
    private List<int[]> getShipsCoords(String S) {
        return Stream.of(S.split(","))
            .flatMap(s -> Stream.of(s.split(" ")))
            .map(s -> parseCoord(s)).collect(Collectors.toList());
    }
    
    public String solution(int N, String S, String T) {
        List<int[]> ships = getShipsCoords(S);
        List<int[]> hits = Stream.of(T.split(" "))
                .map(Task1_Battleship::parseCoord)
                .collect(Collectors.toList());
        int sunk = 0;
        int nonsunk = 0;
        for (int i = 0; i < ships.size(); i += 2) {
            int[] leftUp = ships.get(i);
            int[] rightDown = ships.get(i + 1);
            int max = (Math.abs(rightDown[0] - leftUp[0]) + 1) * (Math.abs(leftUp[1] - rightDown[1]) + 1);
            boolean isHit = false;
            for (int[] hit: hits) {
                if (leftUp[1] <= hit[1] && hit[1] <= rightDown[1])
                    if (hit[0] <= rightDown[0] && leftUp[0] <= hit[0]) {
                        max--;
                        isHit = true;
                    }
            }
            if (max == 0)
                sunk++;
            else if (isHit)
                nonsunk++;
        }
        return String.format("%d,%d", sunk, nonsunk);
    }
    
    public static String solve(int N, String S, String T) {
        return new Task1_Battleship().solution(N, S, T);
    }
    
    public static void main(String[] args) {
        assertEquals("1,1", solve(10, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
        assertEquals("0,1", solve(3, "1A 1B,2C 2C", "1B"));
        assertEquals("1,0", solve(3, "1A 1A", "1A"));
        assertEquals("1,0", solve(3, "1A 1A,2B 2B", "1A"));
        assertEquals("0,1", solve(6, "1A 1D", "1A"));
        assertEquals("1,0", solve(6, "1A 1D", "1A 1B 1C 1D"));
        assertEquals("0,0", solve(6, "1A 1D", "2A 2B 2C 2D"));
        assertEquals("0,1", solve(6, "1A 1D", "2A 2B 2C 1D"));
        assertEquals("0,1", solve(6, "11A 11D", "2A 2B 2C 11D"));
    }
    
}
