/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._222;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.PairInt;

/**
 * <pre>{@code
 * Date: 02/01/2021
 * 
 * Problem: Maximum Units on a Truck
 * Status: accepted
 * 
 * You are assigned to put some amount of boxes onto one
 * truck. You are given a 2D array boxTypes, where
 * boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi]:
 *  - numberOfBoxesi is the number of boxes of type i.
 *  - numberOfUnitsPerBoxi is the number of units in each
 *    box of the type i.
 * 
 * You are also given an integer truckSize, which is the
 * maximum number of boxes that can be put on the truck.
 * You can choose any boxes to put on the truck as long as
 * the number of boxes does not exceed truckSize.
 * 
 * Return the maximum total number of units that can be
 * put on the truck.
 *
 * Example 1:
 * 
boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
 * 
 * Output
 * 
8
 * 
 * }</pre>
 */
public class Task1_Maximum_Units_on_a_Truck {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        var l = new ArrayList<PairInt>();
        for (int i = 0; i < boxTypes.length; i++) {
            l.add(new PairInt(boxTypes[i][0], boxTypes[i][1]));
        }
        l.sort(Comparator.comparingInt(PairInt::getY).reversed());
        int boxNum = 0;
        int units = 0;
        while (boxNum < truckSize && !l.isEmpty()) {
            PairInt box = l.get(0);
            if (box.a == 0) {
                l.remove(0);
                continue;
            }
            units += box.b;
            box.a--;
            boxNum++;
        }
        return units;
    }

    @Test
    public void test() {
        assertEquals(8, maximumUnits(new int[] []{
            {1,3},
            {2,2},
            {3,1}
        }, 4));
        assertEquals(91, maximumUnits(new int[] []{
            {5,10},
            {2,5},
            {4,7},
            {3,9}
        }, 10));
    }
}
