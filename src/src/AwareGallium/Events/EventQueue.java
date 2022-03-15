package AwareGallium.Events;

import AwareGallium.Wrappers.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class EventQueue {

    private List<IEvent> events = new List<IEvent>();

    public void addEvent(IEvent event) {
        List<IEvent> newList = new List<IEvent>();
        boolean fitted = false;
        for (IEvent e: events) {
            if (e.getTime() >= event.getTime()) {
                newList.add(event);
                fitted = true;
            }
            newList.add(e);
        }
        if (newList.size() == 0 || !fitted)
            newList.add(event);

        events = newList;

    }

    public void addEvent(IEvent[] events) {
        for (IEvent event: events)
            addEvent(event);
    }

    public IEvent popEvent() {
        IEvent event = events.get(0);
        events.remove(0);

        return event;
    }
}
