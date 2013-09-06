class Sentry extends GameObject {
    
    final static String filepath= "images/sentry.png";
    
    int count;
    
    public Sentry(int x, int y) {
        super(x,y,filepath);
        count= 0;
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        switch((count++/50)%4) {
            case 0: y-=4; break;
            case 1: x+=4; break;
            case 2: y+=4; break;
            case 3: x-=4; break;
        }
    }
    
    public void collide(Player p) {
        p.kill();
    }
    
}