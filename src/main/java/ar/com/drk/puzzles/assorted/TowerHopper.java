package ar.com.drk.puzzles.assorted;

public class TowerHopper {
  // https://www.youtube.com/watch?v=kHWy5nEfRIQ

  public static void main(final String[] args) {
    final int[] test = new int[]{4, 2, 0, 0, 2, 0};
    System.out.println("Can hop: " + canHop(test));
  }

  private static boolean canHop(final int[] path) {
    int farthest = 0;
    for (int step = 0; step < path.length; step++) {
      final int farthestFromHere = step + path[step];
      if (farthestFromHere > farthest) {
        farthest = farthestFromHere;
      }
    }
    return farthest >= path.length;
  }
}
