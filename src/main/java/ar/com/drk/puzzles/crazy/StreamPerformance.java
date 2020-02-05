package ar.com.drk.puzzles.crazy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamPerformance {

    private static final int RUNS = 1000000;
    private static final int RUNS_LARGE = 100;
    public static void main(String[] args) {
        Integer[] numbers = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] largeNumbers = new Integer[10000];
        for(int i=0; i<largeNumbers.length; i++) largeNumbers[i] = i;

        long elapsedStreamSmall = runAndBenchmark(() -> {
            Set<Integer> set = Arrays.stream(numbers).collect(Collectors.toSet());
        }, "Stream small", RUNS);

        long elapsedForEachSmall = runAndBenchmark(() -> {
            Set<Integer> set = new HashSet<>();
            for (int num : numbers) {
                set.add(num);
            }
        }, "ForEach small", RUNS);

        System.out.println("Ratio " + (double)elapsedStreamSmall/elapsedForEachSmall);

        long elapsedStreamLarge =runAndBenchmark(() -> {
            Set<Integer> set = Arrays.stream(largeNumbers).collect(Collectors.toSet());
        }, "Stream large", RUNS_LARGE);

        long elapsedForEachLarge =runAndBenchmark(() -> {
            Set<Integer> set = new HashSet<>();
            for (int num : largeNumbers) {
                set.add(num);
            }
        }, "ForEach large", RUNS_LARGE);

        System.out.println("Ratio " + (double)elapsedStreamLarge/elapsedForEachLarge);
    }

    static long runAndBenchmark(Runnable code, String name, int runs) {
        long start = System.nanoTime();
        for(int i=0; i<=runs; i++) {
            code.run();
        }
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000;
        System.out.println(name + " - Elapsed " + elapsed + " milliseconds");
        return elapsed;
    }
}
