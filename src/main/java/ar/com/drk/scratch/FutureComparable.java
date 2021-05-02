package ar.com.drk.scratch;

import lombok.RequiredArgsConstructor;

public class FutureComparable {

  @RequiredArgsConstructor
  static class Top<T extends Top> implements Comparable<T> {

    final protected int number;

    @Override
    public int compareTo(final T o) {
      return Integer.compare(number, o.number);
    }
  }


  static class Left extends Top<Left> {
    public Left(final int number) {
      super(number);
    }
  }

  static class Right extends Top<Right> {
    public Right(final int number) {
      super(number);
    }
  }

  public static void main(final String[] args) {
    System.out.println(new Left(1).compareTo(new Left(2)));
  }
}
