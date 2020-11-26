package ar.com.drk.scratch;

import java.util.HashSet;
import java.util.Set;

public class HashTable {

  public static class A {
    public void getA() {
    }
  }

  public static class B extends A {
    public void getB() {
    }
  }

  public static class C extends B {
    public void getC() {
    }
  }

  public static void main(final String[] args) {
    final Set<C> set = new HashSet<>();
  }

  public void testIn(final Set<? super C> set) {
    set.add(new C());
  }

  public void testIn2(final Set<? extends C> set) {
    set.iterator().next().getB();
  }
}
