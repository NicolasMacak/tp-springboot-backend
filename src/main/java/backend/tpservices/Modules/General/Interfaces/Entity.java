package backend.tpservices.Modules.General.Interfaces;

public interface Entity<ObjectType> {
    public void update(ObjectType object);
    public void verifyFields();

    public Long getId();
}
