package ar.com.drk.game.game_of_life.basic;

import ar.com.drk.game.game_of_life.basic.matrix.FixedFiniteMatrix;
import ar.com.drk.game.game_of_life.basic.matrix.IterationConsumer;
import ar.com.drk.game.game_of_life.basic.matrix.Matrix;
import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Set;

public class Board {
  public static final int ALIVE = 1;
  public static final int DEAD = 0;

  private final Matrix matrix;

  public Board(final int width, final int height) {
    this.matrix = new FixedFiniteMatrix(width, height);
  }
  
  public void printTo(final PrintStream out) {
    final MatrixNewLineIterator matrixNewLineIterator = new MatrixNewLineIterator(
        (x, y, value) -> out.print(matrix.get(x, y) == 1 ? 'X' : '-'),
        () -> out.println()
    );
    matrix.iterateAll(matrixNewLineIterator::visit);
    out.println("\n===");
  }

  private Set<Change> calculateNextGeneration() {
    final ImmutableSet.Builder<Change> builder = new ImmutableSet.Builder<>();

    matrix.iterateActive((x, y, value) -> {
      final Change change = eval(x, y);
      if (change != null) {
        builder.add(change);
      }
    });
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
    return matrix.get(x, y) == ALIVE;
  }

  private int aliveNeighbours(final int x, final int y) {
    return matrix.get(x - 1, y - 1)
        + matrix.get(x, y - 1)
        + matrix.get(x + 1, y - 1)
        + matrix.get(x - 1, y)
        + matrix.get(x + 1, y)
        + matrix.get(x - 1, y + 1)
        + matrix.get(x, y + 1)
        + matrix.get(x + 1, y + 1);
  }

  private boolean establishNextGeneration(final Set<Change> changes) {
    changes.forEach(change -> change.applyTo(matrix));
    return !changes.isEmpty();
  }

  public boolean tick() {
    return establishNextGeneration(calculateNextGeneration());
  }

  public static Board from(final int width, final int height, final Collection<Change> changes) {
    final Board board = new Board(width, height);
    changes.forEach(change -> change.applyTo(board.matrix));
    return board;
  }

  @RequiredArgsConstructor
  private static class MatrixNewLineIterator {
    private final IterationConsumer<Integer, Integer, Integer> consumer;
    private final Runnable newLineAction;
    private Integer lastY = null;

    public void visit(final int x, final int y, final int value) {
      if (lastY == null) {
        lastY = y;
      }
      if (y != lastY) {
        newLineAction.run();
        lastY = y;
      }
      consumer.accept(x, y, value);
    }
  }
}
