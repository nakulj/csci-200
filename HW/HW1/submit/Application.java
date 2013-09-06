import java.io.*;
public class Application {
    
    public static void main (String[] arg) throws IOException, java.lang.ClassNotFoundException {
        
        BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
        int c1;
        Game game= new Game();
        
        loop: while(true){
            
            System.out.println("1. New game");
            System.out.println("2. Load game");
            System.out.println("3. Quit");

            c1= Integer.parseInt(in.readLine());
            switch (c1) {
                
                case 1: //Create a new game object
                    game= new Game();
                    break;
                    
                case 2: //Deserialize an old game object
                    System.out.println("Enter the name of the file to load");
                    ObjectInputStream get=
                            new ObjectInputStream(new FileInputStream(in.readLine()));
                    game= (Game) get.readObject();
                    get.close();
                    break;
                    
                case 3: //End loop
                    break loop;
                    
                default: //Reprompt
                    System.out.println("Invalid choice, please try again");
                    continue loop;
            }
            //Call the game object's play() method
            if(game.play(in)) {
                //Only enters this section if the player wants to save the game
                System.out.println("Would you like to save the game (Y/N)?");
                if(in.readLine().equals("Y")) {
                    System.out.println("Enter the name of the file to save to");
                    ObjectOutput out=
                            new ObjectOutputStream(new FileOutputStream(in.readLine()));
                    out.writeObject(game);
                    out.close();
                    //Serialize the object and save to file
                }
            }
            
        }
       
    }
    
}
