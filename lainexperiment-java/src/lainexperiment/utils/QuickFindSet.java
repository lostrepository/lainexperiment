package lainexperiment.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

/**
 * Disjoint set implementation based on Quick Find algorithm
 */
public class QuickFindSet {

    private int[] u;
    
    /**
     * Creates an empty set
     */
    public QuickFindSet(int size) {
        u = new int[size];
        Arrays.fill(u, -1);
    }
    
    /**
     * Returns group id of the item i.
     * If item i is not part of a group, its id equals -1,
     */
    public int getId(int item) {
        return u[item];
    }

    /**
     * Joins groups of a and b together. 
     */
    public void join(int a, int b) {
        if (u[a] == -1 && u[b] == -1) {
            u[a] = a;
            u[b] = a;
            return;
        }
        if (u[b] != -1 && u[a] == -1) {
            u[a] = u[b];
            return;
        }
        if (u[a] != -1 && u[b] == -1) {
            u[b] = u[a];
            return;
        }
        if (u[a] == u[b]) return;
        var t = u[b];
        for (int i = 0; i < u.length; i++) {
            if (u[i] == t) u[i] = u[a];
        }
    }
    
    public static void main(String[] args) {
        QuickFindSet qs = new QuickFindSet(10);
        qs.join(0, 1);
        qs.join(0, 5);
        assertEquals(0, qs.getId(5));

        qs.join(2, 5);
        assertEquals(0, qs.getId(2));
        
        qs.join(3, 4);
        assertEquals(3, qs.getId(3));
        
        qs.join(5, 4);
        assertEquals(0, qs.getId(3));

        qs.join(4, 6);
        assertEquals(0, qs.getId(3));
    }
}
