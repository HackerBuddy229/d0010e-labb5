package AwareGallium.Events;

import AwareGallium.Entities.Customer;
import AwareGallium.SimulationState;
import AwareGallium.State;

/**
 * @author Rasmus Bengtsson
 */
public class SimulationStateEvent implements IEvent{

    private final SimulationState simulationState;
    public final float time;

    public static final String STOP_NAME = "Stop";
    public static final String START_NAME = "Start";

    /**
     * @param simulationState An enumeration representing the start or end of the simulation
     * @param time The time for the event to take place
     */
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

        //update state
        state.updateView(this);


        //create customer build events
            //if time_l < closing time
            // time_l += random
            //create customer
            //add customer
        float time = 0.0F;
        time += state.store.customerArrivalFunction.next();

        if (state.store.openingHours.isAlive(state.time)) {
            CustomerBuildEvent event = new CustomerBuildEvent(time);
            state.eventQueue.addEvent(event);
        }

        state.simulationState = SimulationState.START;
    }

    private void stop(State state){
        //update view a last time
        state.updateView(this);

        //change state
        state.simulationState = SimulationState.STOP;


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
