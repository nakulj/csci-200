class GameObject implements java.io.Serializable {
    //Generic game object
    int r;
    int c;
    protected final static int R= 5;
    
    public GameObject(int rr, int cc) {
        r= rr;
        c= cc;
    }
    
    public final int getR() {
        return r;
    }
    
    public final int getC() {
        return c;
    }
    
    public void move(){}
    public String getSym(){return " ";}
    
}