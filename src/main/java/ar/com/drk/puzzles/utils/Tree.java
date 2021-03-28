package ar.com.drk.puzzles.utils;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Tree<V> {

  @RequiredArgsConstructor
  static class Node<V> {
    private final V value;
    private Node<V> left;
    private Node<V> right;
  }

  private Node<V> root;

  public Tree(final V rootValue) {
    root = new Node<>(rootValue);
  }

  public List<V> depthFirst() {
    final List<V> result = Lists.newArrayList();
    final Stack<Node<V>> stack = new Stack<>();

    stackLeftNodes(stack, root);
    while (!stack.empty()) {
      final Node<V> node = stack.pop();
      result.add(node.value);
      stackLeftNodes(stack, node.right);
    }

    return result;
  }

  private void stackLeftNodes(final Stack<Node<V>> stack, final Node<V> initialNode) {
    Node<V> node = initialNode;
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }

  public static <V extends Comparable<V>> Tree<V> of(final Collection<V> values) {
    final Iterator<V> iterator = values.iterator();
    if (!iterator.hasNext()) {
      throw new IllegalArgumentException("Collection must not be empty");
    }
    final Tree<V> tree = new Tree<>(iterator.next());
    while (iterator.hasNext()) {
      addComparableToTree(tree, iterator.next());
    }
    return tree;
  }

  private static <V extends Comparable<V>> void addComparableToTree(final Tree<V> tree, final V value) {
    Node<V> currentNode = tree.root;
    Node<V> parentNode = tree.root;
    while (currentNode != null) {
      parentNode = currentNode;
      if (currentNode.value.compareTo(value) > 0) {
        currentNode = currentNode.left;
      } else {
        currentNode = currentNode.right;
      }
    }
    if (parentNode.value.compareTo(value) > 0) {
      parentNode.left = new Node<>(value);
    } else {
      parentNode.right = new Node<>(value);
    }
  }

  public static void main(final String[] args) {
    final Tree<Integer> tree = Tree.of(Lists.newArrayList(5, 0, 9, 3, 2, 1, 6, 8, 7, 4));
    System.out.println(tree.depthFirst());
  }
}
