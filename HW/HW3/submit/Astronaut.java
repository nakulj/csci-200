class Astronaut extends GameObject {
    
    final static String filepath= "images/astronaut.png";
    
    int count=0;
    
    public Astronaut(int x, int y) {
        super(x,y,filepath);
        count= 0;
    }
    
    public void restoreImg() {
        super.restoreImg(filepath);
    }
    
    public void move() {
        switch((count++/28)%8) {
            case 0:
            case 3:
                x++; y++; break;
            case 1:
            case 2:
                x++; y--; break;
            case 4:
            case 5:
            case 6:
            case 7:
                x--; break;
        }
    }
    
    public void collide(Player p) {
        p.score+=20;
    }
    
}