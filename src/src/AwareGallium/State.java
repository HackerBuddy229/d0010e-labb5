package AwareGallium;

import AwareGallium.Events.EventQueue;
import AwareGallium.Infrastructure.Store;

import java.util.Observable;

public class State extends Observable {
    public float time;
    public Store store;
    public EventQueue eventQueue;
    public SimulationState simulationState;
}
