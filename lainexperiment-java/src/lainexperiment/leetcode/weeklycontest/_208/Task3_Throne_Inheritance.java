/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.leetcode.weeklycontest._208;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import lainexperiment.utils.N;
import lainexperiment.utils.tree.T;

/**
 * <pre>{@code
 * Date: 15/03/2020
 * 
 * Problem: Throne Inheritance
 * Status: accepted
 * 
 * A kingdom consists of a king, his children, his grandchildren, and so on.
 * Every once in a while, someone in the family dies or a child is born.
 * 
 * The kingdom has a well-defined order of inheritance that consists of the
 * king as the first member. Let's define the recursive function
 * Successor(x, curOrder), which given a person x and the inheritance order
 * so far, returns who should be the next person after x in the order of
 * inheritance.
 * 
 * For example, assume we have a kingdom that consists of the king, his
 * children Alice and Bob (Alice is older than Bob), and finally Alice's
 * son Jack.
 * 
 * - In the beginning, curOrder will be ["king"].
 * - Calling Successor(king, curOrder) will return Alice, so we append to
 *   curOrder to get ["king", "Alice"].
 * - Calling Successor(Alice, curOrder) will return Jack, so we append to
 *   curOrder to get ["king", "Alice", "Jack"].
 * - Calling Successor(Jack, curOrder) will return Bob, so we append to
 *   curOrder to get ["king", "Alice", "Jack", "Bob"].
 * - Calling Successor(Bob, curOrder) will return null. Thus the order of
 *   inheritance will be ["king", "Alice", "Jack", "Bob"].
 *   
 * Using the above function, we can always obtain a unique order of inheritance.
 * 
 * Implement the ThroneInheritance class:
 * 
 * - ThroneInheritance(string kingName) Initializes an object of the
 *   ThroneInheritance class. The name of the king is given as part of
 *   the constructor.
 * - void birth(string parentName, string childName) Indicates that
 *   parentName gave birth to childName.
 * - void death(string name) Indicates the death of name. The death of the
 *   person doesn't affect the Successor function nor the current inheritance
 *   order. You can treat it as just marking the person as dead.
 * - string[] getInheritanceOrder() Returns a list representing the current
 *   order of inheritance excluding dead people.
 *
 * Input
 * 
"ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth", "getInheritanceOrder", "death", "getInheritanceOrder"
["king"], ["king", "andy"], ["king", "bob"], ["king", "catherine"], ["andy", "matthew"], ["bob", "alex"], ["bob", "asha"], [null], ["bob"], [null]
 * 
 * Output
 * 
null, null, null, null, null, null, null, ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"], null, ["king", "andy", "matthew", "alex", "asha", "catherine"]
 * 
 * }</pre>
 */
public class Task3_Throne_Inheritance {

    class ThroneInheritance {

        class Node extends N<String> {
            boolean dead;
            public Node(String val) {
                super(val);
            }
        }
        
        T<String> tree;
        Map<String, Node> parents = new HashMap<>();
        
        public ThroneInheritance(String kingName) {
            var n = new Node(kingName);
            tree = new T<>(n);
            parents.put(kingName, n);
        }
        
        public void birth(String parentName, String childName) {
            Node child = new Node(childName);
            parents.get(parentName).add(child);
            parents.put(childName, child);
        }
        
        public void death(String name) {
            parents.get(name).dead = true;
        }
        
        public List<String> getInheritanceOrder() {
            var l = new ArrayList<String>();
            preorder((Node)tree.root, l);
            return l;
        }

        private void preorder(Node n, List<String> out) {
            if (!n.dead)
                out.add(n.val);
            n.childs().forEach(ch -> preorder((Node) ch, out));;
        }
    }
    
    List<List<String>> execute(List<String> cmds, List<List<String>> args) {
        ThroneInheritance t = null;
        var out = new ArrayList<List<String>>();
        for (int i = 0; i < cmds.size(); i++) {
            var a = args.get(i);
            switch (cmds.get(i)) {
            case "ThroneInheritance":
                t = new ThroneInheritance(a.get(0));
                out.add(null);
                break;
            case "birth": {
                t.birth(a.get(0), a.get(1));
                out.add(null);
                break;
            }
            case "death": {
                t.death(a.get(0));
                out.add(null);
                break;
            }
            case "getInheritanceOrder": {
                out.add(t.getInheritanceOrder());
                break;
            }
            }
        }
        return out;
    }
    
    @Test
    public void test() {
        var expected = Arrays.asList(null, null, null, null, null, null, null,
            List.of("king", "andy", "matthew", "bob", "alex", "asha", "catherine"), null,
            List.of("king", "andy", "matthew", "alex", "asha", "catherine"));
        assertEquals(expected, execute(
                List.of("ThroneInheritance", "birth", "birth", "birth", "birth", "birth", "birth",
                    "getInheritanceOrder", "death", "getInheritanceOrder"),
                Arrays.asList(List.of("king"), List.of("king", "andy"), List.of("king", "bob"),
                    List.of("king", "catherine"), List.of("andy", "matthew"), 
                    List.of("bob", "alex"), List.of("bob", "asha"), null, List.of("bob"), null)));
    }
}
