package ar.com.drk.puzzle.hackerrank;

public class CounterGame {
  public static void main(final String[] args) {
    System.out.println(counterGame(6) + " wins");
  }

  static String counterGame(final long n) {
    boolean louise = true;
    long value = n;
    while (value > 1) {
      if (isPowerOfTwo(value)) {
        value -= nextPowerOfTwoDown(value);
      } else {
        value /= 2;
      }
      louise = !louise;
    }
    return !louise ? "Louise" : "Richard";
  }

  static boolean isPowerOfTwo(final long n) {
    return n != 0 && (n & (n - 1)) != 0;
  }

  static long nextPowerOfTwoDown(long n) {
    long next = 1;
    while (n > 1) {
      n >>= 1;
      next <<= 1;
    }
    return next;
  }
}
