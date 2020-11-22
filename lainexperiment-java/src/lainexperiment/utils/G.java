package lainexperiment.utils;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

import lainexperiment.utils.V.Color;

/**
 * Graph class. All vertices are indexed starting from 0.
 */
public class G implements Iterable<V> {

    public List<V> vertices;

    /**
     * Creates unconnected graph with n vertices
     */
    public G(int n) {
        this.vertices = range(0, n)
                .mapToObj(V::new)
                .collect(toList());
    }

    /**
     * G g = new G(new int[][] {
     *       {2, 4, 3},
     *       {1, 0, 4},
     *       {1, 4}});
     */
    public G(int[][] adjList) {
        this.vertices = range(0, adjList.length)
                .mapToObj(V::new)
                .collect(toList());
        range(0, adjList.length).forEach(i -> {
            Arrays.stream(adjList[i])
                .mapToObj(this::get)
                .forEach(u -> get(i).add(u));
        });
    }

    public V get(int i) { return vertices.get(i); }

    public int size() { return vertices.size(); }

    public void color(Color c) { stream().forEach(v -> v.color = c); }

    public Stream<V> stream() { return vertices.stream(); }

    public void connect(int from, int to, boolean isDirected) {
        connect(from, to, 0, isDirected);
    }

    public void connect(int from, int to, int weight, boolean isDirected) {
        V v = get(from);
        V u = get(to);
        v.add(u, weight);
        if (!isDirected)
            u.add(v, weight);
    }

    @Override
    public Iterator<V> iterator() {
        return vertices.iterator();
    }

    @Override
    public String toString() {
        var sj = new StringJoiner("\n");
        vertices.stream()
            .map(V::toString)
            .forEach(sj::add);
        return sj.toString();
    }
}
