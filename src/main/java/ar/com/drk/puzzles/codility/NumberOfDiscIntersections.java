package ar.com.drk.puzzles.codility;

import java.util.Arrays;

public class NumberOfDiscIntersections {

    // N^2 https://app.codility.com/demo/results/trainingC5R4UX-ZVR/

    public static void main(String[] args) {
        final int[] test = new int[]{1, 5, 2, 1, 4, 0};
        final int[] testThreeOnes = new int[]{1, 1, 1};
        final int[] testSingle = new int[]{10};
        final int[] testOverflow = new int[]{2147483647, 2147483647};
        System.out.println("Intersections: " + solution(test));
    }

    static int solution(int[] A) {
        if (A.length < 2) {
            return 0;
        }
        int carry = 0;
        long[] start = new long[A.length];
        long[] stop = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            long starts = (long) i - A[i];
            if (starts >= 0) {
                start[(int) starts]++;
            } else {
                carry++;
            }
            long finishes = (long) i + A[i];
            if (finishes < stop.length) {
                stop[(int) finishes]++;
            }
        }
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(start));
        System.out.println(Arrays.toString(stop));
        long intersections = combinationsWithoutRepetitions(carry);
        for (int i = 0; i < A.length; i++) {
            intersections += (carry * start[i]) + combinationsWithoutRepetitions(start[i]);
            System.out.println("Carry " + carry + " - Intersections [" + i + "]" + intersections);
            carry += start[i];
            carry -= stop[i];
        }
        return (int) intersections;
    }

    static long combinationsWithoutRepetitions(long n) {
        return factorialOf(n) / (factorialOf(2) * factorialOf(n - 2));
    }

    static long factorialOf(long n) {
        int result = 1;
        for (long i = 2; i <= n; i++) result *= i;
        return result;
    }

}
