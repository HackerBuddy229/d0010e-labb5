package AwareGallium;

import AwareGallium.Events.IEvent;

public class Simulator {
    public State state;
    public View view;

    public void run() {
        IEvent event;
        while ((event = state.eventQueue.popEvent()) != null) {
            //set time
            state.time = event.getTime();

            //run event
            event.run(state);
        }
    }
}
