
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

class Player implements Serializable{
    
    transient private Image img;
    private int x, y;
    private int lives= 0;
    int score= 0;
    
    public Player(int x, int y) {
        ImageIcon i= new ImageIcon(this.getClass().getResource("images/player.png"));
        img= i.getImage();
        this.x= x;
        this.y= y;
    }
    
    public Rectangle getRect() {
        return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }
    
    public void restoreImg() {
        ImageIcon i= new ImageIcon(this.getClass().getResource("images/player.jpg"));
        img= i.getImage();
    }
    
    public void up() {
        y-=(y>10)?5:0;
    }
    
    
    public void down() {
        y+=(y<400)?5:0;
    }
    
    public void right() {
        x+=3;
    }
    
    public void displace() {
        y=45;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void kill() {
        lives--;
    }
    
    public void addLife() {
        lives++;
    }
    
    public int getLives() {
        return lives;
    }
    
    public int getx() {
        return x;
    }
    
    public int gety() {
        return y;
    }
    
}