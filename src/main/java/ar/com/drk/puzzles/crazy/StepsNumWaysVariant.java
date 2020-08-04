package ar.com.drk.puzzles.crazy;

import java.util.Arrays;

public class StepsNumWaysVariant {

    public static void main(String[] args) {
        final int steps = 6;
        final int[] options = new int[]{2, 3};
        System.out.println("numWays(" + steps + ", " + Arrays.toString(options) + ") = " + numWays(steps, options));
    }

    private static int numWays(int steps, final int[] options) {
        if (steps < options[0]) {
            return 0;
        }
        for (int i = 0; i < options.length; i++) {
            if (steps == options[i]) {
                return i + 1;
            }
        }
        return Arrays.stream(options)
            .map(option -> numWays(steps - option, options))
            .sum();

    }
}
