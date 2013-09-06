import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Game extends JPanel implements Serializable, ActionListener, KeyListener{
    
    int R, C;
    int windowStart;
    int visWindowW;
    Player p;
    Obstacle[][] obstacle;
    Life[][] life;
    Missile[][] missile;
    Sentry[][] sentry;
    Lightning[][] lightning;
    GameObject[][] objects;
    double
            obstacleP,
            lifeP,
            missileP,
            sentryP,
            lightningP;
    private int score;
    private int livesLeft;
    String[] map;
    boolean inPlay;
    boolean paused;
    private Timer t;
    
    public Game() {
        R=5; C= 500;
        windowStart= 0;
        visWindowW= 5;
        p= new Player(R/2,0);
        obstacle= new Obstacle[R][C];
        life= new Life[R][C];
        missile= new Missile[R][C];
        sentry= new Sentry[R][C];
        lightning= new Lightning[R][C];
        obstacleP=  0.20;
        lifeP=      0.01;
        missileP=   0.05;
        sentryP=    0.02;
        lightningP= 0.01;
        score= 0;
        livesLeft= 0;
        map= new String[R];
        inPlay= true;
        paused= false;
        for(int i= 0; i<R; i++) {
            for(int j=1; j<C; j++) {
                obstacle[i][j]= Math.random()<obstacleP ? new Obstacle(i,j): null;
                life[i][j]= Math.random()<lifeP ? new Life(i,j): null;
                missile[i][j]= (Math.random()<missileP && i==R-1)? new Missile(i,j): null;//Missiles can only start from the bottom
                sentry[i][j]= Math.random()<sentryP ? new Sentry(i,j): null;
                lightning[i][j]= (Math.random()<lightningP && i==0)? new Lightning(i,j): null;//Lightning can only start from the top
            }
        }//Initialise world with objects
        checkCollisions();//Deal with objects that coincide
        render();//Deisplay the objects on the panel
        setFocusable(true);
        requestFocus();
        setBackground(Color.WHITE);
        setFont(new Font("Consolas", Font.BOLD, 24));
        setDoubleBuffered(true);
        addKeyListener(this);
        t= new Timer(250, this);
    }
    
    public void checkCollisions() {
        int occupied= 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                occupied+= (obstacle[i][j] == null)?0:1;
                occupied+= (life[i][j] == null)?0:1;
                occupied+= (missile[i][j] == null)?0:1;
                occupied+= (sentry[i][j] == null)?0:1;
                occupied+= (lightning[i][j] == null)?0:1;
                //Find the number of objects in the same space
                if(occupied>1) {
                    obstacle[i][j]= null;
                    life[i][j]= null;
                    missile[i][j]= null;
                    sentry[i][j]= null;
                    lightning[i][j]= null;
                    if(p.r == i && p.c == j)
                        livesLeft--;
                    if(livesLeft==0) {
                        inPlay= false;
                    }//If there are multiple objects, all are destroyed
                    continue;
                }
                if(occupied == 0) {
                    continue;
                }//If there are no objects, then don't need to do anything
                //Only reach this point if there was only one object
                if(life[i][j] != null && p.at(i, j)) {
                    life[i][j]= null;
                    objects[i][j]= null;
                    livesLeft++;
                    continue;
                }//If the object it a life, and if it meets the player, it is consumed
                if(p.at(i, j)) {
                    obstacle[i][j]= null;
                    life[i][j]= null;
                    missile[i][j]= null;
                    sentry[i][j]= null;
                    lightning[i][j]= null;
                    livesLeft--;
                    if(livesLeft==0)
                        inPlay= false;
                    continue;
                }//If the object is anything other than a life, and if it meets the player, it is destroyed and the player loses a life
            }
        }
    }
    public void render() {
        
        for(int i=0; i<R; i++) {
            map[i]= "";
            for(int j=windowStart; j<visWindowW; j++) {
                //Find the object at i,j and add its symbol
                if(p.r == i && p.c == j) {
                    map[i]+= p.getSym();
                    continue;
                }
                if(obstacle[i][j] != null) {
                    map[i]+= obstacle[i][j].getSym();
                    continue;
                }
                if(life[i][j] != null) {
                    map[i]+= life[i][j].getSym();
                    continue;
                }
                if(missile[i][j] != null) {
                    map[i]+= missile[i][j].getSym();
                    continue;
                }
                if(sentry[i][j] != null) {
                    map[i]+= sentry[i][j].getSym();
                    continue;
                }
                if(lightning[i][j] != null) {
                    map[i]+= lightning[i][j].getSym();
                    continue;
                }
                map[i]+= " ";
            }
        }
    }
    public void start() {
        t.start();
    }
    
    public void actionPerformed(ActionEvent ae) {
        repaint();
        for(int i=0; i<R; i++) {
            for(int j=windowStart; j<visWindowW; j++) {
                //advance all the moving objects
                if(missile[i][j]!=null) {
                    Missile m= new Missile(missile[i][j]);
                    m.move();
                    missile[m.r][m.c]= m;
                    missile[i][j]= null;
                    checkCollisions();
                    continue;
                }
                if(sentry[i][j]!=null) {
                    Sentry s= new Sentry(sentry[i][j]);
                    s.move();
                    sentry[s.r][s.c]= s;
                    sentry[i][j]= null;
                    checkCollisions();
                    continue;
                }
                if(lightning[i][j]!=null) {
                    Lightning l= new Lightning(lightning[i][j]);
                    l.move();
                    lightning[l.r][l.c]= l;
                    lightning[i][j]= null;
                    checkCollisions();
                    continue;
                }
            }
        }
    }
    
    public void keyReleased(KeyEvent ke) {
    }
    public void keyPressed(KeyEvent ke) {
    }
    public void keyTyped(KeyEvent ke) {
        char ch= ke.getKeyChar();
        switch(ch) {
            case 'x': inPlay= false; break;
            case 'p': paused= !paused;
            case 'w': p.up(); checkCollisions(); break;
            case 's': p.down(); checkCollisions(); break;
            case 'd': p.right(); checkCollisions(); break;
        }
    }//process the input
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        java.awt.FontMetrics fm= g.getFontMetrics();
        int strW= fm.stringWidth(map[0]);
        int strH= fm.getHeight();
        int x= (getWidth()-strW)/2;
        int y0= strH+(getHeight()-R*strH)/2;
        for(int i=0; i<R; i++) {
            g.drawString(map[i], x, y0+i*strH);
        }
    }//draw the map to the screen
}
