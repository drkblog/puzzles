package ar.com.drk.puzzles.codility;

public class NumberOfDiscIntersections {

    // N^2 https://app.codility.com/demo/results/trainingC5R4UX-ZVR/

    public static void main(String[] args) {
        final int[] test = new int[]{1, 5, 2, 1, 4, 0};
        final int[] testOverflow = new int[]{2147483647, 2147483647};
        System.out.println("Intersections: " + solution(testOverflow));
    }

    static int solution(int[] A) {
        int intersections = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if ((long) A[i] + A[j] - (j - i) >= 0) {
                    intersections++;
                }
            }
        }
        return intersections;
    }
}
