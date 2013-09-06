package restaurant.gui;

import restaurant.CustomerAgent;
import restaurant.MaitreDAgent;

import java.awt.*;

public class CustomerGui implements Gui{

    private CustomerAgent agent = null;
    private boolean isPresent = false;

    private MaitreDAgent maitreD;

    private int xPos, yPos;
    private int xDestination, yDestination;
    private enum Command {noCommand, GoToSeat, LeaveRestaurant};
    private Command command=Command.noCommand;

    public static final int xTable = 200;
    public static final int yTable = 250;

    public CustomerGui(CustomerAgent c, MaitreDAgent m) {
        agent = c;
        xPos = -40;
        yPos = -40;
        xDestination = -40;
        yDestination = -40;
        maitreD = m;
    }

    public void updatePosition() {
        if (xPos < xDestination)
            xPos++;
        else if (xPos > xDestination)
            xPos--;

        if (yPos < yDestination)
            yPos++;
        else if (yPos > yDestination)
            yPos--;

        if (xPos == xDestination && yPos == yDestination) {
	    if (command==Command.GoToSeat) agent.msgAnimationFinishedGoToSeat();
	    else if (command==Command.LeaveRestaurant) agent.msgAnimationFinishedLeaveRestaurant();
	    command=Command.noCommand;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        g.fillRect(xPos, yPos, 20, 20);
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean p) {
        isPresent = p;
    }

    public void DoGoToSeat(int x, int y) {
        xDestination = x;
	yDestination = y;
	command = Command.GoToSeat;
    }

    public void DoExitRestaurant() {
        xDestination = -40;
	yDestination = -40;
	command = Command.LeaveRestaurant;
    }
}
