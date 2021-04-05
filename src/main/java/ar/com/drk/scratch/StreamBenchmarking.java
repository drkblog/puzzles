package ar.com.drk.scratch;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.IntStream;

public class StreamBenchmarking {

  private final Set<Integer> numbers = Sets.newHashSet();

  private void add(final int number) {
    numbers.add(number);
  }

  private boolean isEven(final int number) {
    return number % 2 == 0;
  }

  private String convert(final int number) {
    numbers.remove(number);
    return Integer.toString(number);
  }

  public void run() {
    IntStream.range(0, 1000000)
        .parallel()
        .peek(this::add)
        .filter(this::isEven)
        .mapToObj(this::convert)
        .collect(ImmutableList.toImmutableList());
  }

  public static void main(final String[] args) throws Throwable {
    new StreamBenchmarking().run();
  }
}
