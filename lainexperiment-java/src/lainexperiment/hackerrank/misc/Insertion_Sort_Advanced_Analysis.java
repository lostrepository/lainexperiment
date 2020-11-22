/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.hackerrank.misc;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * <pre>{@code
 * Date: 08/11/2020
 *
 * Hacker rank
 * Problem: Insertion Sort Advanced Analysis
 * Status: accepted
 * 
 * Problem
 * 
 * Insertion Sort is a simple sorting technique which was covered in
 * previous challenges. Sometimes, arrays may be too large for us to
 * wait around for insertion sort to finish. Is there some other way
 * we can calculate the number of shifts an Insertion Sort performs
 * when sorting an array?
 * 
 * If k[i] is the number of elements over which the ith element of
 * the array has to shift, then the total number of shifts will be
 * k[1] + k[2] + ... + k[n]. For example, consider the array: 4 3 2 1
 * 
 *  Array       Shifts
 *  [4,3,2,1]   
 *  [3,4,2,1]   1
 *  [2,3,4,1]   2
 *  [1,2,3,4]   3
 * 
 * Total shifts = 1 + 2 + 3 = 6
 * 
 * Input Format
 * 
 * The first line contains a single integer t the number of queries to perform.
 * 
 * The following t pairs of lines are as follows:
 * 
 * - The first line contains an integer n the length of array
 * - The second line contains n space-separated integers of array
 * 
 * Output Format
 * 
 * Print t lines containing the number of shifts for each query.
 * 
 * Input
 * 
9
5
5 2 3 4 1
3
3 2 1
4
5 4 6 7
4
2 1 4 3
5  
1 1 1 2 2  
5  
2 1 3 1 2
5
8 1 2 3 4
7
1 3 5 7 2 4 6
3
2 12 3
 * 
 * Output
 * 
7
3
1
2
0
4
4
6
1
 * 
 * }</pre>
 */
public class Insertion_Sort_Advanced_Analysis {

    long R = 0;

    int[] mergeSort(int[] a, int s, int e) {
        if (s > e) return new int[0];
        if (s == e) {
            return new int[] {a[s]};
        }
        if (e - s == 1) {
            int[] r = new int[2];
            r[0] = Math.min(a[s], a[e]);
            r[1] = Math.max(a[s], a[e]);
            R += (a[s] <= a[e])? 0: 1;
            return r;
        }
        int m = (s + e) / 2;
        int[] l = mergeSort(a, s, m);
        int[] r = mergeSort(a, m + 1, e);
        a = new int[l.length + r.length];
        int li = 0, ri = 0;
        int i = 0;
        while (i < a.length) {
            if (li == l.length || ri == r.length)
                break;
            if (l[li] <= r[ri]) {
                a[i] = l[li];
                li++;
            } else {
                R += l.length - li;
                a[i] = r[ri];
                ri++;
            }
            i++;
        }
        while (li < l.length) {
            a[i++] = l[li++];
        }
        while (ri < r.length) {
            a[i++] = r[ri++];
        }
        return a;
    }

    long solve(int[] a) {
        //System.out.println(Arrays.toString(a));
        a = mergeSort(a, 0, a.length - 1);
        //System.out.println(Arrays.toString(a));
        return R;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = System.getProperty("local") == null? 
                new Scanner(System.in): new Scanner(Insertion_Sort_Advanced_Analysis.class.getResourceAsStream(
                    "Insertion_Sort_Advanced_Analysis.in"));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] a = IntStream.generate(() -> scanner.nextInt())
                .limit(n)
                .toArray();
            long r = new Insertion_Sort_Advanced_Analysis().solve(a);
            System.out.println(r);
        }
        scanner.close();
    }

}
