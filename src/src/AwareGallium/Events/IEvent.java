package AwareGallium.Events;

import AwareGallium.State;

public interface IEvent {
    public float time = 0.0F; //TODO: wtf java?
    public void run(State state);

}
