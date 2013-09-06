import java.io.*;
import javax.swing.*;
public class Application {
    
    static BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
    static Game game;
    static JFrame frame= new JFrame();
    
    public static void main (String[] arg) throws IOException, java.lang.ClassNotFoundException {
        
        
        System.out.println("Enter your name");
        String name= in.readLine();
        game= new Game();
        System.out.println("Game loaded. Press enter when ready");
        in.readLine();
        frame.add(game);
        game.addKeyListener(game);
        game.start();
        game.setFocusable(true);
        game.requestFocus();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Space");
        frame.setVisible(true);
        game.setFocusable(true);
        game.requestFocus();
        while(game.inPlay);
        System.out.println("Thank you for playing");
    }
    
}
