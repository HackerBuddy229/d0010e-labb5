package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.SimulationState;
import AwareGallium.State;

public class SimulationStateEvent implements IEvent{

    private final SimulationState simulationState;
    public final float time;

    public SimulationStateEvent(SimulationState simulationState, float time) {
        this.simulationState = simulationState;

        this.time = time;
    }

    @Override
    public void run(State state) {
        switch (simulationState) {
            case START:
                start(state);
                break;
            case STOP:
                stop(state);
        }
    }

    private void start(State state){
        //create customer build events
            //while time_l < closing time
            // time_l += random
            //create customer
            //add customer
        float time = 0.0F;
        time += state.store.customerArrivalFunction.next();

        while (time < state.store.openingHours.end) {
            CustomerBuildEvent event = new CustomerBuildEvent(time);
            state.eventQueue.addEvent(event);
        }

        //update state
        state.notifyObservers();
    }

    private void stop(State state){
        //change state
        state.simulationState = SimulationState.STOP;

        //update view a last time
        state.notifyObservers();
    }

    @Override
    public float getTime() {
        return 0;
    }
}
