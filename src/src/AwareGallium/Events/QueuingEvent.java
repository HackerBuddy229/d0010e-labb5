package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.Lifetime;
import AwareGallium.State;

public class QueuingEvent implements IEvent{

    public static final String EVENT_NAME = "Kö";
    public final float time;
    public final Customer customer;

    public QueuingEvent(float time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    @Override
    public void run(State state) {

        if (state.store.checkoutCapacity - state.store.paymentsQueue.size() > 0) {
            PaymentEvent event = new PaymentEvent(state.time, customer);
            state.eventQueue.addEvent(event);
        }

        //add customer to queue
        state.store.paymentsQueue.add(customer);
        customer.timeInQueue = new Lifetime(state.time);

        //update state
        state.updateView(this);

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
