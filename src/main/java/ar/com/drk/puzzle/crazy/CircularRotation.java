package ar.com.drk.puzzle.crazy;

import java.util.Arrays;

public class CircularRotation {
  public static void main(final String[] args) {
    final int[] min = new int[]{1, 2, 3, 4, 5};
    final int[] test = new int[]{1, 2, 3, 4, 5, 6, 7};
    final int[] testL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    final int[] testXL = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};

    //testAllK(testL);
    System.out.println("k[" + 38 + "]" + Arrays.toString(solution(testXL, 38)));
  }

  private static void testAllK(final int[] test) {
    for (int k = 0; k < test.length; k++) {
      final int[] A = Arrays.copyOf(test, test.length);
      System.out.println("k[" + k + "]" + Arrays.toString(solution(A, k)));
    }
  }

  private static int[] solution(final int[] A, int k) {
    if (k >= A.length) {
      k %= A.length;
    }
    if (k == 0) {
      return A;
    }
    if (A.length == 2) {
      swap(A, 0, 1);
      return A;
    }
    if (k > A.length / 2) {
      k = A.length - k;
      rotateLeft(A, k);
    } else {
      rotateRight(A, k);
    }
    return A;
  }

  private static void rotateLeft(final int[] A, final int k) {
    int src = A.length - 1;
    int dst = k - 1;
    for (int i = 0; i < A.length - 1; i++) {
      swap(A, src, dst);
      src--;
      dst--;
      if (dst < 0) {
        dst = k - 1;
      }
    }
  }

  private static void rotateRight(final int[] A, final int k) {
    int src = 0;
    int dst = A.length - k;
    for (int i = 0; i < A.length - 1; i++) {
      swap(A, src, dst);
      src++;
      dst++;
      if (dst == A.length) {
        dst = A.length - k;
      }
    }
  }

  private static void swap(final int[] A, final int x, final int y) {
    if (x == y) return;
    A[x] = A[x] ^ A[y];
    A[y] = A[y] ^ A[x];
    A[x] = A[x] ^ A[y];
  }
}
