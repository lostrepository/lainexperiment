/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc.graphs;

import lainexperiment.utils.G;
import lainexperiment.utils.N;
import lainexperiment.utils.V;
import lainexperiment.utils.tree.T;

/**
 * <pre>{@code
 * Date: 07/12/2019
 * 
 * Problem
 * 
 * Build random spanning tree for a graph
 * 
 * Input Format
 * 
 * Given N lines. Each line i will contain n digits which are
 * adjacent vertices of the vertex i
 * 
 * Input
 * 
2 4
2 4 3
1 0 4
1 4
3 1 2 0
 * 
 * Output
 * 
4
 2
  0
  1
   3
 * 
 * }</pre>
 */
public class Find_random_spanning_tree {

    static N<Integer> buildTree(V v) {
        N<Integer> n = new N<>(v.id);
        v.visited = true;
        for (V u: v) {
            if (u.visited) continue;
            n.add(buildTree(u));
        }
        return n;
    }

    public static void main(String[] args) {
        G g = new G(new int[][] {
            {2, 4},
            {2, 4, 3},
            {1, 0, 4},
            {1, 4},
            {3, 1, 2, 0}});
        T<Integer> tree = new T<>(buildTree(g.get((int) (Math.random() * g.size()))));
        System.out.println(tree);
    }
    
}
