package AwareGallium.Entities;

public class PersonnelFactory extends EntityFactory {
    public PersonnelFactory(SequentialIdentityProvider seq) {
        super(seq);
    }

    @Override
    public IEntity build(float time) {
        return new Personnel(getNewId(), new Lifetime(time));
    }
}
