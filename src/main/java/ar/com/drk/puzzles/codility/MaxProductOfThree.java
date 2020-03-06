package ar.com.drk.puzzles.codility;

public class MaxProductOfThree {
    public static void main(String[] args) {
        int[] A = new int[]{-3, 1, 2, -2, 5, 6};
        int[] B = new int[]{-5, 5, -5, 4};
        System.out.println("Max triplet is: " + solution(B));
    }

    static int solution(int[] A) {
        Heap maxHeap = new Heap(3);
        for (int i = 0; i < A.length; i++) {
            maxHeap.accept(A[i]);
        }
        return 1;
    }

    static class Heap {
        private final int size;
        private final int[] heap;

        private int minimumPosition = 0;
        private int count = 0;

        public Heap(int size) {
            this.size = size;
            heap = new int[size];
            for (int i = 0; i < heap.length; i++) {
                heap[i] = Integer.MIN_VALUE;
            }
        }

        public void accept(int value) {
            if (count < size) {
                heap[count] = value;
                count++;
            } else if (value > heap[minimumPosition]) {
                heap[minimumPosition] = value;
            }
            minimumPosition = findMinimumPosition();
        }

        private int findMinimumPosition() {
            int minimum = Integer.MAX_VALUE;
            int minimumPosition = 0;
            for (int i = 0; i < count; i++) {
                if (heap[i] < minimum) {
                    minimum = heap[i];
                    minimumPosition = i;
                }
            }
            return minimumPosition;
        }
    }

}
