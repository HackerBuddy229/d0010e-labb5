package AwareGallium.Entities;

/**
 * @author Rasmus Bengtsson
 * Provides sequentially increasing identifiers for entitys
 */
public class SequentialIdentityProvider {

    private int lastId = -1;

    /**
     * @return The next identifier in the pattern
     */
    public String nextId() {
        lastId++;
        return String.format("%d", lastId);
    }
}
