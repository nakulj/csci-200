package restaurant.gui;


import restaurant.CustomerAgent;
import restaurant.MaitreDAgent;

import java.awt.*;

public class MaitreDGui implements Gui {

    private MaitreDAgent agent = null;

    private int xPos = -20, yPos = -20;
    private int xDestination = -20, yDestination = -20;

    public static final int xTable = 200;
    public static final int yTable = 250;

    public MaitreDGui(MaitreDAgent agent) {
        this.agent = agent;
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
           agent.msgAtTable();
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(xPos, yPos, 20, 20);
    }

    public boolean isPresent() {
        return true;
    }

    public void DoBringToTable(CustomerAgent customer) {
        xDestination = xTable + 20;
        yDestination = yTable - 20;
        //customer.getGui().DoGoToSeat(xTable, yTable);
    }

    public void DoLeaveCustomer() {
        xDestination = -20;
        yDestination = -20;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
