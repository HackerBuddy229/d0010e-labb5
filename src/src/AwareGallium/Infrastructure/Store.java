package AwareGallium.Infrastructure;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.Lifetime;
import AwareGallium.Entities.SequentialIdentityProvider;
import AwareGallium.Wrappers.List;
import Lab3.FIFO;
import random.UniformRandomStream;

public class Store {
    //TODO: customers per hour

    public List<Customer> customers;
    public List<Checkout> checkouts;
    public int capacity;
    public FIFO paymentsQueue;
    public Lifetime openingHours;
    public int customersPerUnit;
    public SequentialIdentityProvider seq;
    public UniformRandomStream customerValueFunction;
    public UniformRandomStream customerTimeFunction;

}
