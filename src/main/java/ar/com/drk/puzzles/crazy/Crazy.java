package ar.com.drk.puzzles.crazy;

public class Crazy {
  public static void main(String[] args) {
//        System.out.println("Why is this " + (Integer.valueOf(10) == Integer.valueOf(10)));
//        System.out.println("Why is this " + (Integer.valueOf(1000000000) == Integer.valueOf(1000000000)));
//        System.out.println("Result " + solution(new int[] {3, 1, 2, 4, 3}));
    System.out.println("Result " + solution(new int[]{1, -2, 3}));
  }

  static int solution(int[] A) {
    int smallest = Integer.MAX_VALUE;
    int left = 0;
    int right = 0;
    for (int i = 0; i < A.length; i++) {
      right += A[i];
    }
    for (int i = 0; i < A.length - 1; i++) {
      left += A[i];
      right -= A[i];
      int difference = Math.abs(left - right);
      if (difference < smallest) {
        smallest = difference;
      }
    }
    return smallest;
  }
}
