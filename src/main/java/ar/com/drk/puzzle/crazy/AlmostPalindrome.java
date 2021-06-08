package ar.com.drk.puzzle.crazy;

public class AlmostPalindrome {
  public static void main(final String[] args) {
    final String[] test = new String[]{"abccba", "abccbx", "ab1ba", "lele", "1234x4321", ".234x43.1"};
    for (final String word : test) {
      System.out.println("Is almost palindrome '" + word + "': " + almostPalindrome(word));
    }
  }

  private static boolean almostPalindrome(final String word) {
    int errors = 0;
    final int length = word.length();
    for (int i = 0; i < length / 2; i++) {
      if (word.charAt(i) != word.charAt(length - i - 1)) errors++;
    }
    return errors < 2;
  }
}
