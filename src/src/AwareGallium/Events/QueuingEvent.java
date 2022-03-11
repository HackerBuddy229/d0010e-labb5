package AwareGallium.Events;

import AwareGallium.State;

public class QueuingEvent implements IEvent{

    public final float time;

    public QueuingEvent(float time) {
        this.time = time;
    }

    @Override
    public void run(State state) {
        //use queue length to create payment event
        
    }

    @Override
    public float getTime() {
        return time;
    }
}
