package ar.com.drk.puzzles.codility;

public class Triangle {

    // Arrays.sort() and verifying N + N+1 > N+2 got 93% https://app.codility.com/demo/results/trainingB92X5X-GY7/
    // Fixing overflow: https://app.codility.com/demo/results/trainingQ5X2HH-BZG/
    // My mergeSort implementation: https://app.codility.com/demo/results/trainingUBC3PZ-EM9/
    public static void main(String[] args) {
        int[] testYes = new int[]{10, 2, 5, 1, 8, 20};
        int[] testNo = new int[]{10, 50, 5, 1};
        int[] testFailed = new int[]{5, 3, 3};
        int[] testExtreme = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        System.out.println("There is a triangle: " + solution(testYes));
    }

    static int solution(int[] A) {
        mergeSort(A, 0, A.length - 1);
        for (int i = 0; i <= A.length - 3; i++) {
            if ((long) A[i] + A[i + 1] > A[i + 2]) return 1;
        }
        return 0;
    }

    static void mergeSort(int[] A, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(A, left, middle);
            mergeSort(A, middle + 1, right);
            mergeHalves(A, left, middle, right);
        }
    }

    static void mergeHalves(int[] A, int left, int middle, int right) {
        int[] temporary = new int[right - left + 1];
        int lp = 0;
        int rp = middle - left + 1;
        for (int i = left; i <= right; i++) {
            temporary[i - left] = A[i];
        }
        for (int i = left; i <= right; i++) {
            int next = 0;
            if (lp < middle - left + 1 && (rp == temporary.length || temporary[lp] < temporary[rp])) {
                next = temporary[lp];
                lp++;
            } else if (rp < temporary.length && (lp == middle - left + 1 || temporary[lp] >= temporary[rp])) {
                next = temporary[rp];
                rp++;
            }
            A[i] = next;
        }
    }
}
