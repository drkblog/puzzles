package ar.com.drk.puzzles.codility;

import com.google.common.collect.Lists;

import java.util.List;

public class Brackets {

  // https://app.codility.com/demo/results/trainingF6UK7J-FQS/
  // Also solves: https://app.codility.com/demo/results/trainingNBZP6F-6DA/

  public static void main(final String[] args) {
    final List<TestCase> cases = Lists.newArrayList(
        new TestCase("{[()()]}", 1),
        new TestCase("([)()]", 0),
        new TestCase(")(", 0),
        new TestCase("{{{{", 0)
    );
    cases.forEach(testCase ->
        System.out.println("Test " + testCase.data + " => " + ((solution(testCase.data) == testCase.result) ? "OK" : "Wrong"))
    );
  }

  private static int solution(final String data) {
    final char[] stack = new char[data.length()];
    int head = -1;
    for (int i = 0; i < data.length(); i++) {
      final char character = data.charAt(i);
      if (isOpening(character)) {
        stack[++head] = character;
      } else if (isClosing(character)) {
        if (head < 0 || stack[head--] != getCounterPart(character)) {
          return 0;
        }
      } else {
        return -1;
      }
    }
    return (head == -1) ? 1 : 0;
  }

  private static char getCounterPart(final char character) {
    switch (character) {
      case ')':
        return '(';
      case '}':
        return '{';
      case ']':
        return '[';
      default:
        return 'X';
    }
  }

  private static boolean isClosing(final char character) {
    return character == ')' || character == '}' || character == ']';
  }

  private static boolean isOpening(final char character) {
    return character == '(' || character == '{' || character == '[';
  }

  static class TestCase {
    final String data;
    final int result;

    public TestCase(final String data, final int result) {
      this.data = data;
      this.result = result;
    }
  }
}
