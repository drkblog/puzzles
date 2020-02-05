package ar.com.drk.puzzles.codility;

import java.util.Arrays;

public class MinAvgTwoSlice {

    public static void main(String[] args) {
        int[] t1 = new int[] {4, 2, 2, 5, 1, 5, 8}; // 1
        int[] t2 = new int[] {-3, -5, -8, -4, -10}; // 2
        System.out.println("Result: " + solution(t2));
    }

    static int solution(int[] A) {
        double[] average = new double[A.length - 1];
        for (int i=0; i<A.length-1; i++) {
            average[i] = ((double) A[i] + A[i+1]) / 2;
        }
        double min = 10000;
        int p=0;
        int firstMinimum = 0;
        while(p < A.length-1) {
            if (average[p] < min) {
                min = average[p];
                firstMinimum = p;
            }
            p++;
        }
        return firstMinimum;
    }
}
