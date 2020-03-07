package ar.com.drk.puzzles.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Fish {
    public static void main(String[] args) {
        List<TestCase> cases = Lists.newArrayList(
                new TestCase(new int[]{4, 3, 2, 1, 5}, new int[]{0, 1, 0, 0, 0}, 2),
                new TestCase(new int[]{0, 1}, new int[]{1, 1}, 2)
        );
        cases.forEach(
                testCase -> System.out.println(Arrays.toString(testCase.sizes) + " => " + ((solution(testCase.sizes, testCase.directions) == testCase.result) ? "OK" : "Wrong"))
        );
    }

    private static int solution(int[] A, int[] B) {
        int[] size = A;
        int[] direction = B;
        Stack<Integer> upstreamPastPositions = new Stack<>();
        int i = 0;
        while (i < size.length) {
            if (direction[i] == 1) {
                upstreamPastPositions.push(i);
                i++;
            } else if (direction[i] == 0) {
                if (!upstreamPastPositions.empty()) {
                    if (size[upstreamPastPositions.peek()] > size[i]) {
                        size[i] = -1;
                        i++;
                    } else {
                        size[upstreamPastPositions.pop()] = -1;
                    }
                } else {
                    i++;
                }
            }
        }
        int alive = 0;
        for (i = 0; i < size.length; i++) {
            if (size[i] > -1) alive++;
        }
        return alive;
    }

    static class TestCase {
        int[] sizes;
        int[] directions;
        int result;

        public TestCase(int[] sizes, int[] directions, int result) {
            this.sizes = sizes;
            this.directions = directions;
            this.result = result;
        }
    }
}
