package ar.com.drk.puzzles.crazy;

import java.util.Arrays;

public class CircularRotation {
    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4, 5, 6, 7};
        int[] testL = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        for (int k = 1; k <= test.length; k++) {
            System.out.println("k[" + k + "]" + Arrays.toString(solution(test, k)));
        }
    }

    private static int[] solution(final int[] I, int k) {
        int[] A = Arrays.copyOf(I, I.length); //  Just for testing
        for (int i = 0; i < A.length - k; i++) {
            swap(A, i, i + k, k);
        }
//        if (A.length % 2 > 0) {
//            for (int i = 0; i < k - 1; i++) {
//                swap(A, i, i + 1, k);
//            }
//        }
        return A;
    }

    private static void swap(int[] A, int s, int d, int overflow) {
        int x = s % overflow;
        A[x] = A[x] ^ A[d];
        A[d] = A[d] ^ A[x];
        A[x] = A[x] ^ A[d];
    }

}
