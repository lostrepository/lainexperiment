/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 04/07/2015
 * 
 * Given a string build a suffix array for it.
 * 
 * Input Format
 * 
 * String
 * 
 * Output Format
 * 
 * Suffix array
 * 
 * Sample Input
 * 
banana
 * 
 * Sample Output
 * 
4, 3, 6, 2, 5, 1
 * 
 */

package lainexperiment.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuffixTree {

    static class Edge {
        // suffix from the original string [s, e)
        int s, e;
        Node to;
        char[] a;
        public Edge(char[] a, int s, int e, Node to) {
            assertTrue(s < e);
            this.s = s;
            this.e = e;
            this.to = to;
            this.a = a;
        }
        @Override
        public String toString() {
            return new String(a, s, e - s);
        }
    }
    
    static class Node {
        Map<Character, Edge> edges = new HashMap<>(26);
        // suffix link
        Node link;
    }

    static class ActivePoint {
        Node node;
        Edge edge;
        int len;
        void reset(Node n) {
            node = n;
            edge = null;
            len = 0;
        }
        boolean isSet() {
            return len != 0;
        }
        char getChar(char[] a) {
//            if (edge.s + len > edge.e) return 0;
            return a[edge.s + len - 1];
        }
        void set(Node n) {
            node = n;
        }
        void set(char ch, int d) {
            edge = node.edges.get(ch);
            len += d;
        }
        boolean increase() {
            len++;
            if (len > edge.e - edge.s) {
                node = edge.to;
                len = 1;
                return false;
            }
            return true;
        }
        public void split(char[] a) {
            Edge e1 = edge;
            Edge e2 = new Edge(a, e1.s + len, e1.e, e1.to);
            e1.e = e1.s + len;
            e1.to = new Node();
            e1.to.edges.put(a[e1.s + len], e2);
        }
        public void followLink(char[] a, Node link) {
            node = link;
            edge = node.edges.get(a[edge.s]);
        }
    }
    
    static ActivePoint activePoint;
    // how many new suffixes we need to insert
    static int remainder;
    // for suffix links
    static Node tmpNode;

    static void addSuffix(Node root, char[] A, int i) {
        Map<Character, Edge> edges = activePoint.node.edges;
        if (!edges.containsKey(A[i]) && !activePoint.isSet()) {
            edges.put(A[i], new Edge(A, i, A.length, new Node()));
            remainder = 1;
            return;
        }
        if (!activePoint.isSet()) {
            activePoint.set(A[i], +1);
            activePoint.set(root);
            remainder++;
            return;
        }
        char ch = activePoint.getChar(A);
        if (ch == A[i]) {
            if (!activePoint.increase()) {
//                addSuffix(root, A, i);
                remainder++;
                return;
            } else
                remainder++;
            return;
        }
//        if (ch == 0) {
//            activePoint.reset(activePoint.edge.to);
//        } else {
            activePoint.split(A);
            activePoint.edge.to.edges.put(A[i], new Edge(A, i, A.length, new Node()));
            if (tmpNode != null) 
                tmpNode.link = activePoint.edge.to;
//                tmpNode.link = activePoint.node;
            tmpNode = activePoint.edge.to;
//            tmpNode = activePoint.node;
            if (activePoint.node == root)
                //activePoint.set(A[activePoint.edge.s + 1], -1);
//                activePoint.set(A[activePoint.edge.s], -1);
                activePoint.set(A[i - activePoint.len + 1], -1);
            else if (activePoint.node.link != null)
                activePoint.set(activePoint.node.link);
//                activePoint.followLink(A, activePoint.node.link);
            else
                activePoint.set(root);
            remainder--;
//        }
        if (remainder > 0)
            addSuffix(root, A, i);
    }
    
    static Node suffixTree(char[] a) {
        Node root = new Node();
        activePoint = new ActivePoint();
        activePoint.reset(root);
        for (int i = 0; i < a.length; ++i) {
            System.out.println("Adding " + new String(a, i, a.length - i));
            addSuffix(root, a, i);
            tmpNode = null;
            printSuffixTrie(a, root, 0);
            System.out.println("-------------------------------");
        }
        if (remainder != 1) {
            
        }
        return root;
    }
    
    /*
     * Output methods
     * 
     */
    
    static void retrieveSuffixes(char[] a, Node node, String buf, List<String> suffixes) {
        if (node.edges.isEmpty()) {
            suffixes.add(buf);
            return;
        }
        node.edges.forEach((ch, e) -> {
            retrieveSuffixes(a, e.to, buf + new String(a, e.s, e.e - e.s), suffixes);
        });
    }

    static String[] retrieveSuffixes(char[] a, Node node) {
        List<String> l = new ArrayList<>();
        retrieveSuffixes(a, node, "", l);
        return l.toArray(new String[0]);
    }
    
    static void printSuffixTrie(char[] a, Node t, int offset) {
        t.edges.forEach((ch, e) -> {
            String pref = offset > 0? String.format("%" + offset + "s", ""): "";
            System.out.println(pref + new String(a, e.s, e.e - e.s));
            printSuffixTrie(a, e.to, offset + 1);
        });
    }
    
    public static void main(String[] args) {
        char[] a = null;
        Node t = null;
        
        a = "abcabxabcd".toCharArray();
        t = suffixTree(a);
        printSuffixTrie(a, t, 0);
        assertEquals(a.length, retrieveSuffixes(a, t).length);
        
        a = "banana#".toCharArray();
        t = suffixTree(a);
        printSuffixTrie(a, t, 0);
        assertEquals(a.length, retrieveSuffixes(a, t).length);
        
//        a = "mississippi".toCharArray();
//        t = suffixTree(a);
//        printSuffixTrie(a, t, 0);
//        assertEquals(a.length, retrieveSuffixes(a, t).length);
        
    }

}
