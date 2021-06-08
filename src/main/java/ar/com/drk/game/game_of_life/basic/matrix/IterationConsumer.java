package ar.com.drk.game.game_of_life.basic.matrix;

@FunctionalInterface
public interface IterationConsumer<T, U, V> {

  void accept(T x, U y, V value);
}
