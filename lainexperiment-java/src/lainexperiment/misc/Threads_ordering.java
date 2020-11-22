/*
 * Date: 21/10/2019
 * 
 * Problem
 * 
 * There are methods m1, m2, m3 and 3 threads each calling its own method.
 * Make sure that threads can run methods in the following order:
 * 
 * 1. Thread 1 runs m1
 * 2. Thread 2 runs m2
 * 3. Thread 3 runs m3
 *
 * Output
 * 
123
123
123
...
 * 
 */
package lainexperiment.misc;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Semaphore;
public class Threads_ordering {
    Semaphore s2 = new Semaphore(1);
    Semaphore s3 = new Semaphore(1);
    void m1() {
        System.out.print("1");
        s2.release();
    }
    void m2() {
        try {
            s2.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.print("2");
        s3.release();
    }
    void m3() {
        try {
            s3.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("3");
    }
    public static void main(String[] args) throws InterruptedException {
        var v = new Threads_ordering();
        v.s2.acquire();
        v.s3.acquire();
        while (true) {
            ForkJoinPool.commonPool().submit(() -> v.m2());
            ForkJoinPool.commonPool().submit(() -> v.m1());
            ForkJoinPool.commonPool().submit(() -> v.m3());
            Thread.sleep(100);
        }
    }
}
