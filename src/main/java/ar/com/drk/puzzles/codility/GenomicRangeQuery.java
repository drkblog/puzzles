package ar.com.drk.puzzles.codility;

import com.sun.org.glassfish.gmbal.Impact;

import java.util.Arrays;

public class GenomicRangeQuery {

  // https://app.codility.com/demo/results/trainingFT5DRC-YUD/

  public static void main(String[] args) {
    String str1 = "CAGCCTA";
    int[] p1 = new int[] {2, 5, 0};
    int[] q1 = new int[] {4, 5, 6};

    System.out.println("Result 1:" + Arrays.toString(solution(str1, p1, q1)));
  }

  static int[] solution(String S, int[] P, int[] Q) {
    final int queries = P.length;
    final int[] result = new int[queries];
    final int[][] prefix = new int[4][S.length()+1];
    for(int p=0; p < S.length(); p++) {
      for(int o=0; o < 4; o++) {
        if (o == getOrdinal(S.charAt(p))) {
          prefix[o][p + 1] = prefix[o][p] + 1;
        } else {
          prefix[o][p + 1] = prefix[o][p];
        }
      }
    }
    for(int q=0; q < queries; q++) {
      while(!isPresent(prefix, P[q], Q[q], result[q])) result[q]++;
      result[q]++;
    }
    return result;
  }

  private static boolean isPresent(int[][] prefix, int p, int q, int n) {
    return prefix[n][q+1] - prefix[n][p] > 0;
  }

  private static int getOrdinal(final char charAt) {
    switch (charAt) {
      case 'A': return 0;
      case 'C': return 1;
      case 'G': return 2;
      case 'T': return 3;
    }
    return -1;
  }
}
