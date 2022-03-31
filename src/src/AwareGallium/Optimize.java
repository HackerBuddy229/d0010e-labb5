package AwareGallium;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.Lifetime;
import AwareGallium.Entities.SequentialIdentityProvider;
import AwareGallium.Infrastructure.Store;

import java.util.Random;

/**
 * @author Rasmus Bengtsson
 */
public class Optimize {

    static int MAX_ROUNDS = 10;

    /**
     * Runs a simulation state without printing the StoreView
     * @param state the state to simulate
     * @return the final state after a complete simulation
     */
    public static State NoPrint(State state) {
        Simulator sim = new Simulator(state, null);
        sim.run();
        return sim.state;
    }

    /**
     * Alternates the amount of checkouts to find the least amount required to not miss customers
     * @param seed the seed to base this on
     * @return the least amount of checkouts possible
     */
    public static int checkouts(long seed) {
        State state = getState(seed);
        int max  = state.store.customerCapacity;
        int min = 1;

        int currentCheckouts = max/2;
        int currentMissed;

        int previousCheckout;

        do {
            boolean result = anyMissed(seed, currentCheckouts);

            previousCheckout = currentCheckouts;
            if (result) {
                min = currentCheckouts;
                currentCheckouts += ((max-min)/2);
            }
            else {
                max = currentCheckouts;
                currentCheckouts -= ((max-min)/2);
            }

            if (Math.abs(currentCheckouts - previousCheckout) <= 1)
                break;

            System.out.println(currentCheckouts);
        } while (true);


        return Math.min(currentCheckouts, previousCheckout);
    }

    /**
     * @param seed The seed to use to generate the storeState
     * @param checkouts the amount of checkouts to test for
     * @return If any customers where missed out on
     */
    private static boolean anyMissed(long seed, int checkouts) {
        State state = getState(seed);
        state.store.checkoutCapacity = checkouts;
        State result = NoPrint(state);

        for (Customer c :result.store.customers) {
            if (c.getLifetime().duration() == 0.0F)
                return true;
        }

        return false;
    }

    /**
     * Alternates the simulation seed to run checkouts to compensate for random patterns
     * @param seed the seed with which to generate seeds
     */
    public static void seedQuery(long seed) {
        Random seedGenerator = new Random(seed);
        int roundsCurrent = 0;
        int maxCheckouts = 0;

        while (roundsCurrent <= MAX_ROUNDS) {
            long cSeed = seedGenerator.nextInt();
            int currentCheckouts = checkouts(cSeed);

            if (Math.max(maxCheckouts, currentCheckouts) == currentCheckouts) {
                roundsCurrent = 0;
                maxCheckouts = currentCheckouts;
            } else {
                roundsCurrent++;
            }
        }

        System.out.println("Optimal checkouts for seed: " + seed + " - " + maxCheckouts);

    }

    /**
     * Returns a default state with alternating seeds
     * @param seed the seed to use
     * @return the new state
     */
    private static State getState(long seed) {
        Lifetime openingHours = new Lifetime(0.0F, 24.0F);
        Store store = new Store(7,
                3,
                new Lifetime(0, 8),
                3.0F,
                0.6F,
                0.9F,
                0.35F,
                0.6F,
                100F,
                200F,
                13,
                new SequentialIdentityProvider());

        State state = new State(store);

        return state;
    }


}
