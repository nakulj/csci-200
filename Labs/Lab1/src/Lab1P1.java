public class Lab1P1 {
  /*
    The most common problem that new Java programmer face
    is the compilers response to a missing semi-colon.  It's not real
    clear where and what the problem is.  So this simple, two statement
    Java program is missing a semi-colon in the first output statement.

    The error you should see is the following:

Lab1P1.java:10: Invalid type expression.
    System.out.println("This is the first line")
                      ^
Lab1P1.java:11: Invalid declaration.
    System.out.println("This is the second line");
                      ^
2 errors

    As you can see, the error tells you little about the problem, other
    than there is something wrong in those 2 lines.  This is the key
    to missing semi-colons.
  */

  public static void main (String[] args) {
    System.out.println("This is the first line");
    System.out.println("This is the second line");
  }
}
