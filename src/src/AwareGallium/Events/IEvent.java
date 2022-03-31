package AwareGallium.Events;

import AwareGallium.State;

/**
 * @author Rasmus Bengtsson
 */
public interface IEvent {
    public void run(State state);

    public float getTime();
    public String getName();

}
