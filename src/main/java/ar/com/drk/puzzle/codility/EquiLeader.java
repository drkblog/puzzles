package ar.com.drk.puzzle.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EquiLeader {

  // https://app.codility.com/demo/results/trainingBWYKN7-FS6/

  public static void main(final String[] args) {
    final List<TestCase> cases = Lists.newArrayList(
        new TestCase(new int[]{4, 3, 4, 4, 4, 2}, 2),
        new TestCase(new int[]{1, 2}, 0),
        new TestCase(new int[]{0, 0}, 1)
    );
    cases.forEach(testCase -> {
      final int count = solution(testCase.dataset);
      System.out.println("EquiLeaders for " + Arrays.toString(testCase.dataset) + ": " + count + " " + ((count == testCase.result) ? "OK" : "Wrong"));
    });
    ;
  }

  private static int solution(final int[] A) {
    int equiLeaders = 0;
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
    Integer dominator = null;
    int dominatorCount = 0;
    if (!stack.isEmpty()) {
      final Integer value = stack.peek();
      for (int i = 0; i < A.length; i++) {
        if (A[i] == value) {
          if (++dominatorCount > A.length / 2) {
            dominator = i;
          }
        }
      }
    }
    if (dominator != null) {
      int dominatorPassed = 0;
      for (int i = 0; i < A.length; i++) {
        if (A[i] == A[dominator]) {
          dominatorPassed++;
        }
        if (dominatorPassed > (i + 1) / 2 && dominatorCount - dominatorPassed > (A.length - i - 1) / 2) {
          equiLeaders++;
        }
      }
    }
    return equiLeaders;
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
