package AwareGallium.Entities;

public class Personnel implements IEntity{
    private final String identity;
    private final Lifetime lifetime;

    public Personnel(String identity, Lifetime lifetime) {
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
