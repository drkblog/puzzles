package ar.com.drk.puzzles.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class MaxSliceSum {

  // https://app.codility.com/demo/results/trainingRXCD7M-52U/

  public static void main(final String[] args) {
    final List<TestCase> cases = Lists.newArrayList(
        new TestCase(new int[]{3, 2, -6, 4, 0}, 5),
        new TestCase(new int[]{1, 4, 4, 1, -5, 1, 1, 1}, 10),
        new TestCase(new int[]{-10}, -10),
        new TestCase(new int[]{-2, 1}, 1),
        new TestCase(new int[]{-2, -2}, -2)
    );
    cases.forEach(testCase -> {
      final int result = solution(testCase.dataset);
      System.out.println("Maximum slice for " + Arrays.toString(testCase.dataset) + ": " + result + " " + ((result == testCase.result) ? "OK" : "Wrong"));
    });
  }

  public static int solution(final int[] A) {
    if (A.length == 1) {
      return A[0];
    }
    int max_slice = Integer.MIN_VALUE;
    int max_ending = 0;
    for (int i = 0; i < A.length; i++) {
      max_ending = Math.max(A[i], max_ending + A[i]);
      max_slice = Math.max(max_slice, max_ending);
    }
    return max_slice;
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
