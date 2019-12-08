package lainexperiment.utils;

import static java.util.stream.Collectors.joining;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Vertex of a graph
 */
public class V implements Iterable<V> {
    public Set<V> adj = new HashSet<>();
    public boolean visited;
    public int id, w;

    public V(int id) { this.id = id; }

    public void add(V u) {
        adj.add(u);
    }

    @Override
    public String toString() {
        return id + ": " + adj.stream().map(v -> "" + v.id).collect(joining(" "));
    }

    @Override
    public Iterator<V> iterator() {
        return adj.iterator();
    }
}
