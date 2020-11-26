package ar.com.drk.puzzles.codility;

import java.util.Arrays;
import java.util.BitSet;

public class PermCheck {

  public static void main(String[] args) {
    int[] c1 = new int[]{1, 2, 3, 4};
    int[] c2 = new int[]{4, 2, 1, 3};
    int[] c3 = new int[]{1, 2, 3, 4, 5, 6, 8, 9};
    System.out.println("Is permutation " + Arrays.toString(c1) + ": " + solution(c1));
    System.out.println("Is permutation " + Arrays.toString(c2) + ": " + solution(c2));
    System.out.println("Is permutation " + Arrays.toString(c3) + ": " + solution(c3));
  }

  static int solution(int[] A) {
    final BitSet numbers = new BitSet();
    for (int value : A) {
      if (value < 1 || value > 100000) return 0;
      numbers.set(value);
    }
    for (int i = 1; i <= A.length; i++)
      if (!numbers.get(i))
        return 0;
    return 1;
  }
}
