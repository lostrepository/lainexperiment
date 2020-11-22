/* 
 *
 * This source file is a part of lainexperiment project.
 * Description for it can be found in ReadMe.txt.
 *
 */

package lainexperiment.misc;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * <pre>{@code
 * 
 * Date: 30/06/2018
 * 
 * Given a string calculate minhash for it.
 * 
 * }</pre>
 */
public class Minhash {
    private static final int NUMPERM = 128;
    private static final int BANDSIZE = 4;

    private static int[] permA = permutations();
    private static int[] permB = permutations();

    private static final int PRIME = 2147483647;
    private static final int MAXHASH = Integer.MAX_VALUE;

    private final int[] hashvalues = new int[NUMPERM];

    private Supplier<String[]> HASH_BANDS = () -> {
        String[] hashBands = new String[hashvalues.length / BANDSIZE];
        for (int i = 0; i < hashBands.length; i++) {
            int start = i * BANDSIZE;
            int end = start + BANDSIZE;
            int[] band = Arrays.copyOfRange(hashvalues, start, end);
            hashBands[i] = stream(band).mapToObj(String::valueOf).collect(joining("."));
        };
        return hashBands;
    };

    public Minhash() {
        Arrays.fill(hashvalues, MAXHASH);
    }

    public Minhash(String str) {
        this();
        stream(str.split(" ")).forEach(this::add);
    }

    public void add(String str) {
        for (int i = 0; i < hashvalues.length; i++) {
            long a = permA[i];
            long b = permB[i];
            int hash = (int) ((a * hash(str) + b) % PRIME);
            //System.out.println(hash);
            if (hash < hashvalues[i])
                hashvalues[i] = hash;
        }
    }

    // jaccard
    public double distance(Minhash other) {
        double shared = 0;
        for (int i = 0; i < hashvalues.length; i++) {
            if (hashvalues[i] == other.hashvalues[i]) shared++;
        }
        return shared / (2 * hashvalues.length - shared);
    }

    public String[] getHashBands() {
        return HASH_BANDS.get();
    }

    private static int[] permutations() {
        Random rand = new Random();
        Set<Integer> used = new HashSet<>();
        for (int j = 0; j < NUMPERM; j++) {
            int randInt = rand.nextInt(Integer.MAX_VALUE);
            while (used.contains(randInt))
                randInt = rand.nextInt(Integer.MAX_VALUE);
            used.add(randInt);
        }
        return used.stream().mapToInt(Integer::intValue)
                .toArray();
    }

    private int hash(String str) {
        // hash code should be non negative
        return str.hashCode() & Integer.MAX_VALUE;
    }

    static void reset() {
        permA = permutations();
        permB = permutations();
    }

    public static void main(String[] args) {
        IntStream.range(0, 1000)
            .mapToDouble(i -> {
                reset();
                Minhash m1 = new Minhash("there is no spoon");
                Minhash m2 = new Minhash("there is no minhash");
                return m1.distance(m2);
            })
            .sorted()
            .forEach(System.out::println);
    }

}
