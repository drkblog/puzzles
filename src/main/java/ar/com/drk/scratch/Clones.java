package ar.com.drk.scratch;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class Clones {

  @ToString
  @RequiredArgsConstructor
  static class Class implements Cloneable {
    private final int number;

    public Class clone() {
      try {
        return (Class) super.clone();
      } catch (final CloneNotSupportedException e) {
        throw new AssertionError();
      }
    }
  }

  public static void main(final String[] args) {
    final Class object = new Class(555);
    System.out.println(object);
    System.out.println(object.clone());
  }
}
