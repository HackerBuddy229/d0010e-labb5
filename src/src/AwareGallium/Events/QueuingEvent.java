package AwareGallium.Events;

import AwareGallium.Entities.Customer;
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
        //use queue length to create payment event
        float timeToQueue = state.store.checkout.timeToPay()*state.store.paymentsQueue.size();
        PaymentEvent event = new PaymentEvent(timeToQueue, customer);

        //add customer to queue
        state.store.paymentsQueue.add(customer);

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
