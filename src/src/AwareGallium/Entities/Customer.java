package AwareGallium.Entities;


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
}
