package AwareGallium.Entities;

import random.UniformRandomStream;

/**
 * @author Rasmus Bentsson
 */
public class CustomerFactory extends EntityFactory{

    private final UniformRandomStream valueFunction;
    private final UniformRandomStream timeFunction;

    /**
     *
     * @param seq A class providing sequential id strings
     * @param valueFunction The RandomStream dictating the value of every customer
     * @param timeFunction The RandomStream dictating the time to shop for every customer
     */
    public CustomerFactory(SequentialIdentityProvider seq,
                           UniformRandomStream valueFunction,
                           UniformRandomStream timeFunction) {
        super(seq);

        this.valueFunction = valueFunction;
        this.timeFunction = timeFunction;
    }

    /**
     * Generates a Customer
     * @param time The time of entry for our new customer
     * @return The new Customer
     */
    @Override
    public Customer build(float time) {
        String identity = this.getNewId();
        Lifetime lifetime = new Lifetime(time);
        int value = (int)valueFunction.next();
        float timeToShop = (float)timeFunction.next();

        Customer customer = new Customer(value, timeToShop, identity, lifetime);
        return customer;
    }
}
