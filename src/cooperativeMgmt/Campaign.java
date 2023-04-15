package cooperativeMgmt;

public class Campaign {
    private String Id;

    public Campaign(String Id) {
        this.Id = Id;
    }

    public String getId() {
        return Id;
    }

    @Override
    public String toString() {
        return "Id " + Id;
    }
}
