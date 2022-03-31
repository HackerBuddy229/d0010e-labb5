package AwareGallium;

import AwareGallium.Entities.*;
import AwareGallium.Infrastructure.Store;
import AwareGallium.Infrastructure.StoreView;

/**
 * @author Rasmus Bengtsson
 */
public class Main {

    public static void main(String[] args) {
        //get seed
        long seed = Integer.parseInt(args[0]);

        RunOptimize(seed);
        //runDefault(seed);

    }

    private static void RunOptimize(long seed) {
        Optimize.seedQuery(seed);
    }

    private static void runDefault(long seed) {
        //Create state
        //create store

        Lifetime openingHours = new Lifetime(0.0F, 24.0F);
        Store store = new Store(2,
                7,
                new Lifetime(0, 8),
                3.0F,
                0.6F,
                0.9F,
                0.35F,
                0.6F,
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
