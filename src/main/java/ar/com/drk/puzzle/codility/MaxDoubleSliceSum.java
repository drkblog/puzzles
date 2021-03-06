package ar.com.drk.puzzle.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class MaxDoubleSliceSum {

  // Awful

  public static void main(final String[] args) {
    final List<TestCase> cases = Lists.newArrayList(
        new TestCase(new int[]{3, 2, 6, -1, 4, 5, -1, 2}, 17)
    );
    cases.forEach(testCase -> {
      final int result = solution(testCase.dataset);
      System.out.println("Maximum double slice for " + Arrays.toString(testCase.dataset) + ": " + result + " " + ((result == testCase.result) ? "OK" : "Wrong"));
    });

  }

  public static int solution(final int[] A) {
    if (A.length == 3) {
      return 0;
    }
    final int[] minimums = new int[A.length];
    int minimumsIndex = 0;
    int localMinimum = 0, localMinimumIndex = 0;
    boolean found = false;
    for (int i = 0; i < A.length; i++) {
      final int value = A[i];
      if (value > 0) {
        if (found) {
          minimums[minimumsIndex++] = localMinimumIndex;
        }
        localMinimum = 0;
        found = false;
      } else {
        if (value < localMinimum) {
          localMinimum = value;
          localMinimumIndex = i;
          found = true;
        }
      }
    }
    minimums[minimumsIndex++] = A.length - 1;

    final int[] sums = new int[A.length];
    int sumsIndex = 0;
    int start = 0;
    for (int i = 0; i < minimumsIndex; i++) {
      sums[sumsIndex++] = sumUpTo(A, start + 1, minimums[i]);
      start = minimums[i];
    }

    int maxDoubleSlice = 0;
    for (int i = 1; i < sumsIndex; i++) {
      final int doubleSum = sums[i - 1] + sums[i];
      if (doubleSum > maxDoubleSlice) {
        maxDoubleSlice = doubleSum;
      }
    }
    return maxDoubleSlice;
  }

  private static int sumUpTo(final int[] A, final int start, final int stop) {
    int sum = 0;
    for (int i = start; i < stop; i++) {
      sum += A[i];
    }
    return sum;
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
