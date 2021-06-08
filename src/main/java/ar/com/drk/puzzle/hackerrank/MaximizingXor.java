package ar.com.drk.puzzle.hackerrank;

import java.io.IOException;

public class MaximizingXor {

  // Complete the maximizingXor function below.
  static int maximizingXor(final int l, final int r) {
    int msb = r ^ l;
    for (int i = 0; i < 32; i++) msb |= msb >> i;
    return msb;
  }

  public static void main(final String[] args) throws IOException {
    System.out.println("Maximum XOR " + maximizingXor(9, 10));
  }


}
