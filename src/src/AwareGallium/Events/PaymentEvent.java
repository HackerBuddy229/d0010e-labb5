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
        //pop first
            //check valid
        Customer c = state.store.paymentsQueue.first();
        if (!c.equals(customer))
            throw new RuntimeException("Payment queue corrupted; " + state.store.paymentsQueue.toString());

            //process customer
        state.store.paymentsQueue.removeFirst();

        customer.getLifetime().end = state.time;
        customer.timeInQueue.end = state.time;

        //check next
            //create event with random
            //add event
        Customer nextCustomer = state.store.paymentsQueue.first();
        if (nextCustomer != null) {
            float paymentTime = this.time + (float)state.store.checkoutTimeFunction.next();
            PaymentEvent event = new PaymentEvent(paymentTime, nextCustomer);

            state.eventQueue.addEvent(event);
        }

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
