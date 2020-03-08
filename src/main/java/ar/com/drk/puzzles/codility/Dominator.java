package ar.com.drk.puzzles.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Dominator {

    // With a map: https://app.codility.com/demo/results/trainingP5N858-TAE/

    public static void main(String[] args) {
        final List<TestCase> cases = Lists.newArrayList(
                new TestCase(new int[]{3, 4, 3, 2, 3, -1, 3, 3}, 3)
        );
        cases.forEach(testCase -> {
            int index = solution(testCase.dataset);
            System.out.println("Dominator for " + Arrays.toString(testCase.dataset) + ": " + testCase.dataset[index] + " " + ((testCase.dataset[index] == testCase.result) ? "OK" : "Wrong"));
        });
        ;
    }

    private static int solution(int[] A) {
        int maximum = 0;
        Map<Integer, AtomicInteger> frequency = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            frequency.putIfAbsent(A[i], new AtomicInteger());
            AtomicInteger count = frequency.get(A[i]);
            if (count.incrementAndGet() > maximum) {
                maximum = count.get();
                if (maximum > A.length / 2) {
                    return i;
                }
            }
        }
        return -1;
    }

    static class TestCase {
        int[] dataset;
        int result;

        public TestCase(int[] dataset, int result) {
            this.dataset = dataset;
            this.result = result;
        }
    }
}
