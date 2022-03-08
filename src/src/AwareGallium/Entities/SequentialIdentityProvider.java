package AwareGallium.Entities;

public class SequentialIdentityProvider {

    private int lastId = -1;

    public String nextId() {
        lastId++;
        return String.format("%d", lastId);
    }
}
