package AwareGallium.Entities;

/**
 * @author Rasmus Bengtsson
 */
public abstract class EntityFactory implements IEntityFactory{


    private final SequentialIdentityProvider seq;

    /**
     *
     * @param seq A class providing sequential id strings
     */
    public EntityFactory(SequentialIdentityProvider seq) {
        this.seq = seq;
    }

    /**
     * @return The id to use for a specific entity
     */
    protected String getNewId() {
        return seq.nextId();
    }
}
