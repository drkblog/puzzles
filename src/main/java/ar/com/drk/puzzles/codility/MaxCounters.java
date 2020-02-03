package ar.com.drk.puzzles.codility;

import java.util.Arrays;
import java.util.BitSet;

public class MaxCounters {

    /*
    https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/
    First solution was looking for the maximum value in the result array every time a max counter operation appeared.
    Improvement was keeping track of topCounter and topValue as counter were  incremented to avoid cycling result to get the highest.
    Here is the last attempt which passed all tests.
    */
    public static void main(String[] args) {
        int[] program = new int[] {3, 4, 4, 6, 1, 4, 4};
        int N = 5;
        System.out.println("Result: " + Arrays.toString(solution(N, program)));
    }

    static int[] solution(int N, int[] A) {
        int[] result = new int[N];
        int topCounter = 0;
        int topValue = 0;
        int lastTopValue =0;
        BitSet done = new BitSet(N);
        done.set(0, N);
        for(int operation : A) {
            if (operation <= N) {
                int counterIndex = operation - 1;
                if (!done.get(counterIndex)) {
                    result[counterIndex] = lastTopValue;
                    done.set(counterIndex);
                }
                result[counterIndex]++;
                if (result[counterIndex] > topValue) {
                    topValue = result[counterIndex];
                    topCounter = counterIndex;
                }
            }
            if (operation > N) {
                done = new BitSet(N);
                done.set(topCounter);
                lastTopValue = topValue;
            }
        }
        if (!done.isEmpty()) {
            for(int i=0; i<N; i++) {
                if (!done.get(i)) {
                    result[i] = lastTopValue;
                }
            }
        }
        return result;
    }
}
