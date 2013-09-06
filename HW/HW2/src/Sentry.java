public class Sentry extends GameObject implements java.io.Serializable {
    
    final static String sym= ":";
    private int r1,r2;
    boolean flag= false;
    
    public Sentry(int r, int c) {
        super(r, c);
        r1= r;
        r2= r+((r==R-1)?-1:1);
    }
    
    public Sentry(Sentry s) {
        super(s.r,s.c);
        r1= s.r1;
        r2= s.r2;
        flag= s.flag;
    }
    
    public void move() {
        r= flag?r1:r2;
        flag= !flag;
    }
    
    public String getSym() {
        return sym;
    }
    
}