public class Lab1P2 {
  /*
    Another common problem is a missing quote mark on a string literal.
    Again it comes down to trying to make sense of what the compiler is
    trying to tell you.  So this simple, two statement
    Java program is missing a quote mark in both output statements.
    Note that it makes a difference which one is missing

    The errors you should see are the following:

Lab1P2.java:18: ')' expected.
    System.out.println(This is the first line");
                           ^
Lab1P2.java:18: String not terminated at end of line.
    System.out.println(This is the first line");
                                             ^
Lab1P2.java:19: String not terminated at end of line.
    System.out.println("This is the second line);
                       ^
Lab1P2.java:22: Unbalanced parentheses.

^
Lab1P2.java:22: '}' expected.

^
5 errors

    Notice the first statement error generates a pretty good message
    from the compiler, while the second statement generates errors
    farther down, as well.
  */

  public static void main (String[] args) {
    System.out.println("This is the first line");
    System.out.println("This is the second line");
  }
}
