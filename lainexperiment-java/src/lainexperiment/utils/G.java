package lainexperiment.utils;

import static java.util.stream.Collectors.toList;

import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import static java.util.stream.IntStream.range;

import static java.util.Arrays.stream;

/**
 * Graph class. All vertices are indexed starting from 0.
 */
public class G implements Iterable<V> {

    public List<V> vertices;

    public G(int[][] adjList) {
        this.vertices = range(0, adjList.length)
                .mapToObj(V::new)
                .collect(toList());
        range(0, adjList.length).forEach(i -> {
            stream(adjList[i])
                .mapToObj(this::get)
                .forEach(u -> get(i).add(u));
        });
    }

    public V get(int i) { return vertices.get(i); }

    public int size() { return vertices.size(); }

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
