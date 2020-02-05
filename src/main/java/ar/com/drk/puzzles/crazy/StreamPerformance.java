package ar.com.drk.puzzles.crazy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamPerformance {

    private static final int RUNS = 1000000;
    public static void main(String[] args) {
        Integer[] numbers = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        long start = System.nanoTime();
        for(int i=0; i<=RUNS; i++) {
            Arrays.stream(numbers).collect(Collectors.toSet());
        }
        long end = System.nanoTime();
        System.out.println("Stream Elapsed " + (end-start)/1000 + " milliseconds");

        start = System.nanoTime();
        for(int i=0; i<=RUNS; i++) {
            Set<Integer> set = new HashSet<>();
            for (int num : numbers) {
                set.add(num);
            }
        }
        end = System.nanoTime();
        System.out.println("ForEach Elapsed " + (end-start)/1000 + " milliseconds");
    }
}
