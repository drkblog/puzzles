package ar.com.drk.puzzles.crazy;

import java.util.BitSet;
import java.util.stream.IntStream;

public class UniversalTreeCount {

    static class Node {
        final int value;
        final Node left;
        final Node right;
        public Node(final int value, final Node left, final Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        int values[] = new int[] {1,0,1,0,1,1,1,1,1,1};

        final Node root = newTreeLevelOrder(values, 0);

        printTree(root);
        System.out.println("Universal trees: " + countUniversalTrees(root));
    }

    private static int countUniversalTrees(Node node) {
        if (node.left == null && node.right == null)
            return 1;
        if (node.left == null)
            return countUniversalTrees(node.right)
                    + ((node.value == node.right.value) ? 1 : 0);
        if (node.right == null)
            return countUniversalTrees(node.left)
                    + ((node.value == node.left.value) ? 1 : 0);
        return countUniversalTrees(node.left)
                + countUniversalTrees(node.right)
                + ((node.value == node.left.value && node.value == node.right.value) ? 1 : 0);
    }

    private static void printTree(final Node node) {
        BitSet levelStatus = new BitSet();
        printTree(node, 0, levelStatus);
    }
    private static void printTree(final Node node, final int level, final BitSet levelStatus) {
        IntStream.range(0, level-1)
            .forEach(i -> System.out.print(levelStatus.get(i) ? "|  ": "   "));
        if (level > 0) {
            System.out.print(levelStatus.get(level) ? "└──":"├──");
        }
            System.out.println(node.value);
        if (node.left != null) {
            printTree(node.left, level+1, levelStatus);
        }
        if (node.right != null) {
            printTree(node.right, level+1, levelStatus);
        }
        levelStatus.set(level);
    }

    private static Node newTreeLevelOrder(final int[] values, final int index) {
        if (index < values.length) {
            return new Node(
                    values[index],
                    newTreeLevelOrder(values, 2 * index + 1),
                    newTreeLevelOrder(values, 2 * index + 2)
            );
        } else {
            return null;
        }
    }
}
