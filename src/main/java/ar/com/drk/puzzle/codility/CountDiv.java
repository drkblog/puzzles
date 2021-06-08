package ar.com.drk.puzzle.codility;

import java.util.Arrays;

public class CountDiv {

  // Naive solution got 12% -> https://app.codility.com/demo/results/trainingMRESK7-2M2/
  // Passed https://app.codility.com/demo/results/trainingJSFNZV-ME4/
  // Passed formulae https://app.codility.com/demo/results/trainingTPB3NW-YYP/

  public static void main(final String[] args) {
    final int[][] testCases = new int[][]{
        {5, 11, 4, 1},
        {6, 11, 2, 3},
        {3, 111, 5, 22}
    };
    for (final int[] test : testCases) {
      final int result = solution(test[0], test[1], test[2]);
      System.out.println("For " + Arrays.toString(test) + ": " + result + ((result == test[3]) ? " OK" : " Wrong"));
    }
  }

  static int solution(final int A, final int B, final int K) {
    final int higher = B - (B % K);
    final int lower = A + ((K - (A % K)) % K);
    return (higher - lower) / K + 1;
  }
}
