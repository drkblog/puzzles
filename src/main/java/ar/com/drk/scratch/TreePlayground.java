package ar.com.drk.scratch;

import com.google.common.collect.Lists;

import java.util.TreeSet;

public class TreePlayground {
  public static void main(final String[] args) {
    final TreeSet<Integer> values = new TreeSet<>(Lists.newArrayList(5, 4, 3, 2, 6, 8, 7, 9, 1, 0));
    System.out.println(values);

    values.remove(2);
  }
}
