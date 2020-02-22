package ar.com.drk.puzzles.codility;

public class NumberOfDiscIntersections {
    public static void main(String[] args) {
        final int[] test = new int[]{1, 5, 2, 1, 4, 0};
        System.out.println("Intersections: " + solution(test));
    }

    static int solution(int[] A) {
        int intersections = 0;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] + A[j] - (j - i) >= 0) {
                    intersections++;
                    System.out.println("[" + i + "," + j + "]");
                }
            }
        }
        return intersections;
    }
}
