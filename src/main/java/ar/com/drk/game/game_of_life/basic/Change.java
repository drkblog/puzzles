package ar.com.drk.game.game_of_life.basic;

import ar.com.drk.game.game_of_life.basic.matrix.Matrix;
import lombok.Value;

@Value
public class Change {
  int x;
  int y;
  int newValue;

  public void applyTo(final Matrix board) {
    board.set(x, y, newValue);
  }
}
