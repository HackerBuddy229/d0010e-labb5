package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.State;

public class QueuingEvent implements IEvent{

    public final float time;
    private final Customer customer;

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
    }

    @Override
    public float getTime() {
        return time;
    }
}
