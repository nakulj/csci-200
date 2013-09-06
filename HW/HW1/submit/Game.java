import java.io.*;
public class Game implements java.io.Serializable{
    
    private Player p= new Player();
    private Obstacle o= new Obstacle();
    private Missile m= new Missile();
    private char[][] map= new char[5][5];
    private int score= 0;
    
    public boolean play(BufferedReader in) throws IOException {
        
        refreshMap();
        char choice;
        
        System.out.println("Welcome to Space");
        System.out.println("Controls:");
        System.out.println("W: Go up");
        System.out.println("D: Stay");
        System.out.println("S: Go down");
        System.out.println("P: Pause");
        System.out.println("X: Stop playing (you will be prompted to save)");
        printMap();
        loop: while(true) {
            choice= in.readLine().charAt(0);
            switch(choice) {
                case 'W': p.up(); break;
                case 'D': break;
                case 'S': p.down(); break;
                case 'P':
                    do {
                        System.out.println("Game paused. Press 'R' to resume");
                    } while(!in.readLine().equals("R"));
                    System.out.println("Resuming");
                    printMap();
                    continue loop;
                case 'X': return true;
                default:
                    System.out.println("Invalid command, please try again");
                    continue loop;
            }
            o.next();
            m.next();//Advance the locations of the obstacles and missiles
            if(refreshMap()) {
                //Only enter this segment if the player has died
                printMap();
                System.out.println("Game over!");
                System.out.println("Your score: " + score);
                return false;
            }
            refreshMap();
            printMap();
            System.out.println("Score: "+(++score));    //The score increases for every turn that the player survives
        }
        
    }
    
    private boolean refreshMap() {
        //Synchronize the location of all objects on the map
        for(int i= 0; i<5; i++) {
            for(int j= 0; j<5; j++) {
                map[i][j]= o.loc[i][j]?o.ch:(m.loc[i][j]?m.ch:'.');
                //'.' is an empty space
                //Symbols for objects are stored in the 'ch' field
                if(o.loc[i][j] && m.loc[i][j]) {
                    o.loc[i][j]= m.loc[i][j]= false;
                    map[i][j]='.';
                }//Missiles destroy blocks
            }
        }
        map[p.getLoc()][0]= p.ch;
        if(o.loc[p.getLoc()][0] || m.loc[p.getLoc()][0]) {
            //If the player collides with an object, the game ends
            map[p.getLoc()][0]= '*';
            return true;
        }
        return false;
    }
    
    private void printMap() {
        for(int i= 0; i<5; i++) {
            for(int j= 0; j<5; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
    }//display the elements on the maps
    
}
