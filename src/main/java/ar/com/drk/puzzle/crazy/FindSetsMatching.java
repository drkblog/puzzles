package ar.com.drk.puzzle.crazy;

import lombok.Value;

public class FindSetsMatching {

  @Value
  static class Pair<L, R> {
    private L left;
    private R right;
  }

  public static void main(final String[] args) {
    final int[] a = new int[]{2, 3, 7, 8};
    final int[] b = new int[]{1, 2, 9, 15};

    final int target = 14;
    final int left = 0;
    int right = b.length - 1;
    boolean finished = false;
    Pair<Integer, Integer> lastHighest = null;
    Pair<Integer, Integer> lastLowest = null;
    Pair<Integer, Integer> exact = null;
    while (left < a.length && right > 0 && !finished) {
      final long current = a[left] + b[right];
      if (current > target) {
        lastHighest = new Pair<>(left, right);
        right--;
      } else if (current < target) {
        lastLowest = new Pair<>(left, right);
        finished = true;
      } else {
        exact = new Pair<>(left, right);
        finished = true;
      }
    }
    System.out.println("exact " + exact);
    System.out.println("lastHighest " + lastHighest);
    System.out.println("lastLowest " + lastLowest);
  }
}
