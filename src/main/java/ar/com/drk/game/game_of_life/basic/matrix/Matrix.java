package ar.com.drk.game.game_of_life.basic.matrix;

public interface Matrix {
  void set(int x, int y, int newValue);

  int get(int x, int y);

  /**
   * Iterate only the active section of the matrix (best effort).
   * <p>
   * The active section is the minimum rectangle containing all the cells different than zero. The implementation
   * may implement this method just like iterateAll() in the worst case.
   *
   * @param iterationConsumer
   */
  void iterateActive(IterationConsumer<Integer, Integer, Integer> iterationConsumer);

  void iterateAll(IterationConsumer<Integer, Integer, Integer> iterationConsumer);
}
