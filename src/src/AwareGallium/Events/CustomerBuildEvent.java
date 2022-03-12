package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.CustomerFactory;
import AwareGallium.State;

public class CustomerBuildEvent implements IEvent{

    public final float time;

    public CustomerBuildEvent(float time) {
        this.time = time;
    }


    @Override
    public void run(State state) {
        //get customer builder
        CustomerFactory customerFactory = state.store.customerFactory;
        Customer c = customerFactory.build(state.time);

        if (aliveCustomers(state) >= state.store.capacity)
            c.getLifetime().end = state.time;

        //add to store
        state.store.customers.add(c);


        //add queuing event
        QueuingEvent event = new QueuingEvent(state.time + c.timeToShop, c);
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
}
