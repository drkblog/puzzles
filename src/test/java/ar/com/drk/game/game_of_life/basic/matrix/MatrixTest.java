package ar.com.drk.game.game_of_life.basic.matrix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MatrixTest {

  public static final int WIDTH = 100;
  public static final int HEIGHT = 100;

  static Stream<Arguments> matrixKnownImplementations() {
    return Stream.of(
        Arguments.of(new FixedFiniteMatrix(WIDTH, HEIGHT))
    );
  }

  @ParameterizedTest
  @MethodSource("matrixKnownImplementations")
  void setAndGet(final Matrix matrix) {
    // Given I set a diagonal of 1s starting 1, 1
    final int[][] reference = getDiagonalStarting(1, 100);
    copyReferenceToMatrix(reference, matrix);
    // WHEN I read values THEN I get the corresponding values
    assertThat(matrix.get(0, 0)).isEqualTo(0);
    assertThat(matrix.get(1, 0)).isEqualTo(0);
    assertThat(matrix.get(0, 1)).isEqualTo(0);
    assertThat(matrix.get(1, 1)).isEqualTo(1);
    assertThat(matrix.get(2, 2)).isEqualTo(1);
    assertThat(matrix.get(3, 3)).isEqualTo(1);
    assertThat(matrix.get(4, 4)).isEqualTo(1);
  }

  @ParameterizedTest
  @MethodSource("matrixKnownImplementations")
  void iterateActive(final Matrix matrix) {
    // GIVEN I set a diagonal of 1s starting 1, 1
    final int[][] reference = getDiagonalStarting(2, 98);
    copyReferenceToMatrix(reference, matrix);
    // WHEN I iterate only active part
    final List<Integer> numbers = new ArrayList<>();
    matrix.iterateActive((x, y, value) -> numbers.add(value));
    // THEN I get exactly the number of 1s present
    assertThat(numbers.stream().filter(Integer.valueOf(1)::equals).count())
        .isEqualTo(96);
  }

  @ParameterizedTest
  @MethodSource("matrixKnownImplementations")
  void iterateAll(final Matrix matrix) {
    // GIVEN I set a diagonal of 1s starting 1, 1
    final int[][] reference = getDiagonalStarting(2, 98);
    copyReferenceToMatrix(reference, matrix);
    // WHEN I iterate only active part
    final List<Integer> numbers = new ArrayList<>();
    matrix.iterateActive((x, y, value) -> numbers.add(value));
    // THEN I get exactly the number of 1s present
    assertThat(numbers.stream().filter(Integer.valueOf(1)::equals).count())
        .isEqualTo(96);
  }

  private void copyReferenceToMatrix(final int[][] reference, final Matrix matrix) {
    for (int y = 0; y < HEIGHT; y++) {
      for (int x = 0; x < HEIGHT; x++) {
        matrix.set(x, y, reference[x][y]);
      }
    }
  }

  private int[][] getDiagonalStarting(final int start, final int end) {
    final int[][] matrix = new int[WIDTH][HEIGHT];
    int z = start;
    while (z < end && z < WIDTH && z < HEIGHT) {
      matrix[z][z] = 1;
      z++;
    }
    return matrix;
  }
}