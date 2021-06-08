package ar.com.drk.puzzle.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class NumberOfDiscIntersections {

  // N^2 https://app.codility.com/demo/results/trainingC5R4UX-ZVR/
  // O(n) https://app.codility.com/demo/results/trainingEEUT75-56B/

  public static void main(final String[] args) {
    final List<TestCase> cases = Lists.newArrayList(
        new TestCase(new int[]{1, 5, 2, 1, 4, 0}, 11),
        new TestCase(new int[]{1, 1, 1}, 3),
        new TestCase(new int[]{10}, 0),
        new TestCase(new int[]{2147483647, 2147483647}, 1)
    );
    cases.forEach(testCase -> {
      final int solution = solution(testCase.dataset);
      System.out.println(Arrays.toString(testCase.dataset));
      System.out.println("Intersections: " + solution + " " + ((solution == testCase.result) ? "OK" : "Wrong"));
    });
    ;
  }

  static int solution(final int[] A) {
    if (A.length < 2) {
      return 0;
    }
    long carry = 0;
    final long[] start = new long[A.length];
    final long[] stop = new long[A.length];
    for (int i = 0; i < A.length; i++) {
      final long starts = (long) i - A[i];
      if (starts >= 0) {
        start[(int) starts]++;
      } else {
        carry++;
      }
      final long finishes = (long) i + A[i];
      if (finishes < stop.length) {
        stop[(int) finishes]++;
      }
    }
    int intersections = (int) combinationsWithoutRepetitionsOfTwo(carry);
    for (int i = 0; i < A.length; i++) {
      intersections += (carry * start[i]) + combinationsWithoutRepetitionsOfTwo(start[i]);
      carry += start[i];
      carry -= stop[i];
    }
    return (intersections <= 10000000) ? intersections : -1;
  }

  static long combinationsWithoutRepetitionsOfTwo(final long n) {
    return (n * (n - 1)) / 2;
  }

  static class TestCase {
    int[] dataset;
    int result;

    public TestCase(final int[] dataset, final int result) {
      this.dataset = dataset;
      this.result = result;
    }
  }
}
