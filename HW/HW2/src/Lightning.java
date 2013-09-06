public class Lightning extends GameObject implements java.io.Serializable {
    
    final static String sym= "Z";
    boolean flag= false;
    
    public Lightning(int r, int c) {
        super(r, c);
    }
    
    public Lightning(Lightning l) {
        super(l.r,l.c);
        flag= l.flag;
    }
    
    public void move() {
        r--;
        c= flag?c-1:c+1;
        flag= !flag;
    }
    
    public String getSym() {
        return sym;
    }
    
}