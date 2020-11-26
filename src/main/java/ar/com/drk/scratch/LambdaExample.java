package ar.com.drk.scratch;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class LambdaExample {

  static class ValueSet {
    private Set<BigDecimal> values = new HashSet<>();

    public BigDecimal applyAndSum(final Function<BigDecimal, BigDecimal> function) {
      return values.stream()
          .map(function::apply)
          .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
  }

  static class Item {
    public String getName() {
      return "";
    }

    public BigDecimal getValue() {
      return BigDecimal.ONE;
    }
  }

  public static void main(final String[] args) {
    final ValueSet values = new ValueSet();
    values.applyAndSum(value -> value.multiply(BigDecimal.valueOf(10)));

    final Set<Item> items = new HashSet<>();

    final BigDecimal sum = items.stream()
        .parallel()
        .filter(item -> item.getName().startsWith("A"))
        .map(Item::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal total = BigDecimal.ZERO;
    for (final Item item : items) {
      if (item.getName().startsWith("A")) {
        total = total.add(item.getValue());
      }
    }

    final Thread thread = new Thread(() -> System.out.println("Ejemplo"));
    thread.start();
  }
}
