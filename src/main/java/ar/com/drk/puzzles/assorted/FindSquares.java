package ar.com.drk.puzzles.assorted;

import com.google.common.collect.Sets;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Mock google interview: https://www.youtube.com/watch?v=3Q_oYDQ2whs
// Kinda brute force, using HashMap and HashSets
public class FindSquares {
    private final Point[] points;
    private Map<Integer, Set<Integer>> projectionX = new HashMap<>();
    private Map<Integer, Set<Integer>> projectionY = new HashMap<>();

    public FindSquares(final Point[] points) {
        this.points = points;
    }

    private void setupHelpers() {
        for (Point point : points) {
            register(point);
        }
    }

    private void register(Point point) {
        projectionX.computeIfAbsent(point.x, k -> Sets.newHashSet()).add(point.y);
        projectionY.computeIfAbsent(point.y, k -> Sets.newHashSet()).add(point.x);
    }

    public int countSquares() {
        setupHelpers();
        int squares = 0;
        for (Point point : points) {
            squares += squaresFromPoint(point);
        }
        return squares;
    }

    private int squaresFromPoint(Point point) {
        int squares = 0;
        Set<Integer> toTheRight = pointsToTheRight(point);
        Set<Integer> above = pointsAbove(point);
        for (int x : toTheRight) {
            for (int y : above) {
                if (exists(x, y)) {
                    squares++;
                }
            }
        }
        return squares;
    }

    private boolean exists(Integer x, Integer y) {
        return projectionX.get(x).contains(y);
    }

    private Set<Integer> pointsToTheRight(Point point) {
        Set<Integer> toTheRight = Sets.newHashSet();
        projectionY.get(point.y).forEach(x -> {
            if (x > point.x) {
                toTheRight.add(x);
            }
        });
        return toTheRight;
    }

    private Set<Integer> pointsAbove(Point point) {
        Set<Integer> above = Sets.newHashSet();
        projectionX.get(point.x).forEach(y -> {
            if (y > point.y) {
                above.add(y);
            }
        });
        return above;
    }

    public static void main(String[] args) {
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
        FindSquares findSquares = new FindSquares(points);
        System.out.println("Count squares: " + findSquares.countSquares());
    }
}
