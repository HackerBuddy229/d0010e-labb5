package AwareGallium.Entities;

import random.UniformRandomStream;

public class CustomerFactory extends EntityFactory{


    private final UniformRandomStream valueFunction;
    private final UniformRandomStream timeFunction;

    public CustomerFactory(SequentialIdentityProvider seq,
                           UniformRandomStream valueFunction,
                           UniformRandomStream timeFunction) {
        super(seq);

        this.valueFunction = valueFunction;
        this.timeFunction = timeFunction;
    }

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
