package ar.com.drk.puzzles.codility;

public class MaxProductOfThree {

  // https://app.codility.com/demo/results/training4X6KRP-QA8/
  public static void main(String[] args) {
    int[] A = new int[]{-3, 1, 2, -2, 5, 6};
    int[] B = new int[]{-5, 5, -5, 4};
    System.out.println("Max triplet is: " + solution(A));
  }

  static int solution(int[] A) {
    Heap maxHeap = Heap.getMaxHeap(3);
    Heap minHeap = Heap.getMinHeap(2);
    for (int i = 0; i < A.length; i++) {
      maxHeap.accept(A[i]);
      minHeap.accept(A[i]);
    }
    return Math.max(maxHeap.getProduct(), maxHeap.getHighest() * minHeap.getProduct());
  }

  static class Heap {
    private final int size;
    private final int[] heap;
    private final HeapStrategy strategy;

    private int limitPosition = 0;
    private int count = 0;

    interface HeapStrategy {
      int initialValue();

      int findLimitPosition(final int[] heap, int count);

      boolean shouldReplace(int value, int limit);
    }

    private static class HighestValues implements HeapStrategy {

      @Override
      public int initialValue() {
        return Integer.MIN_VALUE;
      }

      @Override
      public int findLimitPosition(final int[] heap, int count) {
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

      @Override
      public boolean shouldReplace(int value, int limit) {
        return value > limit;
      }
    }

    private static class LowestValues implements HeapStrategy {

      @Override
      public int initialValue() {
        return Integer.MAX_VALUE;
      }

      @Override
      public int findLimitPosition(final int[] heap, int count) {
        int maximum = Integer.MIN_VALUE;
        int maximumPosition = 0;
        for (int i = 0; i < count; i++) {
          if (heap[i] > maximum) {
            maximum = heap[i];
            maximumPosition = i;
          }
        }
        return maximumPosition;
      }

      @Override
      public boolean shouldReplace(int value, int limit) {
        return value < limit;
      }
    }

    private Heap(final int size, final HeapStrategy strategy) {
      this.strategy = strategy;
      this.size = size;
      heap = new int[size];
      for (int i = 0; i < heap.length; i++) {
        heap[i] = this.strategy.initialValue();
      }
    }

    public void accept(int value) {
      if (count < size) {
        heap[count] = value;
        count++;
      } else if (strategy.shouldReplace(value, heap[limitPosition])) {
        heap[limitPosition] = value;
      }
      limitPosition = strategy.findLimitPosition(heap, count);
    }

    public int getHighest() {
      int maximum = Integer.MIN_VALUE;
      for (int i = 0; i < count; i++) {
        if (heap[i] > maximum) {
          maximum = heap[i];
        }
      }
      return maximum;
    }

    public int getProduct() {
      int product = 1;
      for (int i = 0; i < count; i++) {
        product *= heap[i];

      }
      return product;
    }

    public static Heap getMaxHeap(int size) {
      return new Heap(size, new HighestValues());
    }

    public static Heap getMinHeap(int size) {
      return new Heap(size, new LowestValues());
    }
  }

}
