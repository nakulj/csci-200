class Scout extends GameObject {
    
    final static String filepath= "images/scout.png";
    
    int count;
    
    public Scout(int x, int y) {
        super(x,y,filepath);
        count= 0;
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        switch((count++/25)%12) {
            case 1:
            case 8:
                y-=5;
                break;
            case 2:
            case 7:
                y+=5;
                break;
            case 4:
            case 11:
                x-=5;
                break;
            case 5:
            case 10:
                x+=5;
                break;
        }
    }
    
    public void collide(Player p) {
        p.kill();
    }
    
}