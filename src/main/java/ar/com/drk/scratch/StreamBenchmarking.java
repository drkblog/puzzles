package ar.com.drk.scratch;

import com.google.common.collect.Lists;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamBenchmarking {

  private static void benchmark(final String name, final Runnable runnable, final int times) {
    final Instant start = Instant.now();
    for (int i = 0; i < times; i++) {
      runnable.run();
    }
    final Instant end = Instant.now();
    System.out.println(name + " - Execution took " + Duration.between(start, end).toMillis() + "ms");
  }

  public static void main(final String[] args) throws Throwable {
    final List<String> words = IntStream.range(0, 10000)
        .mapToObj(i -> String.format("%04d", i))
        .collect(Collectors.toList());

    benchmark("stream", () -> {
      final List<Integer> integers = words.stream()
          .map(Integer::valueOf)
          .collect(Collectors.toList());
    }, 5000);

    benchmark("for", () -> {
      final List<Integer> integers = Lists.newArrayList();
      for (int i = 0; i < words.size(); i++) {
        integers.add(Integer.valueOf(words.get(i)));
      }
    }, 5000);
  }
}
