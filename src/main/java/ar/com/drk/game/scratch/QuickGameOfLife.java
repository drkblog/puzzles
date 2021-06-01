package ar.com.drk.game.scratch;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import lombok.Value;

import java.io.PrintStream;
import java.util.BitSet;
import java.util.Collection;
import java.util.Set;

public class QuickGameOfLife {

  private final BitSet bits = new BitSet();
  private final int width;
  private int minX, minY, maxX, maxY;

  @Value
  static class Point {
    final int x, y;
  }

  private QuickGameOfLife(final int width, final Collection<Point> initialCells) {
    this.width = width;
    this.minX = Integer.MAX_VALUE;
    this.maxX = 0;
    this.minY = Integer.MAX_VALUE;
    this.maxY = 0;
    initialCells.forEach(cell -> set(cell.getX(), cell.getY()));
  }

  private void set(final int x, final int y) {
    bits.set(toIndex(x, y));
    updateBoundaries(x, y);
  }

  private void unset(final int x, final int y) {
    bits.clear(toIndex(x, y));
  }

  private boolean get(final int x, final int y) {
    return bits.get(toIndex(x, y));
  }

  private void updateBoundaries(final int x, final int y) {
    if (x < minX) minX = x;
    if (x > maxX) maxX = x;
    if (y < minY) minY = y;
    if (y > maxY) maxY = y;
  }

  private int toIndex(final int x, final int y) {
    return (y * width) + x;
  }

  private int toX(final int index) {
    return index % width;
  }

  private int toY(final int index) {
    return index / width;
  }

  private void printTo(final PrintStream out) {
    final int maxIndex = bits.size();
    for (int i = 0; i < maxIndex; i++) {
      if (i % width == 0) {
        out.println();
      }
      if (bits.get(i)) {
        out.print('X');
      } else {
        out.print('-');
      }
    }
  }

  @Value
  static class Change {
    private int x;
    private int y;
    private boolean alive;

    public void applyTo(final QuickGameOfLife quickGameOfLife) {
      if (alive) {
        quickGameOfLife.set(x, y);
      } else {
        quickGameOfLife.unset(x, y);
      }
    }
  }

  private Set<Change> calculateNextGeneration() {
    final ImmutableSet.Builder<Change> builder = new ImmutableSet.Builder<>();
    final int rx = minX - 1;
    final int ry = minY - 1;
    for (int y = ry; y < maxY + 2; y++) {
      for (int x = rx; x < maxX + 2; x++) {
        final Change change = eval(x, y);
        if (change != null) {
          builder.add(change);
        }
      }
    }
    return builder.build();
  }

  private Change eval(final int x, final int y) {
    final int aliveNeighbours = aliveNeighbours(x, y);
    if (get(x, y)) {
      if (aliveNeighbours < 2 || aliveNeighbours > 3) return new Change(x, y, false);
    } else {
      if (aliveNeighbours == 3) return new Change(x, y, true);
    }
    return null;
  }

  private int aliveNeighbours(final int x, final int y) {
    return (get(x - 1, y - 1) ? 1 : 0)
        + (get(x, y - 1) ? 1 : 0)
        + (get(x + 1, y - 1) ? 1 : 0)
        + (get(x - 1, y) ? 1 : 0)
        + (get(x + 1, y) ? 1 : 0)
        + (get(x - 1, y + 1) ? 1 : 0)
        + (get(x, y + 1) ? 1 : 0)
        + (get(x + 1, y + 1) ? 1 : 0);
  }

  private boolean establishNextGeneration(final Set<Change> changes) {
    changes.forEach(change -> change.applyTo(this));
    return !changes.isEmpty();
  }

  public boolean tick() {
    return establishNextGeneration(calculateNextGeneration());
  }

  public static void main(final String[] args) {
    final QuickGameOfLife gameOfLife = new QuickGameOfLife(80, Lists.newArrayList(
        new Point(3, 2),
        new Point(4, 3),
        new Point(2, 4),
        new Point(3, 4),
        new Point(4, 4)
    ));
    gameOfLife.printTo(System.out);
    while (gameOfLife.tick()) {
      gameOfLife.printTo(System.out);
    }
  }
}
