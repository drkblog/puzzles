package ar.com.drk.puzzle.crazy;

public class StepsNumWays {

  public static void main(final String[] args) {
    final int steps = 4;
    System.out.println("numWays(" + steps + ") = " + numWays(steps));
  }

  private static int numWays(final int steps) {
    if (steps < 1)
      return 0;
    if (steps == 1)
      return 1;
    if (steps == 2)
      return 2;
    return numWays(steps - 1) + numWays(steps - 2);
  }
}
