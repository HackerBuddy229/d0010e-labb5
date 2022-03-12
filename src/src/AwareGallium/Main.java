package AwareGallium;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.CustomerFactory;
import AwareGallium.Entities.IEntity;
import AwareGallium.Entities.Lifetime;
import AwareGallium.Infrastructure.Checkout;
import AwareGallium.Infrastructure.Store;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class Main {

    public static void main(String[] args) {
        //get seed
        long seed = Integer.parseInt(args[0]);

        //Create state
            //create store

        Lifetime openingHours = new Lifetime(0.0F, 24.0F);
        Store store = new Store(
                20,
                openingHours,
                null,
                new UniformRandomStream(50, 2000, seed),
                new UniformRandomStream(0.2F, 1F, seed),
                new ExponentialRandomStream(10, seed)
        );
        store.checkout = new Checkout(10, openingHours);
        State state = new State(store);

        //Create view
        View view = new View();

        //create simulator
        Simulator sim = new Simulator(state, view);

        //run simulation
        sim.run();
    }

}
