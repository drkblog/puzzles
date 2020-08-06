package ar.com.drk.puzzles.codility;

public class BinaryGap {
  public static void main(String[] args) {
    int number = 51712;
    System.out.println("Largest gap for " + number + ": " + new Solution().solution(number));
    return;
  }

  static class Solution {
    public int solution(int number) {
      int largestGap = 0, zeroCounter = 0;

      while (number > 0 && !lastBitIsOne(number)) number = number >> 1;

      while (number > 0) {
        if (lastBitIsOne(number)) {
          if (zeroCounter > largestGap) {
            largestGap = zeroCounter;
          }
          zeroCounter = 0;
        } else {
          zeroCounter++;
        }
        number = number >> 1;
      }
      return largestGap;
    }

    private static boolean lastBitIsOne(final int number) {
      return (number & 1) == 1;
    }
  }
}
