package AwareGallium.Entities;

/**
 * @author Rasmus Bengtsson
 */
public interface IEntityFactory {
    /**
     * Produces an entity
     * @param time The time of creation
     * @return The entity
     */
    public IEntity build(float time);
}
