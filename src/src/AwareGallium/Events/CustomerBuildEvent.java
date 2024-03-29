package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.CustomerFactory;
import AwareGallium.State;

/**
 * @author Rasmus Bengtsson
 */
public class CustomerBuildEvent implements IEvent{

    public static final String EVENT_NAME = "Ankomst";

    public final float time;

    public Customer costumer;

    /**
     * @param time The time when the event takes place
     */
    public CustomerBuildEvent(float time) {
        this.time = time;
    }


    @Override
    public void run(State state) {



        //get customer builder
        CustomerFactory customerFactory = state.store.customerFactory;
        Customer c = customerFactory.build(state.time);

        if (aliveCustomers(state) >= state.store.customerCapacity || !state.store.openingHours.isAlive(state.time))
            c.getLifetime().end = state.time;

        //add to store
        state.store.customers.add(c);
        this.costumer = c;

        //update state
        state.updateView(this);

        //if customer alive
        if (c.getLifetime().isAlive(state.time)) {
            //add queuing event
            QueuingEvent event = new QueuingEvent(state.time + c.timeToShop, c);
            state.eventQueue.addEvent(event);
        }


        //if store open, add customer build event
        if (state.store.openingHours.isAlive(state.time)) {
            float time = state.time;
            time += state.store.customerArrivalFunction.next();
            CustomerBuildEvent cbe = new CustomerBuildEvent(time);
            state.eventQueue.addEvent(cbe);
        }


    }

    /**
     * Checks for how many customers are alive
     * @param state The current state
     * @return The amount of alive customers
     */
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
