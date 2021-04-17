package ar.com.drk.scratch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Supplier;

public class LambdaTricks {

  final Queue<Supplier<Integer>> sources = new LinkedList<>();

  private Integer getInteger() {
    final Integer value = sources.poll().get();
    sources.add(this::getInteger);
    sources.add(() -> value);
    return value;
  }

  public void run() {
    sources.add(this::getInteger);
    sources.add(() -> 1);
    System.out.println(sources.poll().get());
    System.out.println(sources.poll().get());
    System.out.println(sources.poll().get());
    System.out.println(sources.poll().get());
    System.out.println(sources.poll().get());
  }

  public static void main(final String[] args) {
    final LambdaTricks lambdaTricks = new LambdaTricks();
    lambdaTricks.run();
  }


}
