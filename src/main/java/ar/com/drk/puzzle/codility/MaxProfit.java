package ar.com.drk.puzzle.codility;

public class MaxProfit {

  // https://app.codility.com/demo/results/training8TWX6T-45S/

  public static void main(final String[] args) {
    final int[] test = new int[]{23171, 21011, 21123, 21366, 21013, 21367};
    System.out.println("Max profit is: " + solution(test));
  }

  static int solution(final int[] A) {
    if (A.length == 0) {
      return 0;
    } else if (A.length == 1) {
      return A[0];
    }
    int profit = 0;
    int minimum = A[0];
    int previous = A[0];
    boolean wasAscending = false;
    int i = 1;
    while (i < A.length) {
      if (A[i] < previous && wasAscending) {
        profit = getProfit(profit, minimum, previous);
      }
      if (A[i] < minimum) {
        minimum = A[i];
      }

      // Done, watch line order
      wasAscending = A[i] > previous;
      previous = A[i];
      i++;
    }
    if (wasAscending) {
      profit = getProfit(profit, minimum, previous);
    }

    return profit;
  }

  private static int getProfit(int profit, final int minimum, final int previous) {
    final int localProfit = previous - minimum;
    if (localProfit > profit) {
      profit = localProfit;
    }
    return profit;
  }
}
