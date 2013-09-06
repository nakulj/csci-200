public class Arrays {
    
    public static void main(String[] arg) {
        int[] intArray= {1,5,7,3,7,2,4,0};
        String[] StringArray= {"an","array","of","strings"};
        
        for(int i=0; i<intArray.length; i++)
            System.out.print(intArray[i] + ",");
        System.out.println();
        
        for(int i=0; i<StringArray.length; i++)
            System.out.print(StringArray[i] + ",");
        System.out.println();
    }
    
}