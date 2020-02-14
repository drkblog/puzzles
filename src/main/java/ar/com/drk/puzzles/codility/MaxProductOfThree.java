package ar.com.drk.puzzles.codility;

public class MaxProductOfThree {
    public static void main(String[] args) {
        int[] A = new int[]{-3, 1, 2, -2, 5, 6};
        int[] B = new int[]{-5, 5, -5, 4};
        System.out.println("Max triplet is: " + solution(B));
    }

    static int solution(int[] A) {
        return 1;
    }

    static class Heap {
        private final int[] heap;


        public Heap(int size) {
            heap = new int[size];
        }


        private int findMinimum() {
            int minimum = 0;
            for (int i = 0; i < heap.length; i++) {
                if (i > 0 && heap[i] < heap[i - 1]) {
                    minimum = i;
                }
            }
            return minimum;
        }
    }

}
