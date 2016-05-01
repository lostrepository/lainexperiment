/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

/*
 * Date: 30/04/2016
 * 
 * Problem
 * 
 * Implement radix sort
 *
 * 
 */

package lainexperiment.misc;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class RadixSort {

    static int WIDTH = 32;
    
    static int number(int n, int p) {
        if (p == 0) return 0;
        return String.format("%" + WIDTH + "d", n).replace(" ", "0").charAt(p - 1) - '0'; 
    }
    
    static List[] makeDecks() {
        List[] decks = new List[10];
        Arrays.setAll(decks, i -> new LinkedList());
        return decks;
    }
    
    static List radixSort(List[] decks, int p) {
        List[] newDecks = makeDecks();
        for (List deck: decks) {
            for (int n: (List<Integer>)deck) {
                newDecks[number(n, p)].add(n);
            }
        }
        if (p == 0) return newDecks[0];
        return radixSort(newDecks, p - 1);
    }
    
    static List radixSort(int[] a) {
        List[] decks = makeDecks();
        for (int n: a)
            decks[0].add(n);
        return radixSort(decks, WIDTH);
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(radixSort(new int[]{5, 34, 45, 2, 2345, 32, 45, 10, 6, 2}));
        System.out.println(radixSort(new int[]{34, 7, 3, 34, 798, 9, 3425, 568, 8, 675, 43}));
    }

}
