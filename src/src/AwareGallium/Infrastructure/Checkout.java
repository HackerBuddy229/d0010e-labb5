package AwareGallium.Infrastructure;

import AwareGallium.Entities.Lifetime;
import AwareGallium.Entities.Personnel;

public class Checkout implements Cloneable{
    public int customersPerUnit;
    public Lifetime lifetime;

    public Checkout(int customersPerUnit, Lifetime lifetime) {
        this.customersPerUnit = customersPerUnit;
        this.lifetime = lifetime;
    }

    public float timeToPay() {
        return 1.0F/(float)customersPerUnit;
    }

    @Override
    public Checkout clone() {

        int customersPerUnit = this.customersPerUnit;
        Lifetime lifetime = this.lifetime.clone();

        Checkout out = new Checkout(customersPerUnit, lifetime);

        return out;
    }
}
