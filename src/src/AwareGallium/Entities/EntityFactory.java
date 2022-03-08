package AwareGallium.Entities;

public abstract class EntityFactory implements IEntityFactory{


    private final SequentialIdentityProvider seq;

    public EntityFactory(SequentialIdentityProvider seq) {
        this.seq = seq;
    }

    protected String getNewId() {
        return seq.nextId();
    }
}
