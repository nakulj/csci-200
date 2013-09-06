import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import javax.swing.*;
class Game extends JPanel {
    
    //Create subcomponents
    MenuPanel menu= new MenuPanel();
    GamePanel game= new GamePanel(this,"",1);
    ScoresPanel scores= new ScoresPanel();
    CardLayout cl= (CardLayout)getLayout();
    
    int state= 0;
    String n;
    int lvl;
    
    public Game(CardLayout cl) {
        super(cl);
        game.paused= true;
        add(menu, "menu");
        add(game, "game");
        add(scores, "scores");
        cl.show(this, "menu");
    }
    
    public void back() throws IOException{
        if(state == 1) {
            game.paused= true;
            //TODO add save game code here
            ObjectOutput out=
                    new ObjectOutputStream(new FileOutputStream(game.name));
            out.writeObject(game);
            out.close();
            state= 0;
            cl.show(this, "menu");
        }
        if(state == 2) {
            if(scores.callerstate == 0) {
                state= 0;
                cl.show(this, "menu");
            }
            if(scores.callerstate == 1) {
                state= 1;
                cl.show(this, "game");
            }
        }
    }//To go back
    
    public void load() throws IOException, java.lang.ClassNotFoundException{
        if(state==0) {
            n= (String)JOptionPane.showInputDialog(
                    this,
                    "Please enter your name:",
                    "Dialog",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    null,
                    "name");
            if(n == null)
                return;
            ObjectInputStream get=
                    new ObjectInputStream(new FileInputStream(n));
            game= (GamePanel) get.readObject();
            game.restore(this);
            add(game, "game");
            state= 1;
            cl.show(this, "game");
        }
    }//To load a game
    
    //These functions included outside of input because they throw exceptions
    
    public void input(char ch) {
        switch(ch) {
            
            case '0':
                if(state == 1)
                    game.next();
                break;
            
            case 'n':
                if(state!=0)
                    break;
                n= (String)JOptionPane.showInputDialog(
                        this,
                        "Please enter your name:",
                        "Name",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        null,
                        "name");
            Object[] possibilities = {"1", "2", "3"};
            lvl = Integer.parseInt(
                    (String)JOptionPane.showInputDialog(
                        this,
                        "Please choose a level",
                        "Level",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        possibilities,
                        "1"));
                game= new GamePanel(this,n,lvl);
                add(game, "game");
                state= 1;
                cl.show(this, "game");
                break;
                
            case 'h':
                if(state == 1) {
                    game.paused= true;
                    scores.callerstate= 1;
                    state= 2;
                    cl.show(this, "scores");
                }
                if(state == 0) {
                    scores.callerstate= 0;
                    state= 2;
                    cl.show(this, "scores");
                }
                break;
                
            case 'p':
                if(state == 1) {
                    game.paused= !game.paused;
                }
                break;
                
            default: game.move(ch);
        }

    }
    
}