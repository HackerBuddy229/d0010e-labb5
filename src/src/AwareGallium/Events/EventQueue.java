package AwareGallium.Events;

import AwareGallium.Wrappers.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Rasmus Bengtsson
 * A sorted list of events
 */
public class EventQueue {

    private List<IEvent> events = new List<IEvent>();

    /**
     * Adds and sorts an event into the already sorted backing store
     * @param event the event to be added
     */
    public void addEvent(IEvent event) {
        List<IEvent> newList = new List<IEvent>();
        boolean fitted = false;
        for (IEvent e: events) {
            if (e.getTime() >= event.getTime() && !fitted) {
                newList.add(event);
                fitted = true;
            }
            newList.add(e);
        }

        if (newList.size() == 0 || !fitted)
            newList.add(event);

        events = newList;

    }

    /**
     * calls add event repeatedly to add every element in the array
     * @param events the array of events
     */
    public void addEvent(IEvent[] events) {
        for (IEvent event: events)
            addEvent(event);
    }

    /**
     * Removes and gets the event from the top of the pile
     * @return the top event
     */
    public IEvent popEvent() {
        IEvent event = events.get(0);
        events.remove(0);

        return event;
    }
}
