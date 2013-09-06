public class Obstacle extends GameObject implements java.io.Serializable {
    
    final static String sym= "#";
    
    public Obstacle(int r, int c) {
        super(r, c);
    }
    
    public Obstacle(Obstacle o) {
        super(o.r,o.c);
    }
    
    public void move() {}
    
    public String getSym() {
        return sym;
    }
    
}