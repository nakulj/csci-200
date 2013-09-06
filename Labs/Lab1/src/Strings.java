public class Strings {
    
    public static void main(String[] arg) {
        String s1 = "Welcome to Java";
        String s2 = s1;
        String s3 = new String("Welcome to Java");
        String s4 = s3.intern();
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1.equals(s2));
        System.out.println(s2.equals(s3));
        System.out.println(s1.compareTo(s2));
        System.out.println(s2.compareTo(s3));
        System.out.println(s1 == s4);
        System.out.println(s1.charAt(0));
        System.out.println(s1.indexOf('j'));
        System.out.println(s1.indexOf("to"));
        System.out.println(s1.substring(5,11));
        System.out.println(s1.toLowerCase());
        System.out.println(s1.toUpperCase());
        System.out.println("    Welcome      ".trim());
        System.out.println(s1.substring(5));
    }
    
}