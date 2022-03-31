package AwareGallium.Entities;


import javax.security.auth.login.CredentialException;

/**
 * @author Rasmus Bengtsson
 */
public class Customer implements IEntity{
    public final int value;
    public final float timeToShop;

    public Lifetime timeInQueue;

    private final String identity;
    private final Lifetime lifetime;

    public float timeToPay;

    /**
     *
     * @param value The value of the customers goods
     * @param timeToShop the time it took the customer to pick their goods
     * @param identity A string representing the customer
     * @param lifetime The time that the customer is in the store
     */
    public Customer(int value, float timeToShop, String identity, Lifetime lifetime) {
        this.value = value;
        this.timeToShop = timeToShop;

        this.identity = identity;
        this.lifetime = lifetime;
    }

    /**
     * @return the string representing the customers identity
     */
    @Override
    public String getIdentity() {
        return identity;
    }

    /**
     * @return Returns the time that the customer has been in the store
     */
    @Override
    public Lifetime getLifetime() {
        return lifetime;
    }

    /**
     * @param obj The object to compare
     * @return Weather or not the Customers share an id
     */
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

    /**
     * @return The identifier of the customer
     */
    @Override
    public String toString() {
        return this.identity;
    }
}
