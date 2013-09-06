
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class GamePanel extends JPanel implements Serializable{
    
    Game parent;
    boolean paused= false;
    private int next=0;
    int startx= 0;
    Rectangle visWindow= new Rectangle(startx,0,500,500);
    private Player p= new Player(30,30);
    private ArrayList<GameObject> objects= new ArrayList<GameObject>();
    String name;
    JPanel out= new JPanel();
    JLabel score= new JLabel("Score: 0");
    JLabel lives= new JLabel("Lives: 0");
    int ctr= 0;
    final int end[]={10000,20000,30000};
    int lvl;
    
    public GamePanel(Game p, String n, int lvl) {
        this.lvl=lvl-1;
        this.setBackground(Color.BLACK);
        out.setBackground(Color.WHITE);
        out.add(score);
        out.add(lives);
        parent= p;
        name= n;
        int block;
        for(int x=200; x<end[this.lvl]; x+=50) {
            block=x/50;
            if(Math.random()>.3 && block%3==0)
                objects.add(new Obstacle(x,(int)(Math.random()*420)));
            if(Math.random()>0.7 && block%7==0)
                objects.add(new Missile(x,(int)(Math.random()*420)));
            if(Math.random()>0.1 && block%16==0)
                objects.add(new Life(x,(int)(Math.random()*420)));

            
        }
        switch(lvl) {
            case 1:
                block=0;
                for(int x=200; x<end[this.lvl]; x+=50) {
                    block=x/50;
                    if(Math.random()>0.4 && block%12==0)
                        objects.add(new Lightning(x,(int)(Math.random()*420)));
                    if(Math.random()>0.4 && (block+6)%12==0)
                        objects.add(new Sentry(x,(int)(Math.random()*420)));
                }
                break;
            case 2:
                block=0;
                for(int x=200; x<end[this.lvl]; x+=50) {
                    block=x/50;
                    if(Math.random()>0.4 && block%12==0)
                        objects.add(new BlackHole(x,(int)(Math.random()*420)));
                    if(Math.random()>0.4 && (block+6)%12==0)
                        objects.add(new Technology(x,(int)(Math.random()*420)));
                }
                break;
            case 3:
                block=0;
                for(int x=200; x<end[this.lvl]; x+=50) {
                    block=x/50;
                    if(Math.random()>0.4 && block%12==0)
                        objects.add(new Astronaut(x,(int)(Math.random()*420)));
                    if(Math.random()>0.4 && (block+6)%12==0)
                        objects.add(new Scout(x,(int)(Math.random()*420)));
                }
                break;
        }
        add(out);
    }
    
    public void restore(Game parent) {
        this.parent= parent;
        p.restoreImg();
        for(GameObject o: objects) {
            o.restoreImg();
        }
    }
    
    public void move(int ch) {
        switch(ch) {
            case 'w': p.up(); break;
            case 's': p.down(); break;
            case 'd':
                p.right();
                visWindow.x= (startx+=3);
                p.score+=(ctr++%100==0)?1:0;
        }
    }
    
    public void next() {
        if(paused)
            return;
        if(p.getx()>=end[lvl]) {
            parent.scores.addScore(p.score, name);
            JOptionPane.showMessageDialog(
                    parent,
                    "You successfully navigated the insterstellar void!",
                    "You win!",
                    JOptionPane.INFORMATION_MESSAGE);
            parent.state= 0;
            parent.cl.show(parent, "menu");
        }
        if(next%25==0) {
            move('d');
        }
        if(next%30==0) {
            for(GameObject o: objects)
                if(o.visibleIn(visWindow))
                    o.move();
        }
        Iterator i= objects.iterator();
        while(i.hasNext()) {
            GameObject o= (GameObject)i.next();
            if(p.getRect().intersects(o.getRect())) {
                o.collide(p);
                i.remove();
            }
            if(p.getLives()<0) {
                parent.scores.addScore(p.score, name);
                JOptionPane.showMessageDialog(
                        parent,
                        "You crashed too many times and died",
                        "Game Over",
                        JOptionPane.WARNING_MESSAGE);
                parent.state= 0;
                parent.cl.show(parent, "menu");
                break;
            }
        }
        score.setText("Score: "+p.score);
        lives.setText("Lives:"+p.getLives());
    }
    

    
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d= (Graphics2D)g;
        //g2d.setColor(Color.BLACK);
        //g2d.fillRect(0, 0, 500, 500);
        g2d.drawImage(p.getImage(), p.getx()-startx, p.gety(), this);
        for(GameObject o: objects) {
            if(o.visibleIn(visWindow))
            g2d.drawImage(o.getImage(), o.getx()-startx, o.gety(),  this);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
}