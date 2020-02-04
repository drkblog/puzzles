package ar.com.drk.puzzles.codility;

import com.sun.org.glassfish.gmbal.Impact;

import java.util.Arrays;

public class GenomicRangeQuery {

  public static void main(String[] args) {
    String str1 = "CAGCCTA";
    int[] p1 = new int[] {2, 5, 0};
    int[] q1 = new int[] {4, 5, 6};

    System.out.println("Result 1:" + Arrays.toString(solution(str1, p1, q1)));
  }

  static int[] solution(String S, int[] P, int[] Q) {
    final int queries = P.length;
    final int[] result = new int[queries];
    for(int q = 0; q < queries; q++) {
      int minimum = 3;
      for(int i = P[q]; i <= Q[q]; i++) {
        int iOrdinal = getOrdinal(S.charAt(i));
        if (iOrdinal < minimum) {
          minimum = iOrdinal;
        }
      }
      result[q] = minimum+1;
    }
    return result;
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
