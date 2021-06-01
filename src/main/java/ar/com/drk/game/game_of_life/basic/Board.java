package ar.com.drk.game.game_of_life.basic;

import com.google.common.collect.ImmutableSet;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Set;

public class Board {
  public static final int ALIVE = 1;
  public static final int DEAD = 0;

  private final int width;
  private final int height;
  private final int[][] board;

  public Board(final int width, final int height) {
    this.width = width;
    this.height = height;
    this.board = new int[width][height];
  }

  public void set(final int x, final int y, final int newValue) {
    board[x][y] = newValue;
  }

  public int get(final int x, final int y) {
    return board[x][y];
  }

  public void printTo(final PrintStream out) {
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        out.print(get(x, y) == 1 ? 'X' : '-');
      }
      out.println();
    }
  }

  private Set<Change> calculateNextGeneration() {
    final ImmutableSet.Builder<Change> builder = new ImmutableSet.Builder<>();
    for (int y = 1; y < height - 1; y++) {
      for (int x = 1; x < width - 1; x++) {
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
    if (isAlive(x, y)) {
      if (aliveNeighbours < 2 || aliveNeighbours > 3) return new Change(x, y, DEAD);
    } else {
      if (aliveNeighbours == 3) return new Change(x, y, ALIVE);
    }
    return null;
  }

  private boolean isAlive(final int x, final int y) {
    return get(x, y) == ALIVE;
  }

  private int aliveNeighbours(final int x, final int y) {
    return get(x - 1, y - 1)
        + get(x, y - 1)
        + get(x + 1, y - 1)
        + get(x - 1, y)
        + get(x + 1, y)
        + get(x - 1, y + 1)
        + get(x, y + 1)
        + get(x + 1, y + 1);
  }

  private boolean establishNextGeneration(final Set<Change> changes) {
    changes.forEach(change -> change.applyTo(this));
    return !changes.isEmpty();
  }

  public boolean tick() {
    return establishNextGeneration(calculateNextGeneration());
  }

  public static Board from(final int width, final int height, final Collection<Change> changes) {
    final Board board = new Board(width, height);
    changes.forEach(change -> change.applyTo(board));
    return board;
  }
}
