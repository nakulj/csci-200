package restaurant;

import restaurant.gui.CustomerGui;
import restaurant.gui.RestaurantGui;
import agent.Agent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Restaurant customer agent.
 */
public class CustomerAgent extends Agent {
    private String name;
    private int hungerLevel = 5;        // determines length of meal
    Timer timer = new Timer();
    private RestaurantGui gui = null;
    private CustomerGui customerGui = null;

    // agent connections
    private MaitreDAgent maitreD;

    private boolean isHungry = false; //hack for gui
    public enum AgentState
	    {DoingNothing, WaitingInRestaurant, BeingSeated, Seated, Eating, DoneEating, Leaving};
    private AgentState state = AgentState.DoingNothing;//The start state

    public enum AgentEvent 
	    {none, gotHungry, followWaiter, seated, doneEating};
    AgentEvent event = AgentEvent.none;
    public CustomerAgent(String name) {
        super();
        this.name = name;
    }

    /**
     * Constructor for CustomerAgent class
     *
     * @param name name of the customer
     * @param gui  reference to the gui so the customer can send it messages
     */
    public CustomerAgent(String name, RestaurantGui gui) {
        super();
        this.gui = gui;
        this.name = name;
    }

    /**
     * hack to establish connection to Maitre'D agent.
     */
    public void setMaitreD(MaitreDAgent maitreD) {
        this.maitreD = maitreD;
    }

    public String getCustomerName() {
        return name;
    }
    // Messages

    public void setHungry() {
	isHungry = true;
	if (gui != null)
	    customerGui.setPresent(true);
	print("I'm hungry");
	event = AgentEvent.gotHungry;
	stateChanged();
    }

    public void msgSitAtTable() {
        print("Received msgSitAtTable");
        event = AgentEvent.followWaiter;
        stateChanged();
    }

    public void msgAnimationFinishedGoToSeat() {
	//from animation
	event = AgentEvent.seated;
        stateChanged();
    }
    public void msgAnimationFinishedLeaveRestaurant() {
	//from animation
	/*if (state == AgentState.BeingSeated) {
	    event = AgentEvent.seated;
	}
	else if (state == AgentState.Leaving){
	    state = AgentState.DoingNothing;
	    gui.setCustomerEnabled(this);
	}*/
	state = AgentState.DoingNothing;
	gui.setCustomerEnabled(this);
	stateChanged();
    }

    /**
     * Scheduler.  Determine what action is called for, and do it.
     */
    protected boolean pickAndExecuteAnAction() {
	//	CustomerAgent is a finite state machine
        
	if (state == AgentState.DoingNothing && event == AgentEvent.gotHungry ){
	    state = AgentState.WaitingInRestaurant;
	    goToRestaurant();
            return true;
        }
	if (state == AgentState.WaitingInRestaurant && event == AgentEvent.followWaiter ){
	    state = AgentState.BeingSeated;
            SitDown();
            return true;
        }
	if (state == AgentState.BeingSeated && event == AgentEvent.seated){
	    state = AgentState.Eating;
	    EatFood();
	    return true;
        }

	if (state == AgentState.Eating && event == AgentEvent.doneEating){
	    state = AgentState.Leaving;
            leaveTable();
            return true;
        }

        return false;
    }

    // Actions

    private void goToRestaurant() {
        Do("Going to restaurant");
        maitreD.msgHelloIWantFood(this);//send our instance, so he can respond to us
    }

    private void SitDown() {
        Do("Being seated. Going to table");
        if (customerGui != null) {
            customerGui.DoGoToSeat(CustomerGui.xTable, CustomerGui.yTable);
        }
    }

    private void EatFood() {
	Do("Eating Food");
        //This next complicated line creates and starts a timer thread.
        //We schedule a deadline of getHungerLevel()*1000 milliseconds.
        //When that time elapses, it will call back to the run routine
        //located in the anonymous class created right there inline:
        //TimerTask is an interface that we implement right there inline.
        //Since Java does not all us to pass functions, only objects.
        //So, we use Java syntactic mechanism to create an
        //anonymous inner class that has the public method run() in it.
        timer.schedule(new TimerTask() {
	    Object cookie = 1;
	    public void run() {
		print("Done eating, cookie=" + cookie);
		event = AgentEvent.doneEating;
		isHungry = false;
		stateChanged();
              }
        },
                5000);//getHungerLevel() * 1000);//how long to wait before running task
    }

    private void leaveTable() {
        Do("Leaving.");
        maitreD.msgLeavingTable(this);
        if (customerGui != null) {
	    customerGui.DoExitRestaurant();
        }
    }

    // Accessors, etc.

    public String getName() {
        return name;
    }



    public boolean isHungry() {
        return isHungry;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
        //could be a state change. Maybe you don't
        //need to eat until hunger lever is > 5?
    }

    public String toString() {
        return "customer " + getName();
    }

    public void setGui(CustomerGui g) {
        customerGui = g;
    }

    public CustomerGui getGui() {
        return customerGui;
    }
}

