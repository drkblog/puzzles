package ar.com.drk.puzzles.performance;

import com.google.common.collect.Lists;

import java.util.List;

public class LinkedVsArrayList {
  private final static int LOOPS = 10;
  public static final int ARRAY_SIZE = 10000000;

  public static void main(String[] args) {

    // Setup
    Integer[] numbers = new Integer[ARRAY_SIZE];
    for (int i = 0; i < ARRAY_SIZE; i++) {
      numbers[i] = i;
    }
    List<Integer> srcArrayList = Lists.newArrayList(numbers);
    List<Integer> srcLinkedList = Lists.newLinkedList(srcArrayList);

    // Test
    System.out.println("Add all to an ArrayList using ArrayList as source");
    testWithSourceList(srcArrayList, Lists.newArrayList());
    System.out.println("Add all to a LinkedList using ArrayList as source");
    testWithSourceList(srcArrayList, Lists.newLinkedList());
    System.out.println("Add all to an ArrayList using LinkedList as source");
    testWithSourceList(srcLinkedList, Lists.newArrayList());
    System.out.println("Add all to a LinkedList using LinkedList as source");
    testWithSourceList(srcLinkedList, Lists.newLinkedList());
  }

  private static void testWithSourceList(final List<Integer> source, final List<Integer> destination) {
    long total = 0;
    final StringBuilder log = new StringBuilder();
    for (int i = 0; i < LOOPS; i++) {
      long start = System.currentTimeMillis();
      destination.addAll(source);
      long end = System.currentTimeMillis();
      final long time = end - start;
      total += time;
      log.append("Benchmark: " + time + "\n");
    }
    log.append("Average: " + (total / LOOPS) + "ms");
    System.out.println(log.toString());
  }
}
