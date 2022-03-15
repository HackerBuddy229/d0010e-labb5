package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.CustomerFactory;
import AwareGallium.State;

import javax.management.remote.rmi.RMIConnectionImpl_Stub;

public class CustomerBuildEvent implements IEvent{

    public static final String EVENT_NAME = "Ankomst";

    public final float time;

    public Customer costumer;

    public CustomerBuildEvent(float time) {
        this.time = time;
    }


    @Override
    public void run(State state) {
        //get customer builder
        CustomerFactory customerFactory = state.store.customerFactory;
        Customer c = customerFactory.build(state.time);

        if (aliveCustomers(state) >= state.store.customerCapacity)
            c.getLifetime().end = state.time;

        //add to store
        state.store.customers.add(c);
        this.costumer = c;

        //if customer died then don't add queuing event
        if (!c.getLifetime().isAlive(state.time))
            return;


        //add queuing event
        QueuingEvent event = new QueuingEvent(state.time + c.timeToShop, c);
        state.eventQueue.addEvent(event);

        //update state
        state.updateView(this);
    }

    private int aliveCustomers(State state) {
        int alive = 0;
        for (Customer c: state.store.customers)
            if (c.getLifetime().isAlive(state.time))
                alive++;
        return alive;
    }

    @Override
    public float getTime() {
        return time;
    }

    @Override
    public String getName() {
        return EVENT_NAME;
    }
}
