package AwareGallium.Events;

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
                start();
                break;
            case STOP:
                stop();
        }
    }

    private void start(){
        //open register
        //create customer build events
    }
    private void stop(){
        //update view a last time
        //change state
    }

    @Override
    public float getTime() {
        return 0;
    }
}
