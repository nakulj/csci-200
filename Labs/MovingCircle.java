import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class MovingCircle extends JFrame implements ActionListener, KeyListener{
    Ellipse2D.Double myEllipse;
    Rectangle2D.Double backgroundRectangle;
    static Timer t;

    public MovingCircle() {
	//Make the ellipse at the starting position
	myEllipse = new Ellipse2D.Double( 30, 30, 20, 20 );

	//Make the background rectangle to "erase" the screen
	backgroundRectangle = new Rectangle2D.Double( 0, 0, 400, 300 );
    }
    
    public static void main(String[] args ) {
	MovingCircle b = new MovingCircle();
	b.setSize( 400, 300 );
	b.setVisible(true);
	b.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
	t = new Timer( 500, b );
	t.start();
    }
    
    public void actionPerformed( ActionEvent ae ) {
	//This will be called by the Timer
	myEllipse.setFrame( myEllipse.getX()+1, myEllipse.getY()+1, myEllipse.getWidth(), myEllipse.getHeight());  //Move 1 x-pixel and 1 y-pixel every 50 milliseconds
	repaint();
    }

    public void paint(Graphics g) {
	Graphics2D g2 = (Graphics2D)g;
	g2.setColor( Color.YELLOW );
	g2.fill( backgroundRectangle );
	g2.setColor( Color.RED );
	g2.draw( myEllipse );
    }
    
    public void keyPressed(KeyEvent ke) {
        
    }
    
    public void keyReleased(KeyEvent ke) {
        
    }
    
    public void keyTyped(KeyEvent ke) {
        
    }
}  
