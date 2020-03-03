package ar.com.drk.puzzles.crazy;

import java.util.Arrays;

public class CircularRotation {
    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4, 5, 6};
        int[] testL = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        for (int k = 1; k <= test.length; k++) {
            System.out.println("k[" + k + "]" + Arrays.toString(solution(test, k)));
        }
    }

    private static int[] solution(final int[] I, int k) {
        int[] A = Arrays.copyOf(I, I.length); //  Just for testing
        for (int i = 0; i < A.length - k; i++) {
            swapWithOverflow(A, i, i + k, k);
        }
//        if (A.length / 2 > 0) {
//            if (k < A.length / 2) {
//                swap(A, 0, A.length - 1);
//            }
//        }
        return A;
    }

    private static void swapWithOverflow(int[] A, int s, int d, int overflow) {
        int x = s % overflow;
        int y = (d < A.length) ? d : d - (A.length);
        swap(A, x, y);
    }

    private static void swap(int[] A, int x, int y) {
        A[x] = A[x] ^ A[y];
        A[y] = A[y] ^ A[x];
        A[x] = A[x] ^ A[y];
    }

}
