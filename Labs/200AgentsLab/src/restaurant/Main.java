package restaurant;

import restaurant.MaitreDAgent;
import restaurant.CustomerAgent;

public class Main {

    public static void main(String argv[]) {
        /*
            The following four lines create our four agents.
            Each agent instance has a thread in which it runs.
            The thread is created and started by the constructor.
        */
        MaitreDAgent maitreD = new MaitreDAgent("MaitreD");
        maitreD.startThread();
        CustomerAgent c1 = new CustomerAgent("Fred");
        CustomerAgent c2 = new CustomerAgent("Ethel");
        CustomerAgent c3 = new CustomerAgent("Ginger");
        c1.startThread();
        c2.startThread();
        c3.startThread();
        /*
            The next three lines tell the customer instances who
            the maitrD is. If we were doing this using
            RemoteMethodInvocation (RMI), the agents would find
            each other through the Java rimregistry. The maitrD will
            learn of the customers through the first message the customer
            sends to the maitrD.
        */
        c1.setMaitreD(maitreD);
        c2.setMaitreD(maitreD);
        c3.setMaitreD(maitreD);
        /*
            At this point the agents are started, but are not doing anything,
            because nothing has happened to make them do things. So, we (artifically)
            make each customer hungry. c2 is made especially hungry (that will make
            him eat longer.)
        */
        c1.setHungry();
        c2.setHungerLevel(7);
        c2.setHungry();
        c3.setHungry();
        /*
            This main thread ends leaving the agents to "fend for themselves." You
            will see that after a customer is fed and not hungry, it will set a timer
            to set it hungry again later. That's why this runs for ever.
        */
    }

}

