/*
 * This is the class of non-moving objects.
 * The obstacles are randomly generated with a probability of 20%
 * The ostacles are stationary, so, relative to the player, they are seen to move towards the left.
 * The class holds the symbol reprsenting the obstacles, and the locations of obstacles on the map
 * It also provides a method to advance the positions of the obstacles in the map
 */
public class Obstacle implements java.io.Serializable{
    
    static final char ch= '#';
    boolean[][] loc= new boolean[5][5];
    
    public Obstacle() {
        for(int i= 0; i<5; i++)
            for(int j=1; j<5; j++)
                loc[i][j]= random();
    }
    
    public void next() {
        for(int i=0; i<5; i++) {
            for(int j=0; j<4; j++)
                loc[i][j]= loc[i][j+1];
            loc[i][4]= random();
        }
    }
    
    private static boolean random() {
        return Math.random()<0.2;
    }
    
}
