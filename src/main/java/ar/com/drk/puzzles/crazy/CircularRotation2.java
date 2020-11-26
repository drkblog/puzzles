package ar.com.drk.puzzles.crazy;

import java.util.Arrays;

public class CircularRotation2 {
  public static void main(String[] args) {
    int[] test = new int[]{1, 2, 3, 4, 5, 6, 7};
    int[] testL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    testAllK(testL);
  }

  private static void testAllK(int[] test) {
    for (int k = 1; k <= test.length; k++) {
      int[] A = Arrays.copyOf(test, test.length);
      System.out.println("k[" + k + "]" + Arrays.toString(solution(A, k)));
    }
  }

  private static int[] solution(final int[] A, int k) {
    if (k <= A.length / 2) {
      int src = 0;
      int wnd = A.length - k;
      while (src < A.length) {
        swap(A, src, wnd);
        src++;
        wnd++;
        if (wnd == A.length) {
          wnd = A.length - k;
          if (wnd < src) {
            wnd = src;
          }
        }
      }
    } else {
      int src = A.length - k;
      int wnd = A.length - (A.length - k);
      for (int i = 0; i < A.length; i++) {
        swap(A, src, wnd);
        src++;
        wnd++;
        if (wnd == A.length) {
          wnd = 0;
        }
        if (src == A.length) {
          src = 0;
        }
      }
    }
    return A;
  }

  private static void swap(int[] A, int x, int y) {
    if (x == y) return;
    A[x] = A[x] ^ A[y];
    A[y] = A[y] ^ A[x];
    A[x] = A[x] ^ A[y];
  }

  private static int[] solutionNxK(final int[] I, int k) {
    int[] A = Arrays.copyOf(I, I.length); //  Just for testing
    int limit = A.length * k - k;
    for (int i = limit; i > 0; i--) {
      swapOne(A, i % A.length);
    }
    return A;
  }

  private static void swapOne(int[] A, int x) {
    int d = x + 1;
    int y = (d < A.length) ? d : d - A.length;
    A[x] = A[x] ^ A[y];
    A[y] = A[y] ^ A[x];
    A[x] = A[x] ^ A[y];
  }

}
