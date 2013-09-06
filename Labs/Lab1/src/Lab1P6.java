public class Lab1P6 {
//One hint: There are two problems, but the second problem doesn't show up until you fix
//the first one.

  public static void main (String[] args) {
    int myInt;
    
    myInt = 6;
    System.out.println( "firstInt fibonacci is: " + fibonacci(myInt) );
  }
  
  public static int fibonacci( int end ) {
    int last=0, current=1, next=1;
    
    for ( int i=1; i<end; i++ ) {
      next = last + current;
      last = current;
      current = next;
    }

    return next;
  }
}
