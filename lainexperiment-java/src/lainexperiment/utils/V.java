package lainexperiment.utils;

import static java.util.stream.Collectors.joining;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Vertex of a graph
 */
public class V implements Iterable<V> {

    public enum Color { W, G, B };

    /**
     * vertex = edge weight between this and vertex
     */
    public Map<V, Integer> adj = new HashMap<>();
    public boolean visited;
    public int id, w;
    public Color color;

    /**
     * Min distance from source vertex
     */
    public int d = Integer.MAX_VALUE;

    /**
     * Parent vertex
     */
    public V p;

    public V(int id) { this.id = id; }

    public void add(V v) {
        add(v, 0);
    }

    public void add(V v, int weight) {
        adj.put(v, weight);
    }

    public int weight(V u) {
        return adj.get(u);
    }

    public int distance() {
        return d;
    }

    @Override
    public String toString() {
        return id + ": " + adj.entrySet().stream()
                .map(e -> e.getKey().id + "<" + e.getValue() + ">")
                .collect(joining(" "));
    }

    @Override
    public Iterator<V> iterator() {
        return adj.keySet().iterator();
    }
}
