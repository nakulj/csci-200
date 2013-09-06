package restaurant;

import agent.Agent;
import restaurant.gui.MaitreDGui;

import java.util.*;

/**
 * Restaurant Maitre"D Agent
 */
//We only have 2 types of agents in this prototype. A customer and an agent that
//does all the rest. Rather than calling the other agent a waiter, we called him
//the MaitreDAgent. A MaitreD is the manager of a restaurant who sees that all
//is proceeded as he wishes.
public class MaitreDAgent extends Agent {
    static final int NTABLES = 2;//a global for the number of tables.
    //Notice that we implement waitingCustomers using LinkedList, but type it
    //with List semantics.
    public List<CustomerAgent> waitingCustomers
            = new LinkedList<CustomerAgent>();
    public Collection<Table> tables;
    //note that tables is typed with Collection semantics.
    //Later we will see how it is implemented

    private String name;
    private boolean atTable = false;

    public MaitreDGui maitreDGui = null;

    public MaitreDAgent(String name) {
        super();

        this.name = name;
        // make some tables
        tables = new ArrayList<Table>(NTABLES);
        for (int ix = 1; ix <= NTABLES; ix++) {
            tables.add(new Table(ix));//how you add to a collections
        }
    }

    public String getMaitreDName() {
        return name;
    }

    public String getName() {
        return name;
    }

    public List getWaitingCustomers() {
        return waitingCustomers;
    }

    public Collection getTables() {
        return tables;
    }
    // Messages

    public void msgHelloIWantFood(CustomerAgent cust) {
        waitingCustomers.add(cust);
        stateChanged();
    }

    public void msgLeavingTable(CustomerAgent cust) {
        for (Table table : tables) {
            if (table.getOccupant() == cust) {
                print(cust + " leaving " + table);
                table.setUnoccupied();
                stateChanged();
            }
        }
    }

    public void msgAtTable() {//from animation
        atTable = true;
        stateChanged();
    }

    /**
     * Scheduler.  Determine what action is called for, and do it.
     */
    protected boolean pickAndExecuteAnAction() {
        /* Think of this next rule as:
            Does there exist a table and customer,
            so that table is unoccupied and customer is waiting.
            If so seat him at the table.
            in FOL:
            Et, Ec ~isOccupied(t) & waitingCustomer(c) => seatCustomer(c,t)
        */
        for (Table table : tables) {
            if (!table.isOccupied()) {
                //trying to UNIFY a table with the ~isOccupied(t) clause
                //next try to UNIFY a customer with waitingCustomer(c)
                if (!waitingCustomers.isEmpty()) {
                    //we have matched the premise;
                    //invoke the conclusion, with the
                    //substitutions found during unification.
                    seatCustomer(waitingCustomers.get(0), table);//the action
                    return true;//return true to the main loop of
                    //abstract agent in order to invoke the scheduler again.
                }
            }
        }

        if (atTable)
	    {leaveTable(); return true;}
	
        return false;
        //we have tried all our rules and found
        //nothing to do. So return false to main loop of abstract agent
        //and wait.
    }

    // Actions

    private void seatCustomer(CustomerAgent customer, Table table) {
        //Notice how we print "customer" directly. It's toString method will do it.
        //Same with "table"
        Do("Seating " + customer + " at " + table);
        table.setOccupant(customer);
        waitingCustomers.remove(customer);

        customer.msgSitAtTable();
        maitreDGui.DoBringToTable(customer); 


    }

    public void leaveTable() {
        maitreDGui.DoLeaveCustomer();
        atTable = false;

    }

    public void setGui(MaitreDGui gui) {
        maitreDGui = gui;
    }

    public MaitreDGui getGui() {
        return maitreDGui;
    }

    private class Table {
        CustomerAgent occupiedBy;
        int tableNumber;

        Table(int tableNumber) {
            this.tableNumber = tableNumber;
        }

        void setOccupant(CustomerAgent cust) {
            occupiedBy = cust;
        }

        void setUnoccupied() {
            occupiedBy = null;
        }

        CustomerAgent getOccupant() {
            return occupiedBy;
        }

        boolean isOccupied() {
            return occupiedBy != null;
        }

        public String toString() {
            return "table " + tableNumber;
        }
    }
}

