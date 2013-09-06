public class Missile extends GameObject implements java.io.Serializable {
    
    final static String sym= "^";
    
    public Missile(int r, int c) {
        super(r, c);
    }
    
    public Missile(Missile m) {
        super(m.r,m.c);
    }
    
    public void move() {
        r--;
    }
    
    public String getSym() {
        return sym;
    }
    
}