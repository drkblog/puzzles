package ar.com.drk.puzzle.utils;

public class Permutation {

  private final int digits;

  public Permutation(final int n) {
    this.digits = n;
  }

  public long valueFor(final long n) {
    final long nFactorial = factorialOf(digits);
    if (n > nFactorial) {
      throw new IllegalArgumentException(n + " is bigger than " + nFactorial);
    }
    long register = 0;
    for (int digit = 1; digit <= digits; digit++) {
      register = (register * 10) + (n / (factorialOf(digit) / digit));
    }
    return register;
  }

  private long factorialOf(final int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) result *= i;
    return result;
  }

  public static void main(final String[] args) {
    final Permutation permutation = new Permutation(4);
    System.out.println(permutation.valueFor(0));
    System.out.println(permutation.valueFor(1));
    System.out.println(permutation.valueFor(2));
  }
}
