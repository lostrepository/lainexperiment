/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
package lainexperiment.utils.tree;
import static java.util.stream.Collectors.joining;
import java.util.Collections;
import lainexperiment.utils.N;
/**
 * <pre>{@code
 * N-ary tree
 * }</pre>
 */
public class T<V> {
    public N<V> root;

    public T(N<V> root) { this.root = root; }

    @Override
    public String toString() {
        var buf = new StringBuilder();
        buildString(root, buf, 0);
        return buf.toString();
    }

    private void buildString(N<V> n, StringBuilder buf, int offset) {
        buf.append(Collections.nCopies(offset, " ").stream()
            .collect(joining()));
        buf.append(n.val + "\n");
        n.childs().forEach(ch -> buildString(ch, buf, offset + 1));
    }
}
