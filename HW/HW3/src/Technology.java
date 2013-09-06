class Technology extends GameObject {
    
    final static String filepath= "images/tech.png";
    
    int count=0;
    
    public Technology(int x, int y) {
        super(x,y,filepath);
        count= 0;
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        switch((count++/20)%8) {
            case 7:
            case 0:
            case 1:
                x++; break;
            case 3:
            case 4:
            case 5:
                x--; break;
        }
        switch((count/20)%8) {
            case 1:
            case 2:
            case 3:
                y++; break;
            case 5:
            case 6:
            case 7:
                y--; break;
        }
    }
    
    public void collide(Player p) {
        p.score+=30;
        p.addLife();
        p.addLife();
    }
    
}