package AwareGallium.Infrastructure;

import AwareGallium.Entities.Customer;
import AwareGallium.Events.CustomerBuildEvent;
import AwareGallium.Events.EventQueue;
import AwareGallium.Events.*;
import AwareGallium.Events.SimulationStateEvent;
import AwareGallium.State;
import AwareGallium.View;

import java.util.Observable;
import java.util.Scanner;

public class StoreView extends View {

    private static final String PARAMETER_VIEW_PATH = "deps/parameters.template";
    private static final String HEADER_VIEW_PATH = "deps/header.template";
    private static final String VIEW_TEMPLATE_PATH = "deps/view.template";

    private String viewTemplate = "";

    private float unusedCheckouts = 0.0F;
    private float lastCheckoutActivity = 0.0F;



    public StoreView(State state) {
        printParameters(state);
    }

    @Override
    public void update(Observable observable, Object o) {
        State state = (State) observable;

        //check if the template has been loaded
        if (viewTemplate == "")
            loadViewTemplate();

        //gets the time
        float time = state.time;

        //gets the event
        IEvent event = (IEvent) o;

        //get event name
        String eventName = event.getName();

        //get customer id
        String id = "";
        switch (eventName) {
            case CustomerBuildEvent.EVENT_NAME:
                id = ((CustomerBuildEvent) event).costumer.getIdentity();
                break;
            case QueuingEvent.EVENT_NAME:
                id = ((QueuingEvent) event).customer.getIdentity();
                break;
            case PaymentEvent.EVENT_NAME:
                id = ((PaymentEvent) event).customer.getIdentity();
                break;
        }

        //opening indicator
        String openingIndicator = state.store.openingHours.isAlive(state.time) ? "Ö" : "S";

        //free checkouts
        int freeCheckouts = Math.max(state.store.checkouts.size() - state.store.paymentsQueue.size(), 0);

        //unused checkout time
        float unusedCheckoutTime = getCheckoutFreeTime(state);

        //alive customers
        int alive = aliveCustomers(state);

        //completed purchases
        int complete = completeCustomers(state);

        //missed customers
        int missed = neverCustomers(state);

        //all queued customers
        int allQueuedCustomers = state.store.paymentsQueue.allElementsQueued();

        String out = String.format(viewTemplate,
                time, //time
                o, //event name
                id, //costumer id
                openingIndicator, //opening indicator
                freeCheckouts, //unused checkouts
                unusedCheckoutTime, //time the checkouts have been wasted
                alive, //customers in the store
                complete, //customers finished shopping
                missed, //missed customers
                allQueuedCustomers,
                ) //TODO: resume here


        super.update(observable, o);
    }

    private int aliveCustomers(State state) {
        int alive = 0;
        for (Customer c: state.store.customers)
            alive += c.getLifetime().isAlive(state.time) ? 1 : 0;

        return alive;
    }

    private int completeCustomers(State state) {
        int complete = 0;
        for (Customer c: state.store.customers)
            complete += c.getLifetime().isAlive(state.time) && c.getLifetime().duration() > 0.0F ? 1 : 0;

        return complete;
    }

    private int neverCustomers(State state) {
        int nevers = 0;
        for (Customer c: state.store.customers)
            nevers += c.getLifetime().duration() == 0 ? 1 : 0;

        return nevers;
    }

    private float getCheckoutFreeTime(State state) { //TODO: won't work for stop event
        int paymentLoad = state.store.checkouts.size() - state.store.paymentsQueue.size();
        if (paymentLoad <= 0) { //TODO: fix this logic
            if (lastCheckoutActivity != -1.0F) {
                float increase = state.time - lastCheckoutActivity;
                unusedCheckouts += increase;
                lastCheckoutActivity = state.time;
            } else {
                lastCheckoutActivity = -1.0F;
            }
        }

        return unusedCheckouts;
    }

    private void loadViewTemplate() {
        String template = new Scanner(VIEW_TEMPLATE_PATH).toString();
        viewTemplate = template;
    }

    private void printParameters(State state) {
        String template = new Scanner(PARAMETER_VIEW_PATH).toString();

        String out = String.format(template, ); //TODO: finish this
        System.out.println(out);
    }

    private void printHeaders() {
        String template = new Scanner(HEADER_VIEW_PATH).toString();
        System.out.println(template);
    }


}
