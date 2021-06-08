package ar.com.drk.puzzle.assorted;

import com.google.common.collect.Sets;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Mock google interview: https://www.youtube.com/watch?v=3Q_oYDQ2whs
// Kinda brute force, using HashMap and HashSets
public class FindSquares {
  private final Point[] points;
  private final Map<Integer, Set<Integer>> projectionX = new HashMap<>();
  private final Map<Integer, Set<Integer>> projectionY = new HashMap<>();

  public FindSquares(final Point[] points) {
    this.points = points;
  }

  private void setupHelpers() {
    for (final Point point : points) {
      register(point);
    }
  }

  private void register(final Point point) {
    projectionX.computeIfAbsent(point.x, k -> Sets.newHashSet()).add(point.y);
    projectionY.computeIfAbsent(point.y, k -> Sets.newHashSet()).add(point.x);
  }

  public int countSquares() {
    setupHelpers();
    int squares = 0;
    for (final Point point : points) {
      squares += squaresFromPoint(point);
    }
    return squares;
  }

  private int squaresFromPoint(final Point point) {
    int squares = 0;
    final Set<Integer> toTheRight = pointsToTheRight(point);
    final Set<Integer> above = pointsAbove(point);
    for (final int x : toTheRight) {
      for (final int y : above) {
        if (exists(x, y)) {
          squares++;
        }
      }
    }
    return squares;
  }

  private boolean exists(final Integer x, final Integer y) {
    return projectionX.get(x).contains(y);
  }

  private Set<Integer> pointsToTheRight(final Point point) {
    final Set<Integer> toTheRight = Sets.newHashSet();
    projectionY.get(point.y).forEach(x -> {
      if (x > point.x) {
        toTheRight.add(x);
      }
    });
    return toTheRight;
  }

  private Set<Integer> pointsAbove(final Point point) {
    final Set<Integer> above = Sets.newHashSet();
    projectionX.get(point.x).forEach(y -> {
      if (y > point.y) {
        above.add(y);
      }
    });
    return above;
  }

  public static void main(final String[] args) {
    final Point[] points = new Point[]{
        new Point(1, 1),
        new Point(2, 1),
        new Point(3, 1),
        new Point(1, 2),
        new Point(2, 2),
        new Point(3, 2),
        new Point(1, 5),
        new Point(3, 5),
    };
    final FindSquares findSquares = new FindSquares(points);
    System.out.println("Count squares: " + findSquares.countSquares());
  }
}
