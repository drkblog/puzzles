package ar.com.drk.puzzle.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Dominator {

  // With a map: https://app.codility.com/demo/results/trainingP5N858-TAE/
  // With a stack: https://app.codility.com/demo/results/trainingX5867Y-DHZ/

  public static void main(final String[] args) {
    final List<TestCase> cases = Lists.newArrayList(
        new TestCase(new int[]{3, 4, 3, 2, 3, -1, 3, 3}, 3)
    );
    cases.forEach(testCase -> {
      final int index = solution(testCase.dataset);
      System.out.println("Dominator for " + Arrays.toString(testCase.dataset) + ": " + testCase.dataset[index] + " " + ((testCase.dataset[index] == testCase.result) ? "OK" : "Wrong"));
    });
    ;
  }

  private static int solution(final int[] A) {
    final Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < A.length; i++) {
      if (stack.size() >= 1) {
        if (stack.peek().equals(A[i])) {
          stack.push(A[i]);
        } else {
          stack.pop();
        }
      } else {
        stack.push(A[i]);
      }
    }
    if (!stack.isEmpty()) {
      final Integer value = stack.peek();
      int count = 0;
      for (int i = 0; i < A.length; i++) {
        if (A[i] == value) {
          if (++count > A.length / 2) {
            return i;
          }
        }
      }
    }
    return -1;
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
