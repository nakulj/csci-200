class Obstacle extends GameObject {
    
    final static String filepath= "images/fireball.png";
    
    public Obstacle(int x, int y) {
        super(x,y,filepath);
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        
    }
    
    public void collide(Player p) {
        p.kill();
    }
    
}