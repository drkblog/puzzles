package ar.com.drk.puzzles.interview;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class Processor {

  /// ---

  interface NumberService {
    double getNextNumber();
  }

  static class NumberServiceImpl implements NumberService {

    private double base = 0;
    private double exp = 1;

    public double getNextNumber() {
      exp *= -1;
      base += 0.5;
      return Math.pow(base, exp);
    }
  }

  /// ---

  private NumberService numberService = new NumberServiceImpl();

  private double run(Integer[] values, double initial) {
    double result = initial;
    for (int i = 0; i < values.length; i++) {
      result = values[i] / numberService.getNextNumber() / result;
    }
    return result;
  }

  public double processFile(final String filename, final double initial) throws IOException {
    Optional<String> firstLine = Files.lines(Paths.get(filename)).findFirst();
    Integer[] values = Arrays.stream(firstLine.get().split(","))
        .map(Integer::new)
        .toArray(Integer[]::new);
    return run(values, initial);
  }

  public static void main(String[] args) throws IOException {
    Processor processor = new Processor();
    System.out.println("Result = " + processor.processFile(Processor.class.getResource("data.csv").getFile(), 1.0));
  }
}
