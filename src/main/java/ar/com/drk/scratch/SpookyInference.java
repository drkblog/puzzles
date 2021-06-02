package ar.com.drk.scratch;

import java.util.Comparator;

public class SpookyInference {

  interface Top {
  }

  interface SecondLevel extends Top {
  }

  interface FirstLevel<T extends SecondLevel & Comparable<SecondLevel>> {
    T get();

    int number();
  }

  public static void main(final String[] args) {
    Comparator.<FirstLevel<?>>comparingInt(FirstLevel::number)
        .thenComparing(FirstLevel::get);
  }

  static class B implements SecondLevel, Comparable<SecondLevel> {
    @Override
    public int compareTo(final SecondLevel o) {
      return 0;
    }
  }

  static class A implements FirstLevel<B> {
    @Override
    public B get() {
      return null;
    }

    @Override
    public int number() {
      return 0;
    }
  }
}
