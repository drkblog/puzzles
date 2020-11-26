package ar.com.drk.puzzles.crazy;

import java.util.Arrays;

public class FirstRepeated1toN {
  public static void main(String[] args) {
    final int[] test = new int[]{2, 1, 3, 5, 3, 4, 2};
    System.out.println("First repeated in " + Arrays.toString(test) + " = " + solution(test));
  }

  private static int solution(int[] A) {
    int offset = A.length + 1;
    for (int i = 0; i < A.length; i++) {
      int pointer = A[i];
      if (pointer > offset) {
        return pointer - offset;
      }
      A[pointer] += offset;
    }
    return -1;
  }
}
