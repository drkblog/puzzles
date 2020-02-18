package ar.com.drk.puzzles.codility;

import java.util.Arrays;

public class Distinct {
  public static void main(String[] args) {
    final int[] test = new int[]{2, 1, 1, 2, 3, 1};
    System.out.println("Distinct in " + Arrays.toString(test) + " is " + solution(test));
  }

  static int solution(int[] A) {
    if (A.length == 0) return 0;
    if (A.length == 1) return 1;
    int distinct = 1;
    Arrays.sort(A);
    for (int i = 1; i < A.length; i++) {
      if (A[i - 1] != A[i]) {
        distinct++;
      }
    }

    return distinct;
  }


}
