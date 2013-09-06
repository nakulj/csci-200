
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import javax.swing.ImageIcon;

class GameObject implements Serializable{
    
    transient protected Image img;
    protected int x,y;
    
    public GameObject(int x, int y, String filepath) {
        ImageIcon i= new ImageIcon(this.getClass().getResource(filepath));
        img= i.getImage();
        this.x= x;
        this.y= y;
        
    }
    
    public void restoreImg(String filepath) {
        ImageIcon i= new ImageIcon(this.getClass().getResource(filepath));
        img= i.getImage();
    }
    
    public void restoreImg() {
    }
    
    public void move() {
        
    }
    
    public Rectangle getRect() {
        return new Rectangle(x, y, img.getWidth(null), img.getHeight(null));
    }
    
    public void collide(Player p) {
        
    }
    
    public Image getImage() {
        return img;
    }
    
    public boolean visibleIn(Rectangle r) {
        return getRect().intersects(r);
    }
    
    public int getx() {
        return x;
    }
    
    public int gety() {
        return y;
    }
    
}