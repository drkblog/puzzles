package ar.com.drk.puzzles.crazy;

public class StepsNumWays {

    public static void main(String[] args) {
        final int steps = 4;
        System.out.println("numWays(" + steps + ") = " + numWays(steps));
    }

    private static int numWays(int steps) {
        if (steps < 1)
            return 0;
        if (steps == 1)
            return 1;
        if (steps == 2)
            return 2;
        return numWays(steps-1) + numWays(steps-2);
    }
}
