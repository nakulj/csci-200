/*
 * This class controls the player.
 * It holds the avatar and the current postion.
 * It also has methods to move the player.
 * 
 */
public class Player implements java.io.Serializable {
    
    static final char ch= '>';
    private int loc= 2;
    
    public int getLoc() {
        return loc;
    }
    
    public void up() {
        loc= (loc==0)?0:loc-1;
    }
    
    public void down() {
        loc= (loc==4)?4:loc+1;
    }
    
}
