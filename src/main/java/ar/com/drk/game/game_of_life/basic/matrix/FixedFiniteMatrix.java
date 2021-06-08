package ar.com.drk.game.game_of_life.basic.matrix;

public class FixedFiniteMatrix implements Matrix {

  private final int width;
  private final int height;
  private final int[][] board;

  public FixedFiniteMatrix(final int width, final int height) {
    this.width = width;
    this.height = height;
    this.board = new int[width][height];
  }

  @Override
  public void set(final int x, final int y, final int newValue) {
    board[x][y] = newValue;
  }

  @Override
  public int get(final int x, final int y) {
    return board[x][y];
  }

  @Override
  public void iterateActive(final IterationConsumer<Integer, Integer, Integer> iterationConsumer) {
    iterate(1, width - 1, 1, height - 1, iterationConsumer);
  }

  @Override
  public void iterateAll(final IterationConsumer<Integer, Integer, Integer> iterationConsumer) {
    iterate(0, width, 0, height, iterationConsumer);
  }

  private void iterate(final int xFrom, final int xTo, final int yFrom, final int yTo, final IterationConsumer<Integer, Integer, Integer> iterationConsumer) {
    for (int y = yFrom; y < yTo; y++) {
      for (int x = xFrom; x < xTo; x++) {
        iterationConsumer.accept(x, y, get(x, y));
      }
    }
  }
}
