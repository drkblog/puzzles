package ar.com.drk.puzzles.crazy;

import java.util.BitSet;
import java.util.stream.IntStream;

public class UniversalTreeCount {

    static class Node<T> {
        final T value;
        final Node left;
        final Node right;
        public Node(final T value, final Node left, final Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static class Response {
        int count;
        boolean isUnival;
        public Response(final int count, final boolean isUnival) {
            this.count = count;
            this.isUnival = isUnival;
        }
    }

    private static int count = 0;

    public static void main(String[] args) {
        Integer values[] = new Integer[] {1,0,1,0,1,1,1,1,1,1,1,1,1};
//        Character values[] = new Character[] {
//            'a',
//            'a', 'a',
//            null, null, 'a', 'a',
//            null, null, null, null, null, null, null, 'A'
//        };

        final Node<Integer> root = newTreeLevelOrder(values, 0);

        printTree(root);
        System.out.println("Total nodes: " + values.length);
        System.out.println("Universal trees: " + countUniversalTrees(root).count);
        System.out.println("countUniversalTrees() hits: " + count);
    }

    private static <T> Response countUniversalTrees(Node<T> node) {
        count++;
        if (node.left == null && node.right == null)
            return new Response(1, true);
        if (node.left == null){
            final Response response = countUniversalTrees(node.right);
            final boolean amIUnival = node.value.equals(node.right.value) && response.isUnival;
            return new Response(response.count + (amIUnival ? 1 : 0), amIUnival);
        }
        if (node.right == null) {
            final Response response = countUniversalTrees(node.left);
            final boolean amIUnival = node.value.equals(node.left.value) && response.isUnival;
            return new Response(response.count + (amIUnival ? 1 : 0), amIUnival);
        }
        final Response responseLeft = countUniversalTrees(node.left);
        final Response responseRight = countUniversalTrees(node.right);
        final boolean amIUnival = node.value.equals(node.left.value) && node.value.equals(node.right.value) && responseLeft.isUnival && responseRight.isUnival;
        return new Response(responseLeft.count + responseRight.count + (amIUnival ? 1 : 0), amIUnival);
    }

    private static <T> void printTree(final Node<T> node) {
        BitSet levelStatus = new BitSet();
        printTree(node, 0, levelStatus);
    }
    private static void printTree(final Node node, final int level, final BitSet levelStatus) {
        IntStream.range(1, level)
            .forEach(i -> System.out.print(!levelStatus.get(i) ? "|  ": "   "));
        if (level > 0) {
            System.out.print(levelStatus.get(level) ? "└──":"├──");
        }
        System.out.println(node.value);
        if (node.left != null) {
            levelStatus.clear(level+1);
            printTree(node.left, level+1, levelStatus);
        }
        if (node.right != null) {
            printTree(node.right, level+1, levelStatus);
        }
        levelStatus.set(level);
    }

    private static <T> Node<T> newTreeLevelOrder(final T[] values, final int index) {
        if (index < values.length) {
            if (values[index] == null)
                return null;
            return new Node<>(
                    values[index],
                    newTreeLevelOrder(values, 2 * index + 1),
                    newTreeLevelOrder(values, 2 * index + 2)
            );
        } else {
            return null;
        }
    }
}
