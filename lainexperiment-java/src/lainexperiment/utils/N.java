package lainexperiment.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
/**
 * Tree node
 */
public class N<V> {
    public List<N<V>> childs;
    public V val;
    public Stream<N<V>> childs() {
        return childs.stream();
    }
    public N(V val) { this.val = val; this.childs = new ArrayList<>(); }
    public N(V val, List<N<V>> childs) { this(val); this.childs = childs; }
    public N<V> add(N<V> child) { childs.add(child); return this; }
}
