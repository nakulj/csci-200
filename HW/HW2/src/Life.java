public class Life extends GameObject implements java.io.Serializable {
    
    final static String sym= "+";
    
    public Life(int r, int c) {
        super(r, c);
    }
    
    public Life(Life l) {
        super(l.r, l.c);
    }
    
    public void move() {}
    
    public String getSym() {
        return sym;
    }
    
}