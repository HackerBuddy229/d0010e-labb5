package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.State;

public class PaymentEvent implements IEvent {

    public static final String EVENT_NAME = "Betalning";
    private final float time;
    public final Customer customer;

    public PaymentEvent(float time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    @Override
    public void run(State state) {
        //remove from queue
        Customer c = (Customer)state.store.paymentsQueue.first();
        if (!c.equals(customer))
            throw new RuntimeException("Payment queue corrupted; " + state.store.paymentsQueue.toString());
        state.store.paymentsQueue.removeFirst();


        //set death time
        customer.getLifetime().end = state.time;

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
