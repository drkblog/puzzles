package ar.com.drk.scratch;

import java.util.LinkedList;
import java.util.Queue;

public class Scratch {

  public static void main(final String[] args) {
    final Queue<Integer> integers = new LinkedList<>();
    doMagic(integers);
    while (!integers.isEmpty()) {
      System.out.println(integers.poll());
    }
  }

  private static void doMagic(final Queue<Integer> queue) {
    final Queue rawQueue = queue;
    rawQueue.add(new Object() {
      @Override
      public String toString() {
        rawQueue.add(this);
        return "X";
      }
    });
  }
}