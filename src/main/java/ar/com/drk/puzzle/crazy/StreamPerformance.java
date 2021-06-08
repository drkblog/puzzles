package ar.com.drk.puzzle.crazy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamPerformance {

  private static final int RUNS = 1000000;
  private static final int RUNS_LARGE = 100;

  public static void main(final String[] args) {
    final Integer[] numbers = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    final Integer[] largeNumbers = new Integer[1000];
    for (int i = 0; i < largeNumbers.length; i++) largeNumbers[i] = i;


    final long elapsedStreamSmall = runAndBenchmark(() -> {
      final Set<Integer> set = Arrays.stream(numbers).collect(Collectors.toSet());
    }, "Stream small", RUNS);

    final long elapsedStreamDistinctSmall = runAndBenchmark(() -> {
      final long n = Arrays.stream(numbers).distinct().count();
    }, "StreamDistinct small", RUNS);

    final long elapsedForEachSmall = runAndBenchmark(() -> {
      final Set<Integer> set = new HashSet<>();
      for (final int num : numbers) {
        set.add(num);
      }
    }, "ForEach small", RUNS);

    System.out.println("Ratio " + (double) elapsedStreamSmall / elapsedForEachSmall);

    final long elapsedStreamLarge = runAndBenchmark(() -> {
      final Set<Integer> set = Arrays.stream(largeNumbers).collect(Collectors.toSet());
    }, "Stream large", RUNS_LARGE);

    final long elapsedStreamDistinctLarge = runAndBenchmark(() -> {
      final long n = Arrays.stream(largeNumbers).distinct().count();
    }, "StreamDistinct large", RUNS_LARGE);

    final long elapsedForEachLarge = runAndBenchmark(() -> {
      final Set<Integer> set = new HashSet<>();
      for (final int num : largeNumbers) {
        set.add(num);
      }
    }, "ForEach large", RUNS_LARGE);

    System.out.println("Ratio " + (double) elapsedStreamLarge / elapsedForEachLarge);
  }

  static long runAndBenchmark(final Runnable code, final String name, final int runs) {
    final long start = System.nanoTime();
    for (int i = 0; i <= runs; i++) {
      code.run();
    }
    final long end = System.nanoTime();
    final long elapsed = (end - start) / 1000;
    System.out.println(name + " - Elapsed " + elapsed + " milliseconds");
    return elapsed;
  }
}
