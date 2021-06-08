package ar.com.drk.puzzle.hackerrank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class FormingAMagicSquare {

  static class MatrixIterator implements Iterator<int[][]> {

    private static final int DIGITS = 9;
    private static final int SIDE = 3;
    public static final int PERMUTATIONS = 362880;

    private int current = 123456789;

    @Override
    public boolean hasNext() {
      return current <= PERMUTATIONS;
    }

    @Override
    public int[][] next() {
      final int[][] result = new int[SIDE][SIDE];
      for (int digit = DIGITS; digit > 0; digit--) {
        final int factor = (int) Math.pow(10, 10 - digit);
        result[(digit - 1) / SIDE][(digit - 1) % SIDE] = current % factor / (factor / 10);
      }
      nextPermutation();
      return result;
    }

    private void nextPermutation() {
      int used = current % 10;
      for (int digit = 0; digit < DIGITS; digit++) {
        if (digitAt(digit) + 1 == used) {
          incrementDigitBy(digit, 2);
        } else {
          incrementDigitBy(digit, 1);
        }
        used = digitAt(digit);
      }
    }

    private void incrementDigitBy(final int digit, final int increment) {
      final int delta = increment * (int) Math.pow(10, digit);
      current += delta;
    }

    private int digitAt(final int digit) {
      int register = current;
      final int factor = (int) Math.pow(10, digit);
      register = register % factor / (factor / 10);
      return register;
    }
  }

  static int formingMagicSquare(final int[][] s) {
    final int cost = 0;
    print("Input", s);

    final MatrixIterator iterator = new MatrixIterator();
    print("1", iterator.next());
    print("2", iterator.next());

    return cost;
  }

  private static void print(final String title, final int[][] s) {
    System.out.println(title);
    for (int x = 0; x < s[0].length; x++) {
      for (int y = 0; y < s.length; y++) {
        System.out.print(s[x][y] + " ");
      }
      System.out.println("");
    }
  }


  private static final Scanner scanner = new Scanner(System.in);

  public static void main(final String[] args) throws IOException {
    final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    final int[][] s = new int[3][3];

    for (int i = 0; i < 3; i++) {
      final String[] sRowItems = scanner.nextLine().split(" ");
      scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

      for (int j = 0; j < 3; j++) {
        final int sItem = Integer.parseInt(sRowItems[j]);
        s[i][j] = sItem;
      }
    }

    final int result = formingMagicSquare(s);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedWriter.close();

    scanner.close();
  }
}
