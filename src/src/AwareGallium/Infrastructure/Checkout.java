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

    @Override
    protected Checkout clone() {

        int customersPerUnit = this.customersPerUnit;
        Lifetime lifetime = new Lifetime(this.lifetime.start, this.lifetime.end);

        Checkout out = new Checkout(customersPerUnit, lifetime);

        return out;
    }
}
