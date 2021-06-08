package ar.com.drk.puzzle.codility;

import java.util.Arrays;

public class GenomicRangeQuery {

  // https://app.codility.com/demo/results/trainingFT5DRC-YUD/

  public static void main(final String[] args) {
    final String str1 = "CAGCCTA";
    final int[] p1 = new int[]{2, 5, 0};
    final int[] q1 = new int[]{4, 5, 6};

    System.out.println("Result 1:" + Arrays.toString(solution(str1, p1, q1)));
  }

  static int[] solution(final String S, final int[] P, final int[] Q) {
    final int queries = P.length;
    final int[] result = new int[queries];
    final int[][] prefix = new int[4][S.length() + 1];
    for (int p = 0; p < S.length(); p++) {
      for (int o = 0; o < 4; o++) {
        if (o == getOrdinal(S.charAt(p))) {
          prefix[o][p + 1] = prefix[o][p] + 1;
        } else {
          prefix[o][p + 1] = prefix[o][p];
        }
      }
    }
    for (int q = 0; q < queries; q++) {
      while (!isPresent(prefix, P[q], Q[q], result[q])) result[q]++;
      result[q]++;
    }
    return result;
  }

  private static boolean isPresent(final int[][] prefix, final int p, final int q, final int n) {
    return prefix[n][q + 1] - prefix[n][p] > 0;
  }

  private static int getOrdinal(final char charAt) {
    switch (charAt) {
      case 'A':
        return 0;
      case 'C':
        return 1;
      case 'G':
        return 2;
      case 'T':
        return 3;
    }
    return -1;
  }
}
