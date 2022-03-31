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

        //update state
        state.updateView(this);

        if (state.store.freeCheckouts > 0) {
            customer.timeInQueue = new Lifetime(0.0F, 0.0F);
            float timeToPay = (float)state.store.checkoutTimeFunction.next();
            PaymentEvent event =
                    new PaymentEvent(state.time + timeToPay, customer);
            customer.timeToPay = timeToPay;
            state.eventQueue.addEvent(event);
            state.store.freeCheckouts--;
        } else {
            //add customer to queue
            state.store.paymentsQueue.add(customer);
            customer.timeInQueue = new Lifetime(state.time);
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
