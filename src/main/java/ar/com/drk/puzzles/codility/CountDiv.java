package ar.com.drk.puzzles.codility;

import java.util.Arrays;

public class CountDiv {

    // Naive solution got 12% -> https://app.codility.com/demo/results/trainingMRESK7-2M2/
    // Passed https://app.codility.com/demo/results/trainingJSFNZV-ME4/

    public static void main(String[] args) {
        int[][] testCases = new int[][]{
                {6, 11, 2, 3},
                {3, 111, 5, 22}
        };
        for (int[] test : testCases) {
            int result = solution(test[0], test[1], test[2]);
            System.out.println("For " + Arrays.toString(test) + ": " + result + ((result == test[3]) ? " OK" : " Wrong"));
        }
    }

    static int solution(int A, int B, int K) {
        int lower = A + (K - (A % K));
        int higher = B - (K - (B % K));
//        while (lower % K != 0) lower++;
//        while (higher % K != 0) higher--;
        return (higher - lower) / K + 1;
    }
}
