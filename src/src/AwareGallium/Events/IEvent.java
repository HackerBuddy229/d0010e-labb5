package AwareGallium.Events;

import AwareGallium.State;

public interface IEvent {
    public void run(State state);

    public float getTime();
    public String getName();

}
