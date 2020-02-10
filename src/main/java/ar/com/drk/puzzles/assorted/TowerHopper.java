package ar.com.drk.puzzles.assorted;

public class TowerHopper {
    // https://www.youtube.com/watch?v=kHWy5nEfRIQ

    public static void main(String[] args) {
        int[] test = new int[]{4, 2, 0, 0, 2, 0};
        System.out.println("Can hop: " + canHop(test));
    }

    private static boolean canHop(int[] path) {
        int current = 0;
        while (current < path.length && path[current] != 0) {
            current = hop(path, current);
        }
        return current >= path.length;
    }

    private static int hop(int[] path, int current) {
        final int farthest = path[current] + current;

        if (farthest >= path.length) {
            return farthest;
        }

        int best = current;
        int tallest = 0;
        for (int step = current + 1; step <= farthest; step++) {
            final int ponderedHeight = path[step] - (farthest - step);
            if (ponderedHeight > tallest) {
                tallest = ponderedHeight;
                best = step;
            }
        }
        return best;
    }
}
