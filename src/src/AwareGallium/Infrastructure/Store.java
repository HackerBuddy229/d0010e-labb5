package AwareGallium.Infrastructure;

import AwareGallium.Entities.*;
import AwareGallium.Wrappers.List;
import Lab3.FIFO;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

/**
 * @author Rasmus Bengtsson
 */
public class Store {

    public List<Customer> customers = new List<Customer>();

    public int checkoutCapacity;
    public int freeCheckouts;
    public UniformRandomStream checkoutTimeFunction;

    public int customerCapacity;
    public FIFO<Customer> paymentsQueue = new FIFO<Customer>();
    public Lifetime openingHours;

    public CustomerFactory customerFactory;
    public ExponentialRandomStream customerArrivalFunction;

    public final float lambda;
    public final float minPickTime, maxPickTime;
    public final float minPayTime, maxPayTime;
    public final float minValue, maxValue;
    public final long seed;


    public Store(int checkoutCapacity,
                 int customerCapacity,
                 Lifetime openingHours,
                 float lambda,
                 float minPickTime,
                 float maxPickTime,
                 float minPayTime,
                 float maxPayTime,
                 float minValue,
                 float maxValue,
                 long seed,
                 SequentialIdentityProvider seq) {
        this.checkoutCapacity = checkoutCapacity;
        this.freeCheckouts = checkoutCapacity;
        this.customerCapacity = customerCapacity;
        this.openingHours = openingHours;
        this.lambda = lambda;
        this.minPickTime = minPickTime;
        this.maxPickTime = maxPickTime;
        this.minPayTime = minPayTime;
        this.maxPayTime = maxPayTime;
        this.minValue = minValue;
        this.seed = seed;
        this.maxValue = maxValue;

        this.customerArrivalFunction = new ExponentialRandomStream(lambda, seed);

        this.customerFactory = new CustomerFactory(seq,
                new UniformRandomStream(minValue, maxValue, seed),
                new UniformRandomStream(minPickTime, maxPickTime, seed));

        this.checkoutTimeFunction = new UniformRandomStream(minPayTime,
                maxPayTime, seed);

    }
}
