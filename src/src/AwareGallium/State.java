package AwareGallium;

import AwareGallium.Events.EventQueue;
import AwareGallium.Events.IEvent;
import AwareGallium.Events.SimulationStateEvent;
import AwareGallium.Infrastructure.Store;

import java.util.Observable;

/**
 * @author Rasmus Bengtsson
 * The base simulation state
 */
public class State extends Observable {
    public float time = 0.0F;
    public Store store;
    public EventQueue eventQueue = new EventQueue();
    public SimulationState simulationState = SimulationState.STOP;

    public State(Store store) {
        this.store = store;
        eventQueue.addEvent(new SimulationStateEvent(SimulationState.START, 0.0F));
    }


    public void updateView(IEvent event) {
        this.setChanged();
        this.notifyObservers(event);
    }
}
