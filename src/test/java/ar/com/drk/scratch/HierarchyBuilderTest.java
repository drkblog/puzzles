package ar.com.drk.scratch;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class HierarchyBuilderTest {

  public static final String FILE = "File";
  public static final String EDIT = "Edit";
  public static final String WINDOW = "Window";
  public static final String HELP = "Help";
  public static final String OPEN = "Open";
  public static final String SAVE = "Save";
  public static final String COPY = "Copy";
  public static final String CUT = "Cut";
  public static final String PASTE = "Paste";
  public static final String FIRST = "First";
  public static final String LAST = "Last";
  public static final String ABOUT = "About";

  @Test
  public void givenAOrderedHierarchy_whenIBuildIt_thenIGetATree() {
    // GIVEN a id-name-parent hierarchy
    final List<HierarchyBuilder.Item> source = Lists.newArrayList(
        new HierarchyBuilder.Item(1L, FILE, null, 1),
        new HierarchyBuilder.Item(2L, EDIT, null, 2),
        new HierarchyBuilder.Item(3L, WINDOW, null, 3),
        new HierarchyBuilder.Item(4L, HELP, null, 4),

        new HierarchyBuilder.Item(5L, OPEN, 1L, 1),
        new HierarchyBuilder.Item(6L, SAVE, 1L, 2),

        new HierarchyBuilder.Item(7L, COPY, 2L, 1),
        new HierarchyBuilder.Item(8L, CUT, 2L, 2),
        new HierarchyBuilder.Item(9L, PASTE, 2L, 3),

        new HierarchyBuilder.Item(10L, FIRST, 3L, 1),
        new HierarchyBuilder.Item(11L, LAST, 3L, 2),

        new HierarchyBuilder.Item(12L, ABOUT, 4L, 1)
    );

    // WHEN I build it
    final Set<HierarchyBuilder.Node> roots = HierarchyBuilder.buildHierarchy(source);

    // THEN I get a tree
    assertThat(roots)
        .extracting(HierarchyBuilder.Node::getName)
        .containsExactly(FILE, EDIT, WINDOW, HELP);
    assertThat(roots)
        .flatExtracting(HierarchyBuilder.Node::getChildren)
        .extracting(HierarchyBuilder.Node::getName)
        .containsExactly(OPEN, SAVE, COPY, CUT, PASTE, FIRST, LAST, ABOUT);
  }

  @Test
  public void givenAAssortedHierarchy_whenIBuildIt_thenIGetATree() {
    // GIVEN a id-name-parent hierarchy
    final List<HierarchyBuilder.Item> source = Lists.newArrayList(
        new HierarchyBuilder.Item(10L, FIRST, 3L, 1),
        new HierarchyBuilder.Item(11L, LAST, 3L, 2),

        new HierarchyBuilder.Item(6L, SAVE, 1L, 2),
        new HierarchyBuilder.Item(5L, OPEN, 1L, 1),

        new HierarchyBuilder.Item(4L, HELP, null, 4),
        new HierarchyBuilder.Item(3L, WINDOW, null, 3),
        new HierarchyBuilder.Item(2L, EDIT, null, 2),
        new HierarchyBuilder.Item(1L, FILE, null, 1),

        new HierarchyBuilder.Item(7L, COPY, 2L, 1),
        new HierarchyBuilder.Item(8L, CUT, 2L, 2),
        new HierarchyBuilder.Item(9L, PASTE, 2L, 3),

        new HierarchyBuilder.Item(12L, ABOUT, 4L, 1)
    );

    // WHEN I build it
    final Set<HierarchyBuilder.Node> roots = HierarchyBuilder.buildHierarchy(source);

    // THEN I get a tree
    assertThat(roots)
        .extracting(HierarchyBuilder.Node::getName)
        .containsExactly(FILE, EDIT, WINDOW, HELP);
    assertThat(roots)
        .flatExtracting(HierarchyBuilder.Node::getChildren)
        .extracting(HierarchyBuilder.Node::getName)
        .containsExactly(OPEN, SAVE, COPY, CUT, PASTE, FIRST, LAST, ABOUT);
  }
}