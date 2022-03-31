package AwareGallium;

import AwareGallium.Events.IEvent;

/**
 * @author Rasmus Bengtsson
 */
public class Simulator {
    public State state;
    public View view;

    public Simulator(State state, View view) {
        this.state = state;
        this.view = view;
        if (view != null) {
            state.addObserver(view);
        }

    }

    /**
     * Simulates the events in the event queue using the state
     */
    public void run() {
        IEvent event = state.eventQueue.popEvent();
        if (event != null)
            do {
                //set time
                state.time = event.getTime();

                //run event
                event.run(state);
            } while ((event = state.eventQueue.popEvent()) != null && state.simulationState == SimulationState.START);
    }


}
