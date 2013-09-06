public class Player extends GameObject implements java.io.Serializable {
    
    final static String sym= ">";
    private int lives= 0;
    
    public Player(int r, int c) {
        super(r, c);
    }
    
    public void move(){};
    
    public void up(){
        if(r == 0)
            return;
        r--;
    }
    
    public void down() {
        if(r == R)
            return;
        r++;
    }
    
    public void right() {
        c++;
    }
    
    public String getSym() {
        return sym;
    }
    
    public int getLives() {
        return lives;
    }
    
    public int loseLife() {
        return --lives;
    }
    
    public int gainLife() {
        return ++lives;
    }
    public boolean at(int rr, int cc) {
        return ((r==rr) && (c==cc));
    }
}
