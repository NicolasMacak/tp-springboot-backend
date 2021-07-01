package backend.tpservices.Modules.General.Interfaces;

public interface EntityInterface<ObjectType> {
    public void update(ObjectType object);
    public void verifyFields();
}
