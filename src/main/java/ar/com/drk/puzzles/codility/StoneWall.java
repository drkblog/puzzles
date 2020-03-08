package ar.com.drk.puzzles.codility;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StoneWall {

    // https://app.codility.com/demo/results/trainingWNM899-MJB/

    public static void main(String[] args) {
        final List<TestCase> cases = Lists.newArrayList(
                new TestCase(new int[]{8, 8, 5, 7, 9, 8, 7, 4, 8}, 7)
        );
        cases.forEach(testCase -> {
            int solution = solution(testCase.dataset);
            System.out.println("Blocks for " + Arrays.toString(testCase.dataset) + ": " + solution + " " + ((solution == testCase.result) ? "OK" : "Wrong"));
        });
        ;
    }

    static class Context {
        int blocks = 0;
        int currentHeight = 0;
        Stack<Integer> stack = new Stack<>();

        public void addBlock(int heightToMatch) {
            stack.push(heightToMatch - currentHeight);
            currentHeight = heightToMatch;
            blocks++;
        }

        public void adjustTo(int height) {
            while (currentHeight > height && !stack.empty()) {
                currentHeight -= stack.pop();
            }
            if (currentHeight < height) {
                addBlock(height);
            }
        }
    }

    static int solution(int[] H) {
        final Context ctx = new Context();
        for (int i = 0; i < H.length; i++) {
            if (H[i] > ctx.currentHeight) {
                ctx.addBlock(H[i]);
            } else if (H[i] < ctx.currentHeight) {
                ctx.adjustTo(H[i]);
            }
        }
        return ctx.blocks;
    }

    static class TestCase {
        int[] dataset;
        int result;

        public TestCase(int[] dataset, int result) {
            this.dataset = dataset;
            this.result = result;
        }
    }
}
