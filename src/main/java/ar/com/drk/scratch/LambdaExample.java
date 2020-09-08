package ar.com.drk.scratch;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class LambdaExample {

    static class ValueSet {
        private Set<BigDecimal> values = new HashSet<>();

        public BigDecimal applyAndSum(Function<BigDecimal, BigDecimal> function) {
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

    public static void main(String[] args) {
        ValueSet values = new ValueSet();
        values.applyAndSum(value -> value.multiply(BigDecimal.valueOf(10)));

        Set<Item> items = new HashSet<>();
        items.stream()
                .parallel()
                .filter(item -> item.getName().startsWith("A"))
                .map(item -> item.getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = BigDecimal.ZERO;
        for (Item item : items) {
            if (item.getName().startsWith("A")) {
                total = total.add(item.getValue());
            }
        }

        Thread thread = new Thread(() -> System.out.println("Ejemplo"));
        thread.start();
    }
}
