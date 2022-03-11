package AwareGallium.Infrastructure;

import AwareGallium.Entities.Customer;
import AwareGallium.Entities.CustomerFactory;
import AwareGallium.Entities.Lifetime;
import AwareGallium.Entities.SequentialIdentityProvider;
import AwareGallium.Wrappers.List;
import Lab3.FIFO;
import random.UniformRandomStream;

public class Store {
    //TODO: customers per hour

    public List<Customer> customers = new List<Customer>();
    public List<Checkout> checkouts = new List<Checkout>();

    public int capacity;
    public FIFO paymentsQueue = new FIFO();
    public Lifetime openingHours;
    public int customersPerUnit;

    public CustomerFactory customerFactory;

    public Store(int capacity,
                 Lifetime openingHours,
                 int customersPerUnit,
                 SequentialIdentityProvider seq,
                 UniformRandomStream customerValueFunction,
                 UniformRandomStream customerTimeFunction) {

        this.capacity = capacity;
        this.openingHours = openingHours;
        this.customersPerUnit = customersPerUnit;

        seq = seq != null ? seq : new SequentialIdentityProvider();
        this.customerFactory = new CustomerFactory(seq, customerValueFunction, customerTimeFunction);
    }

    public Store(int capacity,
                 Lifetime openingHours,
                 int customersPerUnit,
                 CustomerFactory customerFactory) {

        this.capacity = capacity;
        this.openingHours = openingHours;
        this.customersPerUnit = customersPerUnit;
        this.customerFactory = customerFactory;
    }

    public void createCheckouts(Checkout template, int amount) {
        for (int i = 0; i < amount; i++)
            checkouts.add(template.clone());
    }
}
