package ar.com.drk.puzzles.crazy;

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

        printTree(root, 0);
    }

    private static void printTree(final Node node, final int level) {
        IntStream.range(0, level-1).forEach(i -> System.out.print("|  "));
        if (level > 0) System.out.print("├──");
        System.out.println(node.value);
        if (node.left != null) {
            printTree(node.left, level+1);
        }
        if (node.right != null) {
            printTree(node.right, level+1);
        }
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
