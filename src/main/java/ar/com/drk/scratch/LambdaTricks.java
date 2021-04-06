package ar.com.drk.scratch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class LambdaTricks {

  public static void main(final String[] args) {

    final Queue<Supplier<Integer>> sources = new LinkedList<>();
    sources.add(() -> 1);
    sources.add(sources::size);
    sources.add(() -> 3);

    System.out.println(sources.stream().map(Supplier::get).collect(Collectors.toList()));
  }
}
