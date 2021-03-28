package ar.com.drk.scratch;

import com.google.common.collect.ImmutableList;
import lombok.Value;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamPlayground {

  @Value
  static class Album {
    private final String name;
    private final String artist;
    private final int sales;
  }

  public static void main(final String[] args) {
    final List<Album> albums = ImmutableList.of(
        new Album("A-A", "A", 19),
        new Album("A-B", "A", 119),
        new Album("A-C", "A", 0),
        new Album("B-A", "B", 111),
        new Album("B-B", "B", 32),
        new Album("B-C", "B", 1),
        new Album("B-D", "B", 44),
        new Album("C-A", "C", 1244),
        new Album("C-B", "C", 12)
    );
    final Map<String, Album> maps = albums.stream()
        .collect(Collectors.toMap(
            Album::getArtist,
            Function.identity(),
            BinaryOperator.maxBy(Comparator.comparing(Album::getSales))
        ));

    System.out.println(maps);
  }
}
