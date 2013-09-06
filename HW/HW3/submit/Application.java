import java.awt.CardLayout;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
class Application extends JFrame implements ActionListener, KeyListener {
    
    Game game;
    Timer t;
    int ctr= 0;
    JMenuBar mb= new JMenuBar();
        JMenu file  = new JMenu("Game");
            JMenuItem newgame   = new JMenuItem("New");
            JMenuItem loadgame  = new JMenuItem("Load");
            JMenuItem scores    = new JMenuItem("Scores");
            JMenuItem quit      = new JMenuItem("Exit");
        JMenu optn  = new JMenu("Options");
            JMenuItem pause     = new JMenuItem("Pause/Resume");
            JMenuItem stop      = new JMenuItem("Back");
            
    
    
    public Application() {
        game= new Game(new CardLayout());
        setJMenuBar(mb);
            mb.add(file);
                file.add(newgame);
                    newgame.addActionListener(new newgameAction());
                file.add(loadgame);
                    loadgame.addActionListener(new loadgameAction());
                file.add(scores);
                    scores.addActionListener(new scoresAction());
                file.add(quit);
                    quit.addActionListener(new quitAction());
            mb.add(optn);
                optn.add(pause);
                    pause.addActionListener(new pauseAction());
                optn.add(stop);
                    stop.addActionListener(new stopAction());
        
    }
    
    class newgameAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            game.input('n');
        }
    }
    
    class loadgameAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try {
                game.load();
            }
            catch(Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
    
    class scoresAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            game.input('h');
        }
    }
    

    class quitAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    class pauseAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            game.input('p');
        }
    }
    
    class stopAction implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            try{
                game.back();
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
    
    public static void main(String[] arg) {
        Application app= new Application();
        app.add(app.game);
        app.setSize(500,500);
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.addKeyListener(app);
        app.t= new Timer(20, app);
        app.t.start();
    }
    
    public void actionPerformed(ActionEvent ae) {
        game.input('0');
        game.repaint();
        repaint();
    }
    
    public void keyPressed(KeyEvent ke){}
    public void keyReleased(KeyEvent ke){}
    public void keyTyped(KeyEvent ke) {
        char ch= ke.getKeyChar();
        if(ch != '0')
            game.input(ch);
    }
    
}