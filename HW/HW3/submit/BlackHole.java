class BlackHole extends GameObject {
    
    final static String filepath= "images/blackhole.png";
    
    int count=0;
    
    public BlackHole(int x, int y) {
        super(x,y,filepath);
        count= 0;
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        if(count++<70)
            x++;
        else {
            count-=70;
            x-=70;
        }
    }
    
    public void collide(Player p) {
        p.displace();
    }
    
}