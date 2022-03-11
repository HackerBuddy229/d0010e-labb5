package AwareGallium.Events;

import AwareGallium.State;

public class CustomerBuildEvent implements IEvent{

    public final float time;

    public CustomerBuildEvent(float time) {
        this.time = time;
    }


    @Override
    public void run(State state) {
        //get customer builder

        //add to store
            //check capacity

        //add queuing event
    }

    @Override
    public float getTime() {
        return time;
    }
}
