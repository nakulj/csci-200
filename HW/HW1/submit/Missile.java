/*
 * This is the class of moving objects.
 * The missiles are randomly generated with probability of 5%
 * The missiles move upward, so, relative to the player, they are seen to move towards the top left.
 * The missiles can destroy players and obstacles, but are themselves destroyed in the process.
 * The class holds the symbol reprsenting the missiles, and the locations of missiles on the map
 * It also provides a method to advance the positions of the missiles in the map
 */
public class Missile implements java.io.Serializable{
    
    static final char ch= '^';
    boolean[][] loc= new boolean[5][5];
    
    public Missile() {
        for(int i= 0; i<5; i++)
            for(int j=1; j<5; j++)
                loc[i][j]= false;
    }
    
    public void next() {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++)
                loc[i][j]= loc[i+1][j+1];
            loc[i][4]= random();
        }
        for(int j=0; j<5; j++) {
            loc[4][j]= random();
        }
    }
    
    private static boolean random() {
        return Math.random()<0.05;
    }
    
}
