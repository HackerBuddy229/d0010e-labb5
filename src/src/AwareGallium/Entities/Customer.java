package AwareGallium.Entities;


import javax.security.auth.login.CredentialException;

public class Customer implements IEntity{
    public final int value;
    public final float timeToShop;

    private final String identity;
    private final Lifetime lifetime;

    public Customer(int value, float timeToShop, String identity, Lifetime lifetime) {
        this.value = value;
        this.timeToShop = timeToShop;

        this.identity = identity;
        this.lifetime = lifetime;
    }

    @Override
    public String getIdentity() {
        return identity;
    }

    @Override
    public Lifetime getLifetime() {
        return lifetime;
    }

    @Override
    public boolean equals(Object obj) {
        //check not null
        if (obj != null)
            try {
                //check type
                Customer c = (Customer) obj;

                //confirm identity
                return c.identity.equals(this.identity);

            } catch (ClassCastException ex) {
                return false;
            }

        return false;
    }
}
