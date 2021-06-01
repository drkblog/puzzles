package ar.com.drk.game.game_of_life.basic;

import lombok.Value;

@Value
public class Change {
  int x;
  int y;
  int newValue;

  public void applyTo(final Board board) {
    board.set(x, y, newValue);
  }
}
