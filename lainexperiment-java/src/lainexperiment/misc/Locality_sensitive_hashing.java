/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */
/*
 * 
 * Date: 30/06/2018
 * 
 * Given set of strings S and a query string T. Print those strings in S
 * which are similar to T.
 * 
 */

package lainexperiment.misc;

import static java.lang.System.out;
import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Locality_sensitive_hashing {

    // hashband -> list of keys
    private Map<String, List<String>> index = new HashMap<>();

    public void add(String key, Minhash mh) {
        String[] hashbands = mh.getHashBands();
        for (int i=0; i<hashbands.length; i++) {
            String band = hashbands[i];
            index.computeIfAbsent(band, k -> new ArrayList<>());
            index.get(band).add(key);
        };
    }

    public Set<String> matches(Minhash mh) {
        Set<String> matches = new HashSet<>();
        String[] hashbands = mh.getHashBands();
        for (int i=0; i<hashbands.length; i++) {
            String band = hashbands[i];
            if (!index.containsKey(band)) continue;
            matches.addAll(index.get(band));
        };
        return matches;
    };

    public static void main(String[] args) {
        Minhash m1 = new Minhash();
        Minhash m2 = new Minhash();
        Minhash m3 = new Minhash();

        stream("there is no spoon".split(" ")).forEach(w -> m1.add(w));
        stream("there is no minhash".split(" ")).forEach(w -> m2.add(w));
        stream("the girl who leapt through time".split(" ")).forEach(w -> m3.add(w));

        Locality_sensitive_hashing index = new Locality_sensitive_hashing();
        index.add("test 1", m1);
        index.add("test 2", m2);
        index.add("test 3", m3);

        index.matches(m1).stream().forEach(out::println);
    }

}
