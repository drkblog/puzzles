package ar.com.drk.puzzles.crazy;

import javafx.util.Pair;

public class FindSetsMatching {

  public static void main(String[] args) {
    int[] a = new int[]{2, 3, 7, 8};
    int[] b = new int[]{1, 2, 9, 15};

    int target = 14;
    int left = 0;
    int right = b.length - 1;
    boolean finished = false;
    Pair<Integer, Integer> lastHighest = null;
    Pair<Integer, Integer> lastLowest = null;
    Pair<Integer, Integer> exact = null;
    while (left < a.length && right > 0 && !finished) {
      long current = a[left] + b[right];
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
