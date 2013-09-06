import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class MovingCircle extends JFrame implements ActionListener{
    Ellipse2D.Double myEllipse;
    Rectangle2D.Double backgroundRectangle;
    int dir= 1;
    int colorCounter= 0;
    int speedCounter= 0;
    Timer t;
    static BufferedReader in= new BufferedReader(new InputStreamReader(System.in));
    public MovingCircle() {
	//Make the ellipse at the starting position
	myEllipse = new Ellipse2D.Double( 30, 30, 20, 20 );

	//Make the background rectangle to "erase" the screen
	backgroundRectangle = new Rectangle2D.Double( 0, 0, 400, 300 );
    }
    
    public static void main(String[] args ) throws IOException{
	MovingCircle b = new MovingCircle();
	b.setSize( 400, 300 );
	b.setVisible(true);
	b.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	b.t = new Timer( 500, b );
	b.t.start();
        while(true) {
            b.dir= Integer.parseInt(in.readLine());
        }
    }
    
    public void actionPerformed( ActionEvent ae ) {
	//This will be called by the Timer
	myEllipse.setFrame( myEllipse.getX()+(dir==1?1:(dir==3?-1:0)), myEllipse.getY()+(dir==0?-1:(dir==2?1:0)), myEllipse.getWidth(), myEllipse.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
	repaint();
    }

    public void paint(Graphics g) {
        colorCounter++; colorCounter%= 20;
	Graphics2D g2 = (Graphics2D)g;
        if((++speedCounter)%25== 0)
            t.setDelay(t.getDelay()/2);
	g2.setColor( (colorCounter<=10)?Color.YELLOW:Color.RED );
	g2.fill( backgroundRectangle );
	g2.setColor( (colorCounter<=10)?Color.RED:Color.YELLOW );
	g2.draw( myEllipse );
    }
}  
