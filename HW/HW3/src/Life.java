class Life extends GameObject {
    
    final static String filepath= "images/life.png";
    
    
    public Life(int x, int y) {
        super(x,y,filepath);
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        
    }
    
    public void collide(Player p) {
        p.addLife();
    }
    
}