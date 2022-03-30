package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.SimulationState;
import AwareGallium.State;

public class SimulationStateEvent implements IEvent{

    private final SimulationState simulationState;
    public final float time;

    public static final String STOP_NAME = "Stop";
    public static final String START_NAME = "Start";

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
            //if time_l < closing time
            // time_l += random
            //create customer
            //add customer
        float time = 0.0F;
        time += state.store.customerArrivalFunction.next();

        if (state.store.openingHours.isAlive(time)) {
            CustomerBuildEvent event = new CustomerBuildEvent(time);
            state.eventQueue.addEvent(event);
        }

        state.simulationState = SimulationState.START;

        //update state
        state.updateView(this);
    }

    private void stop(State state){
        //change state
        state.simulationState = SimulationState.STOP;

        //update view a last time
        state.updateView(this);
    }

    @Override
    public float getTime() {
        return 0;
    }

    @Override
    public String getName() {
        switch (simulationState){
            case STOP:
                return STOP_NAME;
            case START:
                return START_NAME;
        }

        throw new RuntimeException("Malformed SimulationStateEvent");
    }
}
