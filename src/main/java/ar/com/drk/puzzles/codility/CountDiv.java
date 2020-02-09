package ar.com.drk.puzzles.codility;

import java.util.Arrays;

public class CountDiv {
    public static void main(String[] args) {
        int[][] testCases = new int[][]{
                {6, 11, 2, 3},
                {6, 11, 2, 3}
        };
        for (int[] test : testCases) {
            int result = solution(test[0], test[1], test[2]);
            System.out.println("For " + Arrays.toString(test) + ": " + result + ((result == test[3]) ? " OK" : " Wrong"));
        }
    }

    static int solution(int A, int B, int K) {
        int result = 0;
        for (int i = A; i < B; i++) {
            if (i % K == 0) result++;
        }
        return result;
    }
}
