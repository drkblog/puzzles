package ar.com.drk.puzzles.codility;

import java.util.Arrays;

public class MissingInteger {
  public static void main(String[] args) {
    int[] c1 = new int[]{1, 2, 3, 4};
    int[] c2 = new int[]{1, 2, 3, 5};
    int[] c3 = new int[]{1, 2, 3, 4, 5, 6, 8, 9};
    System.out.println("Missing " + Arrays.toString(c1) + ": " + solution(c1));
    System.out.println("Missing " + Arrays.toString(c2) + ": " + solution(c2));
    System.out.println("Missing " + Arrays.toString(c3) + ": " + solution(c3));
  }

  static int solution(int[] A) {
    int missing = 1;
    for (int value : A) {
      if (value == missing) {
        missing++;
      }
    }
    return missing;
  }
}
