package AwareGallium.Infrastructure;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.CustomerFactory;
import AwareGallium.Entities.Lifetime;
import AwareGallium.Entities.SequentialIdentityProvider;
import AwareGallium.Wrappers.List;
import Lab3.FIFO;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

public class Store {
    //TODO: customers per hour

    public List<Customer> customers = new List<Customer>();
    public Checkout checkout; //TODO: refactor for single checkout

    public int capacity;
    public FIFO paymentsQueue = new FIFO();
    public Lifetime openingHours;

    public CustomerFactory customerFactory;
    public ExponentialRandomStream customerArrivalFunction;

    public Store(int capacity,
                 Lifetime openingHours,
                 SequentialIdentityProvider seq,
                 UniformRandomStream customerValueFunction,
                 UniformRandomStream customerTimeFunction,
                 ExponentialRandomStream customerArrivalFunction) {

        this.capacity = capacity;
        this.openingHours = openingHours;
        this.customerArrivalFunction = customerArrivalFunction;

        seq = seq != null ? seq : new SequentialIdentityProvider();
        this.customerFactory = new CustomerFactory(seq, customerValueFunction, customerTimeFunction);
    }

    public Store(int capacity,
                 Lifetime openingHours,
                 CustomerFactory customerFactory,
                 ExponentialRandomStream customerArrivalFunction) {

        this.capacity = capacity;
        this.openingHours = openingHours;
        this.customerFactory = customerFactory;
        this.customerArrivalFunction = customerArrivalFunction;
    }

}
