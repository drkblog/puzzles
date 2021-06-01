package ar.com.drk.game.game_of_life.basic;

import com.google.common.collect.Lists;

import java.util.List;

public class GameOfLife {
  public static void main(final String[] args) throws InterruptedException {
    final List<Change> seed = Lists.newArrayList(
        new Change(3, 2, 1),
        new Change(4, 3, 1),
        new Change(2, 4, 1),
        new Change(3, 4, 1),
        new Change(4, 4, 1)
    );
    final Board board = Board.from(60, 20, seed);
    board.printTo(System.out);
    while (board.tick()) {
      Thread.sleep(1000);
      board.printTo(System.out);
    }
  }
}
