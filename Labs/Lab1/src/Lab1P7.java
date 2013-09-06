public class Lab1P7 {

  public static void main (String[] args) {
    String myString="The quick brown fox jumps over the lazy dog";
    
    reverseIt( myString );
  }
  
  public static void reverseIt( String original ) {
    
    for ( int i=original.length()-1; i>=0; i-- ) {
      System.out.print(original.substring(i,i+1) );
    }

    System.out.println();


  }
}