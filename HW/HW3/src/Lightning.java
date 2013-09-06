class Lightning extends GameObject {
    
    final static String filepath= "images/lightning.png";
    
    int count=0;
    
    public Lightning(int x, int y) {
        super(x,y,filepath);
        count= 0;
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        switch((count/75)%2) {
            case 0: x-=4; break;
            case 1: x+=4; break;
        }
        count++;
    }
    
    public void collide(Player p) {
        p.kill();
    }
    
}