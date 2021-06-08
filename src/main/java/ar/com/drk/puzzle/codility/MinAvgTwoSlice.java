package ar.com.drk.puzzle.codility;

public class MinAvgTwoSlice {

  //https://app.codility.com/demo/results/trainingT3N7QK-A89/
  public static void main(final String[] args) {
    final int[] t1 = new int[]{4, 2, 2, 5, 1, 5, 8}; // 1
    final int[] t2 = new int[]{-3, -5, -8, -4, -10}; // 2
    System.out.println("Result: " + solution(t2));
  }

  static int solution(final int[] A) {
    final int length = A.length;
    final int[] prefix = new int[length];
    prefix[0] = A[0];
    for (int i = 1; i < length; i++) {
      prefix[i] = prefix[i - 1] + A[i];
    }
    int a = 0, b = a + 1;
    double minimum = 10000.0;
    int minimumStart = 0;
    while (a < length - 1 && b < length) {
      final double current = average(prefix, a, b);
      if (current < minimum) {
        minimum = current;
        minimumStart = a;
      }
      if (b == a + 1) {
        b++;
      } else {
        a++;
      }
    }
    return minimumStart;
  }

  private static double average(final int[] prefix, final int a, final int b) {
    final int offset = (a > 0) ? prefix[a - 1] : 0;
    return (double) (prefix[b] - offset) / (b - a + 1);
  }
}
