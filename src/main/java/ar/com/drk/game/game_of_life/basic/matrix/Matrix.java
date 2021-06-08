package ar.com.drk.game.game_of_life.basic.matrix;

public interface Matrix {
  void set(int x, int y, int newValue);

  int get(int x, int y);

  void iterateActive(IterationConsumer<Integer, Integer, Integer> iterationConsumer);

  void iterateAll(IterationConsumer<Integer, Integer, Integer> iterationConsumer);
}
