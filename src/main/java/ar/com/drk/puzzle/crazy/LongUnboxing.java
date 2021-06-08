package ar.com.drk.puzzle.crazy;

public class LongUnboxing {

  private final Long myLong = null;

  public static void main(final String[] args) {

    final LongUnboxing longUnboxing = new LongUnboxing();

    final Long a = null;
    if (a < 1) {

    }
    if (longUnboxing.getLong() < 1) {

    }
    if (longUnboxing.getLongOk() < 1) {

    }
  }

  private Long getLong() {
    return myLong;
  }

  private Long getLongOk() {
    return Long.valueOf(1);
  }
}
