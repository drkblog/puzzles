package ar.com.drk.puzzles.crazy;

public class LongUnboxing {

  private Long myLong = null;

  public static void main(String[] args) {

    LongUnboxing longUnboxing = new LongUnboxing();

    Long a = null;
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
