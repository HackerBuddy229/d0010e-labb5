package AwareGallium;

import AwareGallium.Entities.*;
import AwareGallium.Infrastructure.Checkout;
import AwareGallium.Infrastructure.Store;
import AwareGallium.Infrastructure.StoreView;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class Main {

    public static void main(String[] args) {
        //get seed
        long seed = Integer.parseInt(args[0]);

        //Create state
            //create store

        Lifetime openingHours = new Lifetime(0.0F, 24.0F);
        Store store = new Store(2,
                100,
                new Lifetime(0, 25),
                10,
                0.2F,
                2F,
                0.01F,
                0.2F,
                100F,
                200F,
                seed,
                new SequentialIdentityProvider());
        State state = new State(store);

        //Create view
        View view = new StoreView(state);

        //create simulator
        Simulator sim = new Simulator(state, view);

        //run simulation
        sim.run();
    }

}
