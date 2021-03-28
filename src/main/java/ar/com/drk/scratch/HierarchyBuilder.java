package ar.com.drk.scratch;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HierarchyBuilder {

  private static final Predicate<? super Node> isRootNode = new IsRootNode();

  public static Set<Node> buildHierarchy(final List<Item> source) {
    final Map<Long, Node> idNodeMap = source.stream()
        .map(Node::new)
        .collect(ImmutableMap.toImmutableMap(Node::getId, Function.identity()));

    idNodeMap.values()
        .stream()
        .filter(isRootNode.negate())
        .forEach(node -> {
          idNodeMap.get(node.getParent()).addChild(node);
        });

    return idNodeMap.values()
        .stream()
        .filter(isRootNode)
        .collect(Collectors.toCollection(TreeSet<Node>::new));
  }

  private static class IsRootNode implements Predicate<Node> {
    @Override
    public boolean test(final Node node) {
      return node.getParent() == null;
    }
  }

  @Value
  public static class Node implements Comparable<Node> {
    private final Long id;
    private final String name;
    private final Long parent;
    private final int order;
    private final Set<Node> children = Sets.newTreeSet();

    public Node(final Item item) {
      this.id = item.getId();
      this.name = item.getName();
      this.parent = item.getParent();
      this.order = item.getOrder();
    }

    public void addChild(final Node child) {
      children.add(child);
    }

    @Override
    public int compareTo(final Node node) {
      return Integer.compare(order, node.order);
    }
  }

  @Value
  public static class Item {
    private final Long id;
    private final String name;
    private final Long parent;
    private final int order;
  }
}
