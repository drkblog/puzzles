package ar.com.drk.puzzle.crazy;

import java.util.Arrays;

public class FirstRepeated1toN {
  public static void main(final String[] args) {
    final int[] test = new int[]{2, 1, 3, 5, 3, 4, 2};
    System.out.println("First repeated in " + Arrays.toString(test) + " = " + solution2(test));
  }

  private static int solution1(final int[] A) {
    final boolean[] marker = new boolean[A.length];
    for (int i = 0; i < A.length; i++) {
      if (marker[A[i]]) {
        return A[i];
      }
      marker[A[i]] = true;
    }
    return -1;
  }

  private static int solution2(final int[] A) {
    final int offset = A.length + 1;
    for (int i = 0; i < A.length; i++) {
      final int pointer = A[i] > offset ? A[i] - offset : A[i];
      if (A[pointer] > offset) {
        return pointer;
      }
      A[pointer] += offset;
    }
    return -1;
  }
}
