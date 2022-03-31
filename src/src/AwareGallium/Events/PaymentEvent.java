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
        //update state
        state.updateView(this);

        //fix customer field
        customer.getLifetime().end = state.time;
        customer.timeInQueue.end = state.time - customer.timeToPay;


        // if fifo !not empty
            //pop top
            //create next event with function

        Customer nextCustomer = state.store.paymentsQueue.first();
        if (nextCustomer != null) {
            state.store.paymentsQueue.removeFirst();
            float timeToPay = (float)state.store.checkoutTimeFunction.next();
            float paymentTime = state.time + timeToPay;
            nextCustomer.timeToPay = timeToPay;
            PaymentEvent event = new PaymentEvent(paymentTime, nextCustomer);

            state.eventQueue.addEvent(event);
        } else {
            state.store.freeCheckouts++;
        }
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
