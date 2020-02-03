package ar.com.drk.puzzles.codility;

import java.util.BitSet;

public class FrogRiverOne {
    public int solution(int X, int[] A) {
        BitSet places = new BitSet();
        int placesCovered = 0;
        int second = 0;
        while(placesCovered < X && second < A.length) {
            if (!places.get(A[second])) {
                places.set(A[second]);
                placesCovered++;
            }
            second++;
        }
        return (placesCovered < X) ? -1 : second-1;
    }
}
