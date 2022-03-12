package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.State;

public class PaymentEvent implements IEvent {

    private final float time;
    private final Customer customer;

    public PaymentEvent(float time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    @Override
    public void run(State state) {

    }

    @Override
    public float getTime() {
        return 0;
    }
}
