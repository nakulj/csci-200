
import javax.swing.ImageIcon;

class Missile extends GameObject {
    
    final static String filepath= "images/missile.png";
    final static String filepathF= "images/missileF.png";
    
    int count= 0;
    
    public Missile(int x, int y) {
        super(x,y,filepath);
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        switch((count++/40)%8) {
            case 0:
            case 1:
            case 3:
            case 4:
                img= new ImageIcon(this.getClass().getResource(filepath)).getImage();
                y--;
                break;
            case 2:
            case 5:
            case 6:
            case 7:
                img= new ImageIcon(this.getClass().getResource(filepathF)).getImage();
                y++;
                break;
        }
    }
    
    public void collide(Player p) {
        p.kill();
    }
    
}